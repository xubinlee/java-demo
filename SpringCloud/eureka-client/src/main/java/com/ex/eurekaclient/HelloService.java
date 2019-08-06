package com.ex.eurekaclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface HelloService {

    @GetMapping(value = "/refactor-service/{name}")
    String hello(@PathVariable("name") String name);
}
