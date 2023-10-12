package fr.bordeaux.isped.sitis.examinf201.service;

import fr.bordeaux.isped.sitis.examinf201.domain.PatientDomain;
import fr.bordeaux.isped.sitis.examinf201.dto.CreatePatientDTO;
import fr.bordeaux.isped.sitis.examinf201.dto.PatientDTO;
import fr.bordeaux.isped.sitis.examinf201.enums.AlleleEnum;
import fr.bordeaux.isped.sitis.examinf201.enums.GenderEnum;
import fr.bordeaux.isped.sitis.examinf201.interfaces.IPatientCountGender;
import fr.bordeaux.isped.sitis.examinf201.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PatientService {
    //Attribute
    @Autowired
    PatientRepository patientRepository;
    //Methods
     //Method1
    public PatientDomain savePatient(PatientDTO patientDTO) {
        PatientDomain patientDomain = new PatientDomain(patientDTO);
        return patientRepository.save(patientDomain);
    }
     //Method2
    public ResponseEntity<PatientDomain> findPatientById(Integer patientId) {
        Optional<PatientDomain> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isPresent()) {
            return ResponseEntity.ok(patientOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
     //Method3
    public Page<PatientDomain> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }
     //Method4
    public List<IPatientCountGender> countPatientByGender() {
        List<IPatientCountGender> patientCountByGender = patientRepository.countPatientByGender();
        return patientCountByGender;
    }
     //Method5
    public PatientDomain createPatient(CreatePatientDTO createPatientDTO) {
        PatientDomain parent1 = patientRepository.findById(createPatientDTO.getParentID1()).orElseThrow(() -> new EntityNotFoundException("Parent 1 not found"));
        PatientDomain parent2 = patientRepository.findById(createPatientDTO.getParentID2()).orElseThrow(() -> new EntityNotFoundException("Parent 2 not found"));

        if ((parent1.getPatientGender() == GenderEnum.MALE && parent2.getPatientGender() == GenderEnum.FEMALE) || (parent1.getPatientGender() == GenderEnum.FEMALE && parent2.getPatientGender() == GenderEnum.MALE)) {
            List<AlleleEnum> parent1Allele = Arrays.asList(parent1.getPatientAllele1(), parent1.getPatientAllele2());
            List<AlleleEnum> parent2Allele = Arrays.asList(parent2.getPatientAllele1(), parent2.getPatientAllele2());
            Random rand = new Random();
            AlleleEnum patientAllele1 = parent1Allele.get(rand.nextInt(parent1Allele.size()));
            AlleleEnum patientAllele2 = parent2Allele.get(rand.nextInt(parent2Allele.size()));

            String bloodGroup;
            if ((patientAllele1 == AlleleEnum.B && patientAllele2 == AlleleEnum.B) || (patientAllele1 == AlleleEnum.B && patientAllele2 == AlleleEnum.O) || (patientAllele1 == AlleleEnum.O && patientAllele2 == AlleleEnum.B)) {
                bloodGroup = "B";
            } else if ((patientAllele1 == AlleleEnum.A && patientAllele2 == AlleleEnum.A) || (patientAllele1 == AlleleEnum.A && patientAllele2 == AlleleEnum.O) || (patientAllele1 == AlleleEnum.O && patientAllele2 == AlleleEnum.A)) {
                bloodGroup = "A";
            } else if (patientAllele1 == AlleleEnum.O && patientAllele2 == AlleleEnum.O) {
                bloodGroup = "O";
            } else if (patientAllele1 == AlleleEnum.A && patientAllele2 == AlleleEnum.B) {
                bloodGroup = "AB";
            } else {
                bloodGroup = "AB";
            }

            String lastName;
            if (parent1.getPatientGender() == GenderEnum.MALE) {
                lastName = parent1.getPatientLastName();
            } else {
                lastName = parent2.getPatientLastName();
            }

            PatientDomain newPatient = new PatientDomain();
            newPatient.setPatientFirstName(createPatientDTO.getNewPatientName());
            newPatient.setPatientGender(createPatientDTO.getNewPatientGender());
            newPatient.setPatientBirthPlace(createPatientDTO.getNewPatientBirthPlace());
            newPatient.setPatientBirthDate(createPatientDTO.getNewPatientBirthDate());
            newPatient.setPatientLastName(lastName);
            newPatient.setPatientAllele1(patientAllele1);
            newPatient.setPatientAllele2(patientAllele2);
            newPatient.setBloodGroup(bloodGroup);

            patientRepository.save(newPatient);
            return newPatient;
        } else {
            throw new RuntimeException("Un des parents doit être masculin et l'autre féminin.");
        }
    }
}








