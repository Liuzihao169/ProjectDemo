package com.onezero.friends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import util.IdWorker;
import util.JwtUtil;

import java.net.UnknownHostException;

/**
 * @author hao
 * @create 2019-07-28 ${TIM}
 */
@EnableDiscoveryClient
@EnableFeignClients(value = {"com.onezero.friends.client"})
@SpringBootApplication
public class FriendsApplication9010 {
    public static void main(String[] args) {
        SpringApplication.run(FriendsApplication9010.class,args);
    }

    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new util.JwtUtil();
    }

    @Bean
    public RedisTemplate<String, String> myredisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<String>(String.class));
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}


