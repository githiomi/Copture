package com.githiomi.copture.data.enums;

public enum Sex {

    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private final String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
