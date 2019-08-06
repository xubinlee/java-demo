package com.ex.study.designpattern;

/**
* @Author: lixubin
* @Date: 2019-07-12
* @Description: 代理模式
*/
class PorxyTest{
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }
}

interface Subject{
    void request();
}

class RealSubject implements Subject{
    @Override
    public void request() {
        System.out.println("我是真实的请求");
    }
}

class Proxy implements Subject{

    private RealSubject realSubject;

    @Override
    public void request() {
        if (realSubject==null){
            realSubject= new RealSubject();
        }
        realSubject.request();
    }
}
