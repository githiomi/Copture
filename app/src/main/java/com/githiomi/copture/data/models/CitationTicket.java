package com.githiomi.copture.data.models;

import java.util.List;

public class CitationTicket {

    private String citationTicketId;
    private String driversLicenseNumber;
    private List<String> offenseIds;
    private String officerBadgeNumber;
    private Integer totalPenalty;

    public CitationTicket(String citationTicketId, String driversLicenseNumber, List<String> offenseIds, String officerBadgeNumber, Integer totalPenalty) {
        this.citationTicketId = citationTicketId;
        this.driversLicenseNumber = driversLicenseNumber;
        this.offenseIds = offenseIds;
        this.officerBadgeNumber = officerBadgeNumber;
        this.totalPenalty = totalPenalty;
    }

    // Getter sna setters
    public String getCitationTicketId() {
        return citationTicketId;
    }

    public void setCitationTicketId(String citationTicketId) {
        this.citationTicketId = citationTicketId;
    }

    public String getDriversLicenseNumber() {
        return driversLicenseNumber;
    }

    public void setDriversLicenseNumber(String driversLicenseNumber) {
        this.driversLicenseNumber = driversLicenseNumber;
    }

    public List<String> getOffenseIds() {
        return offenseIds;
    }

    public void setOffenseIds(List<String> offenseIds) {
        this.offenseIds = offenseIds;
    }

    public String getOfficerBadgeNumber() {
        return officerBadgeNumber;
    }

    public void setOfficerBadgeNumber(String officerBadgeNumber) {
        this.officerBadgeNumber = officerBadgeNumber;
    }

    public Integer getTotalPenalty() {
        return totalPenalty;
    }

    public void setTotalPenalty(Integer totalPenalty) {
        this.totalPenalty = totalPenalty;
    }
}
