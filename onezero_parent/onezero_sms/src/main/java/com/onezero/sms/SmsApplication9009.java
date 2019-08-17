package com.onezero.sms;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hao
 * @create 2019-07-24 ${TIM}
 * 短信发送模块
 */
@EnableRabbit
@SpringBootApplication
public class SmsApplication9009 {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication9009.class,args);
    }
}
