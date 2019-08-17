package com.onezero.serach.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author hao
 * @create 2019-07-21 ${TIM}
 * 文章实体类
 */
@Document(indexName = "onezero_article",type = "article")
public class Article implements Serializable{
    /**
     * 1、是否索引 表示是否能搜索到改域
     * 2、是否分词 搜索的是 匹配分词之后的内容 还是匹配整体
     * 3、是否存储 代表 是否在页面上显示（也就是有一些字段，可以搜索到该域 但是不需要显示到页面上面）
     */
    @Id
    @Field //默认不会存储在索引库
    private String id;      //标识id
    @Field(index= true
            ,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String title;   //小标题
    @Field(index= true
            ,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String content; //内容
    private String state;   //审核状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
