package com.zyj.searchstudy.elasticsearch.dao;

import com.zyj.searchstudy.elasticsearch.EasySerchTransportClient;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yunjie.zyj on 2018/6/21.
 */
@Service
public class IndexConfigDAOImpl {
    private Logger logger = LoggerFactory.getLogger(IndexConfigDAOImpl.class);
    @Autowired
    EasySerchTransportClient easySerchTransportClient;

    public String createIndex(String indexName) {
        try {
            CreateIndexResponse indexResponse =
                    easySerchTransportClient.getIndicesAdminClient().prepareCreate(indexName).get();
            return indexResponse.index();
        } catch (Exception e) {
            logger.error("indexName create error, indexName :" + indexName, e);
            return e.getClass().getName();
        }
    }

    public boolean deleteIndex(String indexName) {
        DeleteIndexResponse deleteIndexResponse =
                easySerchTransportClient.getIndicesAdminClient().prepareDelete(indexName).get();
        return deleteIndexResponse.isAcknowledged();
    }
}
