package com.onezero.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author hao
 * @create 2019-07-28 ${TIM}
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication6868 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication6868.class,args);
    }
}
