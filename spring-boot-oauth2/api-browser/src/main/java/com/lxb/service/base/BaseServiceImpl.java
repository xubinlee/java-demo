package com.lxb.apibrowser.service.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxb.apibrowser.mapper.base.BaseMapper;
import org.springframework.aop.framework.AopContext;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class BaseServiceImpl<T> extends ServiceImpl<BaseMapper<T>, T> implements BaseService<T> {
    @Override
    public Boolean remove(T entity) {
        return remove(new QueryWrapper<>(entity));
    }

    @Override
    public boolean update(T entity, T condition) {
        return update(entity, new QueryWrapper<>(condition));
    }

    @Override
    public List<T> listByIds(Collection<? extends Serializable> idList) {
        return (List<T>) super.listByIds(idList);
    }

    @Override
    public List<T> listByMap(Map<String, Object> columnMap) {
        return (List<T>) super.listByMap(columnMap);
    }

    @Override
    public T getOne(T entity) {
        return getOne(entity, true);
    }

    @Override
    public T getOne(T entity, boolean throwable) {
        return getOne(new QueryWrapper<>(entity), throwable);
    }

    @Override
    public Map<String, Object> getMap(T entity) {
        return getMap(new QueryWrapper<>(entity));
    }

    @Override
    public Object getObj(T entity) {
        return getObj(new QueryWrapper<>(entity), Function.identity());
    }

    @Override
    public int count(T entity) {
        return count(new QueryWrapper<>(entity));
    }

    @Override
    public List<T> list(T entity) {
        return list(new QueryWrapper<>(entity));
    }

    @Override
    public IPage<T> page(IPage<T> page, T entity) {
        return page(page, new QueryWrapper<>(entity));
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<T> page, T entity) {
        return pageMaps(page, new QueryWrapper<>(entity));
    }

    @Override
    public List<Map<String, Object>> listMaps(T entity) {
        return listMaps(new QueryWrapper<>(entity));
    }

    @Override
    public List<Object> listObjs(T entity) {
        return listObjs(new QueryWrapper<>(entity));
    }

    @Override
    public <R> List<R> listObjs(T entity, Function<? super Object, R> mapper) {
        return listObjs(entity, mapper);
    }

    /**
     * 重载父类获取当前模型class的方法，父类方法有两个参数化类型，而此类只有一个,所以取0号位
     * @return
     */
    @Override
    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), 0);
    }

    /**
     * 获取当前的Aop代理对象
     * @param that
     * @param <T>
     * @return
     */
    protected <T> T aopProxy(T that){
        return (T) AopContext.currentProxy();
    }
}
