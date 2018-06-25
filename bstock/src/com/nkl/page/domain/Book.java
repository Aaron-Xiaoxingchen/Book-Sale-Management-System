package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Book extends BaseDomain {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6925524708882684408L;
	private Integer book_id; // 
	private BookType bookType; // 
	private String book_no; // 
	private String book_name; // 
	private String book_pic;
	private Double book_price; // 
	private String book_hobby; // 使用空格隔开
	private Stock stock; // 
	private Integer book_count; // 
	private Integer warn_count; // 
	private String book_desc; // 
	
	private Integer user_id; // 
	private String[] user_hobbys;
	private String[] book_hobbys;
	private int user_flag;//0-无 1-表示开启自动检索
	private String ids;
	
	private Integer book_type_id; // 
	private String book_type_name; // 
	private Integer stock_id; // 
	private String stock_name; // 
	private String province_name; //

	public String getBook_descShow(){
		if (!StringUtil.isEmptyString(book_desc)) {
			return Transcode.htmlDiscode(book_desc);
		}
		return book_desc;
	}
	
	public String getBook_desc() {
		return book_desc;
	}

	public void setBook_desc(String book_desc) {
		this.book_desc = book_desc;
	}

	public BookType getBookType() {
		return bookType;
	}

	public String getBook_no() {
		return book_no;
	}

	public String getBook_name() {
		return book_name;
	}

	public String getBook_pic() {
		return book_pic;
	}

	public Double getBook_price() {
		return book_price;
	}

	public String getBook_hobby() {
		return book_hobby;
	}

	public Stock getStock() {
		return stock;
	}

	public Integer getBook_count() {
		return book_count;
	}

	public Integer getWarn_count() {
		return warn_count;
	}

	public String[] getUser_hobbys() {
		return user_hobbys;
	}

	public String[] getBook_hobbys() {
		return book_hobbys;
	}

	public int getUser_flag() {
		return user_flag;
	}

	public String getIds() {
		return ids;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public void setBook_pic(String book_pic) {
		this.book_pic = book_pic;
	}

	public void setBook_price(Double book_price) {
		this.book_price = book_price;
	}

	public void setBook_hobby(String book_hobby) {
		this.book_hobby = book_hobby;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setBook_count(Integer book_count) {
		this.book_count = book_count;
	}

	public void setWarn_count(Integer warn_count) {
		this.warn_count = warn_count;
	}

	public void setUser_hobbys(String[] user_hobbys) {
		this.user_hobbys = user_hobbys;
	}

	public void setBook_hobbys(String[] book_hobbys) {
		this.book_hobbys = book_hobbys;
	}

	public void setUser_flag(int user_flag) {
		this.user_flag = user_flag;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getBook_id() {
		return book_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getBook_type_id() {
		return book_type_id;
	}

	public String getBook_type_name() {
		return book_type_name;
	}

	public Integer getStock_id() {
		return stock_id;
	}

	public String getStock_name() {
		return stock_name;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setBook_type_id(Integer book_type_id) {
		this.book_type_id = book_type_id;
	}

	public void setBook_type_name(String book_type_name) {
		this.book_type_name = book_type_name;
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

	 

}
