package com.lxb.apibrowser.security.browser.authentication;

import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @Author: lixubin
* @Date: 2019-08-19
* @Description:
*/
public interface LoginSuccessHandler {

    void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                 Authentication authentication) throws IOException, ServletExce