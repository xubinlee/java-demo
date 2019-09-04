
package com.lxb.apibrowser.security.app.config;

import com.lxb.apibrowser.security.app.authentication.openid.OpenIdAuthenticationSecurityConfig;
import com.lxb.apibrowser.security.app.authentication.wxmini.WxMiniAuthenticationSecurityConfig;
import com.lxb.apibrowser.security.core.authentication.configurer.AuthorizationConfigurerManager;
import com.lxb.apibrowser.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.lxb.apibrowser.security.core.validate.code.ValidateCodeSecurityConfig;
import com.lxb.apibrowser.security.app.config.support.AppAccessDeniedHandler;
import com.lxb.apibrowser.security.app.config.support.AppOAuth2AuthenticationExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 资源服务器配置
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer socialSecurityConfig;

    @Autowired
    private WxMiniAuthenticationSecurityConfig wxMiniAuthenticationSecurityConfig;

    @Autowired
    private AuthorizationConfigurerManager authorizationConfigurerManager;

    @Autowired
    private AppAccessDeniedHandler appAccessDeniedHandler;

    @Autowired
    private AppOAuth2AuthenticationExceptionEntryPoint appOAuth2AuthenticationExceptionEntryPoint;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .authenticationEntryPoint(appOAuth2AuthenticationExceptionEntryPoint)
                .accessDeniedHandler(appAccessDeniedHandler);
        super.configure(resources);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .authenticationEntryPoint(appOAuth2AuthenticationExceptionEntryPoint)
                .accessDeniedHandler(appAccessDeniedHandler)
                .and()
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(wxMiniAuthenticationSecurityConfig)
                .and()
                .apply(socialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .csrf().disable();

        authorizationConfigurerManager.config(http.authorizeRequests());
    }
}
