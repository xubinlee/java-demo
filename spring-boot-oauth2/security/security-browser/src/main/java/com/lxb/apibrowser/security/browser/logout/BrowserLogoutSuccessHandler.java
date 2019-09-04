
package com.lxb.apibrowser.security.browser.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxb.apibrowser.security.core.support.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: lixubin
 * @Date: 2019-08-19
 * @Description: 默认的退出成功处理器，如果设置了app.security.browser.signOutUrl，则跳到配置的地址上，
 * 如果没配置，则返回json格式的响应。
 */
public class BrowserLogoutSuccessHandler implements LogoutSuccessHandler {

    public BrowserLogoutSuccessHandler(String signOutSuccessUrl) {
        this.signOutSuccessUrl = signOutSuccessUrl;
    }

    private String signOutSuccessUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        if (StringUtils.isBlank(signOutSuccessUrl)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new Response("退出成功")));
        } else {
            response.sendRedirect(signOutSuccessUrl);
   