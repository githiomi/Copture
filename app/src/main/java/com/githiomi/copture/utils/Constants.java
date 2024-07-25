package com.githiomi.copture.utils;

import com.githiomi.copture.data.enums.Nationality;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

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

}
