package com.nkl.admin.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;
import com.nkl.page.dao.ApplyDao;
import com.nkl.page.dao.BookDao;
import com.nkl.page.dao.BookTypeDao;
import com.nkl.page.dao.EvaluateDao;
import com.nkl.page.dao.LogisticsDao;
import com.nkl.page.dao.OrdersDao;
import com.nkl.page.dao.OrdersDetailDao;
import com.nkl.page.dao.StockDao;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.Apply;
import com.nkl.page.domain.Book;
import com.nkl.page.domain.BookType;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.OrdersDetail;
import com.nkl.page.domain.Stock;
import com.nkl.page.domain.User;

public class AdminManager {

	UserDao userDao;
	BookTypeDao bookTypeDao;
	BookDao bookDao;
	OrdersDao ordersDao;
	OrdersDetailDao ordersDetailDao;
	ApplyDao applyDao;
	EvaluateDao evaluateDao;
	LogisticsDao logisticsDao;
	StockDao stockDao;
	
	/**
	 * @Title: listProvinces
	 * @Description: 查询省份
	 * @return List<Map<String,String>>
	 */
	public List<Map<String, String>> listProvinces(){
		List<Map<String, String>> pList = new ArrayList<Map<String,String>>();
		String[] pStrings = {"北京市","天津市","重庆市","上海市","河北省","山西省","辽宁省","吉林省","黑龙江省","江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省","海南省","四川省","贵州省","云南省","陕西省","甘肃省","青海省","台湾省"};
		for (String province : pStrings) {
			Map<String, String> pMap = new HashMap<String, String>();
			pMap.put("id", province);
			pMap.put("text", province);
			pList.add(pMap);
		}
		return pList;
	} 
	
	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User>  listUsers(User user,int[] sum){
		
		if (sum!=null) {
			sum[0] = userDao.listUsersCount(user);
		}
		List<User> users = userDao.listUsers(user);
		
		if (user.getUser_type()==2) {
			if (users!=null && users.size()>0) {
				for (User user2 : users) {
					Stock stock = new Stock();
					stock.setUser(user2);
					stock.setStart(-1);
					List<Stock> stocks = stockDao.listStocks(stock);
					if (stocks!=null) {
						user2.setStocks(stocks);
					}
				}
			}
		}
		
		return users;
	}
	
	/**
	 * @Title: getUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User  getUser(User user){
		
		User _user = userDao.getUser(user);
		
		return _user;
	}
	 
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void  addUser(User user){
		
		userDao.addUser(user);
		
	}
	
	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void  updateUser(User user){
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user);
		
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息--出现异常提示
	 * @param user
	 * @return void
	 */
	public void  delUsers(User user){
		
		userDao.delUsers(user.getIds().split(","));
		
	}
	
	/**
	 * @Title: listBookTypes
	 * @Description: 图书类型查询
	 * @param bookType
	 * @return List<BookType>
	 */
	public List<BookType> listBookTypes(BookType bookType, int[] sum) {
		
		if (sum != null) {
			sum[0] = bookTypeDao.listBookTypesCount(bookType);
		}
		List<BookType> bookTypes = bookTypeDao.listBookTypes(bookType);

		
		return bookTypes;
	}

	/**
	 * @Title: queryBookType
	 * @Description: 图书类型查询
	 * @param bookType
	 * @return BookType
	 */
	public BookType queryBookType(BookType bookType) {
		
		BookType _bookType = bookTypeDao.getBookType(bookType);
		
		return _bookType;
	}

	/**
	 * @Title: addBookType
	 * @Description: 添加图书类型
	 * @param bookType
	 * @return void
	 */
	public void addBookType(BookType bookType) {
		
		bookTypeDao.addBookType(bookType);
		
	}

	/**
	 * @Title: updateBookType
	 * @Description: 更新图书类型信息
	 * @param bookType
	 * @return void
	 */
	public void updateBookType(BookType bookType) {
		
		bookTypeDao.updateBookType(bookType);
		
	}

	/**
	 * @Title: delBookType
	 * @Description: 删除图书类型信息--出现异常提示
	 * @param bookType
	 * @return void
	 */
	public void delBookTypes(BookType bookType) {
		
		bookTypeDao.delBookTypes(bookType.getIds().split(","));
		
	}
	
	/**
	 * @Title: listStocks
	 * @Description: 图书仓库查询
	 * @param stock
	 * @return List<Stock>
	 */
	public List<Stock> listStocks(Stock stock, int[] sum) {
		
		if (sum != null) {
			sum[0] = stockDao.listStocksCount(stock);
		}
		List<Stock> stocks = stockDao.listStocks(stock);

		
		return stocks;
	}

