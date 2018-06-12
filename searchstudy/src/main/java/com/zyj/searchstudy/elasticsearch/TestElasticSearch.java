package com.zyj.searchstudy.elasticsearch;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;

/**
 * Created by yunjie.zyj on 2018/6/12.
 */
public class TestElasticSearch {
    @Test
    public void testTransportClient() throws Exception {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getLoopbackAddress(), 9300));
        GetResponse response = client.prepareGet("bank", "_doc", "1").get();
        System.out.println(response.toString());
        client.close();
    }
}
