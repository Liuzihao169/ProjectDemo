package com.onezero.serach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author hao
 * @create 2019-07-20 ${TIM}
 */
@SpringBootApplication
public class SearchApplication9007 {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication9007.class,args);
    }
    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }
}
