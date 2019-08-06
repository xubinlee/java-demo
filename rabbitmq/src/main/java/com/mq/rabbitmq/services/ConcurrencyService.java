package com.mq.rabbitmq.services;

public interface ConcurrencyService {

    /**
     * 处理抢单
     * @param mobile
     */
    void manageRobbing(String mobile);
}
