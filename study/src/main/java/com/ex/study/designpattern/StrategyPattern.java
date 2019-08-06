package com.ex.study.designpattern;

/**
* @Author: lixubin
* @Date: 2019-07-12
* @Description: 策略模式
*/
public class StrategyPattern {
    public static void main(String[] args) {
        Environment environment = new Environment(new AddStrategy());
        int i = environment.calculate(1, 1);
        System.out.println(i);
    }
}

interface  Strategy{
    public int calc(int num1, int num2);
}

class AddStrategy implements  Strategy{

    @Override
    public int calc(int num1, int num2) {
        return num1+num2;
    }
}

class SubtractStrategy implements Strategy{

    @Override
    public int calc(int num1, int num2) {
        return num1-num2;
    }
}

class Environment{
    private Strategy strategy;

    public Environment(Strategy strategy){
        this.strategy=strategy;
    }

    public int calculate(int num1, int num2){
        return strategy.calc(num1, num2);
    }
}
