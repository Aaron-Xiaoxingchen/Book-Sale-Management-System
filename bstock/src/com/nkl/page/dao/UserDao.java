package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.User;

public class UserDao extends BaseDao {

	public void addUser(User user){
		super.add(user);
	}

	public void delUser(Integer user_id){
		super.del(User.class, user_id);
	}

	public void delUsers(String[] user_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <user_ids.length; i++) {
			sBuilder.append(user_ids[i]);
			if (i !=user_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM User WHERE user_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(sql, params);	}

	public void updateUser(User user){
		User _user = (User)super.get(User.class, user.getUser_id());
		if (!StringUtil.isEmptyString(user.getUser_name())) {
			_user.setUser_name(user.getUser_name());
		}
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			_user.setUser_pass(user.getUser_pass());
		}
		if (!StringUtil.isEmptyString(user.getReal_name())) {
			_user.setReal_name(user.getReal_name());
		}
		if (!StringUtil.isEmptyString(user.getNick_name())) {
			_user.setNick_name(user.getNick_name());
		}
		if (!StringUtil.isEmptyString(user.getUser_mail())) {
			_user.setUser_mail(user.getUser_mail());
		}
		if (user.getUser_age()!=null && user.getUser_age()!=0) {
			_user.setUser_age(user.getUser_age());
		}
		if (user.getUser_sex()!=null && user.getUser_sex()!=0) {
			_user.setUser_sex(user.getUser_sex());
		}
		if (!StringUtil.isEmptyString(user.getUser_hobby())) {
			_user.setUser_hobby(user.getUser_hobby());
		}
		if (user.getUser_money()!=null && user.getUser_money()!=-1) {
			_user.setUser_money(user.getUser_money());
		}

		super.update(_user);	
	}
	
	public void updateUserMoney(User user){
		User _user = (User)super.get(User.class, user.getUser_id());
		if (user.getUser_money()!=null && user.getUser_money()!=-1) {
			_user.setUser_money(user.getUser_money());
		}

		super.update(_user);	
	}
	
	@SuppressWarnings("rawtypes")
	public User getUser(User user){
		User _user=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM User WHERE 1=1");
		if (user.getUser_id()!=null && user.getUser_id()!=0) {
			sBuilder.append(" and user_id =" + user.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			sBuilder.append(" and user_pass ='" + user.getUser_pass() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_name())) {
			sBuilder.append(" and user_name ='" + user.getUser_name() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_types())) {
			sBuilder.append(" and user_type in (" + user.getUser_types() +") ");
		}
		if (user.getUser_type()!=null && user.getUser_type()!=0) {
			sBuilder.append(" and user_type =" + user.getUser_type() +" ");
		}
		
		List list = super.executeQueryHql(sBuilder.toString(), null);
		if (list != null && list.size() > 0) {
			 _user = (User)list.get(0);
		}
		return _user;
	}

	@SuppressWarnings("rawtypes")
	public List<User>  listUsers(User user){
		List<User> users = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM User WHERE 1=1");
		if (user.getUser_id()!=null && user.getUser_id()!=0) {
			sBuilder.append(" and user_id =" + user.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			sBuilder.append(" and user_pass ='" + user.getUser_pass() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_name())) {
			sBuilder.append(" and user_name like '%" + user.getUser_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(user.getReal_name())) {
			sBuilder.append(" and real_name like '%" + user.getReal_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_types())) {
			sBuilder.append(" and user_type in (" + user.getUser_types() +") ");
		}
		if (user.getUser_type()!=null && user.getUser_type()!=0) {
			sBuilder.append(" and user_type =" + user.getUser_type() +" ");
		}
		
		sBuilder.append(" order by user_id asc ");
		
		List list = null;
		if (user.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), null, user.getStart(), user.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), null);
		}
		if (list != null && list.size() > 0) {
			users = new ArrayList<User>();
			for (Object object : list) {
				users.add((User)object);
			}
		}
		return users;
	}
	
	public int listUsersCount(User user){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM User WHERE 1=1");
		if (user.getUser_id()!=null && user.getUser_id()!=0) {
			sBuilder.append(" and user_id =" + user.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			sBuilder.append(" and user_pass ='" + user.getUser_pass() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_name())) {
			sBuilder.append(" and user_name like '%" + user.getUser_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(user.getReal_name())) {
			sBuilder.append(" and real_name like '%" + user.getReal_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_types())) {
			sBuilder.append(" and user_type in (" + user.getUser_types() +") ");
		}
		if (user.getUser_type()!=null && user.getUser_type()!=0) {
			sBuilder.append(" and user_type =" + user.getUser_type() +" ");
		}

		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), null);
		sum = (int)count;
		return sum;
	}

}
