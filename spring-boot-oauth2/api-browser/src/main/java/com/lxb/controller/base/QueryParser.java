package com.lxb.apibrowser.controller.base;

import com.lxb.apibrowser.common.ReflectHelper;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class QueryParser<T> {

    //查询对象
    private Query<T> query;

    private Map<String, Object> equals;

    /**
     * 属性转换器 把属性名称转换成需要的名称 String 对象的属性名称， String 实际设置条件时的名称
     * 比如对象属性名 转 数据库列名
     */
    private Function<String, String> propertyTranslator;

    public QueryParser(Query<T> query, Function<String, String> propertyTranslator){
        this.query=query;
        this.propertyTranslator=propertyTranslator;
        this.equals= converterConditions(query.getEq());
    }

    public QueryParser(Query<T> query){
        this(query, s->s);
    }

    /**
     * 解析升序降序排序
     * @param sortConsumer
     */
    public void parseSort(BiConsumer<String, String> sortConsumer){
        parseMapConditions(query.getSort(), sortConsumer);
    }

    /**
     * 解析模糊查询
     * @param likeConsumer
     */
    public void parseLikes(BiConsumer<String, String> likeConsumer){
        parseMapConditions(query.getLike(), likeConsumer);
    }

    /**
     * 解析大于等于
     * @param greaterEqualConsumer
     */
    public void parseGreaterEqual(BiConsumer<String, String> greaterEqualConsumer){
        parseMapConditions(query.getGe(), greaterEqualConsumer);
    }

    /**
     * 解析小于等于
     * @param lessEqualConsumer
     */
    public void parseLessEqual(BiConsumer<String, String> lessEqualConsumer){
        parseMapConditions(query.getLe(), lessEqualConsumer);
    }

    /**
     * 解析的属性和值
     * @param map
     * @param biConsumer
     */
    private void parseMapConditions(Map<String, String> map, BiConsumer<String, String> biConsumer){
        if (MapUtils.isEmpty(map)){
            return;
        }
        for (Map.Entry<String, String> entry:map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            if (StringUtils.isBlank(key) || !isValidValue(value) || isConflict(key)){
                continue;
            }
            biConsumer.accept(propertyTranslator.apply(key), value);
        }
    }

    /**
     * 把对象的属性和值转成Map类型的条件
     * @param obj
     * @return
     */
    private Map<String, Object> converterConditions(Object obj){
        Map<String, Object> properties;
        try {
            properties= ReflectHelper.objectToMap(obj);
        } catch (Exception e) {
            return Collections.emptyMap();
        }
        if (properties==null || properties.isEmpty()){
            return Collections.emptyMap();
        }
        HashMap<String, Object> conditions = new HashMap<>(properties.size());
        conditions.entrySet().stream()
                .filter(entity->isValidValue(entity.getValue()))
                .forEach(entity->conditions.put(entity.getKey(),entity.getValue()));
        return conditions;
    }

    private boolean isValidValue(Object value){
        if (value==null){
            return false;
        }
        if (value instanceof String){
            return StringUtils.isNotBlank((String)value);
        }
        return true;
    }

    /**
     * 是否有冲突
     * 如果实体设置的属性的值，实体属性的条件为等值条优先于其他
     * @param key
     * @return
     */
    private boolean isConflict(String key){
        return isValidValue(equals.get(key));
    }
}
