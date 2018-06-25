package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Stock;

public class StockDao extends BaseDao {

	public void addStock(Stock stock){
		super.add(stock);
	}

	public void delStock(Integer stock_id){
		super.del(Stock.class, stock_id);
	}

	public void delStocks(String[] stock_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <stock_ids.length; i++) {
			sBuilder.append(stock_ids[i]);
			if (i !=stock_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM Stock WHERE stock_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(sql, params);
	}

	public void updateStock(Stock stock){
		Stock _stock = (Stock)super.get(Stock.class, stock.getStock_id());
		if (!StringUtil.isEmptyString(stock.getProvince_name())) {
			_stock.setProvince_name(stock.getProvince_name());
		}
		if (!StringUtil.isEmptyString(stock.getStock_name())) {
			_stock.setStock_name(stock.getStock_name());
		}
		if (stock.getUser()!=null && stock.getUser().getUser_id()!=0) {
			_stock.setUser(stock.getUser());
		}
		super.update(_stock);
	}

	@SuppressWarnings("rawtypes")
	public Stock getStock(Stock stock){
		Stock _stock=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Stock s JOIN FETCH s.user u WHERE 1=1");
		if (stock.getStock_id()!=null && stock.getStock_id()!=0) {
			sBuilder.append(" and stock_id = " + stock.getStock_id() +" ");
		}
		if (!StringUtil.isEmptyString(stock.getProvince_name())) {
			sBuilder.append(" and province_name = '" + stock.getProvince_name() +"' ");
		}

		List list = super.executeQueryHql(sBuilder.toString(), null);
		if (list != null && list.size() > 0) {
			 _stock = (Stock)list.get(0);
		}
		return _stock;
	}

	@SuppressWarnings("rawtypes")
	public List<Stock>  listStocks(Stock stock){
		List<Stock> stocks = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Stock s JOIN FETCH s.user u WHERE 1=1");
		if (stock.getStock_id()!=null && stock.getStock_id()!=0) {
			sBuilder.append(" and stock_id = " + stock.getStock_id() +" ");
		}
		if (!StringUtil.isEmptyString(stock.getStock_name())) {
			sBuilder.append(" and stock_name like '%" + stock.getStock_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(stock.getProvince_name())) {
			sBuilder.append(" and province_name = '" + stock.getProvince_name() +"' ");
		}
		if (stock.getUser()!=null && stock.getUser().getUser_id()!=0) {
			sBuilder.append(" and u.user_id = " + stock.getUser().getUser_id() +" ");
		}
		if (stock.getUser()!=null && !StringUtil.isEmptyString(stock.getUser().getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + stock.getUser().getReal_name() +"%' ");
		}
		sBuilder.append(" order by stock_id asc ");

		List list = null;
		if (stock.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), null, stock.getStart(), stock.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), null);
		}
		if (list != null && list.size() > 0) {
			stocks = new ArrayList<Stock>();
			for (Object object : list) {
				stocks.add((Stock)object);
			}
		}
		return stocks;
	}

	public int  listStocksCount(Stock stock){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("select count(*) FROM Stock s JOIN s.user u WHERE 1=1");
		if (stock.getStock_id()!=null && stock.getStock_id()!=0) {
			sBuilder.append(" and stock_id = " + stock.getStock_id() +" ");
		}
		if (!StringUtil.isEmptyString(stock.getStock_name())) {
			sBuilder.append(" and stock_name like '%" + stock.getStock_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(stock.getProvince_name())) {
			sBuilder.append(" and province_name = '" + stock.getProvince_name() +"' ");
		}
		if (stock.getUser()!=null && stock.getUser().getUser_id()!=0) {
			sBuilder.append(" and u.user_id = " + stock.getUser().getUser_id() +" ");
		}
		if (stock.getUser()!=null && !StringUtil.isEmptyString(stock.getUser().getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + stock.getUser().getReal_name() +"%' ");
		}

		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), null);
		sum = (int)count;
		return sum;
	}

}
