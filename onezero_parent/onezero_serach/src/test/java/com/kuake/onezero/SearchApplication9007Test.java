package com.kuake.onezero;

import com.onezero.serach.SearchApplication9007;
import com.onezero.serach.bean.Book;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.font.TextAttribute;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * @author hao
 * @create 2019-07-20 ${TIM}
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = SearchApplication9007.class)
public class SearchApplication9007Test{

    @Autowired
    private ElasticsearchTemplate template;

    /**
     * 测试 添加文档操作
     * 添加 其实也就是对文档进行索引的操作
     */
    @Test
    public void testinsert() {
        IndexQueryBuilder builder = new IndexQueryBuilder();
        for (int i = 0; i < 10; i++) {
            String id = "00"+i;
            template.index(builder.withObject(new Book(id,i+"号变形金刚","这是讲述汽车人与狂派之间的故事第"+i+"回",10*i)).build());
        }
    }

    /**
     * 1、单字符串全文
     * 2、某字段精确查询（不分词）
     * 3、某字段查询 分词
     * 4、字段模糊查询
     * 5、多个字段匹配某字符串
     * 6、@PageableDefault(sort = "weight", direction = Sort.Direction.DESC) 设置分页
     */
    /**
     * 查询所有文档对象
     */
    @Test
    public void testfindAll(){
        // page:   代表第几页 从0开始
        // size：  代表每页的数量
        Pageable pageable = PageRequest.of(2,3);
        //创建查询对象
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()).setPageable(pageable);

        List<Book> books = template.queryForList(criteriaQuery, Book.class);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    /**
     * 条件查询 使用上面三种方法都可以使用
     */
    @Test
    public void testQuery1(){
        //构建查询对象
        Criteria criteria = new Criteria("bookName").startsWith("1");
        List<Book> books = template.queryForList(new CriteriaQuery(criteria), Book.class);
        print(books);
    }

    /**
     * 某字段 精确查询 term查询
     */
    @Test
    public void testQuery2(){
        List<Book> lsit = template.queryForList(new NativeSearchQueryBuilder().withQuery(termQuery("bookName", "1号变形金刚")).build(), Book.class);
        print(lsit);
    }

    /**
     * 模糊查询
     */
    @Test
    public void testQuery3(){
        List<Book> books = template.queryForList(new NativeSearchQueryBuilder().withQuery(matchQuery("bookName", "变形金刚")).build(), Book.class);
        print(books);
    }

    /**
     * 字符串 全文检索
     */
    @Test
    public void testQuery4(){
        List<Book> list = template.queryForList(new NativeSearchQueryBuilder().withQuery(queryStringQuery("金刚")).build(), Book.class);
        print(list);
    }

    /**
     * 查询区间
     */
    @Test
    public void testQuery5(){
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria("pagecount").between(30,70));
        List<Book> list = template.queryForList(criteriaQuery, Book.class);
        print(list);
    }


    /**
     * 内容 match多字段
     * @param books
     */
    @Test
    public void testQuery6(){
        List<Book> list = template.queryForList(new NativeSearchQueryBuilder().withQuery(multiMatchQuery("金刚", "bookName", "content")).build(), Book.class);
        print(list);
    }

    @Test
    public void testQuery7(){
        List<Book> list = template.queryForList(new NativeSearchQueryBuilder().withQuery(matchQuery("bookName",  "变形")).build(), Book.class);
        print(list);
    }

    //输出
    public void print(List<Book> books){
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
