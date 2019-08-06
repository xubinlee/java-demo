package com.ex.robbincustomer;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class CacheCommand extends HystrixCommand {

    @Autowired
    private RestTemplate restTemplate;

    private Long id;

    public CacheCommand(Setter setter, RestTemplate restTemplate, Long id){
        super(setter);
        this.restTemplate=restTemplate;
        this.id=id;
    }
    @Override
    protected Object run() throws Exception {
        return restTemplate.getForObject("http://eureka-client/hystrix/cache",Integer.class);
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }
    public static void flushRequestCache(Long id){
        HystrixRequestCache.getInstance(
                HystrixCommandKey.Factory.asKey("test"), HystrixConcurrencyStrategyDefault.getInstance())
                .clear(String.valueOf(id));
    }

    public Long getId() {
        return id;
    }
}
