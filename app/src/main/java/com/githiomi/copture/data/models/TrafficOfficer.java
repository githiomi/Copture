package com.githiomi.copture.data.models;

import com.githiomi.copture.data.enums.OfficerRole;
import com.githiomi.copture.data.enums.PoliceStation;
import com.githiomi.copture.data.enums.Sex;

public class TrafficOfficer {

    private String officerBadgeNumber;
    private String officerSurname;
    private String officerNames;
    private OfficerRole officerRole;
    private PoliceStation officerStation;
    private TrafficOfficer officerSupervisor;
    private Sex sex;
    private String officerDob;
    private String password;

    public String getOfficerBadgeNumber() {
        return officerBadgeNumber;
    }

    public void setOfficerBadgeNumber(String officerBadgeNumber) {
        this.officerBadgeNumber = officerBadgeNumber;
    }

    public String getOfficerSurname() {
        return officerSurname;
    }

    public void setOfficerSurname(String officerSurname) {
        this.officerSurname = officerSurname;
    }

    public String getOfficerNames() {
        return officerNames;
    }

    public void setOfficerNames(String officerNames) {
        this.officerNames = officerNames;
    }

    public OfficerRole getOfficerRole() {
        return officerRole;
    }

    public void setOfficerRole(OfficerRole officerRole) {
        this.officerRole = officerRole;
    }

    public PoliceStation getOfficerStation() {
        return officerStation;
    }

    public void setOfficerStation(PoliceStation officerStation) {
        this.officerStation = officerStation;
    }

    public TrafficOfficer getOfficerSupervisor() {
        return officerSupervisor;
    }

    public void setOfficerSupervisor(TrafficOfficer officerSupervisor) {
        this.officerSupervisor = officerSupervisor;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getOfficerDob() {
        return officerDob;
    }

    public void setOfficerDob(String officerDob) {
        this.officerDob = officerDob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
