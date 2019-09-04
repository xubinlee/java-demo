package com.lxb.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;


public interface BaseService<T> extends IService<T> {

    Boolean remove(T entity);

    boolean update(T entity, T condition);

    T getOne(T entity);

    T getOne(T entity, boolean throwable);

    Map<String, Object> getMap(T entity);

    Object getObj(T entity);

    int count(T entity);

    List<T> list(T entity);

    IPage<T> page(IPage<T> page, T entity);

    IPage<Map<String, Object>> pageMaps(IPage<T> page, T entity);

    List<Map<String, Object>> listMaps(T entity);

    List<Object> listObjs(T entity);

    <R> List<R> listObjs(T entity, Function<? super Object, R> mapper);

}
