package com.lxb.apibrowser.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
* @Author: lixubin
* @Date: 2019-08-20
* @Description:
*/
public class Md5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return DigestUtils.md5Hex(charSequence.toString()).toUpperCase();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);