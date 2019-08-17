package com.onezero.article;
import com.onezero.article.pojo.Article;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import util.IdWorker;

import java.net.UnknownHostException;

@SpringBootApplication
/**
 * 文章模块微服务
 */
@EnableEurekaClient
@EnableCaching
public class ArticleApplication9004 {

	public static void main(String[] args) {
		SpringApplication.run(ArticleApplication9004.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}

	//自定义rediesTemplate 添加序列化机制
	@Bean
	public RedisTemplate<String, Article> redisTemplateForArticle(
			RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
		RedisTemplate<String, Article> template = new RedisTemplate<>();
		template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Article>(Article.class));
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
}
