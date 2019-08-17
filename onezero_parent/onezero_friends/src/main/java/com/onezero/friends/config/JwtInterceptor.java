package com.onezero.friends.config;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hao
 * @create 2019-07-26 ${TIM}
 * jdk1.8中接口可以含有使用default修饰的方法 并且有方法体
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;
    //在目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("【拦截器....friends】");
        String token = request.getHeader("token");
        if(StringUtils.isNotBlank(token)){
            String tk = token.substring(7);
            try {
                Claims claims = jwtUtil.parseJWT(tk);
                if (null!=claims){
                    if(claims.get("roles").equals("admin")){
                        request.setAttribute("admin",tk);
                    }
                    if(claims.get("roles").equals("user")){
                        request.setAttribute("user",tk);
                    }
                }
            }catch (Exception ex){
                throw new RuntimeException("claims解析出错");
            }
            }
        return true;
    }
}
