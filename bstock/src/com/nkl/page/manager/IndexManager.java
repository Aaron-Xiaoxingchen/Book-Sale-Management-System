package com.nkl.page.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.Param;
import com.nkl.common.util.StringUtil;
import com.nkl.page.dao.ApplyDao;
import com.nkl.page.dao.BookDao;
import com.nkl.page.dao.BookTypeDao;
import com.nkl.page.dao.EvaluateDao;
import com.nkl.page.dao.LogisticsDao;
import com.nkl.page.dao.OrdersDao;
import com.nkl.page.dao.OrdersDetailDao;
import com.nkl.page.dao.StockDao;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.Book;
import com.nkl.page.domain.BookType;
import com.nkl.page.domain.Evaluate;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.OrdersDetail;
import com.nkl.page.domain.Stock;
import com.nkl.page.domain.User;

public class IndexManager {

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
	
	public void  updateUserMoney(User user){
		
		userDao.updateUserMoney(user);
		
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
	 * @Title: listBooks
	 * @Description: 查询图书信息
	 * @param book
	 * @return List<Book>
	 */
	public List<Book>  listBooks(Book book,int[] sum){
		
		if (sum!=null) {
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
	 * @Title: listBooks2
	 * @Description: 查询图书信息
	 * @param book
	 * @return List<Book>
	 */
	public List<Book>  listBooks2(Book book,int[] sum){
		
		if (sum!=null) {
			sum[0] = bookDao.listBooksCount2(book);
		}
		List<Book> books = bookDao.listBooks2(book);
		
		return books;
	}
	
	/**
	 * @Title: queryBook2
	 * @Description: 图书查询
	 * @param book
	 * @return Book
	 */
	public Book queryBook2(Book book) {
		
		Book _book = bookDao.getBook2(book);
		
		return _book;
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
	 * @Title: listEvaluates
	 * @Description: 查询商品评价信息
	 * @param evaluate
	 * @return List<Evaluate>
	 */
	public List<Evaluate>  listEvaluates(Evaluate evaluate,int[] sum){
		if (sum!=null) {
			sum[0] = evaluateDao.listEvaluatesCount(evaluate);
		}
		List<Evaluate> evaluates = evaluateDao.listEvaluates(evaluate);
		return evaluates;
	}
	
	/**
	 * @Title: listLogisticss
	 * @Description: 查询商品物流信息
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
	 * @Title: addCard
	 * @Description: 添加购物车
	 * @param ordersDetail
	 * @return 1-成功 2-失败
	 */
	@SuppressWarnings("unchecked")
	public int addCard(OrdersDetail ordersDetail) {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) Param.getSession("card");
		if (card==null) {
			card = new ArrayList<OrdersDetail>();
		}
		OrdersDetail oldDetail = getBookFromCard(ordersDetail.getBook_no());
		if (oldDetail==null) {//新增商品
			//计算总额
			double book_money = ordersDetail.getBook_price()*ordersDetail.getBook_count();
			ordersDetail.setBook_money(book_money);
			card.add(ordersDetail);
		}else {
			if (!oldDetail.getProvince_name().equals(ordersDetail.getProvince_name())) {
				return 2;
			}
			//修改购物车商品
			card.remove(oldDetail);
			oldDetail.setBook_count(oldDetail.getBook_count()+ordersDetail.getBook_count());
			double book_money = oldDetail.getBook_price()*oldDetail.getBook_count();
			oldDetail.setBook_money(book_money);
			card.add(oldDetail);
		}
		Param.setSession("card", card);
		return 1;
		
	}
	
	/**
	 * @Title: modifyCard
	 * @Description: 修改购物车商品
	 * @param ordersDetail
	 * @return 1-成功 2-失败
	 */
	@SuppressWarnings("unchecked")
	public void modifyCard(OrdersDetail ordersDetail) {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) Param.getSession("card");
		OrdersDetail oldDetail = getBookFromCard(ordersDetail.getBook_no());
		
		//修改购物车商品
		card.remove(oldDetail);
		oldDetail.setBook_count(ordersDetail.getBook_count());
		double book_money = oldDetail.getBook_price()*oldDetail.getBook_count();
		oldDetail.setBook_money(book_money);
		card.add(oldDetail);
		Param.setSession("card", card);
		
	}
	
	/**
	 * @Title: delBookFromCard
	 * @Description: 从购物车删除商品
	 * @param book_id
	 */
	@SuppressWarnings("unchecked")
	public void delBookFromCard(String book_no) {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) Param.getSession("card");
		if (card!=null) {
			for (OrdersDetail ordersDetail : card) {
				if (ordersDetail.getBook_no().equals(book_no)) {
					card.remove(ordersDetail);
					break;
				}
			}
		}
		Param.setSession("card", card);
		
	}
	
	/**
	 * @Title: clearCard
	 * @Description: 清空购物车
	 */
	public void clearCard() {
		//清空购物车
		Param.removeSession("card");
		
	}
	
	@SuppressWarnings("unchecked")
	private OrdersDetail getBookFromCard(String book_no) {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) Param.getSession("card");
		if (card!=null) {
			for (OrdersDetail ordersDetail : card) {
				if (ordersDetail.getBook_no().equals(book_no)) {
					return ordersDetail;
				}
			}
		}else {
			return null;
		}
		return null;
	}
	
