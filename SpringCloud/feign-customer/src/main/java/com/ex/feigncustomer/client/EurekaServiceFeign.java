package com.ex.feigncustomer.client;

import com.ex.feigncustomer.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "eureka-client")
public interface EurekaServiceFeign {

    @RequestMapping(value = "client", method = RequestMethod.GET)
    String helloFeign();

    @GetMapping("user")
    User userFeign();

    @GetMapping("greet/{dd}")
    String greetFeign(@PathVariable("dd") String dd);
}
