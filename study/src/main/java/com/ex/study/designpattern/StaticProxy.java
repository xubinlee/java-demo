package com.ex.study.designpattern;

import com.ex.study.reflect.UserService;

public class StaticProxy {
    public static void main(String[] args) {
        IUserService userService = new UserServiceImpl();
        final UserServiceProxy proxy = new UserServiceProxy(userService);
        proxy.request();
    }
}

interface IUserService{
    void request();
}

class UserServiceImpl implements IUserService{
    @Override
    public void request() {
        System.out.println("this is userService");
    }
}

class UserServiceProxy implements IUserService{
    private IUserService userService;
    public UserServiceProxy(IUserService userService){
        this.userService=userService;
    }
    @Override
    public void request() {
        long startTime = System.currentTimeMillis();
        userService.request();
        System.out.println("reques cost:"+(System.currentTimeMillis()-startTime));
    }
}
