
package com.wqb.security.app.config;

import com.wqb.security.app.config.support.AppAccessDeniedHandler;
import com.wqb.security.app.config.support.AppOAuth2AuthenticationExceptionEntryPoint;
import com.wqb.security.app.authentication.ClientAuthenticationFilter;
import com.wqb.security.core.properties.OAuth2ClientProperties;
import com.wqb.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenStore tokenStore;

	@Autowired(required = false)
	private JwtAccessTokenConverter jwtAccessTokenConverter;

	@Autowired(required = false)
	private TokenEnhancer jwtTokenEnhancer;

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired

    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientAuthenticationFilter clientAuthenticationFilter;

    @Autowired
    private AppAccessDeniedHandler appAccessDeniedHandler;

    @Autowired
    private AppOAuth2AuthenticationExceptionEntryPoint appOAuth2AuthenticationExceptionEntryPoint;

	/**
	 * 认证及token配置
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore)
				.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);

		if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            jwtAccessTokenConverter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());

            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);
            enhancerChain.setTokenEnhancers(enhancers);

			endpoints
                    .tokenEnhancer(enhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
		}

	}

	/**
	 * tokenKey的访问权限表达式配置
	 */
	@Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("isAuthenticated()")
                .accessDeniedHandler(appAccessDeniedHandler)
                .authenticationEntryPoint(appOAuth2AuthenticationExceptionEntryPoint)
                .addTokenEndpointAuthenticationFilter(clientAuthenticationFilter);
	}

	/**
	 * 客户端配置
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		InMemoryClientDetailsServiceBuilder builder = clients.inMemory();

		if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
			for (OAuth2ClientProperties client : securityProperties.getOauth2().getClients()) {
				builder.withClient(client.getClientId())
						.secret(passwordEncoder.encode(client.getClientSecret()))
						.authorizedGrantTypes("password", "authorization_code", "refresh_token", "client_credentials")
						.accessTokenValiditySeconds(client.getAccessTokenValidateSeconds())
						.refreshTokenValiditySeconds(client.getRefreshTokenValidateSeconds())
                        .autoApprove(true)
                        .scopes("all");
			}
		}
	}

}
