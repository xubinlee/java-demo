package com.mq.rabbitmq.controllers;

import com.mq.rabbitmq.services.InitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConcurrencyController {
    private static final Logger log= LoggerFactory.getLogger(ConcurrencyController.class);

    private static final String Prefix="concurrency";

    @Autowired
    private InitService initService;

    @RequestMapping(value = Prefix+"/robbing/thread",method = RequestMethod.GET)
    public void robbingThread(){
        initService.generateMultiThread();
    }
}
