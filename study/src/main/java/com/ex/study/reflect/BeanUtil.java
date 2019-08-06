package com.ex.study.reflect;

import org.springframework.context.support.StaticApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BeanUtil {

    /**
     * 根据标准javaBean对象的属性名获取其属性值
     *
     * @param obj
     * @param propertyName
     * @return
     */
    public static Object getValueByPropertyName(Object obj, String propertyName) {
        // 1.根据属性名称获取其get方法名称（getter属性）
        String getMethodName = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        // 2.获取方法对象
        Class c = obj.getClass();
        try {
            // 3.获取get方法，get方法都是public且无参的
            Method method = c.getMethod(getMethodName);
            // 4.通过方法的反射操作方法
            return method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 打印类的方法（包括参数和返回类型）
     *
     * @param obj
     */
    public static void printClassMethod(Object obj) {
        Class<?> c = obj.getClass();
        Method[] methods = c.getMethods();
        StringBuilder sb = new StringBuilder();
        for (Method method : methods) {
            Class<?> returnType = method.getReturnType();
            String methodName = method.getName();
            sb.append(returnType.getName() + " ");
            sb.append(methodName + "(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                sb.append(parameterType.getName() + ",");
            }
            if (parameterTypes.length > 0) {
                sb.deleteCharAt(sb.length() - 1);
                sb.append(")\r\n");
            }
        }
        System.out.println(sb);
    }

    /**
     * 获取成员变量的信息
     * 成员变量也是对象
     * java.lang.reflect.Field
     * getFields()方法获取的是所有public的成员变量的信息
     * getDeclaredFields获取该类自己声明的成员变量的信息
     *
     * @param obj
     */
    public static void printClassField(Object obj) {
        Class<?> c = obj.getClass();
        Field[] declaredFields = c.getDeclaredFields();
        for (Field field : declaredFields) {
            Class<?> fieldType = field.getType();
            String typeName = fieldType.getName();
            String fieldName = field.getName();
            System.out.println(typeName + " " + fieldName + "\r\n");
        }
    }

    /**
     * 打印对象的构造函数
     *
     * @param obj
     */
    public static void printClassCon(Object obj) {
        Class<?> c = obj.getClass();
        Constructor<?>[] declaredConstructors = c.getDeclaredConstructors();
        StringBuilder sb = new StringBuilder();
        for (Constructor<?> constructor : declaredConstructors) {
            String constructorName = constructor.getName();
            sb.append(constructorName + " (");
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                sb.append(parameterType.getName() + ",");
            }
            if (parameterTypes.length > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")\r\n");
        }
        System.out.println(sb);
    }
}
