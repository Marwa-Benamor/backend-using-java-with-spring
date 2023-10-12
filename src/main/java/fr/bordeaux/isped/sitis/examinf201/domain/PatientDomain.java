package fr.bordeaux.isped.sitis.examinf201.domain;

import fr.bordeaux.isped.sitis.examinf201.dto.PatientDTO;
import fr.bordeaux.isped.sitis.examinf201.enums.AlleleEnum;
import fr.bordeaux.isped.sitis.examinf201.enums.GenderEnum;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tab_patient", schema = "exam_inf201")
public class PatientDomain {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer patientId;
    @Column(name = "patient_first_name")
    private String patientFirstName;
    @Column(name = "patient_last_name")
    private String patientLastName;
    @Enumerated(EnumType.STRING)
    @Column(name = "patient_gender")
    private GenderEnum patientGender;
    @Column(name = "patient_birth_date")
    private LocalDate patientBirthDate;
    @Column(name = "patient_birth_place")
    private String patientBirthPlace;
    @Enumerated(EnumType.STRING)
    @Column(name = "patient_allele1")
    private AlleleEnum patientAllele1;
    @Enumerated(EnumType.STRING)
    @Column(name = "patient_allele2")
    private AlleleEnum patientAllele2;
    @Column(name = "patient_blood_group")
    private String bloodGroup;

    //Constructor
    public PatientDomain() {
    }
    public PatientDomain(PatientDTO patientDTO) {
        this.patientFirstName = patientDTO.getPatientFirstName();
        this.patientBirthDate = patientDTO.getPatientBirthDate();
        this.patientLastName = patientDTO.getPatientLastName();
        this.patientGender = patientDTO.getPatientGender();
        this.patientBirthPlace = patientDTO.getPatientBirthPlace();
        this.patientAllele1 = patientDTO.getPatientAllele1();
        this.patientAllele2 = patientDTO.getPatientAllele2();
        calculateBloodGroup();
    }

    //Getters and Setters
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public GenderEnum getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(GenderEnum patientGender) {
        this.patientGender = patientGender;
    }

    public LocalDate getPatientBirthDate() {
        return patientBirthDate;
    }

    public void setPatientBirthDate(LocalDate patientBirthDate) {
        this.patientBirthDate = patientBirthDate;
    }

    public String getPatientBirthPlace() {
        return patientBirthPlace;
    }

    public void setPatientBirthPlace(String patientBirthPlace) {
        this.patientBirthPlace = patientBirthPlace;
    }

    public AlleleEnum getPatientAllele1() {
        return patientAllele1;
    }

    public void setPatientAllele1(AlleleEnum patientAllele1) {
        this.patientAllele1 = patientAllele1;
    }

    public AlleleEnum getPatientAllele2() {
        return patientAllele2;
    }

    public void setPatientAllele2(AlleleEnum patientAllele2) {
        this.patientAllele2 = patientAllele2;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    //Other Methods
    private void calculateBloodGroup() {
        if ((patientAllele1 == AlleleEnum.B && patientAllele2 == AlleleEnum.B) || (patientAllele1 == AlleleEnum.B && patientAllele2 == AlleleEnum.O) || (patientAllele1 == AlleleEnum.O && patientAllele2 == AlleleEnum.B)) {
            bloodGroup = "B";
        } else if ((patientAllele1 == AlleleEnum.A && patientAllele2 == AlleleEnum.A) || (patientAllele1 == AlleleEnum.A && patientAllele2 == AlleleEnum.O) || (patientAllele1 == AlleleEnum.O && patientAllele2 == AlleleEnum.A)) {
            bloodGroup = "A";
        } else if (patientAllele1 == AlleleEnum.O && patientAllele2 == AlleleEnum.O) {
            bloodGroup = "O";
        } else if (patientAllele1 == AlleleEnum.A && patientAllele2 == AlleleEnum.B) {
            bloodGroup = "AB";
        } else{
            bloodGroup = "AB";
        }
    }
}
