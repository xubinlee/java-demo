package com.ex.robbincustomer;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class UserHystrixCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;

    private Long id;

    public UserHystrixCommand(Setter setter, RestTemplate restTemplate, Long id){
        super(setter);
        this.restTemplate=restTemplate;
        this.id=id;
    }
    @Override
    protected User run() throws Exception {
        //本地请求
//        return restTemplate.getForObject("http://localhost:8080/user",User.class);
        //连注册中心请求
        return restTemplate.getForObject("http://eureka-client/user",User.class);
    }

    public static void main(String[] args) {
        //同步请求
        User userSync = new UserHystrixCommand(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)
        ), new RestTemplate(), 0L).execute();
        System.out.println("-------------------This is sync request's response:"+userSync);
        //异步请求
        Future<User> userFuture = new UserHystrixCommand(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)
        ), new RestTemplate(), 0L).queue();

        User userAsync =null;
        try {
            userAsync = userFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------This is async request's response:"+userAsync);
    }
}
