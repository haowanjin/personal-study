package com.ddup.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudUserServiceApplication.class, args);
    }

}
