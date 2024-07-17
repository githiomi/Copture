package com.githiomi.copture.utils;

public enum HotlineContacts {

    // Police Stations
    DIRECT("Police Direct Line", 999L),
    CID("Central CID", 2302081212L),
    POLICE_INFO("Police Information Services", 2080034L),
    NORTH("North Police Headquarters",	2649709L),
    SOUTH("South Police Headquarters",	6277216L),
    CENTRAL("Central Police Headquarters",	6765116L),
    EAST("East Police Headquarters",	4130944L),
    WEST("East Police Headquarters",	4661764L),

    // Lawyers
    SUPREME("Mauritius Supreme Court", 3442L),
    MAGISTRATE("Mauritius Magistrate Court", 159L),

    // Medical Centers
    SAMU("SAMU Ambulances", 114L),
    C_CARE("C-Care Hospital", 43674586L),
    SSR("Sir Seewoosagur Ramgoolam Hospital", 999L),

    // Fire Fighters
    FIRE_SERVICES_1("Fire fighter Services 1", 995L),
    FIRE_SERVICES_2("Fire fighter Services 2", 115L),
    RAPID_RESPONSE("Fire Rapid Response", 4543L)
    ;

    private final String hotlineDescription;
    private final Long hotlineNumber;

    HotlineContacts(String hotlineDescription, Long hotlineNumber) {
        this.hotlineDescription = hotlineDescription;
        this.hotlineNumber = hotlineNumber;
    }
}
