package com.githiomi.copture.data.enums;

public enum PoliceStation {

    CID("Crminal Investigative Department"),
    NORTH("North Police Station"),
    SOUTH("South Police Station"),
    WEST("West Police Station"),
    EAST("East Police Station"),
    CENTRAL("Central Police Station");

    private final String policeStation;

    PoliceStation(String policeStation){
        this.policeStation = policeStation;
    }

    public String getPoliceStation(){
        return this.policeStation;
    }
}
