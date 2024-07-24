package com.githiomi.copture.data.enums;

public enum Nationality {

    KENYAN("Kenyan"),
    MAURITIAN("Mauritian"),
    TANZANIAN("Tanzanian"),
    NIGERIAN("Nigerian"),
    FRENCH("French"),
    AMERICAN("American");

    private final String nationality;

    Nationality(String nationality){
        this.nationality = nationality;
    }

    public String getNationality(){
        return this.nationality;
    }
}
