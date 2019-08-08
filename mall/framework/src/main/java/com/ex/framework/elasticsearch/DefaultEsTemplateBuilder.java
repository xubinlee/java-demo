package com.ex.framework.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class DefaultEsTemplateBuilder implements EsTemplateBuilder {

    @Autowired
    private EsConfig esConfig;
    public DefaultEsTemplateBuilder(){
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
    @Override
    public ElasticsearchTemplate build() {
        return new ElasticsearchTemplate(transportClient());
    }

    private TransportClient transportClient(){
        try {
            Settings.Builder settings = Settings.builder().put("cluster.name", esConfig.getClusterName());
            TransportClient client = new PreBuiltTransportClient(settings.build());
            Map<String, Integer> nodeMap = parseNodeIpInfo();
            for (Map.Entry<String, Integer> entry : nodeMap.entrySet()) {
                client.addTransportAddress(new TransportAddress(InetAddress.getByName(entry.getKey()), entry.getValue()));
            }
            return client;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析节点IP信息，多个节点用逗号隔开，IP和端口用冒号隔开
     * @return
     */
    private Map<String, Integer> parseNodeIpInfo(){
        String[] nodeIpInfoArr = esConfig.getClusterNodes().split(",");
        Map<String, Integer> map = new HashMap<>(nodeIpInfoArr.length);
        for (String ipInfo : nodeIpInfoArr) {
            String[] ipInfoArr = ipInfo.split(":");
            map.put(ipInfoArr[0],Integer.parseInt(ipInfoArr[1]));
        }
        return map;
    }
}
