package fr.bordeaux.isped.sitis.examinf201.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.bordeaux.isped.sitis.examinf201.enums.AlleleEnum;
import fr.bordeaux.isped.sitis.examinf201.enums.GenderEnum;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDTO {
    //Attributes
    private String patientFirstName;
    private String patientLastName;
    private GenderEnum patientGender;
    private LocalDate patientBirthDate;
    private String patientBirthPlace;
    private AlleleEnum patientAllele1;
    private AlleleEnum patientAllele2;

    //Constructor
    public PatientDTO() {
    }

    //Getters and setters
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
}
