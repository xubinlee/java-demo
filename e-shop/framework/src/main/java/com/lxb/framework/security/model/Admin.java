package com.enation.app.javashop.framework.security.model;

/**
 * 管理员角色
 *
 * @author zh
 * @version v7.0
 * @date 18/6/27 上午10:09
 * @since v7.0
 */

public class Admin {
    /**
     * 管理员id
     */
    private Integer uid;
    /**
     * 管理员名称
     */
    private String username;
    /**
     * 是否是超级管理员
     */
    private Integer founder;
    /**
     * 权限
     */
    private String role;
    /**
     * uuid
     */
    private String uuid;


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

    public Integer getFounder() {
        return founder;
    }

    public void setFounder(Integer founder) {
        this.founder = founder;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", founder=" + founder +
                ", role='" + role + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
