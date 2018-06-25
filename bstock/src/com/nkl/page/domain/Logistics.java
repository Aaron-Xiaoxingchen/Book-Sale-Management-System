package com.nkl.page.domain;

import java.util.Date;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.DateUtil;

public class Logistics extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -3086279816822207575L;
	private Integer logistics_id; // 
	private String orders_no; // 
	private Date logistics_date; // 
	private String logistics_desc; // 

	private String ids;
	private String random;

	public String getLogistics_dateDesc(){
		try {
			return DateUtil.dateToDateString(logistics_date);
		} catch (Exception e) {
			return null;
		}
		
	}

	public Integer getLogistics_id() {
		return logistics_id;
	}

	public String getOrders_no() {
		return orders_no;
	}

	public Date getLogistics_date() {
		return logistics_date;
	}

	public String getLogistics_desc() {
		return logistics_desc;
	}

	public String getIds() {
		return ids;
	}

	public String getRandom() {
		return random;
	}

	public void setLogistics_id(Integer logistics_id) {
		this.logistics_id = logistics_id;
	}

	public void setOrders_no(String orders_no) {
		this.orders_no = orders_no;
	}

	public void setLogistics_date(Date logistics_date) {
		this.logistics_date = logistics_date;
	}

	public void setLogistics_desc(String logistics_desc) {
		this.logistics_desc = logistics_desc;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

}
