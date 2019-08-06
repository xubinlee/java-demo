package com.ex.robbincustomer.service;

import com.ex.robbincustomer.CacheCommand;
import com.ex.robbincustomer.User;
import com.ex.robbincustomer.UserHystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hystrixFallback")
    public String testService(){
        long start = System.currentTimeMillis();
        //设置随机3秒内延迟，hystrix默认延迟2秒未返回则熔断，调用回调方法
        int sleepMillis = new Random().nextInt(3000);
        System.out.println("-------sleep-time"+sleepMillis);
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String body = restTemplate.getForEntity("http://eureka-client/client", String.class).getBody();
        long end = System.currentTimeMillis();
        System.out.println("-----spend-time"+(end-start));
        return body;
    }

    /**
     * 调用服务失败处理方法：返回类型为字符串
     * @return “error"
     */
    public String hystrixFallback(){
        return "error";
    }

    /**
     * 使用自定义HystrixCommand同步方法调用接口
     */
    public User useSyncRequestGetUser(){
        //这里使用Spring注入的RestTemplate, Spring注入的对象都是静态的
        User userSync = new UserHystrixCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("")).andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)),
                restTemplate ,0L).execute();

        return userSync;
    }

    /**
     * 使用自定义HystrixCommand异步方法调用接口
     */
    public User useAsyncRequestGetUser(){

        Future<User> userFuture = new UserHystrixCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("")).andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)),
                restTemplate,0L).queue();

        User userAsync = null;

        try {
            //获取Future内部包含的对象
            userAsync = userFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return userAsync;
    }

    /**
     * 使用注解实现异步请求调用
     *
     * 注意：此处AsyncResult为netfix实现，spring也做了实现，注意导包。
     */
    @HystrixCommand(fallbackMethod = "fallbackForUserTypeReturnMethod")
    public Future<User> asyncRequest(){
        return new AsyncResult<User>(new User()){
            public User invoke(){
                return restTemplate.getForObject("http://eureka-client/user",User.class);
            }
        };
    }

    /**
     * 调用服务失败处理方法：返回类型为User
     */
    public User fallbackForUserTypeReturnMethod(){
        return new User("1","1","1");
    }

    /**
     * 继承方式开启请求缓存,注意commandKey必须与清除的commandKey一致
     */
    public void openCacheByExtends(){
        CacheCommand command1 = new CacheCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("group")).andCommandKey(HystrixCommandKey.Factory.asKey("test")),
                restTemplate,1L);
        CacheCommand command2 = new CacheCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("group")).andCommandKey(HystrixCommandKey.Factory.asKey("test")),
                restTemplate,1L);
        Integer result1 = (Integer) command1.execute();
        Integer result2 = (Integer) command2.execute();
        System.out.println(String.format("first request result is:%s ,and secend request result is: %s", result1, result2));;
    }

    /**
     * 继承方式清除请除缓存
     */
    public void clearCacheByExtends(){
        CacheCommand.flushRequestCache(1L);
        System.out.println("请求缓存已清空！");
    }
}
