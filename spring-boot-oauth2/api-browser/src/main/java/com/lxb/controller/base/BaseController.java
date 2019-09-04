package com.lxb.apibrowser.controller.base;

import com.lxb.apibrowser.controller.base.method.SelectPage;
import com.lxb.apibrowser.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController<T> implements SelectPage<T> {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    private BaseService<T> service;

    @Override
    public BaseService<T> getBaseService() {
        return service;
    }
}
