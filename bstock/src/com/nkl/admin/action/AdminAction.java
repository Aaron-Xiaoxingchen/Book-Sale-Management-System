package com.nkl.admin.action;

import java.util.Date;
import java.util.List;

import com.nkl.admin.manager.AdminManager;
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

public class AdminAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	AdminManager adminManager = (AdminManager)BeanLocator.getInstance().getBean("adminManager");

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
	int flag;
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.getUser(admin);
			Param.setSession("admin", admin);
			
		} catch (Exception e) {
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}
		setSuccessTip("编辑成功", "modifyInfo.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = (User)Param.getSession("admin");
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("admin", admin);
			}
			
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}
		setSuccessTip("修改成功", "modifyPwd.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户
	 * @return String
	 */
	public String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查询注册用户
			paramsUser.setUser_type(1);
			//设置分页信息
			setPagination(paramsUser);
			int[] sum={0};
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加用户页面
	 * @return String
	 */
	public String addUserShow(){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @return String
	 */
	public String addUser(){
		try {
			 //添加用户
			paramsUser.setUser_type(1);
			paramsUser.setReg_date(new Date());
			adminManager.addUser(paramsUser);
			setSuccessTip("添加用户成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("添加用户异常", "Admin_listUsers.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑用户
	 * @return String
	 */
	public String editUser(){
		try {
			 //得到用户
			User user = adminManager.getUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑用户
	 * @return String
	 */
	public String saveUser(){
		try {
			 //保存编辑用户
			adminManager.updateUser(paramsUser);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		setSuccessTip("编辑用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户
	 * @return String
	 */
	public String delUsers(){
		try {
			 //删除用户
			adminManager.delUsers(paramsUser);

			setSuccessTip("删除用户成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("删除用户异常", "Admin_listUsers.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listStockers
	 * @Description: 查询仓库管理员
	 * @return String
	 */
	public String listStockers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查询注册仓库管理员
			paramsUser.setUser_type(2);
			//设置分页信息
			setPagination(paramsUser);
			int[] sum={0};
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询仓库管理员异常", "main.jsp");
			return "infoTip";
		}
		
		return "stockerShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加仓库管理员页面
	 * @return String
	 */
	public String addStockerShow(){
		return "stockerEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加仓库管理员
	 * @return String
	 */
	public String addStocker(){
		try {
			 //添加仓库管理员
			paramsUser.setUser_type(2);
			paramsUser.setReg_date(new Date());
			adminManager.addUser(paramsUser);
			setSuccessTip("添加仓库管理员成功", "Admin_listStockers.action");
		} catch (Exception e) {
			setErrorTip("添加仓库管理员异常", "Admin_listStockers.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑仓库管理员
	 * @return String
	 */
	public String editStocker(){
		try {
			 //得到仓库管理员
			User user = adminManager.getUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询仓库管理员异常", "Admin_listStockers.action");
			return "infoTip";
		}
		
		return "stockerEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑仓库管理员
	 * @return String
	 */
	public String saveStocker(){
		try {
			 //保存编辑仓库管理员
			adminManager.updateUser(paramsUser);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "stockerEdit";
		}
		setSuccessTip("编辑仓库管理员成功", "Admin_listStockers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除仓库管理员
	 * @return String
	 */
	public String delStockers(){
		try {
			 //删除仓库管理员
			adminManager.delUsers(paramsUser);

			setSuccessTip("删除仓库管理员成功", "Admin_listStockers.action");
		} catch (Exception e) {
			setErrorTip("删除仓库管理员异常，请确认该管理员没有关联任何仓库", "Admin_listStockers.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listBookTypes
	 * @Description: 查询图书类型
	 * @return String
	 */
	public String listBookTypes(){
		try {
			if (paramsBookType==null) {
				paramsBookType = new BookType();
			}
			
			//设置分页信息
			setPagination(paramsBookType);
			//总的条数
			int[] sum={0};
			//查询图书类型列表
			List<BookType> bookTypes = adminManager.listBookTypes(paramsBookType,sum); 
			
			Param.setAttribute("bookTypes", bookTypes);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询图书类型异常", "main.jsp");
			return "infoTip";
		}
		
		return "bookTypeShow";
	}
	
	/**
	 * @Title: addBookTypeShow
	 * @Description: 显示添加图书类型页面
	 * @return String
	 */
	public String addBookTypeShow(){
		return "bookTypeEdit";
	}
	
	/**
	 * @Title: addBookType
	 * @Description: 添加图书类型
	 * @return String
	 */
	public String addBookType(){
		try {
			//检查图书类型是否存在
			BookType bookType = new BookType();
			bookType.setBook_type_name(paramsBookType.getBook_type_name());
			bookType = adminManager.queryBookType(bookType);
			if (bookType!=null) {
				tip="失败，该类型已经存在！";
				Param.setAttribute("bookType", paramsBookType);
				return "bookTypeEdit";
			}
			
			 //添加图书类型
			adminManager.addBookType(paramsBookType);
			
			setSuccessTip("添加成功", "Admin_listBookTypes.action");
		} catch (Exception e) {
			setErrorTip("添加图书类型异常", "Admin_listBookTypes.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editBookType
	 * @Description: 编辑图书类型
	 * @return String
	 */
	public String editBookType(){
		try {
			 //得到图书类型
			BookType bookType = adminManager.queryBookType(paramsBookType);
			Param.setAttribute("bookType", bookType);
			
		} catch (Exception e) {
			setErrorTip("查询图书类型异常", "Admin_listBookTypes.action");
			return "infoTip";
		}
		
		return "bookTypeEdit";
	}
	
	/**
	 * @Title: saveBookType
	 * @Description: 保存编辑图书类型
	 * @return String
	 */
	public String saveBookType(){
		try {
			//检查图书类型是否存在
			BookType bookType = new BookType();
			bookType.setBook_type_name(paramsBookType.getBook_type_name());
			bookType = adminManager.queryBookType(bookType);
			if (bookType!=null&&bookType.getBook_type_id().intValue()!=paramsBookType.getBook_type_id().intValue()) {
				tip="失败，该类型已经存在！";
				Param.setAttribute("bookType", paramsBookType);
				return "bookTypeEdit";
			}
			
			 //保存编辑图书类型
			adminManager.updateBookType(paramsBookType);
			
			setSuccessTip("编辑成功", "Admin_listBookTypes.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("bookType", paramsBookType);
			return "bookTypeEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delBookTypes
	 * @Description: 删除图书类型
	 * @return String
	 */
	public String delBookTypes(){
		try {
			 //删除图书类型
			adminManager.delBookTypes(paramsBookType);
			
			setSuccessTip("删除图书类型成功", "Admin_listBookTypes.action");
		} catch (Exception e) {
			setErrorTip("删除图书类型异常，请确认没有该类型的图书", "Admin_listBookTypes.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listStocks
	 * @Description: 查询图书仓库
	 * @return String
	 */
	public String listStocks(){
		try {
			if (paramsStock==null) {
				paramsStock = new Stock();
			}
			//设置分页信息
			setPagination(paramsStock);
			int[] sum={0};
			List<Stock> stocks = adminManager.listStocks(paramsStock,sum); 
			Param.setAttribute("stocks", stocks);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询图书仓库异常", "main.jsp");
			return "infoTip";
		}
		
		return "stockShow";
	}
	
	/**
	 * @Title: addStockShow
	 * @Description: 显示添加图书仓库页面
	 * @return String
	 */
	public String addStockShow(){
		//查询仓管员
		User user = new User();
		user.setStart(-1);
		user.setUser_type(2);
		List<User> users = adminManager.listUsers(user, null);
		Param.setAttribute("users", users);
		
		//省份
		Param.setAttribute("plist", adminManager.listProvinces());
		
		return "stockEdit";
	}
	
	/**
	 * @Title: addStock
	 * @Description: 添加图书仓库
	 * @return String
	 */
	public String addStock(){
		try {
			
			//检查图书仓库是否存在
			Stock stock = new Stock();
			stock.setProvince_name(paramsStock.getProvince_name());
			stock = adminManager.queryStock(stock);
			if (stock!=null) {
				tip="失败，该省份仓库已经存在！";
				Param.setAttribute("stock", paramsStock);
				
				//查询仓管员
				User user = new User();
				user.setStart(-1);
				user.setUser_type(2);
				List<User> users = adminManager.listUsers(user, null);
				Param.setAttribute("users", users);
				
				//省份
				Param.setAttribute("plist", adminManager.listProvinces());
				
				return "stockEdit";
			}
			 //添加图书仓库
			adminManager.addStock(paramsStock);

			setSuccessTip("添加图书仓库成功", "Admin_listStocks.action");
		} catch (Exception e) {
			setErrorTip("添加图书仓库异常", "Admin_listStocks.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: editStock
	 * @Description: 编辑图书仓库
	 * @return String
	 */
	public String editStock(){
		try {
			 //得到图书仓库
			Stock stock = adminManager.queryStock(paramsStock);
			Param.setAttribute("stock", stock);
			
			//查询仓管员
			User user = new User();
			user.setStart(-1);
			user.setUser_type(2);
			List<User> users = adminManager.listUsers(user, null);
			Param.setAttribute("users", users);
			
			//省份
			Param.setAttribute("plist", adminManager.listProvinces());
			
		} catch (Exception e) {
			setErrorTip("查询图书仓库异常", "Admin_listStocks.action");
			return "infoTip";
		}
		
		return "stockEdit";
	}
	
	/**
	 * @Title: saveStock
	 * @Description: 保存编辑图书仓库
	 * @return String
	 */
	public String saveStock(){
		try {
			 //保存编辑图书仓库
			adminManager.updateStock(paramsStock);
			setSuccessTip("编辑图书仓库成功", "Admin_listStocks.action");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("编辑图书仓库失败", "Admin_listStocks.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delStocks
	 * @Description: 删除图书仓库
	 * @return String
	 */
	public String delStocks(){
		try {
			 //删除图书仓库
			adminManager.delStocks(paramsStock);

			setSuccessTip("删除图书仓库成功", "Admin_listStocks.action");
		} catch (Exception e) {
			setErrorTip("删除图书仓库异常，请确认该仓库没有任何图书库存", "Admin_listStocks.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listBooks
	 * @Description: 查询图书
	 * @return String
	 */
	public String listBooks(){
		try {
			if (paramsBook==null) {
				paramsBook = new Book();
			}
			//设置分页信息
			setPagination(paramsBook);
			int[] sum={0};
			//用户身份限制
			User admin = (User)Param.getSession("admin");
			if (admin.getUser_type()==2 && flag!=1) {
				paramsBook.setUser_id(admin.getUser_id());
			}
			List<Book> books = adminManager.listBooks(paramsBook,sum); 
			Param.setAttribute("books", books);
			setTotalCount(sum[0]);
			
			//查询图书类型
			BookType bookType = new BookType();
			bookType.setStart(-1);
			List<BookType> bookTypes = adminManager.listBookTypes(bookType, null);
			Param.setAttribute("bookTypes", bookTypes);
			
			//查询图书仓库
			Stock stock = new Stock();
			stock.setStart(-1);
			if (admin.getUser_type()==2 && flag!=1) {
				stock.setUser(admin);
			}
			List<Stock> stocks = adminManager.listStocks(stock, null);
			Param.setAttribute("stocks", stocks);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("查询图书异常", "main.jsp");
			return "infoTip";
		}
		
		return "bookShow";
	}
	
	/**
	 * @Title: addBookShow
	 * @Description: 显示添加图书页面
	 * @return String
	 */
	public String addBookShow(){
		//查询图书类型
		BookType bookType = new BookType();
		bookType.setStart(-1);
		List<BookType> bookTypes = adminManager.listBookTypes(bookType, null);
		Param.setAttribute("bookTypes", bookTypes);
		
		//查询图书仓库
		Stock stock = new Stock();
		stock.setStart(-1);
		//用户身份限制
		User admin = (User)Param.getSession("admin");
		if (admin.getUser_type()==2) {
			stock.setUser(admin);
		}
		List<Stock> stocks = adminManager.listStocks(stock, null);
		Param.setAttribute("stocks", stocks);
		
		return "bookEdit";
	}
	
	/**
	 * @Title: addBook
	 * @Description: 添加图书
	 * @return String
	 */
	public String addBook(){
		try {
			
			//检查图书书号是否存在
			Book book = new Book();
			book.setBook_no(paramsBook.getBook_no());
			book.setStock(paramsBook.getStock());
			book = adminManager.queryBook(book);
			if (book!=null) {
				tip="失败，该仓库里面该书号已经存在！";
				Param.setAttribute("book", paramsBook);
				
				//查询图书类型
				BookType bookType = new BookType();
				bookType.setStart(-1);
				List<BookType> bookTypes = adminManager.listBookTypes(bookType, null);
				Param.setAttribute("bookTypes", bookTypes);
				
				//查询图书仓库
				Stock stock = new Stock();
				stock.setStart(-1);
				//用户身份限制
				User admin = (User)Param.getSession("admin");
				if (admin.getUser_type()==2) {
					stock.setUser(admin);
				}
				List<Stock> stocks = adminManager.listStocks(stock, null);
				Param.setAttribute("stocks", stocks);
				
				return "bookEdit";
			}
			 //添加图书
			adminManager.addBook(paramsBook);

			setSuccessTip("添加图书成功", "Admin_listBooks.action");
		} catch (Exception e) {
			setErrorTip("添加图书异常", "Admin_listBooks.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: queryBook
	 * @Description: 查询图书详情
	 * @return String
	 */
	public String queryBook(){
		try {
			 //得到图书
			Book book = adminManager.queryBook(paramsBook);
			Param.setAttribute("book", book);
			
		} catch (Exception e) {
			setErrorTip("查询图书异常", "Admin_listBooks.action");
			return "infoTip";
		}
		
		return "bookDetail";
	}
	 
	/**
	 * @Title: editBook
	 * @Description: 编辑图书
	 * @return String
	 */
	public String editBook(){
		try {
			 //得到图书
			Book book = adminManager.queryBook(paramsBook);
			Param.setAttribute("book", book);
			
			//查询图书类型
			BookType bookType = new BookType();
			bookType.setStart(-1);
			List<BookType> bookTypes = adminManager.listBookTypes(bookType, null);
			Param.setAttribute("bookTypes", bookTypes);
			
			//查询图书仓库
			Stock stock = new Stock();
			stock.setStart(-1);
			//用户身份限制
			User admin = (User)Param.getSession("admin");
			if (admin.getUser_type()==2) {
				stock.setUser(admin);
			}
			List<Stock> stocks = adminManager.listStocks(stock, null);
			Param.setAttribute("stocks", stocks);
			
		} catch (Exception e) {
			setErrorTip("查询图书异常", "Admin_listBooks.action");
			return "infoTip";
		}
		
		return "bookEdit";
	}
	
	/**
	 * @Title: saveBook
	 * @Description: 保存编辑图书
	 * @return String
	 */
	public String saveBook(){
		try {
			 //保存编辑图书
			adminManager.updateBook(paramsBook);
			setSuccessTip("编辑图书成功", "Admin_listBooks.action");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("编辑图书失败", "Admin_listBooks.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delBooks
	 * @Description: 删除图书
	 * @return String
	 */
	public String delBooks(){
		try {
			 //删除图书
			adminManager.delBooks(paramsBook);

			setSuccessTip("删除图书成功", "Admin_listBooks.action");
		} catch (Exception e) {
			setErrorTip("删除图书异常", "Admin_listBooks.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 查询图书订单
	 * @return String
	 */
	public String listOrderss(){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//获取用户,用户只能查询自己的借阅
			User admin = (User)Param.getSession("admin");
			if (admin.getUser_type()==1) {
				paramsOrders.setUser_id(admin.getUser_id());
			}else if (admin.getUser_type()==2) {
				paramsOrders.setStocker_id(admin.getUser_id());
			}
			//设置分页信息
			setPagination(paramsOrders);
			//总的条数
			int[] sum={0};
			//查询图书订单列表
			List<Orders> orderss = adminManager.listOrderss(paramsOrders,sum); 
			
			Param.setAttribute("orderss", orderss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("查询图书订单异常", "main.jsp");
			return "infoTip";
		}
		
		return "ordersShow";
	}
	
	/**
	 * @Title: sendOrders
	 * @Description: 订单发货
	 * @return String
	 */
	public String sendOrders(){
		try {
			 //订单发货
			int flag = adminManager.sendOrders(paramsOrders);
			if (flag==2) {
				setErrorTip("订单发货失败，库存图书数量不足", "Admin_listOrderss.action");
				return "infoTip";
			}
			
			setSuccessTip("订单发货成功", "Admin_listOrderss.action");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("订单发货异常", "Admin_listOrderss.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delOrderss
	 * @Description: 删除图书订单
	 * @return String
	 */
	public String delOrderss(){
		try {
			 //删除图书订单
			adminManager.delOrderss(paramsOrders);
			
			setSuccessTip("删除图书订单成功", "Admin_listOrderss.action");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("删除图书订单异常", "Admin_listOrderss.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listOrdersDetails
	 * @Description: 查询商品订单明细
	 * @return String
	 */
	public String listOrdersDetails(){
		try {
			if (paramsOrdersDetail==null) {
				paramsOrdersDetail = new OrdersDetail();
			}
			//设置分页信息
			setPagination(paramsOrdersDetail);
			//总的条数
			int[] sum={0};
			//查询商品订单明细
			List<OrdersDetail> ordersDetails = adminManager.listOrdersDetails(paramsOrdersDetail,sum); 
			Param.setAttribute("ordersDetails", ordersDetails);
			setTotalCount(sum[0]);
			
			Orders orders = new Orders();
			orders.setOrders_no(paramsOrdersDetail.getOrders_no());
			orders = adminManager.queryOrders(orders);
			Param.setAttribute("orders_no", orders.getOrders_no());
			if (ordersDetails!=null && ordersDetails.size()>0) {
				Param.setAttribute("orders_money", orders.getOrders_money());
			}
			
			
		} catch (Exception e) {
			setErrorTip("查询商品订单明细异常", "main.jsp");
			return "infoTip";
		}
		
		return "ordersDetailShow";
	}
	
	/**
	 * @Title: listLogisticss
	 * @Description: 查询商品订单物流信息
	 * @return String
	 */
	public String listLogisticss(){
		try {
			if (paramsLogistics==null) {
				paramsLogistics = new Logistics();
			}
			//设置分页信息不分页
			paramsLogistics.setStart(-1);
			//查询商品订单物流信息
			List<Logistics> logisticss = adminManager.listLogisticss(paramsLogistics,null); 
			Param.setAttribute("logisticss", logisticss);
			
			Param.setAttribute("orders_no", paramsLogistics.getOrders_no());
			
		} catch (Exception e) {
			setErrorTip("查询商品订单物流信息异常", "main.jsp");
			return "infoTip";
		}
		
		return "logisticsShow";
	}
	
	/**
	 * @Title: addLogisticsShow
	 * @Description: 显示添加物流信息页面
	 * @return String
	 */
	public String addLogisticsShow(){
		Param.setAttribute("orders_no", paramsLogistics.getOrders_no());
		return "logisticsEdit";
	}
	
	/**
	 * @Title: addLogistics
	 * @Description: 添加物流信息
	 * @return String
	 */
	public String addLogistics(){
		try {
			 //添加物流信息
			adminManager.addLogistics(paramsLogistics);

			setSuccessTip("添加物流信息成功", "Admin_listLogisticss.action?paramsLogistics.orders_no="+paramsLogistics.getOrders_no());
		} catch (Exception e) {
			setErrorTip("添加物流信息异常", "Admin_listLogisticss.action?paramsLogistics.orders_no="+paramsLogistics.getOrders_no());
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listApplys
	 * @Description: 查询图书调货
	 * @return String
	 */
	public String listApplys(){
		try {
			if (paramsApply==null) {
				paramsApply = new Apply();
			}
			//设置分页信息
			setPagination(paramsApply);
			//总的条数
			int[] sum={0};
			//获取用户,用户只能查询自己的借阅
			User admin = (User)Param.getSession("admin");
			if (admin.getUser_type()==2) {
				paramsApply.setUser_id(admin.getUser_id());
			}
			//查询图书调货列表
			List<Apply> applys = adminManager.listApplys(paramsApply,sum); 
			
			Param.setAttribute("applys", applys);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询图书调货异常", "main.jsp");
			return "infoTip";
		}
		
		return "applyShow";
	}
	
	/**
	 * @Title: listApplysAssess
	 * @Description: 查询图书调货申请
	 * @return String
	 */
	public String listApplysAssess(){
		try {
			if (paramsApply==null) {
				paramsApply = new Apply();
			}
			//设置分页信息
			setPagination(paramsApply);
			//总的条数
			int[] sum={0};
			//获取用户,用户只能查询自己的借阅
			User admin = (User)Param.getSession("admin");
			paramsApply.setStocker_id(admin.getUser_id());
			//查询图书调货列表
			List<Apply> applys = adminManager.listApplys(paramsApply,sum); 
			
			Param.setAttribute("applys", applys);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询图书调货异常", "main.jsp");
			return "infoTip";
		}
		
		return "applyAssessShow";
	}
	
	/**
	 * @Title: addApplyShow1
	 * @Description: 显示添加图书调货页面1
	 * @return String
	 */
	public String addApplyShow1(){
		try {
			if (paramsBook==null) {
				paramsBook = new Book();
			}
			//设置分页信息
			setPagination(paramsBook);
			int[] sum={0};
			//用户身份限制
			User admin = (User)Param.getSession("admin");
			if (admin.getUser_type()==2) {
				paramsBook.setUser_id(admin.getUser_id());
			}
			List<Book> books = adminManager.listBooks(paramsBook,sum); 
			Param.setAttribute("books", books);
			setTotalCount(sum[0]);
			
			//查询图书类型
			BookType bookType = new BookType();
			bookType.setStart(-1);
			List<BookType> bookTypes = adminManager.listBookTypes(bookType, null);
			Param.setAttribute("bookTypes", bookTypes);
			
			//查询图书仓库
			Stock stock = new Stock();
			stock.setStart(-1);
			if (admin.getUser_type()==2 && flag!=1) {
				stock.setUser(admin);
			}
			List<Stock> stocks = adminManager.listStocks(stock, null);
			Param.setAttribute("stocks", stocks);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("查询图书异常", "main.jsp");
			return "infoTip";
		}
		
		return "applyEdit1";
	}
	
	/**
	 * @Title: addApplyShow
	 * @Description: 显示添加图书调货页面
	 * @return String
	 */
	public String addApplyShow(){
		//查询图书
		Book book = adminManager.queryBook(paramsBook);
		Param.setAttribute("book", book);
		//查询调货仓库
		Stock stock = new Stock();
		stock.setStart(-1);
		List<Stock> stocks = adminManager.listStocks(stock, null);
		if (stocks!=null && stocks.size()>0) {
			stocks.remove(book.getStock());
		}
		Param.setAttribute("stocks", stocks);
		
		return "applyEdit";
	}
	
	/**
	 * @Title: addApply
	 * @Description: 添加图书调货
	 * @return String
	 */
	public String addApply(){
		try {
			 //添加图书调货
			adminManager.addApply(paramsApply);
			
			setSuccessTip("添加成功", "Admin_listApplys.action");
		} catch (Exception e) {
			setErrorTip("添加图书调货异常", "Admin_listApplys.action");
		}
		
		return "infoTip";
	}
	
	
	/**
	 * @Title: assessApply
	 * @Description: 确认图书调货
	 * @return String
	 */
	public String assessApply(){
		try {
			 //确认图书调货
			int flag = adminManager.updateApply(paramsApply);
			if (flag==2) {
				setErrorTip("确认调货失败，调货仓库库存数量不足", "Admin_listApplysAssess.action");
				return "infoTip";
			}
			
			setSuccessTip("确认调货成功", "Admin_listApplysAssess.action");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("确认调货失败", "Admin_listApplysAssess.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delApplys
	 * @Description: 删除图书调货申请
	 * @return String
	 */
	public String delApplys(){
		try {
			 //删除图书调货
			adminManager.delApplys(paramsApply);
			
			setSuccessTip("删除图书调货申请成功", "Admin_listApplys.action");
		} catch (Exception e) {
			setErrorTip("删除图书调货申请异常", "Admin_listApplys.action");
		}
		
		return "infoTip";
	}
	
	private boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
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

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
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
