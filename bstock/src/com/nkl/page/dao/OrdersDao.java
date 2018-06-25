package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Orders;

public class OrdersDao extends BaseDao {

	public void addOrders(Orders orders){
		super.add(orders);
	}

	public void delOrders(Integer orders_id){
		super.del(Orders.class, orders_id);
	}

	public void delOrderss(String[] orders_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <orders_ids.length; i++) {
			sBuilder.append(orders_ids[i]);
			if (i !=orders_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM Orders WHERE orders_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(sql, params);
	}

	public void updateOrders(Orders orders){
		Orders _orders = (Orders)super.get(Orders.class, orders.getOrders_id());
		if (orders.getOrders_flag()!=null && orders.getOrders_flag()!=0) {
			_orders.setOrders_flag(orders.getOrders_flag());
		}
		super.update(_orders);
	}
	
	public void updateOrdersMoney(Orders orders){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE Orders SET orders_money = (select sum(d.goods_money) from OrdersDetail d where d.orders_no='"+orders.getOrders_no()+"') ");
		sBuilder.append(" WHERE orders_no = '" + orders.getOrders_no() +"' ");

		Object[] params = null;
		super.executeUpdateHql(sBuilder.toString(), params);
	}
	
	@SuppressWarnings("rawtypes")
	public Orders getOrders(Orders orders){
		Orders _orders=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Orders WHERE 1=1");
		if (orders.getOrders_id()!=null && orders.getOrders_id()!=0) {
			sBuilder.append(" and orders_id = " + orders.getOrders_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_no())) {
			sBuilder.append(" and orders_no = '" + orders.getOrders_no() +"' ");
		}

		List list = super.executeQueryHql(sBuilder.toString(), null);
		if (list != null && list.size() > 0) {
			 _orders = (Orders)list.get(0);
		}
		return _orders;
	}

	@SuppressWarnings("rawtypes")
	public List<Orders>  listOrderss(Orders orders){
		List<Orders> orderss = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Orders o WHERE 1=1");

		if (orders.getOrders_id()!=null && orders.getOrders_id()!=0) {
			sBuilder.append(" and orders_id = " + orders.getOrders_id() +" ");
		}
		if (orders.getUser_id()!=null && orders.getUser_id()!=0) {
			sBuilder.append(" and o.user_id = " + orders.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getReal_name())) {
			sBuilder.append(" and o.real_name like '%" + orders.getReal_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_no())) {
			sBuilder.append(" and o.orders_no like '%" + orders.getOrders_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(orders.getProvince_name())) {
			sBuilder.append(" and o.province_name like '%" + orders.getProvince_name() +"%' ");
		}
		if (orders.getOrders_flag()!=null && orders.getOrders_flag()!=0) {
			sBuilder.append(" and o.orders_flag = " + orders.getOrders_flag() +" ");
		}
		if (orders.getStocker_id()!=null && orders.getStocker_id()!=0) {
			sBuilder.append(" and o.province_name in (select s.province_name from Stock s where s.user.user_id=" + orders.getStocker_id() +") ");
		}
		sBuilder.append(" order by o.orders_date desc,o.orders_id asc ");

		List list = null;
		if (orders.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), null, orders.getStart(), orders.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), null);
		}
		if (list != null && list.size() > 0) {
			orderss = new ArrayList<Orders>();
			for (Object object : list) {
				orderss.add((Orders)object);
			}
		}
		return orderss;
	}

	public int  listOrderssCount(Orders orders){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Orders o  WHERE 1=1");

		if (orders.getOrders_id()!=null && orders.getOrders_id()!=0) {
			sBuilder.append(" and orders_id = " + orders.getOrders_id() +" ");
		}
		if (orders.getUser_id()!=null && orders.getUser_id()!=0) {
			sBuilder.append(" and o.user_id = " + orders.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getReal_name())) {
			sBuilder.append(" and o.real_name like '%" + orders.getReal_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(orders.getOrders_no())) {
			sBuilder.append(" and o.orders_no like '%" + orders.getOrders_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(orders.getProvince_name())) {
			sBuilder.append(" and o.province_name like '%" + orders.getProvince_name() +"%' ");
		}
		if (orders.getOrders_flag()!=null && orders.getOrders_flag()!=0) {
			sBuilder.append(" and o.orders_flag = " + orders.getOrders_flag() +" ");
		}
		if (orders.getStocker_id()!=null && orders.getStocker_id()!=0) {
			sBuilder.append(" and o.province_name in (select s.province_name from Stock s where s.user.user_id=" + orders.getStocker_id() +") ");
		}

		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), null);
		sum = (int)count;
		return sum;
	}

}
