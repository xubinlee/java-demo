package com.ex.robbincustomer.controller;

import com.ex.robbincustomer.User;
import com.ex.robbincustomer.service.RibbonService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class RibbonController {

    @Autowired
    private RibbonService ribbonService;

    @GetMapping("/invoke")
    public String helloHystrix(){
        //调用服务层方法
        return ribbonService.testService();
    }

    /**
     * 发送同步请求，使用继承方式实现自定义Hystrix
     */
    @GetMapping("/sync")
    public User sendSyncRequestGetUser(){
        return ribbonService.useSyncRequestGetUser();
    }

    /**
     * 发送异步请求，使用继承方式实现自定义Hystrix
     */
    @GetMapping("/async")
    public User sendAsyncRequestGetUser(){
        return ribbonService.useAsyncRequestGetUser();
    }
    /**
     * 使用注解发送异步请求
     */
    @GetMapping("/async2")
    public User sendAsyncRequestByAnnotation(){
        Future<User> userFuture = ribbonService.asyncRequest();
        try {
            return userFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 继承方式开启请求缓存,并多次调用CacheCommand的方法
     * 在两次请求之间加入清除缓存的方法
     */
    @GetMapping("/cacheOn")
    public void openCacheTest(){
        //初始化Hystrix请求上下文
        HystrixRequestContext.initializeContext();
        //开启请求缓存并测试两次
        ribbonService.openCacheByExtends();
        //清除缓存
        ribbonService.clearCacheByExtends();
        //再次开启请求缓存并测试两次
        ribbonService.openCacheByExtends();
    }
}
