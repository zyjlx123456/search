package com.zyj.searchstudy.elasticsearch;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestPath {
    public static void main(String[] args) throws Exception {

        URI keyStorePath = ClassLoader.getSystemResource("esnode.pem").toURI();
        Path path = Paths.get(keyStorePath);
        String str = path.toString();
        Path path1 = Paths.get(str);

        System.out.println(Files.isReadable(path1));
    }
}
