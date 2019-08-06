package com.ex.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProviderController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("user")
    public User getUser(){
        List<String> services = discoveryClient.getServices();
        System.out.println(services.toString());
        return new User("lxb","male","123456789");
    }

    @GetMapping("greet/{dd}")
    public String greet(@PathVariable String dd){
        List<String> services = discoveryClient.getServices();
        return dd+":"+services.toString();
    }

    @GetMapping("/user/{name}")
    public User getUserByName(@PathVariable String name){
        List<String> services = discoveryClient.getServices();
        System.out.println(services.toString());
        if (name.isEmpty()){
            return new User();
        }else if ("lxb".equals(name)){
            return new User("lxb","male","123456789");
        }else{
            return new User("随机用户","male","987654321");
        }
    }
}
