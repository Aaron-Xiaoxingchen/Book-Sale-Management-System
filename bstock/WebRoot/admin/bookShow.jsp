<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}


function serch()
{
   document.info.action="Admin_listBooks.action";
   document.info.submit();
}
function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的图书！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delBooks.action?paramsBook.ids="+ids;
       document.info.submit();
    }
    
}
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
  document.info.action="Admin_listBooks.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listBooks.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">图书管理&gt;&gt;图书查询</span>
</div>
<form name="info" id="info" action="Admin_listBooks.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">图书列表</td>
    <td width="" align="right">
            图书名称：
      <input type="text" name="paramsBook.book_name" value="${paramsBook.book_name}" class="inputstyle" Style="width:100px;"/>&nbsp;
            图书类型：
      <s:select name="paramsBook.bookType.book_type_id" value="%{#attr.paramsBook.bookType.book_type_id}" 
      		list="#attr.bookTypes" listKey="book_type_id" listValue="book_type_name" 
      		cssClass="selectstyle" cssStyle="width:100px;" headerKey="0" headerValue="请选择">
      </s:select>&nbsp;
            所在仓库：
      <s:select name="paramsBook.stock.stock_id" value="%{#attr.paramsBook.stock.stock_id}" 
      		list="#attr.stocks" listKey="stock_id" listValue="stock_name" 
      		cssClass="selectstyle" cssStyle="width:100px;" headerKey="0" headerValue="请选择">
      </s:select>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <s:if test="#attr.flag!=1">
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
      </s:if>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
   	 <s:if test="#attr.flag!=1">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     </s:if>
     <td width="40" align="center">序号</td>
     <td width="" align="center">图书书号</td>
     <td width="" align="center">图书名称</td>
     <td width="" align="center">图书类型</td>
     <td width="" align="center">图书价格</td>
     <td width="" align="center">所在仓库</td>
     <td width="" align="center">库存数量</td>
     <td width="" align="center">预警数量</td>
     <td width="" align="center">操作</td>
   </tr>
   <s:if test="#attr.books!=null && #attr.books.size()>0">
   <s:iterator value="#attr.books" id="book" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <s:if test="#attr.flag!=1">
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#book.book_id}" cssStyle="vertical-align:text-bottom;"/></td>
     </s:if>
     <td width="" align="center"><s:property value="#status.index+#attr.paramsBook.start+1"/></td>
     <td width="" align="center"><s:property value="#book.book_no"/></td>
     <td width="" align="center"><s:property value="#book.book_name"/></td>
     <td width="" align="center"><s:property value="#book.bookType.book_type_name"/></td>
     <td width="" align="center">￥<s:property value="#book.book_price"/></td>
     <td width="" align="center"><s:property value="#book.stock.stock_name"/></td>
     <s:if test="#book.book_count lte #book.warn_count">
      <td width="" align="center" style="background-color:red"><s:property value="#book.book_count"/></td>
     </s:if>
     <s:else>
     <td width="" align="center"><s:property value="#book.book_count"/></td>
     </s:else>
     <td width="" align="center"><s:property value="#book.warn_count"/></td>
     <td width="" align="center">
       <s:a href="Admin_queryBook.action?paramsBook.book_id=%{#book.book_id}">详情</s:a>&nbsp;&nbsp;
       <s:if test="#attr.flag!=1">
       <s:a href="Admin_editBook.action?paramsBook.book_id=%{#book.book_id}">编辑</s:a>
       </s:if>
     </td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="10" align="center"><b>&lt;不存在图书信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>