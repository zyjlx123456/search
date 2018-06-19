package com.zyj.searchstudy.elasticsearch;

import com.floragunn.searchguard.ssl.SearchGuardSSLPlugin;
import com.floragunn.searchguard.ssl.util.SSLConfigConstants;
import org.apache.commons.codec.binary.Base64;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.ReindexPlugin;
import org.elasticsearch.join.ParentJoinPlugin;
import org.elasticsearch.percolator.PercolatorPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.transport.Netty4Plugin;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * 测试的时候注意，这里使用的是6.2.4
 */
public class TransportClentTest {
    public static Logger logger = LoggerFactory.getLogger(TransportClentTest.class);

    private TransportClient client;

    @Before
    public void getClient() throws Exception {
        URI esnode_pem = ClassLoader.getSystemResource("kirk.pem").toURI();
        Path esnode_pem_path = Paths.get(esnode_pem);
        URI rootca_pem = ClassLoader.getSystemResource("root-ca.pem").toURI();
        Path rootca_pem_path = Paths.get(rootca_pem);
        URI esnode_key_pem = ClassLoader.getSystemResource("kirk.key").toURI();
        Path esnode_key_pem_path = Paths.get(esnode_key_pem);
        Settings settings = Settings.builder()
                .put("cluster.name", "es-zyj")
                .put("searchguard.ssl.transport.pemcert_filepath", esnode_pem_path.toString())
                .put("searchguard.ssl.transport.pemtrustedcas_filepath", rootca_pem_path.toString())
                .put("searchguard.ssl.transport.pemkey_filepath", esnode_key_pem_path.toString())
                //spock
//                .put("searchguard.ssl.transport.pemkey_password","4mjcOe1gDKOV")
                //kirk
                .put("searchguard.ssl.transport.pemkey_password","7JdmMReoSSgh")
                .put("searchguard.ssl.transport.resolve_hostname","false")
                .put("searchguard.ssl.transport.enforce_hostname_verification",false)
//                .put("searchguard.ssl.http.pemcert_filepath", esnode_pem_path.toString())
//                .put("searchguard.ssl.http.pemtrustedcas_filepath", rootca_pem_path.toString())
//                .put("searchguard.ssl.http.pemkey_filepath", esnode_key_pem_path.toString())
//                .put("searchguard.ssl.http.pemkey_password","7JdmMReoSSgh")
                .build();

        client = new PreBuiltTransportClient(settings, SearchGuardSSLPlugin.class)
                .addTransportAddress(new TransportAddress(InetAddress.getLoopbackAddress(), 9300));

//        client = new PreBuiltTransportClient(settings)
//                .addTransportAddress(new TransportAddress(InetAddress.getLoopbackAddress(), 9300));
//        client.threadPool().getThreadContext().putHeader("Authorization", encodeBasicHeader("admin", "admin"));
    }


    public static String encodeBasicHeader(final String username, final String password) {
        try {
            return "Basic " + Base64.encodeBase64String((username + ":" + password).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    @After
    public void close() {
        client.close();
    }

    @Test
    public void getsugenGardInfo(){
        SearchResponse response = client.prepareSearch("searchguard")
                .setTypes("sg")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchAllQuery())                 // Query
                .get();
        System.out.println(response.toString());
    }


    @Test
    public void getFendoId() {
//        System.out.println("--------------------");
//        System.out.println(client.admin().cluster().nodesInfo(new NodesInfoRequest()).actionGet().getNodes().size());
//        System.out.println("--------------------");
        GetResponse response = client.prepareGet("fendo", "fendodata", "1").get();
        System.out.println(response.toString());
    }


    @Test
    public void testTransportClient() throws Exception {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("user", "zyj")
                .field("postDate", new Date())
                .field("message", "this is Elasticsearch")
                .endObject();

        IndexResponse response = client.prepareIndex("fendo", "fendodata", "1").setSource(builder).get();
        System.out.println(response.getResult());
        client.close();
    }


    @Test
    public void deleteByQuery() {
        DeleteByQueryAction.INSTANCE.newRequestBuilder(client).filter(QueryBuilders.matchAllQuery()).source("fendo").get();
    }

    @Test
    public void indexRequest() throws Exception{
        IndexResponse response = client.prepareIndex("fendo", "fendodata", "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
                .get();
        System.out.println(response);
    }

    @Test
    public void updateRequest() throws Exception {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("fendo");
        updateRequest.type("fendodata");
        updateRequest.id("1");
        updateRequest.doc(XContentFactory.jsonBuilder()
                .startObject()
                .field("user", "zyj")
                .endObject());
        client.update(updateRequest).get();
    }

    @Test
    public void multiGet() throws Exception {
        MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
                .add("fendo", "fendodata", "1") //一个id的方式
                .add("fendo", "fendodata", "2", "3") //多个id的方式
                .get();

        for (MultiGetItemResponse itemResponse : multiGetItemResponses) { //迭代返回值
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {      //判断是否存在
                String json = response.getSourceAsString(); //_source 字段
                System.out.println(json);
            }
        }
    }

    @Test
    public void multiSearch() throws Exception {
        SearchResponse response = client.prepareSearch("fendo").setTypes("fendodata")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(QueryBuilders.termQuery("user", "zyj"))
                .setFrom(0).setSize(2).get();
        System.out.println(response.toString());
    }

    @Test
    public void aggregrationTest() throws Exception {
        SearchResponse response = client.prepareSearch("fendo").setTypes("fendodata")
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(AggregationBuilders.terms("user").field("user"))
                .setSize(0).get();
        System.out.println(response.toString());
    }

    @Test
    public void mappingTest() throws Exception {
        client.admin().indices().prepareCreate("fendo").get();

        XContentBuilder mapping = XContentFactory.jsonBuilder().startObject()
                .startObject("properties")
                .startObject("user").field("type", "keyword").endObject()
                .endObject()
                .endObject();

        System.out.println(mapping.string());

        client.admin().indices().preparePutMapping("fendo").setType("fendodata")
                .setSource(mapping).get();
    }
}
