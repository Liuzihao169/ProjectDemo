package com.kuake.vo;

import java.util.List;

import com.kuake.pojo.Dict;
import com.kuake.pojo.Greens;

public class Vo {
	
	private String user_name;
	
	private String user_password;
	
	private String dict_code;
	
	private String dict_type_code;
	
	private Long greens_id;
	
	private Long greens_type;
	
	private Greens greens;
	
	private List<Dict> greensLevels;
	
	private List<Greens> greensList;

	public List<Greens> getGreensList() {
		return greensList;
	}

	public void setGreensList(List<Greens> greensList) {
		this.greensList = greensList;
	}

	public Greens getGreens() {
		return greens;
	}

	public void setGreens(Greens greens) {
		this.greens = greens;
	}

	public List<Dict> getGreensLevels() {
		return greensLevels;
	}

	public void setGreensLevels(List<Dict> greensLevels) {
		this.greensLevels = greensLevels;
	}

	public Long getGreens_id() {
		return greens_id;
	}

	public void setGreens_id(Long greens_id) {
		this.greens_id = greens_id;
	}

	public Long getGreens_type() {
		return greens_type;
	}

	public void setGreens_type(Long greens_type) {
		this.greens_type = greens_type;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getDict_code() {
		return dict_code;
	}

	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}

	public String getDict_type_code() {
		return dict_type_code;
	}

	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}
	

}
