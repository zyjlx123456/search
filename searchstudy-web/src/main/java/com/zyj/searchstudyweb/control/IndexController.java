package com.zyj.searchstudyweb.control;

import com.zyj.searchstudy.elasticsearch.dao.IndexConfigDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yunjie.zyj on 2018/6/21.
 */
@RestController
@RequestMapping("/config/index")
public class IndexController {

    @Autowired
    IndexConfigDAOImpl indexConfigDAOImpl;

    @RequestMapping("/create.html")
    public String create(@RequestParam(value = "indexName") String name) {
        String result = indexConfigDAOImpl.createIndex(name);
        return result;
    }

    @RequestMapping("/delete.html")
    public Boolean delete(@RequestParam(value = "indexName") String name) {
        Boolean result = indexConfigDAOImpl.deleteIndex(name);
        return result;
    }
}
