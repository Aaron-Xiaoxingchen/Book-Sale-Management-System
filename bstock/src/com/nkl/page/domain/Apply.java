package com.nkl.page.domain;

import java.util.Date;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.DateUtil;

public class Apply extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -4339048244536642790L;
	private Integer apply_id; // 
	private Integer user_id; // 
	private String real_name; // 
	private Integer stock_id; // 
	private String stock_name; // 
	private String book_no; // 
	private String book_name; // 
	private Integer stock_id2; // 
	private String stock_name2; // 
	private Integer book_count; // 
	private Date apply_date; // 
	private Integer apply_flag; // 1:待确认 2:已确认

	private Integer stocker_id; // 
	private String ids;
	private String random;
	
	public String getApply_flagDesc() {
		switch (apply_flag) {
		case 1:
			return "待确认";
		case 2:
			return "已确认";
		default:
			return "";
		}
	}
	
	public String getApply_dateDesc(){
		try {
			return DateUtil.dateToDateString(apply_date);
		} catch (Exception e) {
			return null;
		}
		
	}

	public Integer getApply_id() {
		return apply_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public String getReal_name() {
		return real_name;
	}

	public Integer getStock_id() {
		return stock_id;
	}

	public String getStock_name() {
		return stock_name;
	}

	public String getBook_no() {
		return book_no;
	}

	public String getBook_name() {
		return book_name;
	}

	public Integer getStock_id2() {
		return stock_id2;
	}

	public String getStock_name2() {
		return stock_name2;
	}

	public Integer getBook_count() {
		return book_count;
	}

	public Date getApply_date() {
		return apply_date;
	}

	public Integer getApply_flag() {
		return apply_flag;
	}

	public String getIds() {
		return ids;
	}

	public String getRandom() {
		return random;
	}

	public void setApply_id(Integer apply_id) {
		this.apply_id = apply_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public void setStock_id(Integer stock_id) {
		this.stock_id = stock_id;
	}

	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}

	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public void setStock_id2(Integer stock_id2) {
		this.stock_id2 = stock_id2;
	}

	public void setStock_name2(String stock_name2) {
		this.stock_name2 = stock_name2;
	}

	public void setBook_count(Integer book_count) {
		this.book_count = book_count;
	}

	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}

	public void setApply_flag(Integer apply_flag) {
		this.apply_flag = apply_flag;
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
