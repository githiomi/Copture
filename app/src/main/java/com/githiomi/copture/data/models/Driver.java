package com.githiomi.copture.data.models;

import com.githiomi.copture.data.enums.BloodGroup;
import com.githiomi.copture.data.enums.Nationality;
import com.githiomi.copture.data.enums.Sex;

import lombok.Data;

@Data
public class Driver {

    private String driverLicenseNumber;
    private String driverSurname;
    private String driverNames;
    private String driverDob;
    private Nationality driverNationality;
    private Sex sex;
    private BloodGroup bloodGroup;
    private String driverLicenseExpirationDate;

    public Driver(String driverLicenseNumber, String driverSurname, String driverNames, String driverDob,
                  Nationality driverNationality, Sex sex, BloodGroup bloodGroup, String driverLicenseExpirationDate) {
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverSurname = driverSurname;
        this.driverNames = driverNames;
        this.driverDob = driverDob;
        this.driverNationality = driverNationality;
        this.sex = sex;
        this.bloodGroup = bloodGroup;
        this.driverLicenseExpirationDate = driverLicenseExpirationDate;
    }

}
