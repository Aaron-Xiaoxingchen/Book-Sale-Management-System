<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加调货申请信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var num = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsApply\\.stock_id2").val()=='0'){
			alert('调货仓库不能为空');
			return;
		}
		var stock_name2 = $("#paramsApply\\.stock_id2").find("option:selected").text();
		var stock_name = $("#paramsApply\\.stock_name").val();
		if(stock_name2==stock_name){
			alert('调货仓库不能为本库');
			return;
		}
		if(!num.exec($("#paramsApply\\.book_count").val())){
			alert('调货数量必须为数字');
			return;
		}
		$("#paramsApply\\.stock_name2").val(stock_name2);
		$("#info").attr('action','Admin_addApply.action').submit();
		 
	 });
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">调货申请管理&gt;&gt;添加调货申请</span>
</div>
<form id="info" name="info" action="Admin_addApply.action" method="post">   
<s:hidden id="paramsApply.user_id" name="paramsApply.user_id" value="%{#attr.admin.user_id}" /> 
<s:hidden id="paramsApply.real_name" name="paramsApply.real_name" value="%{#attr.admin.real_name}" /> 
<s:hidden id="paramsApply.stock_id" name="paramsApply.stock_id" value="%{#attr.book.stock.stock_id}" /> 
<s:hidden id="paramsApply.stock_name" name="paramsApply.stock_name" value="%{#attr.book.stock.stock_name}" /> 
<s:hidden id="paramsApply.book_no" name="paramsApply.book_no" value="%{#attr.book.book_no}" /> 
<s:hidden id="paramsApply.book_name" name="paramsApply.book_name" value="%{#attr.book.book_name}" /> 
<s:hidden id="paramsApply.stock_name2" name="paramsApply.stock_name2" value="" />
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">添加调货申请</TD>
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
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 申请仓库：</td>
          <td width="65%">
            <s:property value="#attr.book.stock.stock_name"/>
          </td>
        </tr>
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 图书书号：</td>
          <td width="65%">
            <s:property value="#attr.book.book_no"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 图书名称：</td>
          <td width="65%">
            <s:property value="#attr.book.book_name"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 调货仓库：</td>
          <td width="65%">
          	<s:select id="paramsApply.stock_id2" name="paramsApply.stock_id2"
	      		list="#attr.stocks" listKey="stock_id" listValue="stock_name" 
	      		class="selectstyle" cssStyle="width:155px;" headerKey="0" headerValue="请选择">
	        </s:select>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 调货数量：</td>
          <td width="65%">
          	<s:textfield name="paramsApply.book_count" id="paramsApply.book_count" value=""/>
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
          	<input type="button" id="addBtn" Class="btnstyle" value="提交申请" />
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