package com.ex.framework.elasticsearch;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.data.elasticsearch")
@Data
public class EsConfig {
    private String clusterName;
    private String clusterNodes;
    private String indexName;
}
