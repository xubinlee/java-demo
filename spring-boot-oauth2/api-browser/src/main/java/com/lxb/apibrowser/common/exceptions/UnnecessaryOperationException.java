package com.lxb.common.exceptions;

/**
* @Author: lixubin
* @Date: 2019-08-20
* @Description: 不必要的操作异常
*/

public class UnnecessaryOperationException extends BizException {

    public UnnecessaryOperationException() {
        super();
    }

    public UnnecessaryOperationException(String message) {
        super(message);
    }

    public UnnecessaryOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
