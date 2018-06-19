package com.zyj.searchstudy.elasticsearch;

import org.elasticsearch.common.xcontent.*;
import org.junit.Test;

public class TestXContent {
    @Test
    public void testXContent() throws Exception{
        String message = "{\"test\":\"test\",\"zyj\":\"123\"}";
        XContentBuilder b = XContentFactory.jsonBuilder().prettyPrint();
        try (XContentParser p = XContentFactory.xContent(XContentType.JSON).createParser(NamedXContentRegistry.EMPTY, message)) {
            b.copyCurrentStructure(p);
        }
        System.out.println(b.string());
    }
}
