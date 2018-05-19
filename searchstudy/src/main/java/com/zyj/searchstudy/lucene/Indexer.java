package com.zyj.searchstudy.lucene;

import com.google.common.collect.Lists;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by yunjie.zyj on 2018/5/13.
 */
public class Indexer {


    private IndexWriter indexWriter;

    public Indexer(String indexDir) throws IOException {
        Path path = Paths.get(indexDir);
        Directory directory = FSDirectory.open(path);
        Analyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
//        indexWriterConfig.setUseCompoundFile(false);

        indexWriter = new IndexWriter(directory, indexWriterConfig);

    }


    public void index() throws IOException {
        Article article1 = new Article();
        article1.setId(1);
        article1.setTitle("Lucene全文检索");
        article1.setContent("Lucene是apache软件基金会4 jakarta项目组的一个子项目，是一个开放源代码的全文检索引擎工具包，但它不是一个完整的全文检索引擎，而是一个全文检索引擎的架构，提供了完整的查询引擎和索引引擎，部分文本分析引擎（英文与德文两种西方语言）。");
        Article article2 = new Article();
        article2.setId(2);
        article2.setTitle("Lucene2全文检索");
        article2.setContent("Lucene2是全文搜索引擎");

        Article article3 = new Article();
        article3.setId(3);
        article3.setTitle("Lucene3全文检索");
        article3.setContent("Lucene3是全文搜索引擎");

        List<Article> articleList = Lists.newArrayList(article1, article2,article3);
        for (Article article : articleList) {
            Document document = new Document();
            document.add(new TextField("id", article.getId().toString(), Field.Store.YES));
            document.add(new TextField("title", article.getTitle(), Field.Store.YES));
            document.add(new TextField("content", article.getContent(), Field.Store.YES));
            indexWriter.addDocument(document);
        }
        indexWriter.close();
    }
}
