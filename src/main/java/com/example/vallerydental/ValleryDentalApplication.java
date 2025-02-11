package com.example.vallerydental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ValleryDentalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ValleryDentalApplication.class, args);
    }
}
