package com.onezero.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.zip.ZipFile;

/**
 * @author hao
 * @create 2019-07-30 ${TIM}
 */
@Component
public class ManagerFilter extends ZuulFilter{

    /**
     * 过滤器的类型
     * pre 请求之前
     * route 路由时
     * post rout和error之后
     * error 发生error时调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的优先级别 数值越小优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否开启过滤器
     * true 开启
     * false 关闭
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的主要执行方法
     * 返回然后object 代表继续执行
     * requestContext.setSendZuulResponse(false)//终止运行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getHeader("token");
        System.out.println("路由网关======>token"+token);
        System.out.println("网关过滤器");
        return null;
    }
}
