package com.githiomi.copture.data.enums;

public enum FolderPaths {

    LICENSE("Driver's License"),
    PLATE("Vehicle License Plate"),
    INSURANCE("Vehicle Insurance"),
    FITNESS("Vehicle Fitness"),
    DECLARATION("Vehicle Declaration"),
    FRONT_PROFILE("Vehicle Front Profile"),
    REAR_PROFILE("Vehicle Rear Profile"),
    SIDE_PROFILE("Vehicle Side Profile");

    private final String folderName;

    FolderPaths(String folderName){
        this.folderName = folderName;
    }

    public String getFolderName(){
        return this.folderName;
    }
}
