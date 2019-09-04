package com.wqb.security;

import com.wqb.security.browser.config.BrowserSecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Shoven
 * @since 2019-05-09 15:40
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BrowserSecurityConfiguration browserSecurityConfiguration;

    @Override
    public void configure(WebSecurity web) throws Exception {
        browserSecurityConfiguration.configureWeb(web);
        web.ignoring().antMatchers(
                "/js/**",
                "/css/**",
                "/files/**",
                "/img/**",
                "/plugins");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        browserSecurityConfiguration.configureHttp(http);
    }
}
