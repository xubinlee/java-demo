package com.ex.mallcore.goodssearch.service.impl;

import com.ex.framework.database.DaoSupport;
import com.ex.framework.database.Page;
import com.ex.framework.elasticsearch.EsConfig;
import com.ex.framework.elasticsearch.EsSettings;
import com.ex.framework.util.StringUtil;
import com.ex.mallcore.goods.model.dos.CategoryDO;
import com.ex.mallcore.goodssearch.model.GoodsSearchDTO;
import com.ex.mallcore.goodssearch.model.GoodsSearchLine;
import com.ex.mallcore.goodssearch.model.GoodsWords;
import com.ex.mallcore.goodssearch.service.GoodsSearchManager;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GoodsSearchManagerImpl implements GoodsSearchManager {

    @Autowired
    private DaoSupport daoSupport;

    @Autowired
    protected ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    protected EsConfig esConfig;

    @Override
    public Page search(GoodsSearchDTO goodsSearch) {
        Integer pageNo = goodsSearch.getPageNo();
        Integer pageSize = goodsSearch.getPageSize();

        SearchRequestBuilder searchRequestBuilder;
        try {
            searchRequestBuilder = this.createQuery(goodsSearch);
            //设置分页信息
            searchRequestBuilder.setFrom((pageNo - 1) * pageSize).setSize(pageSize);
            // 设置是否按查询匹配度排序
            searchRequestBuilder.setExplain(true);
            SearchResponse response = searchRequestBuilder.execute().actionGet();

            SearchHits searchHits = response.getHits();
            List<GoodsSearchLine> resultlist = new ArrayList<>();
            for (SearchHit hit : searchHits) {
                Map<String, Object> map = hit.getSourceAsMap();
                GoodsSearchLine goodsSearchLine = new GoodsSearchLine();
                goodsSearchLine.setName(map.get("goodsName").toString());
                goodsSearchLine.setDiscountPrice(StringUtil.toDouble(map.get("discountPrice").toString()));
                goodsSearchLine.setThumbnail(map.get("thumbnail").toString());
                goodsSearchLine.setPrice(StringUtil.toDouble(map.get("price").toString(), 0d));
                goodsSearchLine.setGoodsId(Integer.parseInt(map.get("goodsId").toString()));
                goodsSearchLine.setSmall(map.get("small").toString());
                goodsSearchLine.setCommentNum(Integer.parseInt(map.get("commentNum").toString()));
                goodsSearchLine.setBuyCount(Integer.parseInt(map.get("buyCount").toString()));
                goodsSearchLine.setGrade(StringUtil.toDouble(map.get("grade").toString(), 0d));
                goodsSearchLine.setSellerId(Integer.parseInt(map.get("sellerId").toString()));
                goodsSearchLine.setSellerName(map.get("sellerName").toString());
                goodsSearchLine.setSelfOperated(map.get("selfOperated") == null ? 0 : StringUtil.toInt(map.get("selfOperated").toString(), 0));
                resultlist.add(goodsSearchLine);
            }
            Page webPage = new Page<>(pageNo, searchHits.getTotalHits(), pageSize, resultlist);

            return webPage;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Page(pageNo, 0L, pageSize, new ArrayList());
    }
    /**
     * 构建查询条件
     *
     * @return
     * @throws Exception
     */
    protected SearchRequestBuilder createQuery(GoodsSearchDTO goodsSearch) throws Exception {


        String keyword = goodsSearch.getKeyword();
        Integer cat = goodsSearch.getCategory();
        Integer brand = goodsSearch.getBrand();
        String price = goodsSearch.getPrice();
        Integer sellerId = goodsSearch.getSellerId();
        Integer shopCatId = goodsSearch.getShopCatId();
        SearchRequestBuilder searchRequestBuilder = elasticsearchTemplate.getClient().prepareSearch(esConfig.getIndexName()+"_"+ EsSettings.GOODS_INDEX_NAME);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        // 关键字检索
        if (!StringUtil.isEmpty(keyword)) {
            QueryStringQueryBuilder queryString = new QueryStringQueryBuilder(keyword).field("goodsName");
            queryString.defaultOperator(Operator.AND);
            queryString.analyzer("ik_max_word");
            boolQueryBuilder.must(queryString);
        }
        // 品牌搜素
        if (brand != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("brand", brand));
        }
        // 分类检索
//        if (cat != null) {
//
//            CategoryDO category = categoryManager.getModel(cat);
//            if (category == null) {
//                throw new ServiceException("", "该分类不存在");
//            }
//
//            boolQueryBuilder.must(QueryBuilders.wildcardQuery("categoryPath", HexUtil.encode(category.getCategoryPath()) + "*"));
//        }
        //卖家搜索
        if (sellerId != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("sellerId", sellerId));
        }
        // 卖家分组搜索
//        if (shopCatId != null) {
//            ShopCatDO shopCat = shopCatClient.getModel(shopCatId);
//            if (shopCat == null) {
//                throw new ServiceException("", "该分组不存在");
//            }
//            boolQueryBuilder.must(QueryBuilders.wildcardQuery("shopCatPath", HexUtil.encode(shopCat.getCatPath()) + "*"));
//        }

        // 参数检索
        String prop = goodsSearch.getProp();
//        if (!StringUtil.isEmpty(prop)) {
//            String[] propArray = prop.split(Separator.SEPARATOR_PROP);
//            for (String p : propArray) {
//                String[] onpropAr = p.split(Separator.SEPARATOR_PROP_VLAUE);
//                String name = onpropAr[0];
//                String value = onpropAr[1];
//                boolQueryBuilder.must(QueryBuilders.nestedQuery("params", QueryBuilders.termQuery("params.name", name), ScoreMode.None));
//                boolQueryBuilder.must(QueryBuilders.nestedQuery("params", QueryBuilders.termQuery("params.value", value), ScoreMode.None));
//            }
//        }

        //价格搜索
//        if (!StringUtil.isEmpty(price)) {
//            String[] pricear = price.split(Separator.SEPARATOR_PROP_VLAUE);
//            double min = StringUtil.toDouble(pricear[0], 0.0);
//            double max = Integer.MAX_VALUE;
//
//            if (pricear.length == 2) {
//                max = StringUtil.toDouble(pricear[1], Double.MAX_VALUE);
//            }
//            boolQueryBuilder.must(QueryBuilders.rangeQuery("price").from(min).to(max).includeLower(true).includeUpper(true));
//        }

        // 删除的商品不显示
        boolQueryBuilder.must(QueryBuilders.termQuery("disabled", "1"));
        // 未上架的商品不显示
        boolQueryBuilder.must(QueryBuilders.termQuery("marketEnable", "1"));
        // 待审核和审核不通过的商品不显示
        boolQueryBuilder.must(QueryBuilders.termQuery("isAuth", "1"));

        searchRequestBuilder.setQuery(boolQueryBuilder);

        //排序
        String sortField = goodsSearch.getSort();

        String sortId = "goodsId";

        SortOrder sort = SortOrder.ASC;

//        if (sortField != null) {
//
//            Map<String, String> sortMap = SortContainer.getSort(sortField);
//
//            sortId = sortMap.get("id");
//
//            // 如果是默认排序
//            if ("def".equals(sortId)) {
//                sortId = "goodsId";
//            }
//            if ("buynum".equals(sortId)) {
//                sortId = "buyCount";
//            }
//
//            if ("desc".equals(sortMap.get("def_sort"))) {
//                sort = SortOrder.DESC;
//            } else {
//                sort = SortOrder.ASC;
//            }
//        }

        searchRequestBuilder.addSort(sortId, sort);
        //好平率
        if ("grade".equals(sortId)) {
            searchRequestBuilder.addSort("commentNum", SortOrder.DESC);
            searchRequestBuilder.addSort("buyCount", SortOrder.DESC);
        }

        return searchRequestBuilder;


    }

    @Override
    public Map<String, Object> getSelector(GoodsSearchDTO goodsSearch) {
        return null;
    }

    @Override
    public List<GoodsWords> getGoodsWords(String keyword) {
        String sql = "select words,goods_num from es_goods_words " +
                "where words like ? or quanpin like ? or szm like ? order by goods_num desc";
        return (List<GoodsWords>) this.daoSupport
                .queryForPage(sql, 1, 15, GoodsWords.class, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%").getData();
    }
}
