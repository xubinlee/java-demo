package com.lxb.common;

import java.util.UUID;

/**
* @Author: lixubin
* @Date: 2019-08-20
* @Description:
*/
public class RandomUUID {

    public static String get() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
