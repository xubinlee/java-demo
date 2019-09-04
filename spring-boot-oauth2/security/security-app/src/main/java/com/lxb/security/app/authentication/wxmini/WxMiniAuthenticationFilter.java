package com.lxb.apibrowser.security.app.authentication.wxmini;

import com.lxb.apibrowser.security.core.authentication.InvalidArgumentException;
import com.lxb.apibrowser.security.core.properties.SecurityConstants;
import com.lxb.apibrowser.security.core.properties.SecurityProperties;
import com.lxb.apibrowser.security.core.properties.WeixinProperties;
import com.lxb.apibrowser.security.app.social.AppSingUpUtils;
import com.lxb.apibrowser.security.core.social.MyJdbcConnectionRepository;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.security.SocialAuthenticationRedirectException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.FormMapHttpMessageConverter;
import org.springframework.social.support.LoggingErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @Author: lixubin
* @Date: 2019-08-20
* @Description:
*/
public class WxMiniAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private boolean postOnly = true;

    private String providerId;

    private String filterProcessesUrl;

    private String appId;

    private String appSecret;

    private RestTemplate restTemplate;

    private static final String BASE_URL = "https://api.weixin.qq.com/sns/jscode2session";

    private ObjectMapper objectMapper = new ObjectMapper();

    private AppSingUpUtils appSingUpUtils;

    public WxMiniAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login/connect/wxmini", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        if (postOnly && !"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        Map<String, Object> result = getResponseOfLoginUser(request);

        String openId = MapUtils.getString(result, "openid");
        String sessionKey = MapUtils.getString(result, "session_key");
        try {
            WxMiniAuthenticationToken authenticationToken = new WxMiniAuthenticationToken(openId, providerId);
            authenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
            Authentication success = this.getAuthenticationManager().authenticate(authenticationToken);

            SocialUserDetails user = (SocialUserDetails) success.getPrincipal();
            ConnectionRepository connectionRepository = appSingUpUtils.getUsersConnectionRepository()
                    .createConnectionRepository(user.getUserId());

            ConnectionData connectionData = new ConnectionData(providerId, openId,
                    null, null, null, null, sessionKey, null, null);

            MyJdbcConnectionRepository repository = (MyJdbcConnectionRepository) connectionRepository;
            repository.updateConnection(connectionData);
            return success;
        } catch (AuthenticationException e) {
            result.remove("errcode");
            result.remove("errmsg");
            appSingUpUtils.saveWxMiniConnection(new ServletWebRequest(request), result);
            throw new SocialAuthenticationRedirectException(SecurityConstants.DEFAULT_CURRENT_SOCIAL_USER_INFO_URL);
        }
    }

    private Map<String, Object> getResponseOfLoginUser(HttpServletRequest request) {
        // 获取小程序code
        String code = request.getParameter("code");

        if (StringUtils.isBlank(code)) {
            throw new InvalidArgumentException("参数错误，code不能为空");
        }

        String code2SessionUrl = new StringBuilder(BASE_URL)
                .append("?appid="+ appId)
                .append("&secret="+ appSecret)
                .append("&js_code="+code)
                .append("&grant_type=authorization_code")
                .toString();

        String rsp = getRestTemplate().getForObject(code2SessionUrl, String.class);

        Map<String, Object> result;
        try {
            result = objectMapper.readValue(rsp, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        //返回错误码时直接返回空
        String errCode = MapUtils.getString(result, "errcode");
        if(StringUtils.isNotBlank(errCode) && !"0".equals(errCode)){
            String errMsg = MapUtils.getString(result, "errmsg");
            throw new InvalidArgumentException("获取weixin access token失败, errcode:" + errCode + ", errmsg:" + errMsg);
        }

        return result;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        WeixinProperties weixin = securityProperties.getSocial().getWeixin();
        appId = weixin.getAppId();
        appSecret = weixin.getAppSecret();
        providerId = weixin.getWxMiniProviderId();
        setFilterProcessesUrl(weixin.getWxMiniProcessUrl());
    }

    @Override
    public void setFilterProcessesUrl(String filterProcessUrl) {
        this.filterProcessesUrl = filterProcessUrl;
        super.setFilterProcessesUrl(filterProcessesUrl);
    }

    public void setAppSingUpUtils(AppSingUpUtils appSingUpUtils) {
        this.appSingUpUtils = appSingUpUtils;
    }

    private RestTemplate getRestTemplate() {
        if (this.restTemplate == null) {
            this.restTemplate = this.createRestTemplate();
        }

        return this.restTemplate;
    }

    private RestTemplate createRestTemplate() {
        ClientHttpRequestFactory requestFactory = ClientHttpRequestFactorySelector.getRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        List<HttpMessageConverter<?>> converters = new ArrayList(2);
        converters.add(new FormHttpMessageConverter());
        converters.add(new FormMapHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        restTemplate.setErrorHandler(new LoggingErrorHandler());

        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}
