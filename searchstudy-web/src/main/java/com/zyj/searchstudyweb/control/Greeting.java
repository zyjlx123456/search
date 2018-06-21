package com.zyj.searchstudyweb.control;

/**
 * Created by yunjie.zyj on 2018/6/21.
 */
public class Greeting {
    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
