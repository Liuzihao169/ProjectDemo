package com.onezero.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author hao
 * @create 2019-07-18 ${TIM}
 * 吐槽模块微服务 mongodb搭建
 */
@SpringBootApplication
public class SpitApplication9006 {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication9006.class,args);
    }


    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }
}
