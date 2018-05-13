package com.zyj.searchstudy.lucene;

import lombok.Data;

/**
 * Created by yunjie.zyj on 2018/5/14.
 */
@Data
public class Article {
    private Integer id;
    private String title;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
