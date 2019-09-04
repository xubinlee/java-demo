
package com.lxb.security;


import com.lxb.entity.User;
import com.lxb.service.UserService;
import com.lxb.security.core.validate.code.MobileUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
* @Author: lixubin
* @Date: 2019-08-19
* @Description: security oauth2和社交登陆用户服务
*/
@Service
public class UserDetailsServiceImpl implements SocialUserDetailsService, MobileUserDetailsService {

	@Autowired
    private UserService userService;


    /**
     * 用户名
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByLoginUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }

        return buildUserDetails(user);
	}

    /**
     * 用户id
     *
     * @param userId
     * @return
     * @throws UsernameNotFoundException
     */
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        User condition = new User();
        condition.setUserID(userId);
		return null;
	}

    /**
     * 手机号
     *
     * @param mobile
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByMobileNumber(String mobile) throws UsernameNotFoundException {
        User condition = new User();
        User user = userService.getByLoginUser(mobile);
        if (user == null) {
            throw new UsernameNotFoundException("无效手机号码");
        }
        return buildUserDetails(user);
    }


	private SocialUserDetails buildUserDetails(User user) {
        // 0 新建 1启用 2禁用
        boolean enable = !Objects.equals(user.getState(), 2);
        return new SocialUser(user.getLoginUser(), user.getPasword(),
                enable, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));

	}
}
