package com.ex.study.reflect;


import java.lang.reflect.Method;
import java.time.Instant;

public class ReflectTest {
    public static void main(String[] args) {
        User user = new User("李四", "123456", 18);
//        Object username = BeanUtil.getValueByPropertyName(user, "username");
//        System.out.println(username);
//        String s="hello";
//        BeanUtil.printClassMethod(s);
//        BeanUtil.printClassField(user);
        BeanUtil.printClassCon(user);
    }
}