	/**
	 * @Title: addOrders
	 * @Description: 添加商品订单
	 * @param orders
	 * @return 1-成功 2-失败
	 */
	@SuppressWarnings("unchecked")
	public int addOrders(Orders orders) {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) Param.getSession("card");
		//校验地址
		double orders_money=0;
		for (int i = 0; i < card.size(); i++) {
			OrdersDetail ordersDetail = card.get(i);
			orders_money+=ordersDetail.getBook_money();//累计总金额
			if (!ordersDetail.getProvince_name().equals(orders.getProvince_name())) {
				return 2;
			}
		}
		
		//更新用户余额
		User user = new User();
		user.setUser_id(orders.getUser_id());
		user = userDao.getUser(user);
		if (user.getUser_money()< orders_money) {
			return 3;
		}
		user.setUser_money(user.getUser_money()-orders_money);
		userDao.updateUserMoney(user);
		
		//生成订单号
		String orders_no = DateUtil.dateToDateString(new Date(), "yyyyMMddHHmmss")+orders.getUser_id();
		orders.setOrders_no(orders_no);
		//订单日期
		orders.setOrders_date(new Date());
		//1：待发货
		orders.setOrders_flag(1);

		for (int i = 0; i < card.size(); i++) {
			OrdersDetail ordersDetail = card.get(i);
			ordersDetail.setOrders_no(orders_no);//设置订单号
			//保存订单明细
			ordersDetailDao.addOrdersDetail(ordersDetail);
		}
		//设置总额
		orders.setOrders_money(orders_money);
		//保存订单
		ordersDao.addOrders(orders);
		
		//清空购物车
		Param.removeSession("card");
		
		return 1;
		
		
	}

	/**
	 * @Title: listCard
	 * @Description: 查询购物车
	 * @return List<OrdersDetail>
	 */
	@SuppressWarnings("unchecked")
	public List<OrdersDetail> listCard() {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) Param.getSession("card");
		if (card==null) {
			card = new ArrayList<OrdersDetail>();
		}
		return card;
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 商品订单查询
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
	 * @Description: 商品订单查询
	 * @param orders
	 * @return Orders
	 */
	public Orders  queryOrders(Orders orders){
		
		Orders _orders = ordersDao.getOrders(orders);
		
		return _orders;
	}
	
	/**
	 * @Title: listOrdersDetails
	 * @Description: 订单明细查询
	 * @param ordersDetail
	 * @return List<Borrow>
	 */
	public List<OrdersDetail> listOrdersDetails(OrdersDetail ordersDetail, int[] sum) {
		
		if (sum != null) {
			sum[0] = ordersDetailDao.listOrdersDetailsCount(ordersDetail);
		}
		List<OrdersDetail> ordersDetails = ordersDetailDao.listOrdersDetails(ordersDetail);

		
		return ordersDetails;
	}
	
	/**
	 * @Title: finishOrders
	 * @Description: 确认收货
	 * @param Orders
	 * @return void
	 */
	public void finishOrders(Orders orders) {
		orders = ordersDao.getOrders(orders);
		//确认收货
		orders.setOrders_flag(3);
		ordersDao.updateOrders(orders);
	}
	
	/**
	 * @Title: addEvaluate
	 * @Description: 添加商品评价
	 * @param evaluate
	 * @return void
	 */
	public void addEvaluate(Evaluate evaluate) {
		
		//添加商品评价
		evaluate.setEvaluate_date(new Date());
		evaluateDao.addEvaluateBatch(evaluate);
		
		//更新订单为已评价
		Orders orders = new Orders();
		orders.setOrders_no(evaluate.getOrders_no());
		orders = ordersDao.getOrders(orders);
		orders.setOrders_flag(4);
		ordersDao.updateOrders(orders);
		
		
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
