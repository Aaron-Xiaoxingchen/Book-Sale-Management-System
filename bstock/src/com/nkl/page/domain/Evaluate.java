package com.nkl.page.domain;

import java.util.Date;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.DateUtil;

public class Evaluate extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 2769442730248189962L;
	private Integer evaluate_id; // 
	private String orders_no; // 
	private Integer user_id; // 
	private String nick_name; // 
	private String book_no; // 
	private String book_name; // 
	private Date evaluate_date; // 
	private Integer evaluate_level; // 1-差评 2-一般 3-比较满意 4-好评
	private String evaluate_content; // 

	private String ids;
	private String random;
	
	public String getEvaluate_levelDesc(){
		switch (evaluate_level) {
		case 1:
			return "差评";
		case 2:
			return "一般";
		case 3:
			return "比较满意";
		case 4:
			return "好评";
		default:
			return "";
		}
	}
	
	public String getEvaluate_dateDesc(){
		try {
			return DateUtil.dateToDateString(evaluate_date);
		} catch (Exception e) {
			return null;
		}
		
	}

	public Integer getEvaluate_id() {
		return evaluate_id;
	}

	public String getOrders_no() {
		return orders_no;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public String getNick_name() {
		return nick_name;
	}

	public String getBook_no() {
		return book_no;
	}

	public String getBook_name() {
		return book_name;
	}

	public Date getEvaluate_date() {
		return evaluate_date;
	}

	public Integer getEvaluate_level() {
		return evaluate_level;
	}

	public String getEvaluate_content() {
		return evaluate_content;
	}

	public String getIds() {
		return ids;
	}

	public String getRandom() {
		return random;
	}

	public void setEvaluate_id(Integer evaluate_id) {
		this.evaluate_id = evaluate_id;
	}

	public void setOrders_no(String orders_no) {
		this.orders_no = orders_no;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public void setEvaluate_date(Date evaluate_date) {
		this.evaluate_date = evaluate_date;
	}

	public void setEvaluate_level(Integer evaluate_level) {
		this.evaluate_level = evaluate_level;
	}

	public void setEvaluate_content(String evaluate_content) {
		this.evaluate_content = evaluate_content;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	 
}
