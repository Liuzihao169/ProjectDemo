package com.onezero.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hao
 * @create 2019-07-13 ${TIM}
 */
@EnableEurekaClient
@SpringBootApplication
public class BaseApplication9001 {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication9001.class,args);
    }
}
