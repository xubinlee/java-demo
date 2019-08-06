package com.mq.rabbitmq.services.impl;

import com.mq.rabbitmq.services.CommonMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CommonMqServiceImpl implements CommonMqService {

    private static final Logger log= LoggerFactory.getLogger(CommonMqServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    /**
     * 发送抢单信息入队列
     * @param mobile
     */
    @Override
    public void sendRobbingMsgV2(String mobile) {
        try {
            rabbitTemplate.setExchange(env.getProperty("user.order.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("user.order.routing.key.name"));
            Message message= MessageBuilder.withBody(mobile.getBytes("UTF-8"))
                    .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                    .build();
            rabbitTemplate.send(message);
        }catch (Exception e){
            log.error("发送抢单信息入队列V2 发生异常： mobile={} ",mobile);
        }
    }
}
