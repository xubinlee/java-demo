
package com.wqb.security.core.properties;


import com.wqb.security.core.social.support.ConnectProperties;

/**
 * QQ登录配置项
 */
public class QQProperties extends ConnectProperties {

	/**
	 * 第三方id，用来决定发起第三方登录的url，默认是 qq。
	 */
	private String providerId = "qq";
}
