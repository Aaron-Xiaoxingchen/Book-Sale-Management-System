package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.BookType;

public class BookTypeDao extends BaseDao{

	public void addBookType(BookType bookType){
		super.add(bookType);
	}

	public void delBookType(Integer book_type_id){
		super.del(BookType.class, book_type_id);
	}

	public void delBookTypes(String[] book_type_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <book_type_ids.length; i++) {
			sBuilder.append(book_type_ids[i]);
			if (i !=book_type_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM BookType WHERE book_type_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateBookType(BookType bookType){
		BookType _bookType = (BookType)super.get(BookType.class, bookType.getBook_type_id());
		if (!StringUtil.isEmptyString(bookType.getBook_type_name())) {
			_bookType.setBook_type_name(bookType.getBook_type_name());
		}
		super.update(_bookType);
	}

	@SuppressWarnings("rawtypes")
	public BookType getBookType(BookType bookType){
		BookType _bookType=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM BookType WHERE 1=1");
		if (bookType.getBook_type_id()!=null && bookType.getBook_type_id()!=0) {
			sBuilder.append(" and book_type_id = " + bookType.getBook_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(bookType.getBook_type_name())) {
			sBuilder.append(" and book_type_name = '" + bookType.getBook_type_name() +"' ");
		}

		List list = super.executeQueryHql(sBuilder.toString(), null);
		if (list != null && list.size() > 0) {
			 _bookType = (BookType)list.get(0);
		}
		return _bookType;
	}

	@SuppressWarnings("rawtypes")
	public List<BookType>  listBookTypes(BookType bookType){
		List<BookType> bookTypes = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM BookType WHERE 1=1");

		if (bookType.getBook_type_id()!=null && bookType.getBook_type_id()!=0) {
			sBuilder.append(" and book_type_id = " + bookType.getBook_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(bookType.getBook_type_name())) {
			sBuilder.append(" and book_type_name like '%" + bookType.getBook_type_name() +"%' ");
		}
		sBuilder.append(" order by book_type_id asc ");

		List list = null;
		if (bookType.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), null, bookType.getStart(), bookType.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), null);
		}
		
		if (list != null && list.size() > 0) {
			bookTypes = new ArrayList<BookType>();
			for (Object object : list) {
				bookTypes.add((BookType)object);
			}
		}
		return bookTypes;
	}

	public int  listBookTypesCount(BookType bookType){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM BookType WHERE 1=1");

		if (bookType.getBook_type_id()!=null && bookType.getBook_type_id()!=0) {
			sBuilder.append(" and book_type_id = " + bookType.getBook_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(bookType.getBook_type_name())) {
			sBuilder.append(" and book_type_name like '%" + bookType.getBook_type_name() +"%' ");
		}

		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), null);
		sum = (int)count;
		return sum;
	}

}
