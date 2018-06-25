<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图书列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
 
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
	<!--  产品检索展示 -->
	 <div id="product_info">
	 		<!--  产品列表 -->
	 		<form name="info" id="info" action="page_index.action" method="post">
	 		<input type="hidden" id="pageNo" name="pageNo" value="${pageNo}"/>
			<div class="list">
					<div class="sign" style="background:none;text-align:right">
						图书名称：<s:textfield name="paramsBook.book_name" value="%{#attr.paramsBook.book_name}" cssClass="inputstyle" cssStlyle="width:100px;"/>&nbsp;
						图书类型：<s:select id="paramsBook.book_type_id" name="paramsBook.book_type_id" value="%{#attr.paramsBook.book_type_id}" 
							      		list="#attr.bookTypes" listKey="book_type_id" listValue="book_type_name" 
							      		cssClass="inputstyle" cssStyle="width:100px;" headerKey="0" headerValue="请选择">
							      </s:select>&nbsp;&nbsp;
						<input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
					</div>
					<div class="products">
					<s:if test="#attr.books!=null&&#attr.books.size()>0">
					<s:iterator value="#attr.books" id="book">
					<div class="product">
						<div class="productPic"><s:a href="page_queryBook.action?paramsBook.book_no=%{#book.book_no}"><img src="images/books/<s:property value='#book.book_pic'/>" /></s:a></div>
						<div class="productText"><span class="title"><s:property value='#book.book_name'/></span>
						<br/>￥<s:property value='#book.book_price'/>
						</div>
					</div>
					</s:iterator>
					</s:if> 
					</div>
					
					<!--  产品分页 -->
					<jsp:include page="page.jsp"></jsp:include>
				    <!--  产品分页 -->

			</div>
			</form>
			<!--  产品列表 -->
			
	 </div>
	 <!--  产品检索展示 -->
	 
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.info.submit();
}
function ChangePage(pagenum)
{
	 document.getElementById("pageNo").value=pagenum;
	 document.info.submit();
}	 
function serch()
{
   document.info.action="page_index.action";
   document.info.submit();
}
</script>
</body>
</html>