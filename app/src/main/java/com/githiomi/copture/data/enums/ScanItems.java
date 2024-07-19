package com.githiomi.copture.data.enums;

import com.githiomi.copture.R;
import com.githiomi.copture.data.models.ScanItem;

public enum ScanItems {

    DRIVERS_LICENSE(new ScanItem(R.drawable.ic_scan, "Driver's License")),
    VEHICLE_NUMBER_PLATE(new ScanItem(R.drawable.ic_scan, "Vehicle's Number Plate")),
    VEHICLE_INSURANCE(new ScanItem(R.drawable.ic_scan, "Vehicle's Insurance")),
    VEHICLE_FITNESS(new ScanItem(R.drawable.ic_scan, "Vehicle's Fitness")),
    VEHICLE_SIDE(new ScanItem(R.drawable.ic_scan, "Vehicle's Side Profile")),
    VEHICLE_FRONT(new ScanItem(R.drawable.ic_scan, "Vehicle's Front Profile")),
    VEHICLE_BACK(new ScanItem(R.drawable.ic_scan, "Vehicle's Back Profile"));

    private final ScanItem scanItem;

    ScanItems(ScanItem scanItem){
        this.scanItem = scanItem;
    }

    public ScanItem getScanItem() {
        return scanItem;
    }
}
