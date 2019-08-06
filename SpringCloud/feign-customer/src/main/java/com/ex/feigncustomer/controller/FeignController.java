package com.ex.feigncustomer.controller;

import com.ex.feigncustomer.User;
import com.ex.feigncustomer.client.EurekaServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feign")
public class FeignController {

    @Autowired
    private EurekaServiceFeign eurekaServiceFeign;

    @GetMapping("client")
    @ResponseBody
    public String sayHello(){
        return eurekaServiceFeign.helloFeign();
    }

    @GetMapping("user")
    public User getUser(){
        return eurekaServiceFeign.userFeign();
    }

    @GetMapping("greet/{test}")
    public String greet(@PathVariable String test){
        return eurekaServiceFeign.greetFeign(test);
    }
}
