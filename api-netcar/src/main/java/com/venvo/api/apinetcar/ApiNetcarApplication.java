package com.venvo.api.apinetcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiNetcarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiNetcarApplication.class, args);
    }

}
