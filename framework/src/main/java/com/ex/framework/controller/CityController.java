package com.ex.framework.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ex.framework.controller.base.Query;
import com.ex.framework.service.CityService;
import com.ex.framework.support.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ex.framework.controller.base.BaseController;
import com.ex.framework.entity.City;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxb
 * @since 2019-05-28
 */
@RestController
@RequestMapping("/city")
public class CityController extends BaseController<City> {

}
