package com.kuake.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.kuake.pojo.Greens;
import com.kuake.service.GreensService;

@Controller
public class PageController {
	@Autowired
	private GreensService greensService;
	//跳转到登陆页面
	@RequestMapping("/index.action")
	public String toIndexPage(@RequestParam(defaultValue="3") Long typeCode,Model model) throws Exception{
		//跳转到主页 显示商品列列表,然后默认第一次的时候第一个商品分类。
		List<Greens> list = greensService.findGreensListByTypeCode(typeCode);
		model.addAttribute("list", list);
		//回显数据选中的值
		model.addAttribute("typeCode", typeCode);
		return "index";
	}
	
	/*
	 * 跳转页面数量较多的时候，可以通过路径取值的方式  然后返回*/
	
	@RequestMapping("/page/login")
	public String toLoginPage(String msg,Model model ){
		if(msg!=null&&msg.equals("1")){
			model.addAttribute("msg", "权限不足，请登陆");
		}
		return "login";
	}
	//显示验证码
	@RequestMapping("/page/validatecode")
	public String showCode(){
		return "validatecode";
	}
	//跳转到注册页面j
	@RequestMapping("/page/register")
	public String showRegister(){
		return "register";
	}
}
