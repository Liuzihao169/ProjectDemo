package com.onezero.recruit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * 招聘 微服务
 */
@SpringBootApplication
public class RecruitApplication9002 {

	public static void main(String[] args) {
		SpringApplication.run(RecruitApplication9002.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}
	
}
