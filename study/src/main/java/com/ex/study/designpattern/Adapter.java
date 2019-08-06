package com.ex.study.designpattern;

import org.hibernate.validator.cfg.defs.br.CNPJDef;

/**
* @Author: lixubin
* @Date: 2019-07-12
* @Description: 适配器模式
 * 场景：去日本旅游，用GB插头和适配器接JP插座充电
*/
class AdapterTest{
    public static void main(String[] args) {
        //带了适配器,该适配器继承了CNPlug有三脚插头充电功能，实现了JPPlug，可以转成两脚接口
        JPPlug adapter = new Adapter();
        //住日本Hotel
        Hotel hotel = new Hotel(adapter);
        hotel.charge();
    }
}

interface JPPlug{
    /**
     * 二脚插头
     */
    void twopinPlug();
}

class CNPlug{
    public void threepinPlug(){
        System.out.println("我是国标的三脚插头，我通过适配器可以在JP Hotel充电");
    }
}

/**
* @Author: lixubin
* @Date: 2019-07-12
* @Description: 三脚插头转两脚插头适配器
*/
public class Adapter extends CNPlug implements JPPlug {

    @Override
    public void twopinPlug() {
        threepinPlug();
    }
}

class Hotel {

    private JPPlug jpPlug;

    public Hotel(){}

    public Hotel(JPPlug jpPlug){
        this.jpPlug=jpPlug;
    }

    //旅馆充电，只能用两脚插头
    public void charge(){
        jpPlug.twopinPlug();
    }
}
