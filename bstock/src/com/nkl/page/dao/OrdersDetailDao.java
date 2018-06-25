package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.OrdersDetail;

public class OrdersDetailDao extends BaseDao {

	public void addOrdersDetail(OrdersDetail ordersDetail){
		super.add(ordersDetail);
	}

	public void delOrdersDetail(Integer detail_id){
		super.del(OrdersDetail.class, detail_id);
	}

	public void delOrdersDetails(String[] detail_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <detail_ids.length; i++) {
			sBuilder.append(detail_ids[i]);
			if (i !=detail_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM OrdersDetail WHERE detail_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(sql, params);
	}

	public void updateOrdersDetail(OrdersDetail ordersDetail){
	}

	@SuppressWarnings("rawtypes")
	public OrdersDetail getOrdersDetail(OrdersDetail ordersDetail){
		OrdersDetail _ordersDetail=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM OrdersDetail od WHERE 1=1");
		if (ordersDetail.getDetail_id()!=null && ordersDetail.getDetail_id()!=0) {
			sBuilder.append(" and detail_id = " + ordersDetail.getDetail_id() +" ");
		}

		List list = super.executeQueryHql(sBuilder.toString(), null);
		if (list != null && list.size() > 0) {
			 _ordersDetail = (OrdersDetail)list.get(0);
		}
		return _ordersDetail;
	}

	@SuppressWarnings("rawtypes")
	public List<OrdersDetail>  listOrdersDetails(OrdersDetail ordersDetail){
		List<OrdersDetail> ordersDetails = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM OrdersDetail od WHERE 1=1");

		if (ordersDetail.getDetail_id()!=null && ordersDetail.getDetail_id()!=0) {
			sBuilder.append(" and detail_id = " + ordersDetail.getDetail_id() +" ");
		}
		if (!StringUtil.isEmptyString(ordersDetail.getOrders_no())) {
			sBuilder.append(" and od.orders_no like '%" + ordersDetail.getOrders_no() +"%' ");
		}
		sBuilder.append(" order by detail_id asc");

		if (ordersDetail.getStart() != -1) {
			sBuilder.append(" limit " + ordersDetail.getStart() + "," + ordersDetail.getLimit());
		}

		List list = null;
		if (ordersDetail.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), null, ordersDetail.getStart(), ordersDetail.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), null);
		}
		if (list != null && list.size() > 0) {
			ordersDetails = new ArrayList<OrdersDetail>();
			for (Object object : list) {
				ordersDetails.add((OrdersDetail)object);
			}
		}
		return ordersDetails;
	}

	public int  listOrdersDetailsCount(OrdersDetail ordersDetail){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM OrdersDetail od WHERE 1=1");

		if (ordersDetail.getDetail_id()!=null && ordersDetail.getDetail_id()!=0) {
			sBuilder.append(" and detail_id = " + ordersDetail.getDetail_id() +" ");
		}
		if (!StringUtil.isEmptyString(ordersDetail.getOrders_no())) {
			sBuilder.append(" and od.orders_no like '%" + ordersDetail.getOrders_no() +"%' ");
		}

		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), null);
		sum = (int)count;
		return sum;
	}

}
