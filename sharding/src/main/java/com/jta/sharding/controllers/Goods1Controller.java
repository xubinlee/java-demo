package com.jta.sharding.controllers;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.jta.sharding.controllers.base.BaseController;
import com.jta.sharding.domains.Goods1;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ben
 * @since 2019-04-22
 */
@RestController
@RequestMapping("/goods1")
public class Goods1Controller extends BaseController<Goods1> {

}
