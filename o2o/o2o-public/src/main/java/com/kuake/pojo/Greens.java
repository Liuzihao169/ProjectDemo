package com.kuake.pojo;

import java.io.Serializable;

public class Greens implements Serializable {
	private static final long serialVersionUID = 1L;
	//菜品ID
	private Long greens_id;
	//菜品类型
	private String greens_type;
	//菜品名称
	private String greens_name;
	//菜品价格
	private Double greens_price;
	//菜品购买次数
	private Long greens_count;
	//菜品图片路径
	private String greens_picture;
	//菜品剩余量
	private Long greens_surplus;
	//菜品状态（是否下架）
	private Integer greens_status;
	public Long getGreens_id() {
		return greens_id;
	}
	public void setGreens_id(Long greens_id) {
		this.greens_id = greens_id;
	}
	public String getGreens_type() {
		return greens_type;
	}
	public void setGreens_type(String greens_type) {
		this.greens_type = greens_type;
	}
	public String getGreens_name() {
		return greens_name;
	}
	public void setGreens_name(String greens_name) {
		this.greens_name = greens_name;
	}
	public Double getGreens_price() {
		return greens_price;
	}
	public void setGreens_price(Double greens_price) {
		this.greens_price = greens_price;
	}
	public Long getGreens_count() {
		return greens_count;
	}
	public void setGreens_count(Long greens_count) {
		this.greens_count = greens_count;
	}
	public String getGreens_picture() {
		return greens_picture;
	}
	public void setGreens_picture(String greens_picture) {
		this.greens_picture = greens_picture;
	}
	public Long getGreens_surplus() {
		return greens_surplus;
	}
	public void setGreens_surplus(Long greens_surplus) {
		this.greens_surplus = greens_surplus;
	}
	public Integer getGreens_status() {
		return greens_status;
	}
	public void setGreens_status(Integer greens_status) {
		this.greens_status = greens_status;
	}
	

}
