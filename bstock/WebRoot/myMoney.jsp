<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>账户余额充值</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/store.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	var user_id="${userFront.user_id}";
	if(user_id==null || user_id==''){
		alert("请先登录！");
		window.location.href="page_index.action";
	}
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
<div id="middle">
	 <jsp:include page="leftMenu.jsp"></jsp:include>
	 <!--  购物车 -->
	 <div id="product_info">
			<div class="title">个人中心  &gt;&gt;  账户余额充值</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_saveUserFront.action" method="post">    
				 <input type="hidden" name="paramsUser.user_id" value="${userFront.user_id}"/>
				 <table class="ptable" style="margin-bottom:5px;">
					<tr>
			          <td width="15%" align="right" style="padding-right:5px">账户余额：</td>
			          <td colspan="3">${userFront.user_money} 元</td>
			        </tr> 
			        <tr>
			          <td align="right" style="padding-right:5px"><font color="red">*</font> 充值金额：</td>
			          <td colspan="3">
			             <input type="text" id="paramsUser.user_money" name="paramsUser.user_money" value=""/> 元
			          </td>
			        </tr> 
			        <tr class="">
			          <td align="center" height="30" colspan="4">
			            <input type="button" id="saveBtn" Class="btnstyle" value="充 值"/>&nbsp;
			            <span style="color:red">${tip }</span>
			          </td>
			        </tr>
				 </table>
				 </form>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	$(document).ready(function(){
		 var num=/^\d+(\.\d+)?$/;
		 $("#saveBtn").bind('click',function(){
		    if(!num.exec($("#paramsUser\\.user_money").val())){
				alert('充值金额必须为数字');
				return;
			}
			
		    var aQuery = $("#info").serialize();  
			$.post('page_addMoney.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('充值成功！');
							 window.location.reload();
						}else  if(responseObj.err.msg){
							 alert('充值失败：'+responseObj.err.msg);
						}else{
							 alert('充值失败：服务器异常！');
						}	
			},'json');
			 
		 });
		
	});	 
</script>
</body>
</html>