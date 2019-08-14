package com.ex.study;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

public class BeanCopierTest {
    public static void main(String[] args) {
//        sameCopy();
//        difCopy();
//        difCopyConvert();
        wrappedBeanCopier();
    }

    public static void wrappedBeanCopier(){
        One one = new One();
        one.setId(1);
        one.setName("one");
        OneDif oneDif = WrappedBeanCopier.copyProperties(one, OneDif.class);
        System.out.println(oneDif);
    }

    // 属性名称、类型都相同，成功复制
    public static void sameCopy() {
        One one = new One();
        one.setId(1);
        one.setName("one");
        final BeanCopier copier = BeanCopier.create(One.class, Two.class, false);
        Two two = new Two();
        copier.copy(one, two, null);
        System.out.println(one.toString());
        System.out.println(two.toString());
    }
    // 属性名称相同,类型不同，类型不同的不能复制
    public static void difCopy() {
        One one = new One();
        one.setId(1);
        one.setName("one");
        final BeanCopier copier = BeanCopier.create(One.class, OneDif.class, false);
        OneDif oneDif = new OneDif();
        copier.copy(one, oneDif, null);
        System.out.println(one.toString());
        System.out.println(oneDif.toString());
    }
    // 属性名称相同,类型不同的解决方法，使用convert，注意要写上类型相同的情况
    public static void difCopyConvert() {
        One one = new One();
        one.setId(1);
        one.setName("one");
        final BeanCopier copier = BeanCopier.create(One.class, OneDif.class, true);
        OneDif oneDif = new OneDif();
        copier.copy(one, oneDif, new Converter() {
            @Override
            public Object convert(Object o, Class aClass, Object o1) {
                return null;
            }
        });
        System.out.println(one.toString());
        System.out.println(oneDif.toString());
    }
}

class One {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;

    @Override
    public String toString() {
        return " one{  "
                + " \n id : " + this.id
                + " \n name : " + this.name
                + "  \n }";
    }
}

class Two {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return " two{  "
                + " \n id : " + this.id
                + " \n name : " + this.name
                + "  \n }";
    }
}

class OneDif {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return " oneDif{  "
                + " \n id : " + this.id
                + " \n name : " + this.name
                + " \n }";
    }
}

class OneLessSetter {
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private int id;

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return " oneLessSetter{  "
                + " \n id : " + this.id
                + " \n name : " + this.name
                + " \n }";
    }
}