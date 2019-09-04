
package com.lxb.security.browser.session;

import com.lxb.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @Author: lixubin
* @Date: 2019-08-19
* @Description: 默认的session失效处理策略
*/
public class BrowserInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

	public BrowserInvalidSessionStrategy(SecurityProperties securityProperties) {
		super(securityProperties);
	}

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		onSessionInvalid(request, response);
	}

}
