package com.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;


import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.common.transport.I

public class ElasticsearchServer {

    public static TransportClient getClient() throws UnknownHostException {
        Settings settings=Settings.builder().put("client.transport.ping_timeout", "10s").build();
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.226.128"), 9300));


        InetSocketTransportAddress
        return client;

    }


    public  static  TransportClient getClient2(){
        Settings esSettings = Settings.builder()
                .put("cluster.name", "wspph_elasticsearch")
                .put("client.transport.sniff", true)
                .build();
        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(esSettings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.226.128"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("ElasticsearchClient 连接成功");
        return client;
    }



    public static void main(String[] args) throws Exception {
        TransportClient client = getClient();
        System.out.println(client);
    }


}
