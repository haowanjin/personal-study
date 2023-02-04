package com.ddup.elasticsearch.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.jodconverter.core.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/11/5 17:46
 */
@Controller
public class UserController {
    @Resource
    private RestHighLevelClient restHighLevelClient;
    @Value("classpath:ESIndex.json")
    private org.springframework.core.io.Resource esIndexJson;


    public boolean createIndex(String indexName) throws IOException {
        String indexJson = IOUtils.toString(esIndexJson.getInputStream(), Charset.forName("UTF-8"));

        CreateIndexRequest request = new CreateIndexRequest("user");
      //  request.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 1));

        request.mapping(indexJson, XContentType.JSON);
        request.alias(new Alias("user_alias"));

        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(createIndexResponse));
        return true;
    }

    public String testSave(String indexName) throws IOException {
        //createIndex(indexName);

        BulkRequest bulkRequest = new BulkRequest();

        IndexRequest indexRequest = new IndexRequest("user");
        indexRequest.type("student");
        JSONObject obj = new JSONObject();
        obj.put("name", "张三");
        obj.put("age", 19);
        obj.put("addr", "长安区差评街详情号");
        indexRequest.id("1234");

        indexRequest.source(obj, XContentType.JSON);

        bulkRequest.add(indexRequest);
        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return "";
    }
}
