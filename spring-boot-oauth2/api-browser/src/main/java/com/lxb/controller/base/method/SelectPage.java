package com.lxb.apibrowser.controller.base.method;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lxb.apibrowser.common.ReflectHelper;
import com.lxb.apibrowser.common.ResponseUtils;
import com.lxb.apibrowser.controller.base.BaseController;
import com.lxb.apibrowser.controller.base.PropertyTranslator;
import com.lxb.apibrowser.controller.base.Query;
import com.lxb.apibrowser.controller.base.QueryParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


public interface SelectPage<T> extends ServiceProvider<T> {

    @GetMapping
    default ResponseEntity SelectPage(Query<T> query, T entity){
        Class<? extends ServiceProvider> currentCls = getClass();
        Class<T> modelCls;

        if (BaseController.class.isAssignableFrom(currentCls)) {
            modelCls = ReflectHelper.getSuperClassGenericType(currentCls, 0);
        } else {
            modelCls = ReflectHelper.getInterfaceGenericType(currentCls, 0);
        }
        query.setEq(entity);
        QueryParser<T> queryParser = new QueryParser<>(query, new PropertyTranslator(modelCls));

        // 应用查询条件
        QueryWrapper<T> queryWrapper = Wrappers.query(entity);
        queryParser.parseSort((key, value)->{
            if (Query.SortEnum.valueOf(Integer.valueOf(value))== Query.SortEnum.ASC){
                queryWrapper.orderByAsc(key);
            }else {
                queryWrapper.orderByDesc(key);
            }
        });
        queryParser.parseLikes(queryWrapper::like);
        queryParser.parseGreaterEqual(queryWrapper::ge);
        queryParser.parseLessEqual(queryWrapper::le);

        IPage<T> pageData;
        if (query.getCurrent() == -1) {
            List<T> list = getBaseService().list(queryWrapper);
            pageData = query.toPage();
            pageData.setTotal(list.size());
            pageData.setRecords(list);
        } else {
            pageData = getBaseService().page(query.toPage(), queryWrapper);
        }
        if (pageData.getTotal() == 0) {
            return ResponseUtils.ok("暂无数据！", pageData);
        }
        return ResponseUtils.ok(pageData);
    }
}
