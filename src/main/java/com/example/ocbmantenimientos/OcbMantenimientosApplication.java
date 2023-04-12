package com.example.ocbmantenimientos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OcbMantenimientosApplication {

    public static void main(String[] args) {
        SpringApplication.run(OcbMantenimientosApplication.class, args);
    }

}
