package com.mq.rabbitmq.controllers;


import com.mq.rabbitmq.listener.event.PushOrderRecordEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mq.rabbitmq.controllers.base.BaseController;
import com.mq.rabbitmq.domains.OrderRecord;

/**
 * <p>
 * 订单记录表-业务级别 前端控制器
 * </p>
 *
 * @author Ben
 * @since 2019-04-17
 */
@RestController
@RequestMapping("/orderRecord")
public class OrderRecordController extends BaseController<OrderRecord> {

    private static final Logger log= LoggerFactory.getLogger(OrderRecordController.class);

    private static final String Prefix="order";

    //TODO：类似于RabbitTemplate
    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * 下单
     * @return
     */
    @RequestMapping(value = Prefix+"/push",method = RequestMethod.GET)
    public void pushOrder(){
        try {
            PushOrderRecordEvent event=new PushOrderRecordEvent(this,"orderNo_20190419001","物流12");
            publisher.publishEvent(event);
        }catch (Exception e){
            log.error("下单发生异常：",e.fillInStackTrace());
        }
    }
}
