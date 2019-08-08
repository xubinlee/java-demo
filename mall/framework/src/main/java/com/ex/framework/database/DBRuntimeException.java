package com.ex.framework.database;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* @Author: lixubin
* @Date: 2019-07-22
* @Description: 数据库操作运行期异常
*/
public class DBRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -368646349014580765L;

    private static final Log logger = LogFactory
            .getLog(DBRuntimeException.class);

    public DBRuntimeException(String message){
        super(message);
    }

    public DBRuntimeException(Exception e, String sql){
        super("数据库运行期异常");
        e.printStackTrace();
        if (logger.isErrorEnabled()){
            logger.error("数据库运行期异常，相关sql语句为"+sql);
        }
    }

    public DBRuntimeException(String message,String sql) {
        super(message);
        if(logger.isErrorEnabled()){
            logger.error(message+"，相关sql语句为"+ sql);
        }
    }
}
