package com.onezero.qa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * 问答微服务
 */
@EnableDiscoveryClient
@EnableFeignClients(value = {"com.onezero.qa.client"})
@SpringBootApplication
public class QAApplication9003 {

	public static void main(String[] args) {
		SpringApplication.run(QAApplication9003.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}
	
}
