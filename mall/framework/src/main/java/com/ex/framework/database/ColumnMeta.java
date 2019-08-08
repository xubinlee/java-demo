package com.ex.framework.database;

import lombok.Data;

/**
* @Author: lixubin
* @Date: 2019-07-22
* @Description: 列数据
*/
@Data
public class ColumnMeta {
    /**
     * 字段名
     */
    private Object[] names;

    /**
     * 字段值
     */
    private Object[] values;
}
