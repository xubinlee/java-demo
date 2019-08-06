package com.mq.rabbitmq.controllers;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.mq.rabbitmq.controllers.base.BaseController;
import com.mq.rabbitmq.domains.UserLog;

/**
 * <p>
 * 用户操作日志 前端控制器
 * </p>
 *
 * @author Ben
 * @since 2019-04-17
 */
@RestController
@RequestMapping("/userLog")
public class UserLogController extends BaseController<UserLog> {

}
