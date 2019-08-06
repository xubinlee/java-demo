package com.ex.study.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
* @Author: lixubin
* @Date: 2019-07-16
* @Description: 观察者模式
 * 和发布订阅模式的区别：
 * 观察者模式只有观察者和被观察者两个角色，是松耦合的关系；
 * 发布订阅模式有发布者、订阅者和经纪人（Broker）三个角色，发布者和订阅者互相不认识，是完全解耦的，
 * 发布者只需告诉经纪人，我要发送消息，topic是AAA，
 * 订阅者只需告诉经纪人，我要订阅topic是AAA的消息，
 * 经纪人收到发布者的消息后，就会将消息推送给订阅了topic的订阅者
*/
class Client{
    public static void main(String[] args) {
        //灰太狼：被观察者
        Wolf wolf = new Wolf();

        //喜羊羊：观察者
        Sheep sheep = new Sheep();
        wolf.add(sheep);

        //灰太狼入侵
        wolf.invade();
    }
}

public interface Observer {
    String getName();

    //通知更新方法
    void update(String msg);
}

/**
 * 抽象目标
 */
abstract class Observable {

    //观察者集合
    List<Observer> list = new ArrayList<Observer>();

    //添加观察者
    public void add(Observer observer) {
        list.add(observer);
    }

    //删除观察者
    public void remove(Observer observer) {
        list.remove(observer);
    }

    //通知所有观察者
    public void notifyObserver() {
        for (Observer observer : list) {
            observer.update("灰太狼要搞事情啦！");
        }
    }
}

class Wolf extends Observable {

    public void invade() {
        System.out.println("我是灰太狼，我入侵羊村啦！");
        //通知所有观察者
        notifyObserver();
    }
}

class Sheep implements Observer{
    @Override
    public String getName() {
        return "喜羊羊";
    }

    /**
     * 具体的业务逻辑
     * @param msg
     */
    @Override
    public void update(String msg) {
        System.out.println(getName()+"收到通知："+msg);
    }
}

