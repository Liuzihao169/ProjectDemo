package com.onezero.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author hao
 * @create 2019-07-26 ${TIM}
 * jwt 由头部（存储加密信息） 载体（存储主要内容） 签名（由头部+载体+盐加密构成）构成
 */
public class CreateJwt {
    public static void main(String[] args) {
        JwtBuilder jack = Jwts.builder()
                .setId("002")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "jack");

        String compact = jack.compact();
        System.out.println(compact);
    }
}
