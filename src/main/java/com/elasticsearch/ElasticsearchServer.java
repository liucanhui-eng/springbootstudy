package com.elasticsearch;

import com.model.pro.User;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;


import java.util.List;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchServer {
    private final static Logger logger = LoggerFactory.getLogger(ElasticsearchServer.class);

    public TransportClient getClient() throws UnknownHostException {
        //设置连接属性，可以设置很多。
        Settings settings = Settings.builder()
                .put("client.transport.ping_timeout", "40s")
                .put("cluster.name","my-application")
                .build();
        //连接客户端
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.226.128"), 9300));
        return client;
    }


    /**
    * @Description: add 添加数据方法
    * @Param: [client 客户端对象, userList 数据源]
    * @return: void
    * @Author: liucanhui
    * @Date: 2019/2/26
    */
   public void add(TransportClient client, List<User> userList) throws IOException {
        for (User user :userList) {
             XContentBuilder xContentBuilder=null;
             xContentBuilder= XContentFactory.jsonBuilder().startObject().field("id", user.getId())
                    .field("name", user.getName())
                    .field("age", user.getAge())
                    .field("sex",user.getSex()).endObject();
            //创建索引
            IndexResponse response = client.prepareIndex("test1", "user").setSource(xContentBuilder).get();
            System.out.println("索引： "+response.getIndex()+"\t类型： "+response.getType()+"\t 结果"+response.getResult()+"\t状态： "+response.status());
            System.out.println("=============================================================");
        }
        client.close();
    }

    /**
    * @Description: query 根据id,type ,index 查询
    * @Param: [client]
    * @return: void
    * @Author: liucanhui
    * @Date: 2019/2/26
    */
    public void query(TransportClient client) {
        GetResponse getResponse = client.prepareGet("test1", "user","6ptV8mgBdzi8jusOdMWR").get();
        System.out.println("索引库的数据:" + getResponse.getSourceAsString());
        client.close();
    }
    /**
    * @Description: queryAll 查询所有
    * @Param: [client]
    * @return: void
    * @Author: liucanhui
    * @Date: 2019/2/26
    */
    public void queryAll(TransportClient client)throws Exception{
        MatchAllQueryBuilder qb = new MatchAllQueryBuilder();
        SearchResponse response= client.prepareSearch("test1").setTypes("user").setQuery(qb).setFrom(0)
                .setSize(5).get();
        SearchHits searchHits =  response.getHits();
        for(SearchHit hit:searchHits.getHits()){
            String source = hit.getSourceAsString();
            System.out.println(source);
        }
        client.close();
    }

    public void queryByExample(TransportClient client,String field1,String value1,String field2,String value2){
        BoolQueryBuilder qb = QueryBuilders.boolQuery().must(QueryBuilders.termQuery(field1, value1)).must(QueryBuilders.termQuery(field2, value2));

        SearchRequestBuilder sv = client.prepareSearch("test1").setTypes("user").setQuery(qb).setFrom(0)
                .setSize(100);
        SearchResponse response=  sv.get();
        SearchHits searchHits =  response.getHits();
        for(SearchHit hit:searchHits.getHits()){
            logger.info(hit.getSourceAsString());
        }
    }



}
