package com.kuake.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kuake.constant.Constant;
import com.kuake.pojo.User;
import com.kuake.service.UserService;
import com.kuake.vo.Vo;

@Controller
@RequestMapping("/admin")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(Vo vo ,HttpServletRequest req,Model model) throws Exception{
		vo.setDict_code(Constant.USER_LEVEL);
		vo.setDict_type_code("2");
		User existUser=userService.findUserByVo(vo);
		if(existUser==null){
			return "login";
		}
		req.getSession().setAttribute("existUser", existUser);
		return "redirect:/index/index.action";
	}
	
	@RequestMapping("/list")
	public String list(Model model) throws Exception{
		List<User> userList=userService.findAll();
		model.addAttribute("userList", userList);
		model.addAttribute("tabFlag", "3");
		return "index";
	}
	
	@RequestMapping("/delete")
	public String delete(String user_id ,Model model) throws Exception{
		boolean flag=userService.delete(Long.valueOf(user_id));
		if(!flag){
			return null;
		}
		return "redirect:list.action";
	}
	
	@RequestMapping("/exit")
	public String exit(HttpSession session){
		session.removeAttribute("existUser");
		return "redirect:login.action";
	}

}
