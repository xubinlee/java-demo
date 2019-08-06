package com.mq.rabbitmq.listener;

import com.mq.rabbitmq.domains.OrderRecord;
import com.mq.rabbitmq.listener.event.PushOrderRecordEvent;
import com.mq.rabbitmq.services.OrderRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
* @Author: Ben
* @Date: 2019-04-19
* @Description: 这就是监听器-跟RabbitMQ的Listener几乎是一个理念
*/
@Component
public class PushOrderRecordListener implements ApplicationListener<PushOrderRecordEvent> {

    private static final Logger log= LoggerFactory.getLogger(PushOrderRecordListener.class);

    @Autowired
    private OrderRecordService orderRecordService;

    @Override
    public void onApplicationEvent(PushOrderRecordEvent event) {
        log.info("监听到的下单记录： {} ",event);

        try {
            if (event!=null){
                OrderRecord orderRecord = new OrderRecord();
                BeanUtils.copyProperties(event,orderRecord);
                orderRecordService.save(orderRecord);
            }
        }catch (Exception e){
            log.error("监听下单记录发生异常：{} ",event,e.fillInStackTrace());
        }
    }
}
