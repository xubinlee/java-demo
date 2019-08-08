package com.ex.framework.util;

import com.ex.framework.database.ColumnMeta;
import com.ex.framework.database.annotation.Column;
import com.ex.framework.database.annotation.Id;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
* @Author: lixubin
* @Date: 2019-07-22
* @Description: 反射机制工具类
*/
public class ReflectionUtil {

    /**
     * 将po对象中有属性和值转换成map
     * @param po
     * @return
     */
    public static Map po2Map(Object po){
        Map poMap = new HashMap();
        ColumnMeta columnMeta = getColumnMeta(po);

        Object[] columnName = columnMeta.getNames();
        Object[] columnValue = columnMeta.getValues();
        for (int i = 0; i < columnValue.length; i++) {
            poMap.put(columnName[i], columnValue[i]);
        }
        return poMap;
    }

    /**
     * 遍历实体类的属性和数据类型以及属性值
     * @param model
     * @return
     */
    public static ColumnMeta getColumnMeta(Object model){
        ColumnMeta columnMeta = new ColumnMeta();

        try {
            List<Field> fields = new ArrayList<>();
            // 获取所有属性，包括父类
            fields = getParentField(model.getClass(), fields);

            // 属性名
            List columnName = new ArrayList();
            // 属性值
            List columnValue = new ArrayList();

            /**
             * 遍历所有的属性，过滤掉满足以下条件的属性：
             * 1.属性值为null的数据
             * 2.没有添加自定义标签@id和@Column的属性
             */
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    String dbName = field.getName();
                    Column column = field.getAnnotation(Column.class);
                    if (!StringUtil.isEmpty(column.name())) {
                        dbName = column.name();
                    }
                    ReflectionUtils.makeAccessible(field);
                    Object value = ReflectionUtils.getField(field, model);
                    if (value == null && !column.allowNullUpdate()) {
                        continue;
                    }
                    columnName.add(dbName);
                    columnValue.add(value);
                }
            }
            columnMeta.setNames(columnName.toArray());
            columnMeta.setValues(columnValue.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return columnMeta;
    }

    /**
     * 递归获取所有父类的属性
     * @param clazz
     * @param list
     * @return
     */
    public static List<Field> getParentField(Class<?> clazz, List<Field> list){
        if (clazz.getSuperclass() !=Object.class){
            getParentField(clazz.getSuperclass(), list);
        }
        Field[] fields = clazz.getDeclaredFields();
        list.addAll(arrayConvertList(fields));
        return list;
    }

    /**
     * 将数组转换成list
     * @param fields
     * @return
     */
    public static List<Field> arrayConvertList(Field[] fields){
        List<Field> resultList = new ArrayList<>(fields.length);
        Collections.addAll(resultList, fields);
        return resultList;
    }

    /**
     * 根据Class读取主机的字段名
     * @param clazz
     * @return
     */
    public static String getPrimaryKey(Class clazz){
        String columnId="";
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)){
                Id id = field.getAnnotation(Id.class);
                String columnIdName = id.name();

                if (StringUtil.isEmpty(columnIdName)){
                    columnId=field.getName();
                }else{
                    columnId=columnIdName;
                }
                break;
            }
        }
        return columnId;
    }
}
