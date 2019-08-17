package com.kuake.pojo;

import java.io.Serializable;

public class Dict implements Serializable {
	private static final long serialVersionUID = 1L;
	//字典ID
	private Long dict_id;
	//字典编号
	private String dict_code;
	//字典类型
	private String dict_type;
	//字典类型值
	private String dict_type_value;
	//字典类型编号
	private String dict_type_code;
	//字典状态
	private Integer dict_status;
	
	public Long getDict_id() {
		return dict_id;
	}
	public void setDict_id(Long dict_id) {
		this.dict_id = dict_id;
	}
	public String getDict_code() {
		return dict_code;
	}
	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}
	public String getDict_type() {
		return dict_type;
	}
	public void setDict_type(String dict_type) {
		this.dict_type = dict_type;
	}
	public String getDict_type_value() {
		return dict_type_value;
	}
	public void setDict_type_value(String dict_type_value) {
		this.dict_type_value = dict_type_value;
	}
	public String getDict_type_code() {
		return dict_type_code;
	}
	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}
	public Integer getDict_status() {
		return dict_status;
	}
	public void setDict_status(Integer dict_status) {
		this.dict_status = dict_status;
	}
	

}