	/**
	 * @Title: queryStock
	 * @Description: 图书仓库查询
	 * @param stock
	 * @return Stock
	 */
	public Stock queryStock(Stock stock) {
		
		Stock _stock = stockDao.getStock(stock);
		
		return _stock;
	}

	/**
	 * @Title: addStock
	 * @Description: 添加图书仓库
	 * @param stock
	 * @return void
	 */
	public void addStock(Stock stock) {
		
		stockDao.addStock(stock);
		
	}

	/**
	 * @Title: updateStock
	 * @Description: 更新图书仓库信息
	 * @param stock
	 * @return void
	 */
	public void updateStock(Stock stock) {
		
		stockDao.updateStock(stock);
		
	}

	/**
	 * @Title: delStock
	 * @Description: 删除图书仓库信息--出现异常提示
	 * @param stock
	 * @return void
	 */
	public void delStocks(Stock stock) {
		
		stockDao.delStocks(stock.getIds().split(","));
		
	}
	
	/**
	 * @Title: listBooks
	 * @Description: 图书查询
	 * @param book
	 * @return List<Book>
	 */
	public List<Book> listBooks(Book book, int[] sum) {
		
		if (sum != null) {
			sum[0] = bookDao.listBooksCount(book);
		}
		List<Book> books = bookDao.listBooks(book);

		
		return books;
	}

	/**
	 * @Title: queryBook
	 * @Description: 图书查询
	 * @param book
	 * @return Book
	 */
	public Book queryBook(Book book) {
		
		Book _book = bookDao.getBook(book);
		
		return _book;
	}

	/**
	 * @Title: addBook
	 * @Description: 添加图书
	 * @param book
	 * @return void
	 */
	public void addBook(Book book) {
		if (!StringUtil.isEmptyString(book.getBook_desc())) {
			book.setBook_desc(Transcode.htmlEncode(book.getBook_desc()));
		}
		bookDao.addBook(book);
		
	}

	/**
	 * @Title: updateBook
	 * @Description: 更新图书信息
	 * @param book
	 * @return void
	 */
	public void updateBook(Book book) {
		if (!StringUtil.isEmptyString(book.getBook_desc())) {
			book.setBook_desc(Transcode.htmlEncode(book.getBook_desc()));
		}
		bookDao.updateBook(book);
		
	}

