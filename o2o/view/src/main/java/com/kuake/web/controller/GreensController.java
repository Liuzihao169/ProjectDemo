package com.kuake.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuake.constant.BaseConstant;
import com.kuake.pojo.Greens;
import com.kuake.service.GreensService;
import com.kuake.vo.Category;

/**
 * @author Hao
 * @date: 2019年3月12日 下午6:17:45 
 * @Description: 这个菜篮子（商品的controller），
 */
@Controller
@RequestMapping("/greens")
public class GreensController {
	@Autowired
	private GreensService greensService;
	//获得code
	
	
	@ResponseBody
	@RequestMapping("/categorylist")
	public List<Category> showCategoryList() throws Exception{
		List<Category> list = greensService.findCategoryByCode(BaseConstant.GREENS_TYPE);
		//将数据写往页面
		return list;
	}
	//根据分类code 展示商品分类
	@RequestMapping("/list")
	public String showlist(Long typeCode,Model model) throws Exception{
		List<Greens> list = greensService.findGreensListByTypeCode(typeCode);
		//将查询到的结果返回到页面
		model.addAttribute("list", list);
		//回显示选中的分类
		model.addAttribute("typeCode", typeCode);
		return "index";
	}
}
