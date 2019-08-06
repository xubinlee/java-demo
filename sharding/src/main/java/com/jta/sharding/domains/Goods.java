package com.jta.sharding.domains;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.data.annotation.Id;

/**
 * @author yangyang
 * @date 2019/1/29
 */
@TableName("goods")
public class Goods {
    @TableId(type = IdType.AUTO)
    private Long goodsId;

    private String goodsName;

    private Long goodsType;
}
