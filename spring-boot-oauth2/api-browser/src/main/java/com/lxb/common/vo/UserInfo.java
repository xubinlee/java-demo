package com.lxb.apibrowser.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
* @Author: lixubin
* @Date: 2019-08-20
* @Description:
*/
public class UserInfo implements Serializable {

    @JsonIgnore
    private String userId;

    private String userName;

    private String nickname;

    private String idCard;

    private String type;

    private String phone;

    private String email;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date registerTime;

    private String companyName;

    private String companyType;

    private String companyAddress;

    private String businessType;

    private String bankName;

    private String bankAccount;

    private String accountPeriod;

    private String accountingStandard;

    private String accountingBaseCurrency;

    private String unifiedCreditCode;

    private String realNameAccount;

    private String realNameCode;

    private String nationalTaxCode;

    private String localTaxCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(String accountPeriod) {
        this.accountPeriod = accountPeriod;
    }

    public String getAccountingStandard() {
        return accountingStandard;
    }

    public void setAccountingStandard(String accountingStandard) {
        this.accountingStandard = accountingStandard;
    }

    public String getAccountingBaseCurrency() {
        return accountingBaseCurrency;
    }

    public void setAccountingBaseCurrency(String accountingBaseCurrency) {
        this.accountingBaseCurrency = accountingBaseCurrency;
    }

    public String getUnifiedCreditCode() {
        return unifiedCreditCode;
    }

    public void setUnifiedCreditCode(String unifiedCreditCode) {
        this.unifiedCreditCode = unifiedCreditCode;
    }

    public String getRealNameAccount() {
        return realNameAccount;
    }

    public void setRealNameAccount(String realNameAccount) {
        this.realNameAccount = realNameAccount;
    }

    public String getRealNameCode() {
        return realNameCode;
    }

    public void setRealNameCode(String realNameCode) {
        this.realNameCode = realNameCode;
    }

    public String getNationalTaxCode() {
        return nationalTaxCode;
    }

    public void setNationalTaxCode(String nationalTaxCode) {
        this.nationalTaxCode = nationalTaxCode;
    }

    public String getLocalTaxCode() {
        return localTaxCode;
    }

    public void setLocalTaxCode(String localTaxCode) {
        this.localTaxCode = localTaxCode;
    }
}
