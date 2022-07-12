package com.example.mongoclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableAsync
public class MongoclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoclientApplication.class, args);
	}

}
