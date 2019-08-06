package com.ex.activemq;

import java.security.Principal;

public class TestMQ {
    public static void main(String[] args) {
        Producter producter = new Producter();
        producter.init();
        TestMQ testMQ = new TestMQ();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(testMQ.new ProductorMQ(producter)).start();
    }

    private class ProductorMQ implements Runnable{
        Producter producter;
        public ProductorMQ(Producter producter){
            this.producter=producter;
        }

        @Override
        public void run(){
            while (true){
                producter.sendMessage("active-mq");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
