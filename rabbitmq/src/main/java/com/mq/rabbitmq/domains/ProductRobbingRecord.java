package com.mq.rabbitmq.domains;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 抢单记录表
 * </p>
 *
 * @author Ben
 * @since 2019-04-17
 */
@TableName("product_robbing_record")
public class ProductRobbingRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 产品Id
     */
    private Integer productId;

    /**
     * 抢单时间
     */
    private Date robbingTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Date getRobbingTime() {
        return robbingTime;
    }

    public void setRobbingTime(Date robbingTime) {
        this.robbingTime = robbingTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ProductRobbingRecord{" +
        "id=" + id +
        ", mobile=" + mobile +
        ", productId=" + productId +
        ", robbingTime=" + robbingTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
