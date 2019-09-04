package com.lxb.apibrowser.common.exceptions;

/**
* @Author: lixubin
* @Date: 2019-08-20
* @Description: 发票识别异常
*/
public class RecognitionException extends RuntimeException {

    public RecognitionException() {
        super();
    }

    public RecognitionException(String message) {
        super(message);
    }

    public RecognitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
