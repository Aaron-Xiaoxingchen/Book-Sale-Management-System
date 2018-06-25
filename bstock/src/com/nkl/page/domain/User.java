package com.nkl.page.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.DateUtil;

public class User extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 935450826788987376L;
	private Integer user_id; // 
	private String user_name; // 
	private String user_pass; // 
	private String real_name; // 
	private Integer user_sex; // 1：男  2：女
	private String nick_name; // 
	private Integer user_age; // 
	private String user_mail; // 
	private String user_hobby; // 
	private Double user_money; //
	private Date reg_date; // 
	private Integer user_type; // 1:注册用户 2:仓库管理员 3:系统管理员
	
	List<Stock> stocks = new ArrayList<Stock>();
	private String ids; //  
	private String random;
	private String user_types;
	
	public String getUser_typeDesc() {
		switch (user_type) {
		case 1:
			return "注册用户";
		case 2:
			return "仓库管理员";
		case 3:
			return "系统管理员";
		default:
			return "";
		}
	}
	
	public String getUser_sexDesc(){
		switch (user_sex) {
		case 1:
			return "男";
		case 2:
			return "女";
		default:
			return "男";
		}
	}
	
	public String getReg_dateDesc(){
		try {
			return DateUtil.dateToDateString(reg_date);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public void setUser_id(Integer user_id){
		this.user_id=user_id;
	}

	public  Integer getUser_id(){
		return user_id;
	}

	public void setUser_name(String user_name){
		this.user_name=user_name;
	}

	public String getUser_name(){
		return user_name;
	}

	public void setUser_pass(String user_pass){
		this.user_pass=user_pass;
	}

	public String getUser_pass(){
		return user_pass;
	}

	public void setUser_mail(String user_mail){
		this.user_mail=user_mail;
	}

	public String getUser_mail(){
		return user_mail;
	}

	public void setReal_name(String real_name){
		this.real_name=real_name;
	}

	public String getReal_name(){
		return real_name;
	}

	public void setUser_sex(Integer user_sex){
		this.user_sex=user_sex;
	}

	public  Integer getUser_sex(){
		return user_sex;
	}

	public void setUser_age(Integer user_age){
		this.user_age=user_age;
	}

	public  Integer getUser_age(){
		return user_age;
	}

	public void setReg_date(Date reg_date){
		this.reg_date=reg_date;
	}

	public Date getReg_date(){
		return reg_date;
	}
	
	public void setUser_type(Integer user_type){
		this.user_type=user_type;
	}

	public  Integer getUser_type(){
		return user_type;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getUser_types() {
		return user_types;
	}

	public void setUser_types(String user_types) {
		this.user_types = user_types;
	}

	public String getUser_hobby() {
		return user_hobby;
	}

	public void setUser_hobby(String user_hobby) {
		this.user_hobby = user_hobby;
	}

	public String getNick_name() {
		return nick_name;
	}

	public Double getUser_money() {
		return user_money;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public void setUser_money(Double user_money) {
		this.user_money = user_money;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

}
