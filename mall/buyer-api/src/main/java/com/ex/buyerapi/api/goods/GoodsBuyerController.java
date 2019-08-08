package com.ex.buyerapi.api.goods;

import com.ex.mallcore.goods.model.dos.GoodsDO;
import com.ex.mallcore.goods.service.GoodsQueryManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("goods")
@Api(description = "商品相关API")
public class GoodsBuyerController {

    @Autowired
    private GoodsQueryManager goodsQueryManager;

    @ApiOperation(value = "获取全部商品信息")
    @ApiImplicitParam(paramType = "path")
    @GetMapping("all")
    public List<GoodsDO> getGoodsAll(){
        List<GoodsDO> goodsAll = goodsQueryManager.getGoodsAll();
        return goodsAll;
    }

    @PostMapping
    public void insertGoods(){
        GoodsDO goods1 = new GoodsDO();
        goods1.setGoodsName("测试商品1");
        goods1.setEnableQuantity(200);
        goods1.setQuantity(200);

        goodsQueryManager.insertGoods(goods1);
    }
}
