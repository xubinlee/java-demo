package com.ex.mallcore.goodssearch.service;

import com.ex.framework.database.Page;
import com.ex.mallcore.goodssearch.model.GoodsSearchDTO;
import com.ex.mallcore.goodssearch.model.GoodsWords;

import java.util.List;
import java.util.Map;

public interface GoodsSearchManager {
    /**
     * 搜索
     *
     * @param goodsSearch 搜索条件
     * @return 商品分页
     */
    Page search(GoodsSearchDTO goodsSearch);

    /**
     * 获取筛选器
     *
     * @param goodsSearch 搜索条件
     * @return Map
     */
    Map<String, Object> getSelector(GoodsSearchDTO goodsSearch);

    /**
     * 通过关键字获取商品分词索引
     *
     * @param keyword
     * @return
     */
    List<GoodsWords> getGoodsWords(String keyword);
}
