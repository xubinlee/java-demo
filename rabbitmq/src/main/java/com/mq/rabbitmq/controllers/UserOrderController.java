package com.mq.rabbitmq.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mq.rabbitmq.dto.UserOrderDto;
import com.mq.rabbitmq.services.CommonLogService;
import com.mq.rabbitmq.services.UserOrderService;
import com.mq.rabbitmq.supports.exceptions.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mq.rabbitmq.controllers.base.BaseController;
import com.mq.rabbitmq.domains.UserOrder;

/**
 * <p>
 * 用户订单表 前端控制器
 * </p>
 *
 * @author Ben
 * @since 2019-04-17
 */
@RestController
@RequestMapping("/userOrder")
public class UserOrderController extends BaseController<UserOrder> {

    private static final Logger log= LoggerFactory.getLogger(UserOrderController.class);

    private static final String Prefix="user/order";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment env;

    @Autowired
    private UserOrderService userOrderService;

    /**
     * 用户商城下单
     * @param dto
     * @return
     */
    @RequestMapping(value = Prefix+"/push/dead/queue",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void pushUserOrderV2(@RequestBody UserOrderDto dto){
        try {
            UserOrder userOrder = new UserOrder();
            BeanUtils.copyProperties(dto,userOrder);
            userOrder.setStatus(2);//1保存，2已支付，3已失效
            userOrderService.save(userOrder);

            Integer id = userOrder.getId();

            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(env.getProperty("user.order.dead.produce.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("user.order.dead.produce.routing.key.name"));

            rabbitTemplate.convertAndSend(id, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    MessageProperties properties = message.getMessageProperties();
                    properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    properties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,Integer.class);
                    return message;
                }
            });
        }catch (Exception e){
            throw new BizException("用户商城下单失败");
        }
    }
}
