package com.ex.mallcore.goods.service;

import com.ex.mallcore.goods.model.dos.GoodsDO;

import java.util.List;

public interface GoodsQueryManager {
    /**
     * 查询全部商品
     */
    List<GoodsDO> getGoodsAll();

    /**
     * 添加商品
     */
    void insertGoods(GoodsDO goodsDO);
}
