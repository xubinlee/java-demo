package com.lxb.security.core.authentication;


import org.springframework.security.core.AuthenticationException;

/**
* @Author: lixubin
* @Date: 2019-08-19
* @Description:
*/
public class InvalidArgumentException extends AuthenticationException {

    public InvalidArgumentException(String msg) {
        super(msg);
    }

    public InvalidArgumentException(String msg, Throwable t) {
        super(msg, t);
    }
}
