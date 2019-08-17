package com.onezero.serach.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author hao
 * @create 2019-07-20 ${TIM}
 */
@Document(indexName = "bookstore", type="book" ) //indexName设置索引库的名称  type设置文档的类型
public class Book {
    //标识主键
    @Id
    private  String id; //编号
    private String bookName;  //书名
    private String content;     //内容主题
    private int pagecount;      //多少页

    public Book(String id, String bookName, String content, int pagecount) {
        this.id = id;
        this.bookName = bookName;
        this.content = content;
        this.pagecount = pagecount;
    }

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", bookName='" + bookName + '\'' +
                ", content='" + content + '\'' +
                ", pagecount=" + pagecount +
                '}';
    }
}
