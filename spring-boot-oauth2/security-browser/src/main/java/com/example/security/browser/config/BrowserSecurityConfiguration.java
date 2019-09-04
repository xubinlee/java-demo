package com.example.securitybrowser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
* @Author: lixubin
* @Date: 2019-08-16
* @Description: 浏览器环境下安全配置主类
*/
@Component
public class BrowserSecurityConfiguration {
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private FormAuthenticationConfig

    public void configureWeb(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/**/favicon.ico",
                "/**/*.js",
                "/**/*.css",
                "/**/*.jpg",
                "/**/*.png",
                "/**/*.gif");
    }

    public void configureHttp(HttpSecurity http){
        forma
    }
}
