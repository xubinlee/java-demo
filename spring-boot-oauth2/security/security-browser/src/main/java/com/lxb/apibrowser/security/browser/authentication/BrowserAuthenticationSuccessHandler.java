
package com.lxb.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxb.security.core.properties.LoginResponseType;
import com.lxb.security.core.properties.SecurityProperties;
import com.lxb.security.core.support.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @Author: lixubin
* @Date: 2019-08-19
* @Description: 浏览器环境下登录成功的处理器
*/
@Component
public class BrowserAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties securityProperties;

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Autowired(required = false)
    private LoginSuccessHandler loginSuccessHandler;

	/**
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(HttpServletRequest, HttpServletResponse, Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        loginSuccessHandler.onAuthenticationSuccess(request, response, authentication);

		if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getSignInResponseType())) {
            Response result = new Response(authentication.getClass().getSimpleName());

			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(objectMapper.writeValueAsString(result));
		} else {
			// 如果设置了app.security.browser.singInSuccessUrl，总是跳到设置的地址上
			// 如果没设置，则尝试跳转到登录之前访问的地址上，如果登录前访问地址为空，则跳到网站根路径上
			if (StringUtils.isNotBlank(securityProperties.getBrowser().getSingInSuccessUrl())) {
				requestCache.removeRequest(request, response);
				setAlwaysUseDefaultTargetUrl(true);
				setDefaultTargetUrl(securityProperties.getBrowser().getSingInSuccessUrl());
			}
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
