package com.lxb.entity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lxb.common.RandomUUID;
import com.lxb.common.dto.RegisterDTO;
import com.lxb.common.exceptions.BizException;
import com.lxb.common.exceptions.LxbException;
import com.lxb.service.AccountService;
import com.lxb.service.CustomerService;
import com.lxb.service.UserService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

public class Registry {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private RegisterDTO registerDTO;
    private User user;
    private Account account;
    private Customer customer;

    public Registry(RegisterDTO registerDTO) {
        this.registerDTO = registerDTO;
    }

    public User getUser() {
        return user;
    }

    public Account getAccount() {
        return account;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Registry doRegister() {
        checkUserExist();

        String uid = RandomUUID.get();
        String accountId = RandomUUID.get();
        String customerId = RandomUUID.get();

        String userName = registerDTO.getCompanyName();
        int companyType = 3;
        Date now = new Date();

        user = buildNewUser(registerDTO, uid, now);
        if (!userService.save(user)) {
            throw new LxbException("注册用户时，保存用户信息失败");
        }

        account = buildNewAccount(uid, accountId, customerId, userName, companyType, now);
        if (!accountService.save(account)) {
            throw new LxbException("注册用户时，保存账套信息失败");
        }

        customer = buildNewCustomer(uid, accountId, customerId, userName, companyType, now, user, account);
        if (!customerService.save(customer)) {
            throw new LxbException("注册用户时，保存客户信息失败");
        }
        return this;
    }

    private void checkUserExist() {
        LambdaQueryWrapper<User> userNameExist = Wrappers.<User>lambdaQuery()
                .eq(User::getPhone, registerDTO.getPhone()).or()
                .eq(User::getLoginUser, registerDTO.getPhone());
        if(userService.getOne(userNameExist) != null) {
            throw new BizException("该手机号已注册");
        }
    }

    private User buildNewUser(RegisterDTO registerDTO, String uid, Date now) {
        User user = new User();
        user.setUserID(uid);
        user.setLoginUser(registerDTO.getPhone());
        user.setAbleDate(now);
        user.setDisableDate(now);
        // 小规模
        user.setCompanyType("1");
        user.setCreatePsn(uid);
        user.setCreateDate(now);
        user.setUpdatePsn(uid);
        user.setUpdateDate(now);
        user.setUserType(6);
        user.setType(1);
        // 状态为新建
        user.setState(0);
        user.setPasword(passwordEncoder.encode(registerDTO.getPasword()));
        return user;
    }

    private Account buildNewAccount(String uid, String accountId, String customerId, String userName,
                                    int companyType, Date now) {
        Account account = new Account();
        account.setAccountID(accountId);
        account.setCustomID(customerId);
        account.setUserID(uid);
        account.setPeriod(now);
        account.setAccstandards(3);
        account.setCalculate("新会计准则");
        account.setUpdatepsnID(uid);
        account.setUpdatepsn(userName);
        account.setUpdatedate(now);
        account.setCreateDate(now);
        account.setCreatepsn(userName);
        account.setCreatepsnID(uid);
        account.setLastTime(now);
        account.setUseLastPeriod(DateFormatUtils.format(now, "yyyy-MM"));
        account.setCompanyType(companyType);
        account.setCompanyName(registerDTO.getCompanyName());
        account.setJzbwb(1);
        account.setStatu(0);
        account.setInitialStates(1);
        account.setMappingStates(1);
        account.setSsType(1);
        return account;
    }

    private Customer buildNewCustomer(String uid, String accountId, String customerId, String userName,
                                      int companyType, Date now, User user, Account account) {
        Customer customer = new Customer();
        customer.setAccountID(customerId);
        customer.setCustomID(customerId);
        customer.setCusPhone(user.getPhone());
        customer.setCusName(account.getCompanyName());
        customer.setBelongPersonID(uid);
        customer.setBelongPerName(userName);
        customer.setCreatePerName(userName);
        customer.setCreateDate(now);
        customer.setAccstandards(3);
        customer.setThreeAndOne(0);
        customer.setComGxjsqy(0);
        customer.setComKjxzxqy(0);
        customer.setComJsrgynssx(0);
        customer.setSsType(1);
        customer.setCompanyType(companyType);
        customer.setJzbwb(1);
        customer.setAccountID(accountId);
        return customer;
    }
}
