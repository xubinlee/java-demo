package com.ex.framework.database.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @Author: lixubin
* @Date: 2019-07-22
* @Description: sql解析器
*/
public class SqlPaser {

    public static String insertSelectField(String field, String sql){
        sql = "select"+field+","+sql.substring(6,sql.length());
        return sql;
    }

    public static String findOrderBySql(String sql){
        String pattern ="(order\\s*by[\\w|\\W|\\s|\\S]*)";
        Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
        Matcher m = p.matcher(sql);
        if (m.find()){
            return m.group();
        }
        return null;
    }
}
