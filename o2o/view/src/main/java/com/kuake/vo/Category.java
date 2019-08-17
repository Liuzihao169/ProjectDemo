package com.kuake.vo;

/**
 * @author Hao
 * @date: 2019年3月12日 下午6:38:46 
 * @Description: 这是一个自定义的 接收商品分类列表的包装类
 */
public class Category {
	//分类的code
	private String dict_id;
	//分类的名称
	private String dict_type_value;
	public String getDict_type_value() {
		return dict_type_value;
	}
	public void setDict_type_value(String dict_type_value) {
		this.dict_type_value = dict_type_value;
	}
	public String getDict_id() {
		return dict_id;
	}
	public void setDict_id(String dict_id) {
		this.dict_id = dict_id;
	}
	
}
