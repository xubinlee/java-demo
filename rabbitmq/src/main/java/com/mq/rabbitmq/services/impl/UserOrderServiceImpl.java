package com.mq.rabbitmq.services.impl;

import com.mq.rabbitmq.domains.UserOrder;
import com.mq.rabbitmq.mappers.UserOrderMapper;
import com.mq.rabbitmq.services.UserOrderService;
import com.mq.rabbitmq.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户订单表 服务实现类
 * </p>
 *
 * @author Ben
 * @since 2019-04-17
 */
@Service
public class UserOrderServiceImpl extends BaseServiceImpl<UserOrder> implements UserOrderService {

}
