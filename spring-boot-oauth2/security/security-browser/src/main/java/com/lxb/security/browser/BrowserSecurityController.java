
package com.lxb.apibrowser.security.browser;


import com.lxb.apibrowser.security.core.properties.SecurityConstants;
import com.lxb.apibrowser.security.core.social.SocialController;
import com.lxb.apibrowser.security.core.social.support.SocialUserInfo;
import com.lxb.apibrowser.security.core.support.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
* @Author: lixubin
* @Date: 2019-08-22
* @Description: 浏览器环境下与安全相关的服务
*/
@RestController
public class BrowserSecurityController extends SocialController {

	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	/**
	 * 需要引导用户注册或绑定时，通过此服务获取当前社交用户的信息
	 * 返回401（表示认证失败，第一次登陆）和用户信息
     *
	 * @param request
	 * @return
	 */
	@GetMapping(SecurityConstants.DEFAULT_CURRENT_SOCIAL_USER_INFO_URL)
	public ResponseEntity getSocialUserInfo(HttpServletRequest request) {
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        SocialUserInfo socialUserInfo = buildSocialUserInfo(connection);
        Response<SocialUserInfo> response = new Response<SocialUserInfo>()
                .setSuccess(false)
                .setStatus(HttpStatus.UNAUTHORIZED.value())
                .setMessage("第一次登录需要绑定账号")
                .setData(socialUserInfo);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}
}
