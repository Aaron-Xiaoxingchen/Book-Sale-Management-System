package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Book;

public class BookDao extends BaseDao {

	public void addBook(Book book){
		super.add(book);
	}

	public void delBook(Integer book_id){
		super.del(Book.class, book_id);
	}

	public void delBooks(String[] book_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <book_ids.length; i++) {
			sBuilder.append(book_ids[i]);
			if (i !=book_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM Book WHERE book_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(sql, params);
	}

	public void updateBook(Book book){
		Book _book = (Book)super.get(Book.class, book.getBook_id());
		if (book.getBookType()!=null && book.getBookType().getBook_type_id()!=0) {
			_book.setBookType(book.getBookType());
		}
		if (!StringUtil.isEmptyString(book.getBook_name())) {
			_book.setBook_name(book.getBook_name());
		}
		if (!StringUtil.isEmptyString(book.getBook_pic())) {
			_book.setBook_pic(book.getBook_pic());
		}
		if (book.getBook_price()!=null && book.getBook_price()!=-1) {
			_book.setBook_price(book.getBook_price());
		}
		if (!StringUtil.isEmptyString(book.getBook_hobby())) {
			_book.setBook_hobby(book.getBook_hobby());
		}
		if (book.getBook_count()!=null && book.getBook_count()!=-1) {
			_book.setBook_count(book.getBook_count());
		}
		if (book.getWarn_count()!=null && book.getWarn_count()!=-1) {
			_book.setWarn_count(book.getWarn_count());
		}
		if (!StringUtil.isEmptyString(book.getBook_desc())) {
			_book.setBook_desc(book.getBook_desc());
		}
		
		super.update(_book);
	}
	
	public void updateBookCount(Book book){
		Book _book = (Book)super.get(Book.class, book.getBook_id());
		if (book.getBook_count()!=null && book.getBook_count()!=-1) {
			_book.setBook_count(book.getBook_count());
		}
		super.update(_book);
	}

	@SuppressWarnings("rawtypes")
	public Book getBook(Book book){
		Book _book=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" FROM Book b ");
		sBuilder.append(" JOIN FETCH b.bookType bt ");
		sBuilder.append(" JOIN FETCH b.stock s  ");
		sBuilder.append(" JOIN FETCH b.stock.user u WHERE 1=1 ");
		if (book.getBook_id()!=null && book.getBook_id()!=0) {
			sBuilder.append(" and book_id = " + book.getBook_id() +" ");
		}
		if (!StringUtil.isEmptyString(book.getBook_no())) {
			sBuilder.append(" and book_no = '" + book.getBook_no() +"' ");
		}
		if (!StringUtil.isEmptyString(book.getBook_name())) {
			sBuilder.append(" and book_name = '" + book.getBook_name() +"' ");
		}
		if (book.getStock()!=null && book.getStock().getStock_id()!=0) {
			sBuilder.append(" and s.stock_id = " + book.getStock().getStock_id() +" ");
		}

		List list = super.executeQueryHql(sBuilder.toString(), null);
		if (list != null && list.size() > 0) {
			 _book = (Book)list.get(0);
		}
		return _book;
	}

	@SuppressWarnings("rawtypes")
	public List<Book>  listBooks(Book book){
		List<Book> books = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" FROM Book b ");
		sBuilder.append(" JOIN FETCH b.bookType bt ");
		sBuilder.append(" JOIN FETCH b.stock s  ");
		sBuilder.append(" JOIN FETCH b.stock.user u WHERE 1=1 ");

