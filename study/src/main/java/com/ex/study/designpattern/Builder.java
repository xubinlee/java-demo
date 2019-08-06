package com.ex.study.designpattern;

/**
* @Author: lixubin
* @Date: 2019-07-12
* @Description: 建造者模式
*/
class BuilderTest{
    public static void main(String[] args) {
        Director director = new Director(new MobikeBuilder());
        Bike bike = director.construct();
//        MobikeBuilder builder = new MobikeBuilder();
//        builder.construct();
//        Director director = new Director(builder);
    }
}

class Director{
    private Builder builder =null;
    public Director(Builder builder){
        this.builder=builder;
    }
    public Bike construct(){
        builder.buildFrame();
        builder.buildSeat();
        builder.buildTire();
        return builder.buildBike();
    }
}

public abstract class Builder {
    //造框架
    abstract void buildFrame();

    //造坐垫
    abstract void buildSeat();

    //造轮子
    abstract void buildTire();

    //造自行车
    abstract Bike buildBike();

//    public Bike construct(){
//        this.buildFrame();
//        this.buildSeat();
//        this.buildTire();
//        return this.buildBike();
//    }
}

class MobikeBuilder extends Builder {
    private Bike bike = new Bike();

    @Override
    void buildFrame() {
        bike.setFrame(new MobikeFrame());
    }

    @Override
    void buildSeat() {
        bike.setSeat(new MobikeSeat());
    }

    @Override
    void buildTire() {
        bike.setTire(new MobikeTire());
    }

    @Override
    Bike buildBike() {
        return bike;
    }
}

class OfoBuilder extends Builder {
    private Bike bike = new Bike();

    @Override
    void buildFrame() {
        bike.setFrame(new OfoFrame());
    }

    @Override
    void buildSeat() {
        bike.setSeat(new OfoSeat());
    }

    @Override
    void buildTire() {
        bike.setTire(new OfoTire());
    }

    @Override
    Bike buildBike() {
        return bike;
    }
}

interface IFrame {
}

interface ISeat {
}

interface ITire {
}

class MobikeFrame implements IFrame {

}

class MobikeSeat implements ISeat {

}

class MobikeTire implements ITire {

}

class OfoFrame implements IFrame {

}

class OfoSeat implements ISeat {

}

class OfoTire implements ITire {

}

class Bike {
    private IFrame frame;
    private ISeat seat;
    private ITire tire;

    public IFrame getFrame() {
        return frame;
    }

    public void setFrame(IFrame frame) {
        this.frame = frame;
    }

    public ISeat getSeat() {
        return seat;
    }

    public void setSeat(ISeat seat) {
        this.seat = seat;
    }

    public ITire getTire() {
        return tire;
    }

    public void setTire(ITire tire) {
        this.tire = tire;
    }
}
