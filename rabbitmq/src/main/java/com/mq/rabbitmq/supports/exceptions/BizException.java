package com.mq.rabbitmq.supports.exceptions;

/**
 * 业务异常（通常是用户误操作导致，不用记录日志）
 *
 * @author Shoven
 * @since  2018-11-09
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
