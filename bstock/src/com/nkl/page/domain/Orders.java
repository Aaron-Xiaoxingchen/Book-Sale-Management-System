package com.nkl.page.domain;

import java.util.Date;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.DateUtil;

public class Orders extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -8822727721720227338L;
	private Integer orders_id; // 
	private String orders_no; // 
	private Date orders_date; // 
	private Integer user_id; // 
	private String real_name; // 
	private String province_name; // 
	private String user_address; // 
	private String user_phone; // 
	private Double orders_money; // 
	private Integer orders_flag; // 1:待发货 2:已发货 3:已收货 4:已评价

	private Integer stocker_id; // 
	private String ids;
	private String random;

	public String getOrders_flagDesc(){
		switch (orders_flag) {
		case 1:
			return "待发货";
		case 2:
			return "已发货";
		case 3:
			return "已收货";
		case 4:
			return "已评价";
		default:
			return "男";
		}
	}
	
	public String getOrders_dateDesc(){
		try {
			return DateUtil.dateToDateString(orders_date);
		} catch (Exception e) {
			return null;
		}
		
	}

	public Integer getOrders_id() {
		return orders_id;
	}

	public String getOrders_no() {
		return orders_no;
	}

	public Date getOrders_date() {
		return orders_date;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public String getReal_name() {
		return real_name;
	}

	public String getProvince_name() {
		return province_name;
	}

	public String getUser_address() {
		return user_address;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public Double getOrders_money() {
		return orders_money;
	}

	public Integer getOrders_flag() {
		return orders_flag;
	}

	public String getIds() {
		return ids;
	}

	public String getRandom() {
		return random;
	}

	public void setOrders_id(Integer orders_id) {
		this.orders_id = orders_id;
	}

	public void setOrders_no(String orders_no) {
		this.orders_no = orders_no;
	}

	public void setOrders_date(Date orders_date) {
		this.orders_date = orders_date;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public void setOrders_money(Double orders_money) {
		this.orders_money = orders_money;
	}

	public void setOrders_flag(Integer orders_flag) {
		this.orders_flag = orders_flag;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public Integer getStocker_id() {
		return stocker_id;
	}

	public void setStocker_id(Integer stocker_id) {
		this.stocker_id = stocker_id;
	}
	 

}
