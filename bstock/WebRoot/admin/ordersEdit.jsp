<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预约取书</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var book_count2 = '<s:property value="%{#attr.book.book_count2}"/>';
	 var num = /^\d+(\.\d+)?$/;
	 $("#addBtn").bind('click',function(){
		if(book_count2=='0'){
			alert('现存图书数量为0，无法取书');
			return;
		}
		if($("#paramsBorrow\\.return_date").val()==''){
			alert('归还日期不能为空');
			return;
		}
		if($("#paramsBorrow\\.borrow_money").val()!=''){
			if(!num.exec($("#paramsBorrow\\.borrow_money").val())){
				alert('借阅押金必须为数字');
				return;
			}
		}else{
			$("#paramsBorrow\\.borrow_money").val(0);
		}
		$("#paramsBorrow\\.borrow_id").val(0);
		$("#info").attr('action','Admin_finishOrders.action').submit();
		 
	 });
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">图书预约管理&gt;&gt;预约取书</span>
</div>
<form id="info" name="info" action="Admin_finishOrders.action" method="post">   
<s:hidden id="paramsBorrow.borrow_id" name="paramsBorrow.borrow_id" value="0" /> 
<s:hidden id="paramsBorrow.orders_id" name="paramsBorrow.orders_id" value="%{#attr.orders.orders_id}" /> 
<s:hidden id="paramsBorrow.user_id" name="paramsBorrow.user_id" value="%{#attr.orders.user_id}" /> 
<s:hidden id="paramsBorrow.book_id" name="paramsBorrow.book_id" value="%{#attr.orders.book_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">图书预约</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font>图书名称：</td>
          <td width="65%">
          	<s:property value="%{#attr.book.book_name}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font>现存数量：</td>
          <td width="65%">
          	<s:property value="%{#attr.book.book_count2}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font>预约日期：</td>
          <td width="65%">
          	<s:property  value="%{#attr.today}"/>
          </td>
        </tr>
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font>归还日期：</td>
          <td width="65%">
          	<s:textfield name="paramsBorrow.return_date" id="paramsBorrow.return_date"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
          </td>
        </tr>
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font>借阅押金：</td>
          <td width="65%">
          	<s:textfield name="paramsBorrow.borrow_money" id="paramsBorrow.borrow_money" />
          </td>
        </tr>
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<input type="button" id="addBtn" Class="btnstyle" value="预约取书" />
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>