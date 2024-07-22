package com.githiomi.copture.utils;

import com.amazonaws.regions.Regions;
import com.githiomi.copture.data.enums.FolderPaths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AWS {

    public static final String COGNITO_IDENTITY_ID = "us-east-1:4d2ce1f4-0427-409a-95e6-5466edac3b89";
    public static final Regions COGNITO_REGION = Regions.US_EAST_1;
    public static final String S3_BUCKET_NAME = "danielgithiomi-s2110911-copture-honoursproject";

    public static final String S3_BUCKET_URL = "https://" + S3_BUCKET_NAME + ".s3.us-east-1.amazonaws.com";
    public static final List<String> S3_BUCKET_FOLDER_PATHS = new ArrayList<>(
            Arrays.asList(
                    FolderPaths.LICENSE.getFolderName(),
                    FolderPaths.PLATE.getFolderName(),
                    FolderPaths.INSURANCE.getFolderName(),
                    FolderPaths.FITNESS.getFolderName(),
                    FolderPaths.DECLARATION.getFolderName(),
                    FolderPaths.FRONT_PROFILE.getFolderName(),
                    FolderPaths.REAR_PROFILE.getFolderName(),
                    FolderPaths.SIDE_PROFILE.getFolderName()
            )
    );

}
