package com.ex.eurekaclient;

import lombok.Data;

@Data
public class User {

    private String name;

    private String sex;

    private String phone;

    public User(){}

    public User(String name, String sex, String phone){
        this.name=name;
        this.sex=sex;
        this.phone=phone;
    }
}
