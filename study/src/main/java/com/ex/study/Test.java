package com.ex.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
    private final static Logger logger = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) throws InterruptedException {
//        BigDecimal d3 = new BigDecimal("1234500");
//        BigDecimal d4 = d3.stripTrailingZeros();
//        System.out.println(d4.scale());
//        Resource resource = new Resource();
//        Producer producer = new Producer(resource);
//        Consumer consumer = new Consumer(resource);
//        new Thread(producer).start();
//        new Thread(producer).start();
//        new Thread(consumer).start();
//        new Thread(consumer).start();
//        for (int j = 0; j < 5; j++) {
//            for (int i = 0; i < 5; i++) {
//                new Thread(()->{
//                    System.out.println(Thread.currentThread().getName());
//                },"线程A").start();
//            }
//            Thread.sleep(100);
//            for (int i = 0; i < 5; i++) {
////                System.out.println(Thread.currentThread().getName());
//                logger.info(Thread.currentThread().getName());
//            }
//        }
    }
}


class Resource{
    private String name;
    private int count=1;
    private boolean flag=true;
    public synchronized void set(String name){
        if (flag){
            try {
                wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.name=name+"---"+count++;
            System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);
            flag=true;
            this.notify();
        }
    }
    public synchronized void out(){
        if(!flag) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        System.out.println(Thread.currentThread().getName()+"...消费者..."+this.name);
        flag=false;
        this.notify();
    }
}
class Producer implements Runnable{
    private Resource res;
    public Producer(Resource res){
        this.res=res;
    }

    @Override
    public void run() {
        while (true){
            res.set("商品");
        }
    }
}
class Consumer implements Runnable{
    private Resource res;
    public Consumer(Resource res){
        this.res=res;
    }
    @Override
    public void run(){
        while(true){
            res.out();
        }
    }
}






