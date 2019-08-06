package com.mq.rabbitmq.services.impl;

import com.mq.rabbitmq.domains.Product;
import com.mq.rabbitmq.mappers.ProductMapper;
import com.mq.rabbitmq.services.ProductService;
import com.mq.rabbitmq.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author Ben
 * @since 2019-04-17
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

}
