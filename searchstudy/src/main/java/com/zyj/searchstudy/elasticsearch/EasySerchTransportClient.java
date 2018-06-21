package com.zyj.searchstudy.elasticsearch;

import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * Created by yunjie.zyj on 2018/6/21.
 */
public class EasySerchTransportClient {

    private TransportClient client;

    public EasySerchTransportClient() {

    }

    public void init() {
        Settings settings = Settings.builder()
                .put("cluster.name", "es-zyj")
                .build();
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getLoopbackAddress(), 9300));
    }

    public TransportClient getClient() {
        return client;
    }

    public IndicesAdminClient getIndicesAdminClient() {
        return client.admin().indices();
    }

}
