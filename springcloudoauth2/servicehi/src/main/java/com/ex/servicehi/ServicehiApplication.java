package com.ex.servicehi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class})
@EnableEurekaClient
public class ServicehiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicehiApplication.class, args);
    }

}
