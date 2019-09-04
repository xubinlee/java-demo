package com.lxb.apibrowser.common;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

/**
* @Author: lixubin
* @Date: 2019-08-19
* @Description: spring代理对象工具类
*/
public class AopTargetUtils {


    /**
     * 获取被代理的对象
     * @param proxy 代理对象
     * @return
     * @throws Exception
     */
    public static Object getTarget(Object proxy) throws Exception {
        if(!AopUtils.isAopProxy(proxy)) {
            return proxy;
        }
        if(AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTargetObject(proxy);
        } else {
            return getCglibProxyTargetObject(proxy);
        }
    }


    /**
     * 获取cglib动态代理对象
     *
     * @param proxy
     * @return
     * @throws Exception
     */
    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);

        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        return ((AdvisedSupport)advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
    }


    /**
     * 获取jdk动态代理对象
     *
     * @param proxy
     * @return
     * @throws Exception
     */
    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);

        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        return ((AdvisedSupport)advised.get(aopProxy)).getTargetSource().getTarget();
    }

}
