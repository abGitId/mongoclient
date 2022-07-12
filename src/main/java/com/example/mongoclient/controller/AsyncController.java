package com.example.mongoclient.controller;

import com.example.mongoclient.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/async")
    public String testAsync() {
        asyncService.asyncMethod();
        return "async test api...";
    }

    @GetMapping("/test-property")
    public List<?> testProperty() {
        return asyncService.testBeanProperty();
    }

}
