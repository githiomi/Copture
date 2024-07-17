package com.githiomi.copture.data.models;

public class Hotline {

    private final String hotlineDescription;
    private final Long hotlineNumber;

    public Hotline(String hotlineDescription, Long hotlineNumber){
        this.hotlineDescription = hotlineDescription;
        this.hotlineNumber = hotlineNumber;
    }

    public String getHotlineDescription(){
        return this.hotlineDescription;
    }

    public Long getHotlineNumber(){
        return this.hotlineNumber;
    }

}
