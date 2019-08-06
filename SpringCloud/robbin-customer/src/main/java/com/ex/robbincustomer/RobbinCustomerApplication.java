package com.ex.robbincustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
* @Author: lixubin
* @Date: 2019-06-28
* @Description: 消费应用
*/
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class RobbinCustomerApplication {

    @Bean   //将此Bean交给spring容器
    @LoadBalanced   //开启负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(RobbinCustomerApplication.class, args);
    }

}
