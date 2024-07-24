package com.githiomi.copture.data.enums;

import com.githiomi.copture.data.models.Hotline;

import lombok.Getter;

@Getter
public enum HotlineContacts {

    // Police Stations
    DIRECT(new Hotline("Police Direct Line", 999L)),
    CID(new Hotline("Central CID", 2302081212L)),
    POLICE_INFO(new Hotline("Police Information Services", 2080034L)),
    NORTH(new Hotline("North Police Headquarters", 2649709L)),
    SOUTH(new Hotline("South Police Headquarters", 6277216L)),
    CENTRAL(new Hotline("Central Police Headquarters", 6765116L)),
    EAST(new Hotline("East Police Headquarters", 4130944L)),
    WEST(new Hotline("West Police Headquarters", 4661764L)),

    // Lawyers
    SUPREME(new Hotline("Mauritius Supreme Court", 3442L)),
    MAGISTRATE(new Hotline("Mauritius Magistrate Court", 159L)),

    // Medical Centers
    SAMU(new Hotline("SAMU Ambulances", 114L)),
    C_CARE(new Hotline("C-Care Hospital", 43674586L)),
    SSR(new Hotline("Sir Seewoosagur Ramgoolam Hospital", 999L)),

    // Fire Fighters
    FIRE_SERVICES_1(new Hotline("Fire fighter Services 1", 995L)),
    FIRE_SERVICES_2(new Hotline("Fire fighter Services 2", 115L)),
    RAPID_RESPONSE(new Hotline("Fire Rapid Response", 4543L));

    private final Hotline hotline;

    HotlineContacts(Hotline hotline) {
        this.hotline = hotline;
    }

    public Hotline getHotline() {
        return this.hotline;
    }

}
