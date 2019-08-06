package com.ex.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class EurekaClientController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("client")
    public String test(){
        List<String> services = discoveryClient.getServices();
        System.out.println(services);
        return services.toString();
    }
    /**
     * 为了请求测试Hystrix请求缓存提供的返回随机数的接口
     */
    @GetMapping("/hystrix/cache")
    public Integer getRandomInteger(){
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        return randomInt;
    }
}
