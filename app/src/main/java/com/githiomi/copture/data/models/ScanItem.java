package com.githiomi.copture.data.models;

import lombok.Getter;

@Getter
public class ScanItem {

    private final int scanItemIcon;
    private final String scanItemName;

    public ScanItem(int scanItemIcon, String scanItemName){
        this.scanItemIcon = scanItemIcon;
        this.scanItemName = "Scan " + scanItemName;
    }

}
