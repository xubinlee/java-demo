package com.lxb.apibrowser.security.app.social;

import com.lxb.apibrowser.security.app.exception.AppSecretException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * app环境下替换providerSignInUtils，避免由于没有session导致读不到社交用户信息的问题
 */
@Component
public class AppSingUpUtils {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	private UsersConnectionRepository usersConnectionRepository;

	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	/**
	 * 缓存社交网站用户信息到redis
	 * @param request
	 * @param connectionData
	 */
	public void saveConnectionData(WebRequest request, ConnectionData connectionData) {
		redisTemplate.opsForValue().set(getKey(request), connectionData, 30, TimeUnit.MINUTES);
	}

	/**
	 * 将缓存的社交网站用户信息与系统注册用户信息绑定
     * @param request
     * @param userId
     * @return
     */
	public ConnectionData doPostSignUp(WebRequest request, String userId) {
		String key = getKey(request);
		if(!redisTemplate.hasKey(key)){
			throw new AppSecretException("长时间未注册该链接已失效，请重新登录在绑定");
		}
		ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
		Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId())
				.createConnection(connectionData);
		usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);

		redisTemplate.delete(key);

		return connectionData;
	}

	/**
	 * 获取redis key
	 * @param request
	 * @return
	 */
	private String getKey(WebRequest request) {
		String deviceId = request.getHeader("deviceId");
		if (StringUtils.isBlank(deviceId)) {
			throw new AppSecretException("deviceId参数不能为空");
		}
		return "security:social.connect." + deviceId;
	}

	public void saveWxMiniConnection(WebRequest request, Map<String, Object> map) {
        redisTemplate.opsForValue().set(getKey(request), map, 30, TimeUnit.MINUTES);
    }

    public ConnectionData doPostSignUpForWxMini(WebRequest request, String userId) {
        String key = getKey(request);
        if(!redisTemplate.hasKey(key)){
            throw new AppSecretException("长时间未注册该链接已失效，请重新登录在绑定");
        }
        Map<String, Object> connection = (Map<String, Object>)redisTemplate.opsForValue().get(key);
        // todo

        return null;
    }

    public RedisTemplate<Object, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public UsersConnectionRepository getUsersConnectionRepository() {
        return usersConnectionRepository;
    }

    public ConnectionFactoryLocator getConnectionFactoryLocator() {
        return connectionFactoryLocator;
    }
}
