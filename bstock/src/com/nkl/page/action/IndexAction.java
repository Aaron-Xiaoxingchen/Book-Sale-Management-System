package com.nkl.page.action;

import java.util.List;

import com.nkl.common.action.BaseAction;
import com.nkl.common.util.BeanLocator;
import com.nkl.common.util.Param;
import com.nkl.page.domain.Apply;
import com.nkl.page.domain.Book;
import com.nkl.page.domain.BookType;
import com.nkl.page.domain.Evaluate;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.OrdersDetail;
import com.nkl.page.domain.Stock;
import com.nkl.page.domain.User;
import com.nkl.page.manager.IndexManager;

public class IndexAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	IndexManager indexManager = (IndexManager)BeanLocator.getInstance().getBean("indexManager");

	//抓取页面参数
	User paramsUser;
	Book paramsBook;
	BookType paramsBookType;
	Orders paramsOrders;
	OrdersDetail paramsOrdersDetail;
	Apply paramsApply;
	Evaluate paramsEvaluate;
	Logistics paramsLogistics;
	Stock paramsStock;
	String tip;
	
	public String index(){
		try {
			//查询图书信息集合
			if (paramsBook==null) {
				paramsBook = new Book();
			}
			//分页信息设置
			setPagination(paramsBook);
			int[] sum={0};
			List<Book> books = indexManager.listBooks2(paramsBook,sum); 
			Param.setAttribute("books", books);
			setTotalCount(sum[0]);
			
			//查询图书类型
			BookType bookType = new BookType();
			bookType.setStart(-1);
			List<BookType> bookTypes = indexManager.listBookTypes(bookType, null);
			Param.setAttribute("bookTypes", bookTypes);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "index";
	}
	
	/**
	 * @Title: hobby
	 * @Description: 推荐图书
	 * @return String
	 */
	public String hobby(){
		try {
			//查询图书信息集合
			if (paramsBook==null) {
				paramsBook = new Book();
			}
			
			//查询图书类型
			BookType bookType = new BookType();
			bookType.setStart(-1);
			List<BookType> bookTypes = indexManager.listBookTypes(bookType, null);
			Param.setAttribute("bookTypes", bookTypes);
			
			//根据用户条件个性化推荐
			User userFront = (User)Param.getSession("userFront");
			if (userFront==null) {
				return "hobby";
			}
			//分页信息设置
			setPagination(paramsBook);
			paramsBook.setUser_flag(1);
			paramsBook.setUser_hobbys(userFront.getUser_hobby().split(" "));
			int[] sum={0};
			List<Book> books = indexManager.listBooks2(paramsBook,sum); 
			Param.setAttribute("books", books);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "hobby";
	}
	
	/**
	 * @Title: queryBook
	 * @Description: 查询图书详情
	 * @return String
	 */
	public String queryBook(){
		try {
			 //得到图书
			Book book = indexManager.queryBook2(paramsBook);
			Param.setAttribute("book", book);
			
			//查询图书仓库
			Stock stock = new Stock();
			stock.setStart(-1);
			List<Stock> stocks = indexManager.listStocks(stock,null);
			Param.setAttribute("stocks", stocks);
			
			//查询评价集合
			if (paramsEvaluate==null) {
				paramsEvaluate = new Evaluate();
				paramsEvaluate.setBook_no(book.getBook_no());
			}
			//分页信息设置
			setPagination(paramsEvaluate);
			int[] sum={0};
			List<Evaluate> evaluates = indexManager.listEvaluates(paramsEvaluate,sum); 
			Param.setAttribute("evaluates", evaluates);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "bookDetail";
	}
	
	/**
	 * @Title: queryStockBook
	 * @Description: 查询库存图书
	 * @return String
	 */
	public String queryStockBook(){
		try {
			 //得到图书
			Book book = indexManager.queryBook(paramsBook);
			setResult("book", book);
			setResult("flag", "1");
			if (book==null) {
				setResult("flag", "2");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("查询图书库存失败！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: listCard
	 * @Description: 查询购物车
	 * @return String
	 */
	public String listCard(){
		try {
			//查询购物车
			List<OrdersDetail> ordersDetails = indexManager.listCard();
			Param.setAttribute("ordersDetails", ordersDetails);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "card";
	}
	
	/**
	 * @Title: addCard
	 * @Description: 添加到购物车
	 * @return String
	 */
	public String addCard(){
		try {
			//添加到购物车
			int flag = indexManager.addCard(paramsOrdersDetail);
			if (flag==2) {
				setErrorReason("您不能横跨多个省份购买同一本图书");
				return "error2";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("后台服务器异常");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: modifyCard
	 * @Description: 修改购物车
	 * @return String
	 */
	public String modifyCard(){
		try {
			//修改购物车
			indexManager.modifyCard(paramsOrdersDetail);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("修改数量失败！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: delGoodsFromCard
	 * @Description: 从购物车删除
	 * @return String
	 */
	public String delBookFromCard(){
		try {
			//从购物车删除
			indexManager.delBookFromCard(paramsOrdersDetail.getBook_no());
			
			//查询购物车
			List<OrdersDetail> ordersDetails = indexManager.listCard();
			Param.setAttribute("ordersDetails", ordersDetails);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "card";
	}
	
	/**
	 * @Title: clearCard
	 * @Description: 清空购物车
	 * @return String
	 */
	public String clearCard(){
		try {
			//清空购物车
			indexManager.clearCard();
			//查询购物车
			Param.setAttribute("ordersDetails", null);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "card";
	}
	
	/**
	 * @Title: addOrdersShow
	 * @Description: 新增订单页面
	 * @return String
	 */
	public String addOrdersShow(){
		try {
			//查询购物车
			List<OrdersDetail> ordersDetails = indexManager.listCard();
			Param.setAttribute("ordersDetails", ordersDetails);
			
			//查询订单总额
			double orders_money=0;
			for (int i = 0; i < ordersDetails.size(); i++) {
				OrdersDetail ordersDetail = ordersDetails.get(i);
				orders_money+=ordersDetail.getBook_money();//累计总金额
			}
			Param.setAttribute("orders_money", orders_money);
			
			//省份
			Param.setAttribute("plist", indexManager.listProvinces());
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersAdd";
	}
	
	/**
	 * @Title: addOrders
	 * @Description: 新增订单
	 * @return String
	 */
	public String addOrders(){
		try {
			//新增订单
			int flag = indexManager.addOrders(paramsOrders);
			if (flag==2) {
				setErrorReason("您的收货省份和购物车图书的发货省份不一致");
				return "error2";
			}else if (flag==3) {
				setErrorReason("您的账户余额不足");
				return "error2";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("提交订单失败！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: saveUserFront
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveUserFront(){
		try {
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			User userFront = new User();
			userFront.setUser_id(paramsUser.getUser_id());
			userFront = indexManager.getUser(userFront);
			Param.setSession("userFront", userFront);
			
		} catch (Exception e) {
			e.printStackTrace();
			tip = "修改失败";
			return "userInfo";
		}
		tip = "修改成功";
		return "userInfo";
	}
	
	/**
	 * @Title: saveUserFrontPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveUserFrontPass(){
		try {
			 //保存修改个人密码
			indexManager.updateUser(paramsUser);
			//更新session
			User UserFront = (User)Param.getSession("UserFront");
			if (UserFront!=null) {
				UserFront.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("UserFront", UserFront);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tip = "修改失败";
			return "userPwd";
		}
		tip = "修改成功";
		return "userPwd";
	}
	
	/**
	 * @Title: listMyOrderss
	 * @Description: 查询我的商品订单
	 * @return String
	 */
	public String listMyOrderss(){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//获取用户,用户只能查询自己的订单
			User userFront = (User)Param.getSession("userFront");
			if (userFront.getUser_type()==1) {
				paramsOrders.setUser_id(userFront.getUser_id());
			}
			//设置分页信息
			setPagination(paramsOrders);
			//总的条数
			int[] sum={0};
			//查询商品预约列表
			List<Orders> orderss = indexManager.listOrderss(paramsOrders,sum); 
			
			Param.setAttribute("orderss", orderss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersShow";
	}
	
	/**
	 * @Title: listMyOrdersDetails
	 * @Description: 查询我的订单明细
	 * @return String
	 */
	public String listMyOrdersDetails(){
		try {
			if (paramsOrdersDetail==null) {
				paramsOrdersDetail = new OrdersDetail();
			}
			//设置分页信息
			paramsOrdersDetail.setStart(-1);
			//查询订单明细
			List<OrdersDetail> ordersDetails = indexManager.listOrdersDetails(paramsOrdersDetail,null); 
			Param.setAttribute("ordersDetails", ordersDetails);
			
			//订单信息
			Orders orders = new Orders();
			orders.setOrders_no(paramsOrdersDetail.getOrders_no());
			orders = indexManager.queryOrders(orders);
			Param.setAttribute("orders_no", orders.getOrders_no());
			if (ordersDetails!=null && ordersDetails.size()>0) {
				Param.setAttribute("orders_money", orders.getOrders_money());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersDetailShow";
	}
	
	/**
	 * @Title: listLogisticss
	 * @Description: 展示物流信息列表
	 * @return String
	 */
	public String listLogisticss(){
		try {
			//查询物流信息集合
			if (paramsLogistics==null) {
				paramsLogistics = new Logistics();
			}
			//设置分页信息
			paramsLogistics.setStart(-1);
			List<Logistics> logisticss = indexManager.listLogisticss(paramsLogistics,null); 
			Param.setAttribute("logisticss", logisticss);
			
			//订单信息
			Param.setAttribute("orders_no", paramsLogistics.getOrders_no());
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "logisticsShow";
	}
	
	/**
	 * @Title: finishOrders
	 * @Description: 确认收货
	 * @return String
	 */
	public String finishOrders(){
		try {
			//确认收货
			indexManager.finishOrders(paramsOrders);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("确认收货失败！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: addEvaluateShow
	 * @Description: 评价商品界面
	 * @return String
	 */
	public String addEvaluateShow(){
		try {
			//查询订单
			Orders orders = indexManager.queryOrders(paramsOrders);
			Param.setAttribute("orders", orders);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "evaluateAdd";
	}

	/**
	 * @Title: addEvaluate
	 * @Description: 评价商品
	 * @return String
	 */
	public String addEvaluate(){
		try {
			//新增商品评价
			indexManager.addEvaluate(paramsEvaluate);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("评价商品失败！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: reg
	 * @Description: 跳转注册页面
	 * @return String
	 */
	public String reg(){
		return "reg";
	}
	
	/**
	 * @Title: myInfo
	 * @Description: 跳转个人信息页面
	 * @return String
	 */
	public String myInfo(){
		return "userInfo";
	}
	
	/**
	 * @Title: myPwd
	 * @Description: 跳转个人密码页面
	 * @return String
	 */
	public String myPwd(){
		return "userPwd";
	}
	
	/**
	 * @Title: myMoney
	 * @Description: 跳转个人充值页面
	 * @return String
	 */
	public String myMoney(){
		return "userMoney";
	}
	
	/**
	 * @Title: addMoney
	 * @Description: 账户充值
	 * @return String
	 */
	public String addMoney(){
		try {
			//获取用户信息
			User userFront = (User)Param.getSession("userFront");
			//更新账户余额
			userFront.setUser_money(userFront.getUser_money()+paramsUser.getUser_money());
			indexManager.updateUserMoney(userFront);
			
			userFront.setUser_money(userFront.getUser_money());
			Param.setSession("userFront",userFront);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("评价商品失败！");
			return "error2";
		}
		
		return "success";
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public Book getParamsBook() {
		return paramsBook;
	}

	public void setParamsBook(Book paramsBook) {
		this.paramsBook = paramsBook;
	}

	public BookType getParamsBookType() {
		return paramsBookType;
	}

	public void setParamsBookType(BookType paramsBookType) {
		this.paramsBookType = paramsBookType;
	}

	public Orders getParamsOrders() {
		return paramsOrders;
	}

	public void setParamsOrders(Orders paramsOrders) {
		this.paramsOrders = paramsOrders;
	}

	public OrdersDetail getParamsOrdersDetail() {
		return paramsOrdersDetail;
	}

	public Apply getParamsApply() {
		return paramsApply;
	}

	public Evaluate getParamsEvaluate() {
		return paramsEvaluate;
	}

	public Logistics getParamsLogistics() {
		return paramsLogistics;
	}

	public Stock getParamsStock() {
		return paramsStock;
	}

	public void setParamsOrdersDetail(OrdersDetail paramsOrdersDetail) {
		this.paramsOrdersDetail = paramsOrdersDetail;
	}

	public void setParamsApply(Apply paramsApply) {
		this.paramsApply = paramsApply;
	}

	public void setParamsEvaluate(Evaluate paramsEvaluate) {
		this.paramsEvaluate = paramsEvaluate;
	}

	public void setParamsLogistics(Logistics paramsLogistics) {
		this.paramsLogistics = paramsLogistics;
	}

	public void setParamsStock(Stock paramsStock) {
		this.paramsStock = paramsStock;
	}

}
