package com.githiomi.copture.data.models;

import com.githiomi.copture.data.enums.BloodGroup;
import com.githiomi.copture.data.enums.Sex;

public class Driver {

    private String driverLicenseNumber;
    private String driverSurname;
    private String driverNames;
    private String driverDob;
    private Sex sex;
    private BloodGroup bloodGroup;
    private String driverLicenseExpirationDate;

    public Driver(String driverLicenseNumber, String driverSurname, String driverNames, String driverDob, Sex sex, BloodGroup bloodGroup, String driverLicenseExpirationDate) {
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverSurname = driverSurname;
        this.driverNames = driverNames;
        this.driverDob = driverDob;
        this.sex = sex;
        this.bloodGroup = bloodGroup;
        this.driverLicenseExpirationDate = driverLicenseExpirationDate;
    }

    // Getters and setters

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getDriverSurname() {
        return driverSurname;
    }

    public void setDriverSurname(String driverSurname) {
        this.driverSurname = driverSurname;
    }

    public String getDriverNames() {
        return driverNames;
    }

    public void setDriverNames(String driverNames) {
        this.driverNames = driverNames;
    }

    public String getDriverDob() {
        return driverDob;
    }

    public void setDriverDob(String driverDob) {
        this.driverDob = driverDob;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDriverLicenseExpirationDate() {
        return driverLicenseExpirationDate;
    }

    public void setDriverLicenseExpirationDate(String driverLicenseExpirationDate) {
        this.driverLicenseExpirationDate = driverLicenseExpirationDate;
    }
}
