<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.book!=null && #attr.book.book_id!=0">编辑</s:if><s:else>添加</s:else>图书信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="editor/kindeditor.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var num = /^\d+(\.\d+)?$/;
	 $("#addBtn").bind('click',function(){
		KE.sync('noticeContent');
		if($("#paramsBook\\.book_no").val()==''){
			alert('图书书号不能为空');
			return;
		}
		if($("#paramsBook\\.book_name").val()==''){
			alert('图书名称不能为空');
			return;
		}
		if($("#paramsBook\\.bookType\\.book_type_id").val()=='0'){
			alert('图书类型不能为空');
			return;
		}
		if($("#paramsBook\\.book_price").val()!=''){
			if(!num.exec($("#paramsBook\\.book_price").val())){
				alert('图书价格必须为数字');
				return;
			}
		}else{
			$("#paramsBook\\.book_price").val(0);
		}
		if($("#paramsBook\\.stock\\.stock_id").val()=='0'){
			alert('所属仓库不能为空');
			return;
		}
		if($("#paramsBook\\.book_count").val()!=''){
			if(!num.exec($("#paramsBook\\.book_count").val())){
				alert('库存数量必须为数字');
				return;
			}
		}else{
			$("#paramsBook\\.book_count").val(0);
		}
		if($("#paramsBook\\.warn_count").val()!=''){
			if(!num.exec($("#paramsBook\\.warn_count").val())){
				alert('预警数量必须为数字');
				return;
			}
		}else{
			$("#paramsBook\\.warn_count").val(0);
		}
		 
		if($("#paramsBook\\.book_hobby").val()==''){
			alert('图书关键字不能为空');
			return;
		}
		if($("#paramsBook\\.book_pic").val()==''){
			alert('图书图片不能为空');
			return;
		}
		if($("#noticeContent").val()==''){
			alert('图书描述不能为空');
			return;
		}
		$("#paramsBook\\.book_id").val(0);
		$("#info").attr('action','Admin_addBook.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		    KE.sync('noticeContent');
		 	if($("#paramsBook\\.book_name").val()==''){
				alert('图书名称不能为空');
				return;
			}
		 	if($("#paramsBook\\.bookType\\.book_type_id").val()=='0'){
				alert('图书类型不能为空');
				return;
			}
			if($("#paramsBook\\.book_price").val()!=''){
				if(!num.exec($("#paramsBook\\.book_price").val())){
					alert('图书价格必须为数字');
					return;
				}
			}else{
				$("#paramsBook\\.book_price").val(0);
			}
			if($("#paramsBook\\.stock\\.stock_id").val()=='0'){
				alert('所属仓库不能为空');
				return;
			}
		 	if($("#paramsBook\\.book_count").val()!=''){
				if(!num.exec($("#paramsBook\\.book_count").val())){
					alert('库存数量必须为数字');
					return;
				}
			}else{
				$("#paramsBook\\.book_count").val(0);
			}
			if($("#paramsBook\\.warn_count").val()!=''){
				if(!num.exec($("#paramsBook\\.warn_count").val())){
					alert('预警数量必须为数字');
					return;
				}
			}else{
				$("#paramsBook\\.warn_count").val(0);
			}
			if($("#paramsBook\\.book_hobby").val()==''){
				alert('图书关键字不能为空');
				return;
			}
			if($("#paramsBook\\.book_pic").val()==''){
				alert('图书图片不能为空');
				return;
			}
			if($("#noticeContent").val()==''){
				alert('图书描述不能为空');
				return;
			}
			$("#info").attr('action','Admin_saveBook.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">图书管理&gt;&gt;<s:if test="#attr.book!=null && #attr.book.book_id!=0">编辑</s:if><s:else>添加</s:else>图书</span>
</div>
<form id="info" name="info" action="Admin_addBook.action" method="post">   
<s:hidden id="paramsBook.book_id" name="paramsBook.book_id" value="%{#attr.book.book_id}" /> 
<input type="hidden" name="paramsBook.book_pic" id="paramsBook.book_pic" value='<s:property value="#attr.book.book_pic"/>'/>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.book!=null && #attr.book.book_id!=0">编辑</s:if><s:else>添加</s:else>图书</TD>
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
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 图书书号：</td>
          <td width="65%">
            <s:if test="#attr.book!=null && #attr.book.book_id!=0">
            <s:property value="#attr.book.book_no"/>
            </s:if>
            <s:else>
            <s:textfield name="paramsBook.book_no" id="paramsBook.book_no" value="%{#attr.book.book_no}"/>
            </s:else>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 图书名称：</td>
          <td width="65%">
          	<s:textfield name="paramsBook.book_name" id="paramsBook.book_name" value="%{#attr.book.book_name}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 图书类型：</td>
          <td width="65%">
          	<s:select id="paramsBook.bookType.book_type_id" name="paramsBook.bookType.book_type_id" value="%{#attr.book.bookType.book_type_id}" 
	      		list="#attr.bookTypes" listKey="book_type_id" listValue="book_type_name" 
	      		class="selectstyle" cssStyle="width:155px;" headerKey="0" headerValue="请选择">
	        </s:select>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 图书价格：</td>
          <td width="65%">
          	<s:textfield name="paramsBook.book_price" id="paramsBook.book_price" value="%{#attr.book.book_price}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 所属仓库：</td>
          <td width="65%">
          	<s:select id="paramsBook.stock.stock_id" name="paramsBook.stock.stock_id" value="%{#attr.book.stock.stock_id}" 
	      		list="#attr.stocks" listKey="stock_id" listValue="stock_name" 
	      		class="selectstyle" cssStyle="width:155px;" headerKey="0" headerValue="请选择">
	        </s:select>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 库存数量：</td>
          <td width="65%">
          	<s:textfield name="paramsBook.book_count" id="paramsBook.book_count" value="%{#attr.book.book_count}"/>
          </td>
        </tr>
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 预警数量：</td>
          <td width="65%">
          	<s:textfield name="paramsBook.warn_count" id="paramsBook.warn_count" value="%{#attr.book.warn_count}"/>
          </td>
        </tr>
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 图书关键字：</td>
          <td width="65%">
          	<s:textfield name="paramsBook.book_hobby" id="paramsBook.book_hobby" value="%{#attr.book.book_hobby}"/>
          	<span style="color:red">多项使用空格隔开</span>
          </td>
        </tr>
        <tr>
		  <td align="right" style="padding-right:5px">图书图片：</td>
		  <td align="left" colspan="3">
		    <img id="userImg" src="../images/books/<s:property value='#attr.book.book_pic'/>" width="70" height="80" style="border:0px;"/>
	        &nbsp;<span id="op" style="display:none"><img src="images/loading004.gif"  height="80" /></span>
	      </td>
	    </tr>
	    <tr>
		  <td align="right" style="padding-right:5px"></td>
	      <td align="left" colspan="3">
	          <iframe name="uploadPage" src="uploadImg.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
	       </td>
	    </tr>
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font>图书描述：</td>
          <td width="65%">
          	<textarea style="width:400px;height:200px" name="paramsBook.book_desc" id="noticeContent"><s:property value="#attr.book.book_desc" escape="false"/>
          	</textarea>
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
          	<s:if test="#attr.book!=null && #attr.book.book_id!=0">
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