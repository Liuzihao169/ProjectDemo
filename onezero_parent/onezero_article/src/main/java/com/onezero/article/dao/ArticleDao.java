package com.onezero.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.onezero.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{
    /**
     * 更新文章状态 0未审核 1审核
     * @param id
     */
    @Modifying
    @Query(value ="UPDATE tb_article AS a SET a.state = '1' WHERE id = ?",nativeQuery = true)
	public void updateState(String id);

    @Modifying
    @Query(value = "UPDATE tb_article AS a SET a.thumbup = a.thumbup +1 WHERE id = ?1;",nativeQuery = true)
    public void addThumbup(String id);
}
