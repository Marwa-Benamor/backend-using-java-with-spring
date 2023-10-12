package fr.bordeaux.isped.sitis.examinf201.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.bordeaux.isped.sitis.examinf201.enums.GenderEnum;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatePatientDTO {
    //Attributes
    private Integer parentID1;
    private Integer parentID2;
    private String newPatientName;
    private GenderEnum newPatientGender;
    private LocalDate newPatientBirthDate;
    private String newPatientBirthPlace;

    //Getters and Setters
    public Integer getParentID1() {
        return parentID1;
    }

    public void setParentID1(Integer parentID1) {
        this.parentID1 = parentID1;
    }

    public Integer getParentID2() {
        return parentID2;
    }

    public void setParentID2(Integer parentID2) {
        this.parentID2 = parentID2;
    }

    public String getNewPatientName() {
        return newPatientName;
    }

    public void setNewPatientName(String newPatientName) {
        this.newPatientName = newPatientName;
    }

    public GenderEnum getNewPatientGender() {
        return newPatientGender;
    }

    public void setNewPatientGender(GenderEnum newPatientGender) {
        this.newPatientGender = newPatientGender;
    }

    public LocalDate getNewPatientBirthDate() {
        return newPatientBirthDate;
    }

    public void setNewPatientBirthDate(LocalDate newPatientBirthDate) {
        this.newPatientBirthDate = newPatientBirthDate;
    }

    public String getNewPatientBirthPlace() {
        return newPatientBirthPlace;
    }

    public void setNewPatientBirthPlace(String newPatientBirthPlace) {
        this.newPatientBirthPlace = newPatientBirthPlace;
    }
}