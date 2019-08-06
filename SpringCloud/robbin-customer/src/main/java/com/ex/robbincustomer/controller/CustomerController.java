package com.ex.robbincustomer.controller;

import com.ex.robbincustomer.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("customer")
    public String testCustomer(){
        return restTemplate.getForEntity("http://eureka-client/client",String.class).getBody();
    }

    @GetMapping("entity/type")
    public User getEntityByType(){
        ResponseEntity<User> entity = restTemplate.getForEntity("http://eureka-client/user", User.class);
        User user = entity.getBody();
        System.out.println(user.toString());
        return user;
    }

    @GetMapping("entity")
    public String getForEntity(@RequestParam("name") String name){
        return restTemplate.getForEntity("http://eureka-client/greet/{1}",String.class,name).getBody();
    }

    @GetMapping("entity/map/{name}")
    public String getForEntityByMap(@PathVariable String name){
        HashMap<String, String> map = new HashMap<>();
        map.put("name",name);
        return restTemplate.getForEntity("http://eureka-client/greet/{name}",String.class,map).getBody();
    }
    @GetMapping("/entity/uri")
    public String getForEntityByURI(){
        //使用uri进行传参并访问
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://eureka-client/greet/{name}").build().expand("lxb").encode();
        URI uri = uriComponents.toUri();
        return restTemplate.getForEntity(uri, String.class).getBody();
    }
    /**
     * T getForObject(String url, Class<T> responseType)
     */
    @GetMapping("/object")
    public User getForObjectWithNoParam(){
        //相比getForEntity方法，获取对象可以省去调用getBody
        return restTemplate.getForObject("http://eureka-client/user", User.class);
    }

    /**
     * T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables)
     */
    @GetMapping("/object/map")
    public User getForObjectByMap(){
        //使用map传参
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("name","lxb");
        return restTemplate.getForObject("http://eureka-client/user", User.class, paramMap);
    }

    /**
     * T getForObject(String url, Class<T> responseType, Object... uriVariables)
     */
    @GetMapping("/object/param/{name}")
    public User getForObjectByParam(@PathVariable String name){
        return restTemplate.getForObject("http://eureka-client/user/{name}",User.class, name);
    }

    /**
     * T getForObject(URI url, Class<T> responseType)
     */
    @GetMapping("/object/uri/{name}")
    public User getForObjectByURI(@PathVariable String name){
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://eureka-client/user/{name}")
                .build().expand(name).encode();
        URI uri = uriComponents.toUri();
        return restTemplate.getForObject(uri,User.class);
    }
}
