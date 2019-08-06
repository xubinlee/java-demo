package com.ex.eurekaclient;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController implements HelloService {
    @Override
    public String hello(String name) {
        System.out.println(String.format("refactorHelloService的hello方法执行了，入参：name:%s", name));
        return "hello"+name;
    }
}
