package com.githiomi.copture.models;

import lombok.Data;

@Data
public class Hotline {

    private String hotlineDescription;
    private Long hotlineNumber;

    public Hotline(String hotlineDescription, Long hotlineNumber){
        this.hotlineDescription = hotlineDescription;
        this.hotlineNumber = hotlineNumber;
    }

}
