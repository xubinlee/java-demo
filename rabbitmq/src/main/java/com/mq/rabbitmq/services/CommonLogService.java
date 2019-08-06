package com.mq.rabbitmq.services;

import com.mq.rabbitmq.dto.LogDto;

public interface CommonLogService {
    void insertLog(LogDto dto);
}
