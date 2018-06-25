package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Stock extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 7350732717539189450L;
	private Integer stock_id; // 
	private String stock_name; // 
	private String province_name; // 
	private User user; // 

	private String ids;
	private String random;
	public Integer getStock_id() {
		return stock_id;
	}
	public String getStock_name() {
		return stock_name;
	}
	public String getProvince_name() {
		return province_name;
	}
	public User getUser() {
		return user;
	}
	public String getIds() {
		return ids;
	}
	public String getRandom() {
		return random;
	}
	public void setStock_id(Integer stock_id) {
		this.stock_id = stock_id;
	}
	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public void setRandom(String random) {
		this.random = random;
	}

	 

}
