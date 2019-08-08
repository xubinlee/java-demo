package com.ex.framework.elasticsearch;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

public interface EsTemplateBuilder {
    ElasticsearchTemplate build();
}
