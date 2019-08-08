package com.ex.framework.database;

import lombok.Data;

/**
* @Author: lixubin
* @Date: 2019-07-22
* @Description: 元数据
*/
@Data
public class DataMeta {
    private String sql;
    private Object[] paramter;
}
