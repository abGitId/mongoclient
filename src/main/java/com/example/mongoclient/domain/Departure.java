package com.example.mongoclient.domain;

import lombok.Data;

@Data
public class Departure {

    private String code;
    private String city;
    private String country;
    private int runwayLength;
}
