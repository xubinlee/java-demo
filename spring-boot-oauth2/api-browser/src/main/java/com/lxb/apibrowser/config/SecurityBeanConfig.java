
package com.lxb.config;

import com.lxb.security.Md5PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 *
 * 认证相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 * 模块默认的配置。
 */
@Configuration
public class SecurityBeanConfig {

    @Autowired
    private OAuth2ClientContext oAuth2ClientContext;

    @Autowired
    private OAuth2ProtectedResourceDetails  resourceDetails;

	/**
	 * 默认密码处理器
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();
	}


    @Bean
    public OAuth2RestTemplate restOperations() {
        return new OAuth2RestTemplate(resourceDetails, oAuth2ClientContext);
    }
}
