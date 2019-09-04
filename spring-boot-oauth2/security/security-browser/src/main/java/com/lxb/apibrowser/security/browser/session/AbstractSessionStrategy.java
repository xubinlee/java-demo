
package com.lxb.security.browser.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxb.security.core.properties.SecurityProperties;
import com.lxb.security.core.support.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @Author: lixubin
* @Date: 2019-08-19
* @Description: 抽象的session失效处理器
*/
public class AbstractSessionStrategy {
	/**
	 * 跳转的url
	 */
	private String destinationUrl;
	/**
	 * 系统配置信息
	 */
	private SecurityProperties securityPropertie;
	/**
	 * 重定向策略
	 */
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	/**
	 * 跳转前是否创建新的session
	 */
	private boolean createNewSession = true;

	private ObjectMapper objectMapper = new ObjectMapper();


	public AbstractSessionStrategy(SecurityProperties securityPropertie) {
		String invalidSessionUrl = securityPropertie.getBrowser().getSession().getSessionInvalidUrl();
		Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
		this.destinationUrl = invalidSessionUrl;
		this.securityPropertie = securityPropertie;
	}

	protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (createNewSession) {
			request.getSession();
		}

		String sourceUrl = request.getRequestURI();
		String targetUrl;

        if(StringUtils.equals(sourceUrl, securityPropertie.getBrowser().getSignInUrl())
                || StringUtils.equals(sourceUrl, securityPropertie.getBrowser().getSignOutSuccessUrl())){
            targetUrl = sourceUrl;
        }else{
            targetUrl = destinationUrl;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/**
	 * @param request
	 * @return
	 */
	protected Object buildResponseContent(HttpServletRequest request) {
		String message = "session已失效";
		if (isConcurrency()) {
			message = message + "，有可能是并发登录导致的";
		}
		return new Response()
                .setSuccess(false)
                .setStatus(HttpStatus.UNAUTHORIZED.value())
                .setMessage(message);
	}

	/**
	 * session失效是否是并发导致的
	 *
	 * @return
	 */
	protected boolean isConcurrency() {
		return false;
	}


	public void setCreateNewSession(boolean createNewSession) {
		this.createNewSession = createNewSession;
	}

}
