package com.mq.rabbitmq.services.impl;

import com.mq.rabbitmq.dto.LogDto;
import com.mq.rabbitmq.services.CommonLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonLogServiceImpl implements CommonLogService {

    private static final Logger log= LoggerFactory.getLogger(CommonLogService.class);

    /**
     * 通用的写日志服务逻辑
     */
    @Override
    public void insertLog(LogDto dto) {
        log.info("通用的写日志服务逻辑 数据；{} ",dto);

    }
}
