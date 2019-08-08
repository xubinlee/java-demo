package com.ex.buyerapi.api.goods;

import com.ex.framework.database.Page;
import com.ex.mallcore.goodssearch.model.GoodsSearchDTO;
import com.ex.mallcore.goodssearch.model.GoodsWords;
import com.ex.mallcore.goodssearch.service.GoodsSearchManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/goods/search")
@Api("商品检索相关API")
public class GoodsSearchBuyerController {

    @Autowired
    private GoodsSearchManager goodsSearchManager;

    @ApiOperation(value = "查询商品列表")
    @GetMapping
    public Page searchGoods(@ApiIgnore Integer pageNo, @ApiIgnore Integer pageSize, GoodsSearchDTO goodsSearch){

        goodsSearch.setPageNo(pageNo);
        goodsSearch.setPageSize(pageSize);

        return goodsSearchManager.search(goodsSearch);
    }

    @ApiOperation(value = "查询商品分词对应数量")
    @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = true, dataType = "string", paramType = "query")
    @GetMapping("/words")
    public List<GoodsWords> searchGoodsWords(String keyword){

        return goodsSearchManager.getGoodsWords(keyword);
    }
}
