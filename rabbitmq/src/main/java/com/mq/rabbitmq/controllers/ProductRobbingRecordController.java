package com.mq.rabbitmq.controllers;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.mq.rabbitmq.controllers.base.BaseController;
import com.mq.rabbitmq.domains.ProductRobbingRecord;

/**
 * <p>
 * 抢单记录表 前端控制器
 * </p>
 *
 * @author Ben
 * @since 2019-04-17
 */
@RestController
@RequestMapping("/productRobbingRecord")
public class ProductRobbingRecordController extends BaseController<ProductRobbingRecord> {

}
