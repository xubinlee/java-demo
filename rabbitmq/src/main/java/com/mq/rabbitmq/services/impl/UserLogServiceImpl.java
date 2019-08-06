package com.mq.rabbitmq.services.impl;

import com.mq.rabbitmq.domains.UserLog;
import com.mq.rabbitmq.mappers.UserLogMapper;
import com.mq.rabbitmq.services.UserLogService;
import com.mq.rabbitmq.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户操作日志 服务实现类
 * </p>
 *
 * @author Ben
 * @since 2019-04-17
 */
@Service
public class UserLogServiceImpl extends BaseServiceImpl<UserLog> implements UserLogService {

}
