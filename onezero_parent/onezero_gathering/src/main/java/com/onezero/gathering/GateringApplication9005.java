package com.onezero.gathering;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * 活动模块
 */
@SpringBootApplication
public class GateringApplication9005 {

	public static void main(String[] args) {
		SpringApplication.run(GateringApplication9005.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}
	
}
