package com.example.mongoclient.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/util")
@Validated
public class UtilController {

    /*@GetMapping("/header-validate")
    public ResponseEntity<?> getServiceStatus(@NotNull(message = "should not empty") @RequestHeader(value = "my-header",required = false) String myHeader) {

        return ResponseEntity.ok("Mongoclient service up..." + myHeader);
    }*/

    @GetMapping("/get-date")
    public ResponseEntity<?> getDate() {

        //-- BeanUtils.getPropertyDescriptor()
        return ResponseEntity.ok("Mongoclient service up..." + LocalDateTime.now(ZoneOffset.UTC));
    }
}
