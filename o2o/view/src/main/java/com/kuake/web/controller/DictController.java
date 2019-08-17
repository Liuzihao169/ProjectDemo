package com.kuake.web.controller;

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
	public String findDict(Dict dict,Model model){
		return null;
	}

}
