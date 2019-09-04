
package com.wqb.security.core.properties;

/**
 * 浏览器环境配置项
 */
public class BrowserProperties {

    /**
     * session管理配置项
     */
    private SessionProperties session = new SessionProperties();
    /**
     * 登录url
     */
    private String signInUrl = SecurityConstants.DEFAULT_SIGN_IN_URL;

    /**
     * 登陆处理url
     */
    private String signInProcessUrl = SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM;

    /**
     * 登录成功后跳转的地址，如果设置了此属性，则登录成功后总是会跳到这个地址上。
     * 只在signInResponseType为REDIRECT时生效
     */
    private String singInSuccessUrl;

    /**
     * 退出处理url
     */
    private String signOutUrl = SecurityConstants.DEFAULT_SIGN_OUT_URL;

    /**
     * 退出url
     */
    private String signOutSuccessUrl;

    /**
     * 社交登录，如果需要用户注册，跳转的页面
     */
    private String signUpUrl = SecurityConstants.DEFAULT_CURRENT_SOCIAL_USER_INFO_URL;

    /**
     * '记住我'功能的有效时间，默认1小时
     */
    private int rememberMeSeconds = 3600;

    /**
     * 登录响应的方式，默认是json
     */
    private LoginResponseType signInResponseType = LoginResponseType.JSON;

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }

    public String getSignInUrl() {
        return signInUrl;
    }

    public void setSignInUrl(String signInUrl) {
        this.signInUrl = signInUrl;
    }

    public String getSignInProcessUrl() {
        return signInProcessUrl;
    }

    public void setSignInProcessUrl(String signInProcessUrl) {
        this.signInProcessUrl = signInProcessUrl;
    }

    public String getSingInSuccessUrl() {
        return singInSuccessUrl;
    }

    public void setSingInSuccessUrl(String singInSuccessUrl) {
        this.singInSuccessUrl = singInSuccessUrl;
    }

    public String getSignOutSuccessUrl() {
        return signOutSuccessUrl;
    }

    public void setSignOutSuccessUrl(String signOutSuccessUrl) {
        this.signOutSuccessUrl = signOutSuccessUrl;
    }

    public String getSignOutUrl() {
        return signOutUrl;
    }

    public void setSignOutUrl(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public LoginResponseType getSignInResponseType() {
        return signInResponseType;
    }

    public void setSignInResponseType(LoginResponseType signInResponseType) {
        this.signInResponseType = signInResponseType;
    }
}
