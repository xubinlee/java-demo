package com.lxb.apibrowser.security.core.validate.code;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
* @Author: lixubin
* @Date: 2019-08-19
* @Description:
*/
public interface MobileUserDetailsService extends UserDetailsService {


    /**
     * 根据手机号登陆
     *
     * @param mobile
     * @return
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserByMobileNumber(String mobile) throws UsernameNotFoundException;
}
