package com.ex.mallcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ex")
public class MallCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCoreApplication.class, args);
    }

}
