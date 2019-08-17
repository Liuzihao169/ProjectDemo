package com.onezero.serach.service;

import com.onezero.serach.dao.ArticleSearchDao;
import com.onezero.serach.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author hao
 * @create 2019-07-21 ${TIM}
 *  文章处理 sercice层
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleSearchDao searchDao;


    public void add(Article article){
        searchDao.save(article);
    }

    /**
     *
     * @param content
     * @param title
     * @param page
     * @param size
     * @return 根据关键字 从content 和 title中查找相关的信息
     */
    public Page<Article> findByTitleOrContentLike(String keyword,int page,int size){
        Pageable pageable = PageRequest.of(page-1,size);
       return searchDao.findByTitleOrContentLike(keyword,keyword,pageable);
    }
}
