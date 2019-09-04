
package com.lxb.security.app.authentication.wxmini;

import com.lxb.security.app.social.AppSingUpUtils;
import com.lxb.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;


@Component
public class WxMiniAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private SocialUserDetailsService userDetailsService;

	@Autowired
    private AppSingUpUtils appSingUpUtils;

	@Autowired
    private SecurityProperties securityProperties;

	@Override
	public void configure(HttpSecurity http) throws Exception {

        WxMiniAuthenticationFilter wxMiniAuthenticationFilter = new WxMiniAuthenticationFilter();
        wxMiniAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        wxMiniAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        wxMiniAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        wxMiniAuthenticationFilter.setAppSingUpUtils(appSingUpUtils);
        wxMiniAuthenticationFilter.setSecurityProperties(securityProperties);

        WxMiniAuthenticationProvider wxMiniAuthenticationProvider = new WxMiniAuthenticationProvider();
        wxMiniAuthenticationProvider.setUserDetailsService(userDetailsService);
        wxMiniAuthenticationProvider.setUsersConnectionRepository(appSingUpUtils.getUsersConnectionRepository());

		http.authenticationProvider(wxMiniAuthenticationProvider)
			.addFilterAfter(wxMiniAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	}

}
