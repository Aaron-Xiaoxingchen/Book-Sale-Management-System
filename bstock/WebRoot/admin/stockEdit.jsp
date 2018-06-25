<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.stock!=null && #attr.stock.stock_id!=0">编辑</s:if><s:else>添加</s:else>图书仓库信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="editor/kindeditor.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var num = /^\d+(\.\d+)?$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsStock\\.stock_name").val()==''){
			alert('仓库名称不能为空');
			return;
		}
		if($("#paramsStock\\.province_name").val()==''){
			alert('所在省份不能为空');
			return;
		}
		if($("#paramsStock\\.user\\.user_id").val()=='0'){
			alert('库管员不能为空');
			return;
		}
		$("#paramsStock\\.stock_id").val(0);
		$("#info").attr('action','Admin_addStock.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		if($("#paramsStock\\.stock_name").val()==''){
			alert('仓库名称不能为空');
			return;
		}
		if($("#paramsStock\\.province_name").val()==''){
			alert('所在省份不能为空');
			return;
		}
		if($("#paramsStock\\.user\\.user_id").val()=='0'){
			alert('库管员不能为空');
			return;
		}
		$("#info").attr('action','Admin_saveStock.action').submit();
		 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">图书仓库管理&gt;&gt;<s:if test="#attr.stock!=null && #attr.stock.stock_id!=0">编辑</s:if><s:else>添加</s:else>图书仓库</span>
</div>
<form id="info" name="info" action="Admin_addStock.action" method="post">   
<s:hidden id="paramsStock.stock_id" name="paramsStock.stock_id" value="%{#attr.stock.stock_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.stock!=null && #attr.stock.stock_id!=0">编辑</s:if><s:else>添加</s:else>图书仓库</TD>
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
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 仓库名称：</td>
          <td width="65%">
          	<s:textfield name="paramsStock.stock_name" id="paramsStock.stock_name" value="%{#attr.stock.stock_name}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 所在省份：</td>
          <td width="65%">
          	<s:select id="paramsStock.province_name" name="paramsStock.province_name" value="%{#attr.stock.province_name}" 
	      		list="#attr.plist" listKey="id" listValue="text" 
	      		class="selectstyle" cssStyle="width:155px;" headerKey="" headerValue="请选择">
	        </s:select>
          </td>
        </tr>
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 库&nbsp;管&nbsp;员：</td>
          <td width="65%">
          	<s:select id="paramsStock.user.user_id" name="paramsStock.user.user_id" value="%{#attr.stock.user.user_id}" 
	      		list="#attr.users" listKey="user_id" listValue="real_name" 
	      		class="selectstyle" cssStyle="width:155px;" headerKey="0" headerValue="请选择">
	        </s:select>
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
          	<s:if test="#attr.stock!=null && #attr.stock.stock_id!=0">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</s:else>
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
<script>        
	   KE.show({ 
	            id : 'noticeContent',
	            items:['plainpaste', '|', 'selectall', 'bold','italic'],
	            resizeMode:1
	            
	                    
	   });
	   
</script>
</body>
</html>