<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/product.css">
<link rel="stylesheet" type="text/css" href="css/message.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript">
 $(document).ready(function(){
	 
	 //点击购买
	 var user_id = "${userFront.user_id}";
	 var num = /^\d+$/;
	 $("#addCard").bind('click',function(){
		 if(user_id==''){
			 alert('请先登录');
			 return;
		 }
		 if(!num.exec($("#book_count").val())){
			 alert('购买数量必须为数字');
			 return;
		 }
		 if(Number($("#book_count").val()) > Number($("#book_count_real").val())){
			 alert('购买数量大于库存数量');
			 return;
		 }
		 var aQuery = $("#info").serialize();
		 $.post('page_addCard.action',aQuery,
		 	function(responseObj) {
		 			if(responseObj.success) {	
		 				window.location.href="page_listCard.action";
		 			}else  if(responseObj.err.msg){
		 				 alert('失败：'+responseObj.err.msg);
		 			}else{
		 				 alert('失败：服务器异常！');
		 			}	
		 },'json');
	 });
	 
	 //仓库变动处理
	 function changeStock(){
		 var stock_idV = $("#stock_id").val();
		 $("#province_name").val($("#stock_id").find("option:selected").text());
		 var aQuery = {
			"paramsBook.book_no":$("#book_no").val(),
			"paramsBook.stock.stock_id":stock_idV
		 };
		 $.post('page_queryStockBook.action',aQuery,
		 	function(responseObj) {
		 			if(responseObj.success) {	
		 				var book = responseObj.data.book;
		 				var flag = responseObj.data.flag;
		 				if(flag=="2"){
		 					 $("#book_count_real").val(0);
		 					 $("#book_count_realSpan").html("（无货）");
		 					 $("#addCard").attr("disabled",true);
		 				}else{
		 					 $("#book_name").val(book.book_name);
		 					 $("#book_nameSpan").html(book.book_name);
		 					 $("#book_price").val(book.book_price);
		 					 $("#book_priceSpan").html(book.book_price);
		 					 $("#book_count_real").val(book.book_count);
		 					 $("#book_count_realSpan").html("（库存数量："+book.book_count+"）");
		 					 $("#addCard").removeAttr("disabled");
		 				}
		 			}else  if(responseObj.err.msg){
		 				 alert('失败：'+responseObj.err.msg);
		 			}else{
		 				 alert('失败：服务器异常！');
		 			}	
		 },'json');
	 }
	 $("#stock_id").change(function(){
		 changeStock();
	 });
	 
	 changeStock();
}); 
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
 
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middleBg">
	<!--  产品检索介绍 -->
	 <div id="product_info">
			<div class="productShow">
					<div class="title" id="book_nameSpan"><s:property value="#attr.book.book_name"/></div>
					<div class="typehr"></div>
					<div class="pictext">
							<div class="pic"><img src="images/books/<s:property value='#attr.book.book_pic'/>" width="250px" height="250px"/></div>
							<div class="text">
									<form name="info" id="info" action="page_addCard.action" method="post">
									<input type="hidden" name="paramsOrdersDetail.book_no" id="book_no" value="<s:property value='#attr.book.book_no'/>"/>
									<input type="hidden" name="paramsOrdersDetail.book_name" id="book_name" value="<s:property value='#attr.book.book_name'/>"/>
									<input type="hidden" name="paramsOrdersDetail.book_price" id="book_price" value="<s:property value='#attr.book.book_price'/>"/>
									<input type="hidden" name="paramsOrdersDetail.province_name" id="province_name" value=""/>
									<input type="hidden" id="book_count_real" value="<s:property value='#attr.book.book_count'/>"/>
									<div class="textTop" style="height:175px;line-height:35px;">
											图书书号：<span style="color:black"><s:property value="#attr.book.book_no"/></span>
											<br/>图书类型：<span style="color:black"><s:property value="#attr.book.book_type_name"/></span>
											<br/>图书价格：<span id="book_priceSpan" style="color:black">￥<s:property value='#attr.book.book_price'/></span>
											<br/>销售省份：
											<s:select id="stock_id" name="paramsOrdersDetail.stock_id" value="" 
									      		  list="#attr.stocks" listKey="stock_id" listValue="province_name" emptyOption="false"
									      		  cssClass="selectstyle" cssStyle="width:105px;">
									        </s:select>
											<span id="book_count_realSpan" style="color:black">（库存数量：0）</span>
											<br/>购买数量：<input type="text" id="book_count" name="paramsOrdersDetail.book_count" value="1" Class="inputstyle" style="width:100px"/>
									</div>
									<div class="textBtn">
										<img id="addCard" src="images/addCard.png" style="border:none;cursor:pointer;vertical-align:middle" />&nbsp;&nbsp;
									</div>
									</form>
							</div>
					</div>
					<div class="typehr"></div>
					<div class="title">图书详情介绍</div>
					<div class="typehr"></div>
					<div class="intro">
						<s:property value="#attr.book.book_descShow" escape="false"  />
					</div>
					<div class="typehr"></div>
					<div class="title">用户评价</div>
					<div class="typehr"></div>
					
					<!-- 信息开始 -->
					 <s:if test="totalCount > 0">
					 <s:iterator value="#attr.evaluates" id="sblog">
					 <div class="messages">
						 <div class="messages_left">
							<div class="nickName"><s:property value="#sblog.nick_name" /></div>
							<div class="headphoto"><img src="images/head/head001.gif"/></div>
						</div>
				
						<div class="messages_right">
							<div class="time">
								评价等级：<s:property value="#sblog.evaluate_levelDesc" /> &nbsp;&nbsp;&nbsp;&nbsp;
								<%--<img src="images/time.gif" valign="middle"/>--%> 
								评价时间：<s:property value="#sblog.evaluate_dateDesc" />　
							</div>
				
							<div class="sblog_title">
								 <s:property value="#sblog.evaluate_content" />
							</div>
							
						</div>
					 </div>
					 </s:iterator>
					 </s:if>
					<!-- 信息结束 -->
					
					<jsp:include page="page.jsp"></jsp:include>
			</div>

			 
			
	 </div>
	 <!--  产品检索 -->
	 
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script type="text/javascript">
var book_no = "<s:property value='#attr.book.book_no' />";
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  window.location.href="page_queryBook.action?paramsBook.book_no="+book_no+"&paramsEvaluate.book_no="+book_no+"&pageNo="+pagenum;
}
function ChangePage(pagenum)
{
  window.location.href="page_queryBook.action?paramsBook.book_no="+book_no+"&paramsEvaluate.book_no="+book_no+"&pageNo="+pagenum;
}
</script>
</body>
</html>