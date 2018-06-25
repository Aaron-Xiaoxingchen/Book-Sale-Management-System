package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Logistics;

public class LogisticsDao extends BaseDao {

	public void addLogistics(Logistics logistics){
		super.add(logistics);
	}

	public void delLogistics(Integer logistics_id){
		super.del(Logistics.class, logistics_id);
	}

	public void delLogisticss(String[] logistics_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <logistics_ids.length; i++) {
			sBuilder.append(logistics_ids[i]);
			if (i !=logistics_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM Logistics WHERE logistics_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(sql, params);	
	}
	
	public void updateLogistics(Logistics logistics){

	}

	@SuppressWarnings("rawtypes")
	public Logistics getLogistics(Logistics logistics){
		Logistics _logistics=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("From Logistics s ");
		sBuilder.append(" WHERE s.logistics_id = " + logistics.getLogistics_id() );

		List list = super.executeQueryHql(sBuilder.toString(), null);
		if (list != null && list.size() > 0) {
			 _logistics = (Logistics)list.get(0);
		}
		return _logistics;
	}

	@SuppressWarnings("rawtypes")
	public List<Logistics>  listLogisticss(Logistics logistics){
		List<Logistics> logisticss = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Logistics s ");
		sBuilder.append(" WHERE 1=1 ");
		if (logistics.getLogistics_id()!=null && logistics.getLogistics_id()!=0) {
			sBuilder.append(" and s.logistics_id = " + logistics.getLogistics_id() );
		}
		if (!StringUtil.isEmptyString(logistics.getOrders_no())) {
			sBuilder.append(" and s.orders_no = '" + logistics.getOrders_no() +"'");
		}
		
		sBuilder.append(" order by s.logistics_date asc,s.logistics_id asc ");
		
		List list = null;
		if (logistics.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), null, logistics.getStart(), logistics.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), null);
		}
		if (list != null && list.size() > 0) {
			logisticss = new ArrayList<Logistics>();
			for (Object object : list) {
				logisticss.add((Logistics)object);
			}
		}
		return logisticss;
	}
	
	public int  listLogisticssCount(Logistics logistics){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Logistics s ");
		sBuilder.append(" WHERE 1=1");
		if (logistics.getLogistics_id()!=null && logistics.getLogistics_id()!=0) {
			sBuilder.append(" and s.logistics_id = " + logistics.getLogistics_id() );
		}
		if (!StringUtil.isEmptyString(logistics.getOrders_no())) {
			sBuilder.append(" and s.orders_no = '" + logistics.getOrders_no() +"'");
		}
		
		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), null);
		sum = (int)count;
		return sum;
	}

}
