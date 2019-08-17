package com.onezero.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 * 目的：使用Security的加盐功能
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    //authorizeRequests注解权限的开端
    //需要权限的两部分，第一部分拦截的路径 第二部分需要的权限
    //anyRequest().authenticated() 表示任何请求需要认证之后才可以访问
    //.and().csrf().disable();固定写法 表示使csrf失效
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
