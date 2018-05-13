package com.zyj.searchstudy.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;

/**
 * Created by yunjie.zyj on 2018/5/14.
 */
public class SearcherTest {
    @Test
    public void testSearch() throws Exception {
        String dirPath = "./dir/";
        long start = System.currentTimeMillis();
        Searcher searcher = new Searcher();
        TopDocs topDocs = searcher.search(dirPath, "全文");
        long conut = topDocs.totalHits;
        System.out.println("检索总条数：" + conut);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            Document document = searcher.getIndexSearcher().doc(scoreDoc.doc);
            System.out.print("相关度：" + scoreDoc.score + "-----time:" + document.get("id"));
            System.out.println(document.get("content"));
        }

        long end = System.currentTimeMillis();
        System.out.println("indexing time:" + (end - start));
    }
}