	/**
	 * @Title: delBook
	 * @Description: 删除图书信息
	 * @param book
	 * @return void
	 */
	public void delBooks(Book book) {
		
		bookDao.delBooks(book.getIds().split(","));
		
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 图书订单查询
	 * @param orders
	 * @return List<Orders>
	 */
	public List<Orders>  listOrderss(Orders orders,int[] sum){
		
		if (sum!=null) {
			sum[0] = ordersDao.listOrderssCount(orders);
		}
		List<Orders> orderss = ordersDao.listOrderss(orders);
		
		
		return orderss;
	}
	
	/**
	 * @Title: queryOrders
	 * @Description: 图书订单查询
	 * @param orders
	 * @return Orders
	 */
	public Orders  queryOrders(Orders orders){
		
		Orders _orders = ordersDao.getOrders(orders);
		
		return _orders;
	}
	 
	/**
	 * @Title: delOrderss
	 * @Description: 删除图书订单信息
	 * @param orders
	 * @return void
	 */
	public void  delOrderss(Orders orders){
		
		ordersDao.delOrderss(orders.getIds().split(","));
		
	}
	
	/**
	 * @Title: sendOrders
	 * @Description: 订单发货
	 * @param orders
	 * @return 1-成功 2-失败
	 */
	public int sendOrders(Orders orders) {
		orders = ordersDao.getOrders(orders);
		//更新图书库存
		List<Book> books = new ArrayList<Book>();
		OrdersDetail ordersDetail = new OrdersDetail();
		ordersDetail.setOrders_no(orders.getOrders_no());
		List<OrdersDetail> details = ordersDetailDao.listOrdersDetails(ordersDetail);
		for (int i = 0; i < details.size(); i++) {
			OrdersDetail detail = details.get(i);
			Book book = new Book();
			Stock stock = new Stock();
			stock.setStock_id(detail.getStock_id());
			book.setStock(stock);
			book.setBook_no(detail.getBook_no());
			book = bookDao.getBook(book);
			if (book.getBook_count()<detail.getBook_count()) {
				return 2;
			}else {
				book.setBook_count(book.getBook_count() - detail.getBook_count());
				books.add(book);
			}
		}
		if (books.size()>0) {
			for (Book book : books) {
				bookDao.updateBookCount(book);
			}
		}
		
		//确认订单信息
		orders.setOrders_flag(2);//2-已发货 
		ordersDao.updateOrders(orders);
		
		return 1;
		
	}
	
	/**
	 * @Title: listOrdersDetails
	 * @Description: 商品订单明细查询
	 * @param ordersDetail
	 * @return List<OrdersDetail>
	 */
	public List<OrdersDetail> listOrdersDetails(OrdersDetail ordersDetail, int[] sum) {
		if (sum != null) {
			sum[0] = ordersDetailDao.listOrdersDetailsCount(ordersDetail);
		}
		List<OrdersDetail> ordersDetails = ordersDetailDao.listOrdersDetails(ordersDetail);

		return ordersDetails;
	}
	
	/**
	 * @Title: listLogisticss
	 * @Description: 商品订单物流信息查询
	 * @param logistics
	 * @return List<Logistics>
	 */
	public List<Logistics>  listLogisticss(Logistics logistics,int[] sum){
		if (sum!=null) {
			sum[0] = logisticsDao.listLogisticssCount(logistics);
		}
		List<Logistics> logisticss = logisticsDao.listLogisticss(logistics);
		
		return logisticss;
	}
	
	/**
	 * @Title: addLogistics
	 * @Description: 新增物流信息
	 * @param logistics
	 * @return void
	 */
	public void addLogistics(Logistics logistics) {
		logisticsDao.addLogistics(logistics);
	}
	
	
	/**
	 * @Title: listApplys
	 * @Description: 图书调货查询
	 * @param apply
	 * @return List<Apply>
	 */
	public List<Apply> listApplys(Apply apply, int[] sum) {
		
		if (sum != null) {
			sum[0] = applyDao.listApplysCount(apply);
		}
		List<Apply> applys = applyDao.listApplys(apply);

		
		return applys;
	}

	/**
	 * @Title: queryApply
	 * @Description: 图书调货查询
	 * @param apply
	 * @return Apply
	 */
	public Apply queryApply(Apply apply) {
		
		Apply _apply = applyDao.getApply(apply);
		
		return _apply;
	}

	/**
	 * @Title: addApply
	 * @Description: 添加图书调货
	 * @param apply
	 * @return void
	 */
	public void addApply(Apply apply) {
		apply.setApply_date(new Date());
		apply.setApply_flag(1);//1:待确认
		applyDao.addApply(apply);
		
	}

	/**
	 * @Title: updateApply
	 * @Description: 更新图书调货信息
	 * @param apply
	 * @return 1-成功 2-失败
	 */
	public int updateApply(Apply apply) {
		//更新图书库存
		apply = applyDao.getApply(apply);
		Book book2 = new Book();
		Stock stock2 = new Stock();
		stock2.setStock_id(apply.getStock_id2());
		book2.setStock(stock2);
		book2.setBook_no(apply.getBook_no());
		book2 = bookDao.getBook(book2);
		if (book2==null || book2.getBook_count()<apply.getBook_count()) {
			return 2;
		}
		book2.setBook_count(book2.getBook_count() - apply.getBook_count());
		bookDao.updateBookCount(book2);
		Book book1 = new Book();
		Stock stock1 = new Stock();
		stock1.setStock_id(apply.getStock_id());
		book1.setStock(stock1);
		book1.setBook_no(apply.getBook_no());
		book1 = bookDao.getBook(book1);
		book1.setBook_count(book1.getBook_count() + apply.getBook_count());
		bookDao.updateBookCount(book1);
		
		//确认调货
		apply.setApply_flag(2);//2:已确认
		applyDao.updateApply(apply);
		return 1;
	}

	/**
	 * @Title: delApply
	 * @Description: 删除图书调货信息
	 * @param apply
	 * @return void
	 */
	public void delApplys(Apply apply) {
		
		applyDao.delApplys(apply.getIds().split(","));
		
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public BookTypeDao getBookTypeDao() {
		return bookTypeDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public OrdersDao getOrdersDao() {
		return ordersDao;
	}

	public OrdersDetailDao getOrdersDetailDao() {
		return ordersDetailDao;
	}

	public ApplyDao getApplyDao() {
		return applyDao;
	}

	public EvaluateDao getEvaluateDao() {
		return evaluateDao;
	}

	public LogisticsDao getLogisticsDao() {
		return logisticsDao;
	}

	public StockDao getStockDao() {
		return stockDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setBookTypeDao(BookTypeDao bookTypeDao) {
		this.bookTypeDao = bookTypeDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setOrdersDao(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	public void setOrdersDetailDao(OrdersDetailDao ordersDetailDao) {
		this.ordersDetailDao = ordersDetailDao;
	}

	public void setApplyDao(ApplyDao applyDao) {
		this.applyDao = applyDao;
	}

	public void setEvaluateDao(EvaluateDao evaluateDao) {
		this.evaluateDao = evaluateDao;
	}

	public void setLogisticsDao(LogisticsDao logisticsDao) {
		this.logisticsDao = logisticsDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	} 
	
}
