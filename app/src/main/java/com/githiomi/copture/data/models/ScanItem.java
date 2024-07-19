package com.githiomi.copture.data.models;

import lombok.Getter;

public class ScanItem {

    private final int scanItemIcon;
    private final String scanItemName;

    public ScanItem(int scanItemIcon, String scanItemName){
        this.scanItemIcon = scanItemIcon;
        this.scanItemName = "Scan " + scanItemName;
    }

    public int getScanItemIcon() {
        return this.scanItemIcon;
    }

    public String getScanItemName() {
        return this.scanItemName;
    }

}
