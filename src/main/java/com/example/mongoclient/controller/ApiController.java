package com.example.mongoclient.controller;

import com.example.mongoclient.repository.FlightsReository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private FlightsReository flightsReository;

    @GetMapping("/status")
    public ResponseEntity<?> getServiceStatus() {

        return ResponseEntity.ok("Mongoclient service up...");
    }

    // get all flights
    // http://localhost:8084/flights
    @GetMapping("/flights")
    public ResponseEntity<?> getFlights() {

        //-- Sort sort = Sort.by(Sort.Direction.DESC, "").ca;

        //-- return ResponseEntity.ok(flightsReository.findAll(Sort.by(Sort.Order.asc("description").ignoreCase())));
        return ResponseEntity.ok(flightsReository.findAll());
    }

    // flight by id
    // http://localhost:8084/flights/60ed37f12e0b9f51fbfe6794
    @GetMapping("/flights/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable String id) {

        return ResponseEntity.ok(flightsReository.findById(id));
    }

    // get delayed flights
    // http://localhost:8084/flights/delayed
    @GetMapping("/flights/delayed")
    public ResponseEntity<?> getDelayedFlights() {

        return ResponseEntity.ok(flightsReository.findByDelayed(true));
    }

    // Flights by min duration
    // http://localhost:8084/flights/duration/100
    @GetMapping("/flights/duration/{minTime}")
    public ResponseEntity<?> getFlightsByDurationLessThan(@PathVariable int minTime) {

        return ResponseEntity.ok(flightsReository.findByDurationMinLessThan(minTime));
    }

    // Flights by departureCity
    // http://localhost:8084/flights/departure-city/CDG
    @GetMapping("/flights/departure-city/{cityCode}")
    public ResponseEntity<?> getFlightsByDurationLessThan(@PathVariable String cityCode) {

        return ResponseEntity.ok(flightsReository.findByDepartureCity(cityCode));
    }

    // http://localhost:8084/flights/count
    @GetMapping("/flights/count")
    public ResponseEntity<?> getFlightsCount() {

        return ResponseEntity.ok(flightsReository.getFlightsCount());
    }

    // http://localhost:8084/flights/count-type
    @GetMapping("/flights/count-type")
    public ResponseEntity<?> getFlightsCountByType() {

        return ResponseEntity.ok(flightsReository.getCountOfFlightType());
    }
}
