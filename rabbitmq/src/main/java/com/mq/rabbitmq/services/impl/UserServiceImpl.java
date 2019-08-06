package com.mq.rabbitmq.services.impl;

import com.mq.rabbitmq.domains.User;
import com.mq.rabbitmq.mappers.UserMapper;
import com.mq.rabbitmq.services.UserService;
import com.mq.rabbitmq.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Ben
 * @since 2019-04-17
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

}
