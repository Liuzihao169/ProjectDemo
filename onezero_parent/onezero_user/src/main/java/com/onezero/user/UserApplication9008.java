package com.onezero.user;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import util.IdWorker;
import util.JwtUtil;

@EnableEurekaClient
@SpringBootApplication
public class UserApplication9008 {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication9008.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}

	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
    public JwtUtil jwtUtil(){
	    return new JwtUtil();
    }
}
