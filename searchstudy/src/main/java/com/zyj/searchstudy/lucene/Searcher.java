package com.zyj.searchstudy.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yunjie.zyj on 2018/5/14.
 */
public class Searcher {

    private IndexSearcher indexSearcher;

    public TopDocs search(String indexDir, String queryString) throws IOException, ParseException {
        Path path = Paths.get(indexDir);
        Directory directory = FSDirectory.open(path);
        Analyzer analyzer = new StandardAnalyzer();

        IndexReader indexReader = DirectoryReader.open(directory);
        indexSearcher = new IndexSearcher(indexReader);
        //单条件
        QueryParser queryParser = new QueryParser("title", analyzer);
        Query query = queryParser.parse(queryString);
        TopDocs topDocs = indexSearcher.search(query, 10);
        return topDocs;
    }

    public IndexSearcher getIndexSearcher() {
        return indexSearcher;
    }
}
