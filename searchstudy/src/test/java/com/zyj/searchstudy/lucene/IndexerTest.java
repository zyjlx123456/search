package com.zyj.searchstudy.lucene;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by yunjie.zyj on 2018/5/13.
 */
public class IndexerTest {
    @Test
    public void TestIndex() throws IOException {
        String dirPath = "./dir/";
        long start = System.currentTimeMillis();
        Indexer indexer = new Indexer(dirPath);
        indexer.index();
        long end = System.currentTimeMillis();
        System.out.println("indexing time:" + (end- start));

    }
}
