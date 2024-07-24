package com.githiomi.copture.data.models;


import com.githiomi.copture.data.enums.SeverityLevel;

public class Offence {

    private String offenceId;
    private String offenceName;
    private String offenceDescription;
    private String offencePenalty;
    private SeverityLevel offenceSeverity;

    public Offence(String offenceId, String offenceName, String offenceDescription, String offencePenalty, SeverityLevel offenceSeverity){
        this.offenceId = offenceId;
        this.offenceName = offenceName;
        this.offenceDescription = offenceDescription;
        this.offencePenalty = offencePenalty;
        this.offenceSeverity = offenceSeverity;
    }

    // Getters and Setters

    public String getOffenceId() {
        return offenceId;
    }

    public void setOffenceId(String offenceId) {
        this.offenceId = offenceId;
    }

    public String getOffenceName() {
        return offenceName;
    }

    public void setOffenceName(String offenceName) {
        this.offenceName = offenceName;
    }

    public String getOffenceDescription() {
        return offenceDescription;
    }

    public void setOffenceDescription(String offenceDescription) {
        this.offenceDescription = offenceDescription;
    }

    public String getOffencePenalty() {
        return offencePenalty;
    }

    public void setOffencePenalty(String offencePenalty) {
        this.offencePenalty = offencePenalty;
    }

    public SeverityLevel getOffenceSeverity() {
        return offenceSeverity;
    }

    public void setOffenceSeverity(SeverityLevel offenceSeverity) {
        this.offenceSeverity = offenceSeverity;
    }
}
