package com.kuake.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.kuake.pojo.User;
import com.kuake.service.UserService;
import com.kuake.utils.EposResult;

import freemarker.core.ReturnInstruction.Return;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request, Model model) throws Exception {
		EposResult result = userService.login(user);
		if (result.getStatus() == 200) {
			// 说明用户名存在,
			// 将信息存在session当中
			request.getSession().setAttribute("user", (User)result.getData());
			// 重定向到index的Handler
			return "redirect:/";
		}
			// 不等于200，用户名密码错误信息
		model.addAttribute("msg", result.getMsg());
		// 返回逻辑视图,登陆页面,回显错误信息。
		return "login";
	}

	// 检查用户名是否存在
	@RequestMapping("/checkUserName")
	@ResponseBody
	public EposResult checkUserName(String user_name) throws Exception {
		EposResult result = userService.checkUserName(user_name);
		return result;
	}

	@RequestMapping("/register")
	public String register(User user, String identifyingCode, Model model, HttpServletRequest request)
			throws Exception {
		// 从request当中取出验证码。
		String code = (String) request.getSession().getAttribute("key");
		String msg = "";
		if (identifyingCode != null && identifyingCode.equals(code)) {
			// 如果验证码正确
			EposResult result = userService.saveUser(user);
			if (result.getStatus() != 200) {
				msg = "注册失败重新注册";
			} else {
				msg = "注册成功前往登陆页面登陆";
			}
		} else {
			msg = "验证码不正确";
		}
		model.addAttribute("msg", msg);
		// 返回逻辑视图
		return "register";
	}
	
	// 退出登入
	@RequestMapping("/outLogin")
	public String outLogin(HttpServletRequest request){
		// 清空session当中的登陆信息
		request.getSession().setAttribute("user", null);
		return "redirect:/";
	}
	/*//测试
	@RequestMapping("/hello")
	public String hello(){
		return "redirect:/page/login.action";
	}*/
}
