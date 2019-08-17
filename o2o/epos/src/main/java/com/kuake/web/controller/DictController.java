package com.kuake.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kuake.pojo.Dict;
import com.kuake.service.DictService;

@Controller
@RequestMapping("/dict")
public class DictController {
	@Autowired
	private DictService dictService;
	
	@RequestMapping("/list")
	public String findDict(Model model) throws Exception{
		//获取所有字典表数据
		List<Dict> dicts=dictService.findAll();
		
		model.addAttribute("dicts", dicts);
		model.addAttribute("tabFlag", "4");
		return "index";
	}
	
	@RequestMapping("/save")
	public String addDict(Dict dict, Model model) throws Exception{
		boolean flag=dictService.save(dict);
		
		if(!flag){
			return null;
		}
		return "redirect:list.action";
	}
	
	@RequestMapping("/delete")
	public String delete(String dict_id) throws Exception{
		boolean flag=dictService.delete(Long.valueOf(dict_id));
		
		if(!flag){
			return null;
		}
		return "redirect:list.action";
	}

}
