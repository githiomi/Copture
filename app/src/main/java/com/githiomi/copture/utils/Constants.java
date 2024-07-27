package com.githiomi.copture.utils;

import com.githiomi.copture.data.enums.Nationality;
import com.githiomi.copture.data.enums.OfficerRole;
import com.githiomi.copture.data.enums.PoliceStation;
import com.githiomi.copture.data.enums.Sex;
import com.githiomi.copture.data.models.TrafficOfficer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

    // Shared preferences
    public static final String AUTH_SHARED_PREFERENCES = "authentication";
    public static final String LOGGED_IN_USER = "logged_in_user";

    // Create activity and fragments
    public static final String ARG_IMAGE_BITMAP = "image_bitmap";
    public static final String ARG_LICENSE_NUMBER = "License Number";

    // Constant Data
    public static final List<String> NATIONALITIES = new ArrayList<String>(
            Arrays.asList(
                    Nationality.NIGERIAN.getNationality(),
                    Nationality.KENYAN.getNationality(),
                    Nationality.MAURITIAN.getNationality(),
                    Nationality.AMERICAN.getNationality(),
                    Nationality.FRENCH.getNationality(),
                    Nationality.TANZANIAN.getNationality()
            )
    );

    // List of accounts
    public static final List<TrafficOfficer> ACCOUNTS = Arrays.asList(
            new TrafficOfficer(
                    "MTF2001",
                    "Nitish",
                    "Ghoora Singh",
                    OfficerRole.COMMISSIONER,
                    PoliceStation.CENTRAL,
                    "",
                    Sex.MALE,
                    "19.08.1984",
                    "nitishsingh123"
            ),
            new TrafficOfficer(
                    "MTF2001",
                    "Deenagarie",
                    "Pavaday",
                    OfficerRole.INSPECTOR,
                    PoliceStation.CENTRAL,
                    "MTF2001",
                    Sex.FEMALE,
                    "07.03.2000",
                    "pavaday@321"
            )
    );

}
