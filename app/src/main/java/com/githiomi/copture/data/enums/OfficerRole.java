package com.githiomi.copture.data.enums;

public enum OfficerRole {

    CONSTABLE("Constable"),
    SERGEANT("Sergeant"),
    INSPECTOR("Inspector"),
    SUPERINTENDENT("Superintendent"),
    COMMISSIONER("Commissioner"),
    CHIEF("Chief of Police");

    private final String officerRole;

    OfficerRole(String officerRole){
        this.officerRole = officerRole;
    }

    public String getOfficerRole(){
        return this.officerRole;
    }
}
