package com.githiomi.copture.data.enums;

public enum SeverityLevel {

    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String severityLevel;

    SeverityLevel(String severityLevel){
        this.severityLevel = severityLevel;
    }

    public String getSeverityLevel(){
        return severityLevel;
    }
}
