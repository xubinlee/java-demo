package com.ex.oauth2.service;

import com.ex.oauth2.NewUserNotFoundException;
import com.ex.oauth2.entity.Authority;
import com.ex.oauth2.entity.User;
import com.ex.oauth2.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserJPA userJPA;

    @Override
    public UserDetails loadUserByUsername(String login) {
        String lowerCaseLogin = login.toLowerCase();
        User userFormDatabase = userJPA.findByUsernameCaseInsensitive(lowerCaseLogin);
        if (userFormDatabase==null){
            throw new NewUserNotFoundException("User " + lowerCaseLogin + " was not found in the database");
        }
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : userFormDatabase.getAuthorities()) {
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User(
                userFormDatabase.getUsername(),
                userFormDatabase.getPassword(),
                grantedAuthorities);
    }
}