		if (book.getBook_id()!=null && book.getBook_id()!=0) {
			sBuilder.append(" and book_id = " + book.getBook_id() +" ");
		}
		if (!StringUtil.isEmptyString(book.getBook_no())) {
			sBuilder.append(" and book_no like '%" + book.getBook_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(book.getBook_name())) {
			sBuilder.append(" and book_name like '%" + book.getBook_name() +"%' ");
		}
		if (book.getBookType()!=null && book.getBookType().getBook_type_id()!=0) {
			sBuilder.append(" and bt.book_type_id = " + book.getBookType().getBook_type_id() +" ");
		}
		if (book.getUser_flag()==1) {
			sBuilder.append(" and ( ");
			if (book.getUser_hobbys()!=null) {
				int i=1;
				for (String user_hobby : book.getUser_hobbys()) {
					if(i!=1)sBuilder.append(" or " );
					sBuilder.append(" b.book_hobby like '%" + user_hobby +"%' " );
					i++;
				}
			}
			sBuilder.append(" ) ");
		}
		if (book.getStock()!=null && book.getStock().getStock_id()!=0) {
			sBuilder.append(" and s.stock_id = " + book.getStock().getStock_id() +" ");
		}
		if (book.getUser_id()!=null && book.getUser_id()!=0) {
			sBuilder.append(" and u.user_id=" + book.getUser_id() +" ");
		}
		
		sBuilder.append(" order by book_id asc ");

		List list = null;
		if (book.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), null, book.getStart(), book.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), null);
		}
		if (list != null && list.size() > 0) {
			books = new ArrayList<Book>();
			for (Object object : list) {
				books.add((Book)object);
			}
		}
		return books;
	}

	public int  listBooksCount(Book book){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT COUNT(*) FROM Book b ");
		sBuilder.append("   JOIN  b.bookType bt ");
		sBuilder.append("   JOIN  b.stock s  ");
		sBuilder.append("   JOIN  b.stock.user u WHERE 1=1 ");

		if (book.getBook_id()!=null && book.getBook_id()!=0) {
			sBuilder.append(" and book_id = " + book.getBook_id() +" ");
		}
		if (!StringUtil.isEmptyString(book.getBook_no())) {
			sBuilder.append(" and book_no like '%" + book.getBook_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(book.getBook_name())) {
			sBuilder.append(" and book_name like '%" + book.getBook_name() +"%' ");
		}
		if (book.getBookType()!=null && book.getBookType().getBook_type_id()!=0) {
			sBuilder.append(" and bt.book_type_id = " + book.getBookType().getBook_type_id() +" ");
		}
		if (book.getUser_flag()==1) {
			sBuilder.append(" and ( ");
			if (book.getUser_hobbys()!=null) {
				int i=1;
				for (String user_hobby : book.getUser_hobbys()) {
					if(i!=1)sBuilder.append(" or " );
					sBuilder.append(" b.book_hobby like '%" + user_hobby +"%' " );
					i++;
				}
			}
			sBuilder.append(" ) ");
		}
		if (book.getStock()!=null && book.getStock().getStock_id()!=0) {
			sBuilder.append(" and s.stock_id = " + book.getStock().getStock_id() +" ");
		}
		if (book.getUser_id()!=null && book.getUser_id()!=0) {
			sBuilder.append(" and u.user_id=" + book.getUser_id() +" ");
		}

		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), null);
		sum = (int)count;
		return sum;
	}
	
	@SuppressWarnings("rawtypes")
	public Book getBook2(Book book){
		Book _book=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" select b.*,bt.book_type_name,s.stock_name,s.province_name ");
		sBuilder.append("  from book b ");
		sBuilder.append("  join book_type bt on b.book_type_id=bt.book_type_id ");
		sBuilder.append("  join stock s on b.stock_id=s.stock_id ");
		sBuilder.append("  where 1=1  ");
		sBuilder.append("    and b.book_id in (select max(book_id) from book group by book_no) ");
		if (book.getBook_id()!=null && book.getBook_id()!=0) {
			sBuilder.append(" and book_id = " + book.getBook_id() +" ");
		}
		if (!StringUtil.isEmptyString(book.getBook_no())) {
			sBuilder.append(" and book_no = '" + book.getBook_no() +"' ");
		}
		if (book.getStock()!=null && book.getStock().getStock_id()!=0) {
			sBuilder.append(" and b.stock_id = " + book.getStock().getStock_id() +" ");
		}

		List list =  super.executeQueryJavaBeanSql(sBuilder.toString(), Book.class, null, null, null);
		if (list != null && list.size() > 0) {
			 _book = (Book)list.get(0);
		}
		return _book;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Book>  listBooks2(Book book){
		List<Book> books = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" select b.*,bt.book_type_name,s.stock_name,s.province_name ");
		sBuilder.append("  from book b ");
		sBuilder.append("  join book_type bt on b.book_type_id=bt.book_type_id ");
		sBuilder.append("  join stock s on b.stock_id=s.stock_id ");
		sBuilder.append("  where 1=1  ");
		sBuilder.append("    and b.book_id in (select max(book_id) from book group by book_no) ");

		if (book.getBook_id()!=null && book.getBook_id()!=0) {
			sBuilder.append(" and book_id = " + book.getBook_id() +" ");
		}
		if (!StringUtil.isEmptyString(book.getBook_no())) {
			sBuilder.append(" and book_no like '%" + book.getBook_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(book.getBook_name())) {
			sBuilder.append(" and book_name like '%" + book.getBook_name() +"%' ");
		}
		if (book.getBook_type_id()!=null && book.getBook_type_id()!=0) {
			sBuilder.append(" and b.book_type_id = " + book.getBook_type_id() +" ");
		}
		if (book.getUser_flag()==1) {
			sBuilder.append(" and ( ");
			if (book.getUser_hobbys()!=null) {
				int i=1;
				for (String user_hobby : book.getUser_hobbys()) {
					if(i!=1)sBuilder.append(" or " );
					sBuilder.append(" b.book_hobby like '%" + user_hobby +"%' " );
					i++;
				}
			}
			sBuilder.append(" ) ");
		}
		
		sBuilder.append(" order by book_id asc ");

		List list = null;
		if (book.getStart()!=-1) {
			list = super.executeQueryJavaBeanSql(sBuilder.toString(), Book.class, null, null, null, book.getStart(), book.getLimit());
		}else {
			list = super.executeQueryJavaBeanSql(sBuilder.toString(), Book.class, null, null, null);
		}
		if (list != null && list.size() > 0) {
			books = new ArrayList<Book>();
			for (Object object : list) {
				books.add((Book)object);
			}
		}
		return books;
	}

	public int  listBooksCount2(Book book){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" select count(*) ");
		sBuilder.append("  from book b ");
		sBuilder.append("  join book_type bt on b.book_type_id=bt.book_type_id ");
		sBuilder.append("  join stock s on b.stock_id=s.stock_id ");
		sBuilder.append("  where 1=1  ");
		sBuilder.append("    and b.book_id in (select max(book_id) from book group by book_no) ");

		if (book.getBook_id()!=null && book.getBook_id()!=0) {
			sBuilder.append(" and book_id = " + book.getBook_id() +" ");
		}
		if (!StringUtil.isEmptyString(book.getBook_no())) {
			sBuilder.append(" and book_no like '%" + book.getBook_no() +"%' ");
		}
		if (!StringUtil.isEmptyString(book.getBook_name())) {
			sBuilder.append(" and book_name like '%" + book.getBook_name() +"%' ");
		}
		if (book.getBook_type_id()!=null && book.getBook_type_id()!=0) {
			sBuilder.append(" and b.book_type_id = " + book.getBook_type_id() +" ");
		}
		if (book.getUser_flag()==1) {
			sBuilder.append(" and ( ");
			if (book.getUser_hobbys()!=null) {
				int i=1;
				for (String user_hobby : book.getUser_hobbys()) {
					if(i!=1)sBuilder.append(" or " );
					sBuilder.append(" b.book_hobby like '%" + user_hobby +"%' " );
					i++;
				}
			}
			sBuilder.append(" ) ");
		}
		int count = Integer.parseInt(super.executeQueryScalarSql(sBuilder.toString(), null, null, null).toString());
		sum = count;
		return sum;
		
	}

}
