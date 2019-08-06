package com.ex.framework.controller.base;

import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
* @Author: lxb
* @Date: 2019-05-27
* @Description: 属性翻译器 实体属性 -> 数据库字段
*/
public class PropertyTranslator implements Function<String, String> {

    private TableInfo tableInfo;

    private List<TableFieldInfo> fieldList;

    public PropertyTranslator(Class cls){
        this.tableInfo= TableInfoHelper.getTableInfo(cls);
        this.fieldList=tableInfo.getFieldList();
    }
    @Override
    public String apply(String property) {
        if (Objects.equals(tableInfo.getKeyProperty(), property)){
            return tableInfo.getKeyColumn();
        }
        return fieldList.stream()
                .filter(field->Objects.equals(field.getEl(), property))
                .findFirst()
                .map(TableFieldInfo::getColumn)
                .orElseThrow(IllegalArgumentException::new);
    }
}
