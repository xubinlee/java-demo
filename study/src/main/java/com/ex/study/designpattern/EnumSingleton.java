package com.ex.study.designpattern;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum EnumSingleton implements Serializable {
    INSTANCE;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EnumSingleton getSingleton() {
        return INSTANCE;
    }
}

/**
* @Author: lixubin
* @Date: 2019-07-12
* @Description: 枚举单例模式
*/
//https://www.cnblogs.com/chiclee/p/9097772.html
class EnumTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException, ClassNotFoundException {
        EnumSingleton singleton = EnumSingleton.INSTANCE;
        //region 反射攻击问题
        Constructor<EnumSingleton> constructor = EnumSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        EnumSingleton newInstance = constructor.newInstance();
        System.out.println(singleton+"\n"+newInstance);
        //endregion
        //region 避免序列化问题
        singleton.setContent("枚举单例序列化");
        FileOutputStream outputStream = new FileOutputStream("EnumSingleton.obj");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(singleton);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream inputStream = new FileInputStream("EnumSingleton.obj");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        EnumSingleton o = (EnumSingleton)objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(singleton+"\n"+o);
        //endregion
    }
}
