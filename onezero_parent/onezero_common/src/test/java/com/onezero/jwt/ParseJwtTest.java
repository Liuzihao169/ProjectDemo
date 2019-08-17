package com.onezero.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @author hao
 * @create 2019-07-26 ${TIM}
 * 解析jwt测试
 */
public class ParseJwtTest {
    public static void main(String[] args) {
        // 相当于信息的集合
        Claims jack = Jwts.parser().setSigningKey("jack").parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwMDIiLCJpYXQiOjE1NjQxMzkwMzh9.Ooe32WFPYQvWHREzIE3F6nhe8fr3ZRAdSQclgVIIE_s")
                .getBody();

        System.out.println("用户的id:"+jack.getId());
        System.out.println("用户的用户名:"+jack.getSubject());
        System.out.println("token的时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(jack.getIssuedAt()));

    }
}
