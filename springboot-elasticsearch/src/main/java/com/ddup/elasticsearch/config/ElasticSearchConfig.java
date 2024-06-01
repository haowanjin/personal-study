package com.ddup.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/11/5 17:31
 */
@Configuration
//@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticSearchConfig {
    private String protocol;
    private String host;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        String[] hostArr = host.split(",");
        HttpHost[] httpHosts = new HttpHost[hostArr.length];
        for (int i = 0; i < hostArr.length; i++) {
            String[] ipAndPort = hostArr[i].split(":");
            String ipStr = ipAndPort[0];
            int port = Integer.parseInt(ipAndPort[1]);
            httpHosts[i] = new HttpHost(ipStr, port, protocol);
        }
        return new RestHighLevelClient(RestClient.builder(httpHosts));
    }
}
