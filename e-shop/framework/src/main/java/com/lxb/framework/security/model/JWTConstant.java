package com.enation.app.javashop.framework.security.model;
/**
 * 定义JWT基本常量
 * @author zh
 * @version v7.0
 * @date 18/4/12 下午2:46
 * @since v7.0
 */
public class JWTConstant {
    /** 10 days */
    public static final String SECRET = "ThisIsASecret";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    /**时间戳失效时间，单位：秒*/
    public static final int INVALID_TIME = 60;
}
