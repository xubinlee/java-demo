package com.jta.sharding.domains;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ben
 * @since 2019-04-22
 */
@TableName("goods_1")
public class Goods1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long goodsId;

    private String goodsName;

    private Long goodsType;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public Long getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Long goodsType) {
        this.goodsType = goodsType;
    }

    @Override
    public String toString() {
        return "Goods1{" +
        "goodsId=" + goodsId +
        ", goodsName=" + goodsName +
        ", goodsType=" + goodsType +
        "}";
    }
}
