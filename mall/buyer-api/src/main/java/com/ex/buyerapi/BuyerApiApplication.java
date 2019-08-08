package com.ex.buyerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.ex")
@ServletComponentScan
public class BuyerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuyerApiApplication.class, args);
    }

}
