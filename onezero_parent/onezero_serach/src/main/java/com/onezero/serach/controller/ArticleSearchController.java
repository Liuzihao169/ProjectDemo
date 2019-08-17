package com.onezero.serach.controller;

import com.onezero.serach.bean.Book;
import com.onezero.serach.pojo.Article;
import com.onezero.serach.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * @author hao
 * @create 2019-07-21 ${TIM}
 */
@RequestMapping("/article")
@RestController
public class ArticleSearchController {

    @Autowired
    private ArticleService service;

    /**
     * 添加文章
     * @param article
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Article article){
        service.add(article);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @RequestMapping(value = "/{key}/{page}/{size}",method = RequestMethod.GET)
    public Result findByKey(@PathVariable(value = "key") String key,@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) throws UnsupportedEncodingException {
        //手动转码
        String keyword = new String(key.getBytes("iso8859-1"),"utf-8");
        Page<Article> pageList = service.findByTitleOrContentLike(keyword, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Article>(pageList.getTotalElements(),pageList.getContent()));
    }

}
