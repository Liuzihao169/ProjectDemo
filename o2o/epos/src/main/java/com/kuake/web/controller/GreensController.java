package com.kuake.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kuake.constant.Constant;
import com.kuake.pojo.Dict;
import com.kuake.pojo.Greens;
import com.kuake.service.GreensService;
import com.kuake.utils.FastJsonUtil;
import com.kuake.utils.UploadUtils;
import com.kuake.vo.Vo;

@Controller
@RequestMapping("/greens")
public class GreensController {
	@Autowired
	private GreensService greensService;
	
	@RequestMapping("/list")
	public String list(Vo vo,Model model) throws Exception{
		String dict_type_code=vo.getDict_type_code();
		
		vo.setDict_code(Constant.GREENS_TYPE);
		vo=greensService.findByType(vo);
		
		List<Greens> greensList=vo.getGreensList();
		List<Dict> greensLevels=vo.getGreensLevels();
		
		model.addAttribute("greensList", greensList);
		model.addAttribute("greensLevels", greensLevels);
		model.addAttribute("tabFlag", "1");
		model.addAttribute("dict_type_code", dict_type_code);
		return "index";
	}
	
	@RequestMapping("/delete")
	public String delete(Vo vo, Model model) throws Exception{
		 boolean flag=greensService.deleteByVo(vo);
		 String msg="删除成功！";
		 if(!flag){
			 msg="删除失败！";
		 }
		return "redirect:list.action";
	}
	
	@RequestMapping("/edit")
	public String edit(Vo vo,Model model) throws Exception{
		vo.setDict_code(Constant.GREENS_TYPE);
		
		vo=greensService.findById(vo);
		
		Greens green=vo.getGreens();
		List<Dict> greensLevels=vo.getGreensLevels();
		
		model.addAttribute("green", green);
		model.addAttribute("greensLevels", greensLevels);
		return "Test";
	}
	
	@RequestMapping("/update")
	public String update(MultipartFile pictureFile, Greens greens, Model model,HttpServletRequest req) throws Exception{
		//获取图片上传的路径
		String picturePath=req.getSession().getServletContext().getRealPath("");
		
		//获取图片完整名称
		String fileStr=pictureFile.getOriginalFilename();
		if(fileStr!=null && fileStr!=""){
			//使用随机生成的字符串+源图片扩展名组成新的图片名称,防止图片重名
			String newFileName=UploadUtils.getUUIDName(fileStr);
			
			//将图片保存到硬盘                                                  //"C:\\images\\"为图片的位置
											//"newFileName"为图片存在数据库的名称
			pictureFile.transferTo(new File("C:\\images\\"+newFileName));
			
			//将图片名称保存到数据库
			greens.setGreens_picture(newFileName);
		}
		
		
		boolean flag=greensService.update(greens);
		if(!flag){
			return null;
		}
		return "redirect:list.action";
	}
	
	@RequestMapping("/add")
	public void add(HttpServletResponse resp) throws Exception{
		List<Dict> greensLevels=greensService.getGreensType(Constant.GREENS_TYPE);
		String data=FastJsonUtil.toJSONString(greensLevels);
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().print(data);
	}
	
	@RequestMapping("/save")
	public String save(MultipartFile pictureFile, Greens greens, Model model,HttpServletRequest req) throws Exception{
		//获取上传图片的路径
		String picturePath=req.getSession().getServletContext().getRealPath("");
		
		//获取图片完整名称
		String fileStr=pictureFile.getOriginalFilename();
		
		//随机生成图片新的图片名称
		String newFileName=UploadUtils.getUUIDName(fileStr);
		
		//将图片保存到服务器
		pictureFile.transferTo(new File("C:\\images\\"+newFileName));
		
		greens.setGreens_picture(newFileName);
		
		boolean flag=greensService.save(greens);
		if(!flag){
			return null;
		}
		return "redirect:list.action";
	}

}
