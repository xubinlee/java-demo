package com.lxb.apibrowser.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
* @Author: lixubin
* @Date: 2019-08-19
* @Description:
*/
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * 通过可能的参数化类型名称找bean
     *
     * @param cls
     * @param suffix
     * @return
     */
    public static <T> T getBeanByPossibleParameterizedTypeName(Class cls, String suffix) {
        String simpleName = cls.getSimpleName();
        String beanName = Character.toUpperCase(simpleName.charAt(0)) +simpleName.substring(1) + suffix;
        return (T)SpringContextUtils.getBean(beanName);
    }

    /**
     * 根据参数类型，从被注解标注的bean对象中查找
     *
     * @param cls     bean中含有的参数类型
     * @param aClass  注解类型
     * @param <T>
     * @return
     */
    public static <T> T getBeanByParameterizedType(Class cls, Class<? extends Annotation> aClass) {
        Map<String, Object> beans = SpringContextUtils.getContext().getBeansWithAnnotation(aClass);
        for (Object bean : beans.values()) {
            Object target;
            try {
                target = AopTargetUtils.getTarget(bean);
            } catch (Exception e) {
                continue;
            }
            Class targetClass = target.getClass();
            if (ReflectHelper.existSuperClassGenericType(targetClass, cls)) {
                return (T)bean;
            }
            if (ReflectHelper.existInterfaceGenericType(targetClass, cls)) {
                return (T)bean;
            }
        }
        throw new RuntimeException("None of the Bean's ParameterizedType is " + cls.getSimpleName());
    }



    /**
     * 通过name取得Bean, 自动转型为所赋值对象的类型 该类型的bean在IOC容器中也必须是唯一的
     *
     * @param name bean的name或id
     * @param <T> 要加载的Bean的类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 通过class取得Bean,该类型的bean在IOC容器中也必须是唯一的
     *
     * @param clazz 要加载的Bean的class
     * @param <T> 要加载的Bean的类型
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过name和class取得bean，比较适合当类型不唯一时，再通过id或者name来获取bean
     *
     * @param name bean的name或id
     * @param clazz 要加载的Bean的class
     * @param <T> 要加载的Bean的类型
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(name, clazz);
    }


    private static void checkApplicationContext() {
        Assert.notNull(applicationContext, "applicationContext 未注入");
    }


}
