package com.ex.study.designpattern;

import com.sun.org.apache.bcel.internal.generic.IUSHR;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {
        /**
         * 3个参数解释：
         * 1.classloader生成代理类
         * 2.代理类应该实现的接口
         * 3.实现InvocationHandler的切面类
         */
        IUserService userService = (IUserService) Proxy.newProxyInstance(IUserService.class.getClassLoader()
                , new Class[]{IUserService.class}, new RequestCostInvocationHandler(new UserServiceImpl()));

        userService.request();
    }
}

class RequestCostInvocationHandler implements InvocationHandler{
    private Object target;
    public RequestCostInvocationHandler(Object target){
        this.target=target;
    }

    /**
     * 被代理对象的任何方法被执行时，都会先进入这个方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("request".equals(method.getName())) {
            long startTime = System.currentTimeMillis();
            //执行目标对象的方法
            method.invoke(target, args);
            System.out.println("reques cost:"+(System.currentTimeMillis()-startTime));
        }
        return null;
    }
}
