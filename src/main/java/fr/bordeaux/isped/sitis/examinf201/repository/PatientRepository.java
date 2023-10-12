package fr.bordeaux.isped.sitis.examinf201.repository;

import fr.bordeaux.isped.sitis.examinf201.domain.PatientDomain;
import fr.bordeaux.isped.sitis.examinf201.interfaces.IPatientCountGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientDomain, Integer> {

    @Query(
            value = ""
                    + "SELECT "
                    + "  COUNT(*) as patientCount, "
                    + "  patient_gender as gender "
                    + "FROM exam_inf201.tab_patient "
                    + "GROUP BY patient_gender",
            nativeQuery = true)
    List<IPatientCountGender> countPatientByGender();

}

