package com.onezero.serach.dao;

import com.onezero.serach.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author hao
 * @create 2019-07-21 ${TIM}
 */
public interface BookRepository extends ElasticsearchRepository<Book,String> {
}
