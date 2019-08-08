package com.ex.mallcore.goods.service.impl;

import com.ex.framework.database.DaoSupport;
import com.ex.mallcore.goods.model.dos.GoodsDO;
import com.ex.mallcore.goods.service.GoodsQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsQueryManagerImpl implements GoodsQueryManager {

    @Autowired
    @Qualifier("goodsDaoSupport")
    private DaoSupport daoSupport;

    @Override
    public List<GoodsDO> getGoodsAll() {
        String sql = "SELECT * FROM es_goods";
        List<GoodsDO> queryForObjectAll = daoSupport.queryForObjectAll(sql,GoodsDO.class, null);
        return queryForObjectAll;
    }

    @Override
    public void insertGoods(GoodsDO goodsDO) {
        daoSupport.insert("es_goods", goodsDO);
//        goodsId1 = daoSupport.getLastId("");
    }
}
