package com.lxb.common.exceptions;

/**
* @Author: lixubin
* @Date: 2019-08-20
* @Description: 业务异常
*/
public class BizException extends RuntimeException {

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
