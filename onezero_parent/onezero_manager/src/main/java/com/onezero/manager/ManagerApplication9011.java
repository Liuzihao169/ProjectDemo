package com.onezero.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author hao
 * @create 2019-07-30 ${TIM}
 */
@EnableZuulProxy
@SpringBootApplication
public class ManagerApplication9011 {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication9011.class,args);
    }
}
