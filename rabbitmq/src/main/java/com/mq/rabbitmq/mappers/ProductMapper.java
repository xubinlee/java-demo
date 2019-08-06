package com.mq.rabbitmq.mappers;

import com.mq.rabbitmq.domains.Product;
import com.mq.rabbitmq.mappers.base.BaseMapper;

/**
 * <p>
 * 商品信息表 Mapper 接口
 * </p>
 *
 * @author Ben
 * @since 2019-04-17
 */
public interface ProductMapper extends BaseMapper<Product> {
    int updateTotal(Product record);
}
