package com.lxb.framework.elasticsearch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by kingapex on 2018/7/18.
 *
 * @author kingapex
 * @version 1.0
 * @since 7.0.0
 * 2018/7/18
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "spring.data.elasticsearch")
public class EsConfig {

    private String clusterName;

    private String userPass;

    private String nodes;

    /**
     * 索引名称
     */
    private String indexName;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() {
        EsTemplateBuilder esTemplateBuilder = new DefaultEsTemplateBuilder().setClusterName(clusterName).setClusterNodes(nodes).setUserPass(userPass);
        return esTemplateBuilder.build();
    }


}
