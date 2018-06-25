package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Evaluate;

public class EvaluateDao extends BaseDao {

	public void addEvaluate(Evaluate evaluate){
		super.add(evaluate);
	}
	
	public void addEvaluateBatch(Evaluate evaluate){
		String sql = "INSERT INTO evaluate(orders_no,user_id,nick_name,book_no,book_name,evaluate_date,evaluate_level,evaluate_content) "
				   + " SELECT  '"+evaluate.getOrders_no()+"',"+evaluate.getUser_id()+",'"+evaluate.getNick_name()+"',book_no,book_name,'"
				   + evaluate.getEvaluate_dateDesc()+"',"+ evaluate.getEvaluate_level()+",'"+evaluate.getEvaluate_content()
				   + "'  FROM orders_detail WHERE orders_no='"+evaluate.getOrders_no()+"'";
		Object[] params = null;
		super.executeUpdateSql(sql, params);
	}

	public void delEvaluate(Integer evaluate_id){
		super.del(Evaluate.class, evaluate_id);
	}

	public void delEvaluates(String[] evaluate_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <evaluate_ids.length; i++) {
			sBuilder.append(evaluate_ids[i]);
			if (i !=evaluate_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM Evaluate WHERE evaluate_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(sql, params);
	}

	public void updateEvaluate(Evaluate evaluate){
		Evaluate _evaluate = (Evaluate)super.get(Evaluate.class, evaluate.getEvaluate_id());
		super.update(_evaluate);
	}
	
	@SuppressWarnings("rawtypes")
	public Evaluate getEvaluate(Evaluate evaluate){
		Evaluate _evaluate=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Evaluate WHERE 1=1");
		if (evaluate.getEvaluate_id()!=null && evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = " + evaluate.getEvaluate_id() +" ");
		}

		List list = super.executeQueryHql(sBuilder.toString(), null);
		if (list != null && list.size() > 0) {
			 _evaluate = (Evaluate)list.get(0);
		}
		return _evaluate;
	}

	@SuppressWarnings("rawtypes")
	public List<Evaluate>  listEvaluates(Evaluate evaluate){
		List<Evaluate> evaluates = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Evaluate WHERE 1=1");
		if (evaluate.getEvaluate_id()!=null && evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = " + evaluate.getEvaluate_id() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getOrders_no())) {
			sBuilder.append(" and orders_no = '" + evaluate.getOrders_no() +"' ");
		}
		if (evaluate.getUser_id()!=null && evaluate.getUser_id()!=0) {
			sBuilder.append(" and user_id = " + evaluate.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getNick_name())) {
			sBuilder.append(" and nick_name like '%" + evaluate.getNick_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(evaluate.getBook_no())) {
			sBuilder.append(" and book_no = '" + evaluate.getBook_no() +"' ");
		}
		if (!StringUtil.isEmptyString(evaluate.getBook_name())) {
			sBuilder.append(" and book_name like '%" + evaluate.getBook_name() +"%' ");
		}
		
		sBuilder.append(" order by evaluate_date desc,evaluate_id asc ");

		List list = null;
		if (evaluate.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), null, evaluate.getStart(), evaluate.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), null);
		}
		if (list != null && list.size() > 0) {
			evaluates = new ArrayList<Evaluate>();
			for (Object object : list) {
				evaluates.add((Evaluate)object);
			}
		}
		return evaluates;
	}

	public int  listEvaluatesCount(Evaluate evaluate){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Evaluate  WHERE 1=1");

		if (evaluate.getEvaluate_id()!=null && evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = " + evaluate.getEvaluate_id() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getOrders_no())) {
			sBuilder.append(" and orders_no = '" + evaluate.getOrders_no() +"' ");
		}
		if (evaluate.getUser_id()!=null && evaluate.getUser_id()!=0) {
			sBuilder.append(" and user_id = " + evaluate.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getNick_name())) {
			sBuilder.append(" and nick_name like '%" + evaluate.getNick_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(evaluate.getBook_no())) {
			sBuilder.append(" and book_no = '" + evaluate.getBook_no() +"' ");
		}
		if (!StringUtil.isEmptyString(evaluate.getBook_name())) {
			sBuilder.append(" and book_name like '%" + evaluate.getBook_name() +"%' ");
		}

		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), null);
		sum = (int)count;
		return sum;
	}

}
