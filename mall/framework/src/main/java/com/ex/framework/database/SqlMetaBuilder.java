package com.ex.framework.database;

/**
* @Author: lixubin
* @Date: 2019-07-22
* @Description: 元数据构建器，构建模型构建执行sql的构建器
*/
public interface SqlMetaBuilder {

    /**
     * 通过一个模型，获取新增数据的元数据信息
     * @param model 模型
     * @param <T>   model的类型
     * @return  insert的元数据
     */
    <T> DataMeta insert(T model);

    /**
     * 通过一个模型，获取修改数据的元数据信息
     * @param model 模型
     * @param id    主键值
     * @param <T>   model的类型
     * @return
     */
    <T> DataMeta update(T model, Integer id);

    /**
     * 通过class，获取查询一行数据的sql
     * @param clazz 模型的类型
     * @return  查询一个模型的sql，其中已经拼接好根据主键查询的where语句
     */
    String queryForModel(Class clazz);

    /**
     * 通过class，获取删除一行数据的sql
     * @param clazz 模型的类型
     * @return  删除的sql语句，其中已经拼接好根据主键删除的where语句
     */
    String delete(Class clazz);
}
