package com.lxb.apibrowser.common.exceptions;

/**
* @Author: lixubin
* @Date: 2019-08-20
* @Description:
*/
public class LxbException extends RuntimeException {

    public LxbException() {
        super();
    }

    public LxbException(String message) {
        super(message);
    }

    public LxbException(String message, Throwable cause) {
        super(message, cause);
    }
}
