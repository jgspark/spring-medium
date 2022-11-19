package com.example.laboratory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class LaboratoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboratoryApplication.class, args);
    }

}
