package com.ex.deploy.service.impl;

import com.ex.deploy.model.Elasticsearch;
import com.ex.deploy.service.ElasticsearchManager;
import com.ex.framework.database.DaoSupport;
import com.ex.framework.database.Page;
import com.ex.framework.elasticsearch.DefaultEsTemplateBuilder;
import com.ex.framework.elasticsearch.EsTemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ElasticsearchManagerImpl implements ElasticsearchManager {

    @Autowired
    private DaoSupport daoSupport;

    @Override
    public Page list(int page, int pageSize) {
        String sql = "select * from es_elasticsearch ";
        Page webPage = daoSupport.queryForPage(sql, page, pageSize, Elasticsearch.class);
        return webPage;
    }

    @Override
    @Transactional(value = "", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Elasticsearch add(Elasticsearch elasticsearch) {
        daoSupport.insert(elasticsearch);
        return elasticsearch;
    }

    @Override
    @Transactional(value = "", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Elasticsearch edit(Elasticsearch elasticsearch, Integer id) {
        daoSupport.update(elasticsearch,id);
        return elasticsearch;
    }

    @Override
    @Transactional(value = "", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Integer id) {
        daoSupport.delete(Elasticsearch.class, id);
    }

    @Override
    public Elasticsearch getModel(Integer id) {
        return daoSupport.queryForObject(Elasticsearch.class, id);
    }

    @Override
    public Elasticsearch getByDeployId(Integer deployId) {
        String sql = "select * from es_elasticsearch  where deploy_id=?";
        return daoSupport.queryForObject(sql, Elasticsearch.class, deployId);
    }

    @Override
    public void initElasticsearch(Integer deployId) {
        Elasticsearch elasticsearch = new Elasticsearch();
        elasticsearch.setClusterName("elasticsearch-cluster");
        elasticsearch.setClusterNodes("127.0.0.1:9300,127.0.0.1:9301,127.0.0.1:9302");
        elasticsearch.setDeployId(deployId);
        add(elasticsearch);
    }

    @Override
    public boolean testConnection(Elasticsearch elasticsearch) {
        try {
            //连接失败，则重新尝试5次
            for (int i = 0; i <= 4; i++) {
                if (testEs(elasticsearch)) {

                    return true;
                } else {

                    Thread.sleep(1000);
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 封装连接，用于多次尝试连接
     * @param elasticsearch
     * @return
     */
    private boolean testEs(Elasticsearch elasticsearch) {

        try {
            EsTemplateBuilder esTemplateBuilder = new DefaultEsTemplateBuilder();
            ElasticsearchTemplate esTemplate = esTemplateBuilder.build();

            esTemplate.indexExists("test");
            return true;
        } catch (Exception e) {

            return false;
        }

    }
}
