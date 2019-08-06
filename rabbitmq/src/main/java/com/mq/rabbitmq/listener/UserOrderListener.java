package com.mq.rabbitmq.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mq.rabbitmq.services.ConcurrencyService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userOrderListener")
public class UserOrderListener implements ChannelAwareMessageListener {

    private static final Logger log= LoggerFactory.getLogger(UserOrderListener.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConcurrencyService concurrencyService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long tag=message.getMessageProperties().getDeliveryTag();
        try {
            byte[] body=message.getBody();
            String mobile= new String(body,"UTF-8");
            log.info("监听到抢单手机号：{}",mobile);

            concurrencyService.manageRobbing(mobile);
            channel.basicAck(tag,true);
        }catch (Exception ex){
            log.error("用户商城下单 发生异常：",ex.fillInStackTrace());
            channel.basicReject(tag,false);
        }
    }
}
