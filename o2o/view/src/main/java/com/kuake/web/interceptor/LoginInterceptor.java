package com.kuake.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		String HOST_url = request.getContextPath();
		// 判断session中是否有登录信息,
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		//只拦截订单请求，并且用户未登录状态。其他放行。
		if (request.getRequestURI().indexOf("/order") > 0 && object == null) {
		response.sendRedirect(HOST_url+"/page/login.action?msg=1");
			return false;
		} else {
			return true;
		}
	}
}