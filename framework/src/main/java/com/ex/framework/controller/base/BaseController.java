package com.ex.framework.controller.base;

import com.ex.framework.controller.base.method.SelectPage;
import com.ex.framework.service.base.BaseService;
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
