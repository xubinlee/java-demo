package com.mq.rabbitmq.services.impl;

import com.mq.rabbitmq.domains.Product;
import com.mq.rabbitmq.domains.ProductRobbingRecord;
import com.mq.rabbitmq.mappers.ProductMapper;
import com.mq.rabbitmq.services.ConcurrencyService;
import com.mq.rabbitmq.services.ProductRobbingRecordService;
import com.mq.rabbitmq.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ConcurrencyServiceImpl implements ConcurrencyService {

    private static final Logger log= LoggerFactory.getLogger(ConcurrencyServiceImpl.class);

    private static final Integer ProductId=1;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    private AtomicInteger atomicInteger = new AtomicInteger(10);//原子类型，线程安全处理

    @Autowired
    private ProductRobbingRecordService productRobbingRecordService;
    /**
     * 处理抢单
     * @param mobile
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Async
    public void manageRobbing(String mobile) {
        int i = atomicInteger.decrementAndGet();
        if (i >= 0) {
            System.out.println("第" + i + "次");
            try {
                Product product = productService.getById(ProductId);
                if (product != null) {
                    //库存量减一
//                                    int result = productMapper.updateTotal(product);
                    product.setTotal(i);
//                    Product condition = new Product();
//                    condition.setTotal(product.getTotal());
//                    condition.setId(product.getId());
                    boolean update = productService.updateById(product);

                    if (update) {
                        //添加抢单记录
                        ProductRobbingRecord productRobbingRecord = new ProductRobbingRecord();
                        productRobbingRecord.setMobile(mobile);
                        productRobbingRecord.setProductId(product.getId());
                        productRobbingRecordService.save(productRobbingRecord);
                    } else {
                        log.error("抢单失败：mobile={} ", mobile);
                    }

                }
            } catch (Exception ex) {
                log.error("处理抢单发生异常：mobile={} ", mobile);
                //            throw new WqbException(String.format("处理抢单发生异常：mobile=%s ",mobile));
            }
        }




    }
}
