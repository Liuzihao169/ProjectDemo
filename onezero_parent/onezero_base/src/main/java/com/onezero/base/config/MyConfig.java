package com.onezero.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import util.IdWorker;

/**
 * @author hao
 * @create 2019-07-13 ${TIM}
 */
@Configuration
public class MyConfig {

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
