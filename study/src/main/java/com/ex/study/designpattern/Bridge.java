package com.ex.study.designpattern;

/**
* @Author: lixubin
* @Date: 2019-07-12
* @Description: 桥接模式
 * 场景：箱子有不同颜色，不同形状…，通过先选择颜色再选择形状…的形式桥接，挑选箱子
*/
public class Bridge {
    public static void main(String[] args) {
        Color color = new Red();
        Box box = new CircleBox();
        box.setColor(color);
        String name = box.getShape();
        System.out.println(name);
    }
}

interface Color{
    String getColor();
}

class Red implements Color{
    @Override
    public String getColor() {
        return "红色的";
    }
}

class Yellow implements Color{
    @Override
    public String getColor() {
        return "原色的";
    }
}

abstract class Box{
    protected Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract String getShape();
}

class CircleBox extends Box{

    @Override
    public String getShape() {
        return color.getColor()+"圆形箱子";
    }
}

class SquareBox extends Box{
    @Override
    public String getShape() {
        return color.getColor()+"方形箱子";
    }
}
