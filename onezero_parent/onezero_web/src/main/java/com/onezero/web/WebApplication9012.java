package com.onezero.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author hao
 * @create 2019-07-30 ${TIM}
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class WebApplication9012 {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication9012.class,args);
    }
}
