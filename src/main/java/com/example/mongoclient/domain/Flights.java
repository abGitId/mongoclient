package com.example.mongoclient.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Document("flights")
@Data
public class Flights {
    @Id
    private String id;

    @Field("departure")
    /*@TextIndexed
    private String departureCity;*/
    private Departure departure;

    @Field("destination")
    private Departure destination;

    @TextIndexed(weight = 2)
    private String description;

    private String type;
    private String aircraftCode;
    private boolean delayed;
    @Field("duration")
    private int durationMin;
    private LocalDateTime departureDate;
    private int distanceKm;

    @Transient
    private LocalDate createdAt;

    /*public Flights() {
        this.createdAt = LocalDate.now();
        this.internalId = UUID.randomUUID().toString();
    }*/

}
