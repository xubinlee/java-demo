package com.mq.rabbitmq.listener;

import com.mq.rabbitmq.domains.UserOrder;
import com.mq.rabbitmq.services.ConcurrencyService;
import com.mq.rabbitmq.services.UserOrderService;
import com.mq.rabbitmq.supports.exceptions.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Date;

@Component
public class UserOrderDeadListener {

    private static final Logger log= LoggerFactory.getLogger(UserOrderDeadListener.class);

    @Autowired
    private UserOrderService userOrderService;

    @Autowired
    private ConcurrencyService concurrencyService;

    @Transactional(rollbackFor = Exception.class)
    @RabbitListener(queues = "${user.order.dead.real.queue.name}",containerFactory = "multiListenerContainer")
    public void consumeMessage(@Payload Integer id){
        try {
            log.info("死信队列-用户下单超时未支付监听消息： {} ",id);
            UserOrder userOrder = new UserOrder();
            userOrder.setId(id);
            userOrder.setStatus(1);
            UserOrder order = userOrderService.getOne(userOrder);
            if (order!=null){
                order.setStatus(3);
                order.setUpdateTime(new Date());
                userOrderService.updateById(order);
            }else{
                //TODO：已支付-可能需要异步 减库存-异步发送其他日志消息
                log.info("死信队列-用户下单支付成功啦： {} ",id);
                concurrencyService.manageRobbing(String.valueOf(id));
            }
        }catch (Exception e){
            throw new BizException("用户商城下单支付异常");
        }
    }
}
