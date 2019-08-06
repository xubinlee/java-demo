package com.ex.study.designpattern;

/**
* @Author: lixubin
* @Date: 2019-07-16
* @Description: 装饰模式
*/
public class Decorator {
    public static void main(String[] args) {
        CarImpl car = new CarImpl();
        car.move();

        System.out.println("\n增加新功能：天上飞");
        FlyCar flyCar = new FlyCar(car);
        flyCar.move();

        System.out.println("\n增加新功能：水里游");
        WaterCar waterCar = new WaterCar(car);
        waterCar.move();

        System.out.println("\n增加新功能：天上飞、水里游");
        WaterCar waterCar1 = new WaterCar(new FlyCar(car));
        waterCar1.move();
    }
}

interface Car{
    void move();
}

class CarImpl implements Car{
    @Override
    public void move() {
        System.out.println("陆地上跑");
    }
}

class SuperCar implements Car{
    private  Car car;

    public SuperCar(Car car){
        this.car=car;
    }
    @Override
    public void move() {
        car.move();
    }
}

class FlyCar extends SuperCar{
    public FlyCar(Car car) {
        super(car);
    }

    public void fly(){
        System.out.println("天上飞");
    }

    @Override
    public void move() {
        super.move();
        fly();
    }
}

class WaterCar extends SuperCar{
    public WaterCar(Car car) {
        super(car);
    }

    public void swim(){
        System.out.println("水里游");
    }
    @Override
    public void move() {
        super.move();
        swim();
    }
}
