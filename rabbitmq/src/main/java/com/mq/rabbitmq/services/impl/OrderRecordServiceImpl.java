package com.mq.rabbitmq.services.impl;

import com.mq.rabbitmq.domains.OrderRecord;
import com.mq.rabbitmq.mappers.OrderRecordMapper;
import com.mq.rabbitmq.services.OrderRecordService;
import com.mq.rabbitmq.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单记录表-业务级别 服务实现类
 * </p>
 *
 * @author Ben
 * @since 2019-04-17
 */
@Service
public class OrderRecordServiceImpl extends BaseServiceImpl<OrderRecord> implements OrderRecordService {

}
