package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Apply;

public class ApplyDao extends BaseDao{

	public void addApply(Apply apply){
		super.add(apply);
	}

	public void delApply(Integer apply_id){
		super.del(Apply.class, apply_id);
	}

	public void delApplys(String[] apply_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <apply_ids.length; i++) {
			sBuilder.append("?");
			if (i !=apply_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM Apply WHERE apply_id IN(" +sBuilder.toString()+")";

		Object[] params = apply_ids;

		super.executeUpdateHql(sql, params);
	}

	public void updateApply(Apply apply){
		Apply _apply = (Apply)super.get(Apply.class, apply.getApply_id());
		if (_apply.getApply_flag()!=null && _apply.getApply_flag()!=0) {
			_apply.setApply_flag(_apply.getApply_flag());
		}
		
		super.update(_apply);
	}

	@SuppressWarnings("rawtypes")
	public Apply getApply(Apply apply){
		Apply _apply=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Apply WHERE 1=1");
		if (apply.getApply_id()!=null && apply.getApply_id()!=0) {
			sBuilder.append(" and apply_id = " + apply.getApply_id() +" ");
		}

		List list = super.executeQueryHql(sBuilder.toString(), null);
		if (list != null && list.size() > 0) {
			 _apply = (Apply)list.get(0);
		}
		return _apply;
	}

	@SuppressWarnings("rawtypes")
	public List<Apply>  listApplys(Apply apply){
		List<Apply> applys = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Apply WHERE 1=1");
		if (apply.getApply_id()!=null && apply.getApply_id()!=0) {
			sBuilder.append(" and apply_id = " + apply.getApply_id() +" ");
		}
		if (apply.getUser_id()!=null && apply.getUser_id()!=0) {
			sBuilder.append(" and user_id = " + apply.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(apply.getReal_name())) {
			sBuilder.append(" and real_name like '%" + apply.getReal_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(apply.getBook_no())) {
			sBuilder.append(" and book_no like '%" + apply.getBook_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(apply.getBook_name())) {
			sBuilder.append(" and book_name like '%" + apply.getBook_name() +"%' ");
		}
		if (apply.getApply_flag()!=null && apply.getApply_flag()!=0) {
			sBuilder.append(" and apply_flag = " + apply.getApply_id() +" ");
		}
		if (apply.getStocker_id()!=null && apply.getStocker_id()!=0) {
			sBuilder.append(" and stock_id2 in (select stock_id from Stock where user_id=" + apply.getStocker_id() +") ");
		}
		sBuilder.append(" order by apply_id asc ");

		if (apply.getStart() != -1) {
			sBuilder.append(" limit " + apply.getStart() + "," + apply.getLimit());
		}

		List list = null;
		if (apply.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), null, apply.getStart(), apply.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), null);
		}
		if (list != null && list.size() > 0) {
			applys = new ArrayList<Apply>();
			for (Object object : list) {
				applys.add((Apply)object);
			}
		}
		return applys;
	}

	public int  listApplysCount(Apply apply){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Apply WHERE 1=1");

		if (apply.getApply_id()!=null && apply.getApply_id()!=0) {
			sBuilder.append(" and apply_id = " + apply.getApply_id() +" ");
		}
		if (apply.getUser_id()!=null && apply.getUser_id()!=0) {
			sBuilder.append(" and user_id = " + apply.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(apply.getReal_name())) {
			sBuilder.append(" and real_name like '%" + apply.getReal_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(apply.getBook_no())) {
			sBuilder.append(" and book_no like '%" + apply.getBook_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(apply.getBook_name())) {
			sBuilder.append(" and book_name like '%" + apply.getBook_name() +"%' ");
		}
		if (apply.getApply_flag()!=null && apply.getApply_flag()!=0) {
			sBuilder.append(" and apply_flag = " + apply.getApply_id() +" ");
		}
		if (apply.getStocker_id()!=null && apply.getStocker_id()!=0) {
			sBuilder.append(" and stock_id2 in (select stock_id from Stock where user_id=" + apply.getStocker_id() +") ");
		}
		
		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), null);
		sum = (int)count;
		return sum;
	}

}
