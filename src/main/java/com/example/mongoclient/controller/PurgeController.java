package com.example.mongoclient.controller;

import com.example.mongoclient.service.PurgeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/purge")
public class PurgeController {

    @Autowired
    private PurgeServiceImpl purgeServiceImpl;

    @GetMapping
    public ResponseEntity<?> getDate() {

        //-- BeanUtils.getPropertyDescriptor()
        purgeServiceImpl.purgeData();
        return ResponseEntity.ok("Mongoclient service up..." + LocalDateTime.now(ZoneOffset.UTC));
    }
}
