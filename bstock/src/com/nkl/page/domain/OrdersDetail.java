package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class OrdersDetail extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer detail_id; // 
	private String orders_no; // 
	private String book_no; // 
	private String book_name; // 
	private Double book_price; // 
	private Integer book_count; // 
	private Double book_money; // 
	private Integer stock_id; // 
	private String province_name; // 

	private String ids;
	private String random;
	public Integer getDetail_id() {
		return detail_id;
	}
	public String getOrders_no() {
		return orders_no;
	}
	public String getBook_no() {
		return book_no;
	}
	public String getBook_name() {
		return book_name;
	}
	public Double getBook_price() {
		return book_price;
	}
	public Integer getBook_count() {
		return book_count;
	}
	public Double getBook_money() {
		return book_money;
	}
	public Integer getStock_id() {
		return stock_id;
	}
	public String getProvince_name() {
		return province_name;
	}
	public String getIds() {
		return ids;
	}
	public String getRandom() {
		return random;
	}
	public void setDetail_id(Integer detail_id) {
		this.detail_id = detail_id;
	}
	public void setOrders_no(String orders_no) {
		this.orders_no = orders_no;
	}
	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public void setBook_price(Double book_price) {
		this.book_price = book_price;
	}
	public void setBook_count(Integer book_count) {
		this.book_count = book_count;
	}
	public void setBook_money(Double book_money) {
		this.book_money = book_money;
	}
	public void setStock_id(Integer stock_id) {
		this.stock_id = stock_id;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public void setRandom(String random) {
		this.random = random;
	}

	 
}
