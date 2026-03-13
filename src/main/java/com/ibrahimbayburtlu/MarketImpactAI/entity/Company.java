package com.ibrahimbayburtlu.MarketImpactAI.entity;

import lombok.Data;

@Data
public class Company {

    private String name;
    private String ticker;
    private String sector;
    private String industry;
    private String country;
    private String description;

}