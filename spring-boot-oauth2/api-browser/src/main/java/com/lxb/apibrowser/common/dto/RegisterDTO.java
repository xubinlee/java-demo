package com.lxb.common.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
* @Author: lixubin
* @Date: 2019-08-20
* @Description:
*/
public class RegisterDTO implements Serializable {

    @NotBlank(message = "手机号不能为空")
    private String phone;

    @NotBlank(message = "密码不能为空")
    private String pasword;

    @NotBlank(message = "公司名不能为空")
    private String companyName;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "phone='" + phone + '\'' +
                ", pasword='" + pasword + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
