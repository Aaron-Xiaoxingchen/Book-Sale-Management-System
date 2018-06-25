<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书信息详情</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">图书信息详情</span>
</div>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">图书信息详情</TD>
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
          <td width="40%" align="center">
          	<img src="../images/books/<s:property value='%{#attr.book.book_pic}'/>" width="100" /> 
          </td>
          <td width="60%" style="line-height:22px;">
          	图书名称：<s:property value="%{#attr.book.book_name}"/>
          	<br/>图书书号：<s:property value="%{#attr.book.book_no}"/>
          	<br/>图书类型：<s:property value="%{#attr.book.bookType.book_type_name}"/>
          	<br/>图书价格：￥<s:property value="%{#attr.book.book_price}"/> 元
          	<br/>所属仓库：<s:property value="%{#attr.book.stock.stock_name}"/>
          	<br/>库存数量：<s:property value="%{#attr.book.book_count}"/> 本
          	<br/>预警数量：<s:property value="%{#attr.book.warn_count}"/> 本
          	<br/>图书描述：<s:property value="%{#attr.book.book_desc}" escape="false"/>
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
          	<input type="button" id="editBtn" Class="btnstyle" value="返 回" onclick="window.history.back(-1);"/> 
          </td>
        </tr>
      </table>
     </td>
   </tr> 
</table>
</body>
</html>