package com.onezero.serach.dao;

import com.onezero.serach.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author hao
 * @create 2019-07-21 ${TIM}
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {
    // 文章的内容和标题进行全文检索 相当于 mutilMath//多匹配
    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);

}
