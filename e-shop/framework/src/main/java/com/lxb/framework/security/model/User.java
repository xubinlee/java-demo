package com.enation.app.javashop.framework.security.model;

import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户
 * Created by kingapex on 2018/3/8.
 *
 * @author kingapex
 * @version 1.0
 * @since 6.4.0
 * 2018/3/8
 */
public class User {

    /**
     * 会员id
     */
    private Integer uid;

    /**
     * 唯一标识
     */
    private String uuid;

    /**
     * 用户名
     */
    private String username;
    /**
     * 角色
     */
    private List<Role> roles;

    public User() {

        roles = new ArrayList<>();
        this.test(String.class);
    }

    /**
     * 为用户定义角色
     *
     * @param roles 角色集合
     */
    protected void add(Role... roles) {
        for (Role role : roles) {
            this.roles.add(role);
        }
    }

    void test(Class tClass) {


    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
