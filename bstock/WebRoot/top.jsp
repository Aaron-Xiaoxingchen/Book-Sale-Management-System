<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div id="login">
	<div id="infoField" <s:if test="#attr.userFront==null">style="display:none;"</s:if>>
		<s:property value="#attr.userFront.real_name"/>，欢迎您登录！
		<input type="button" id="loginOutBtn" class="btnstyle" value="退出"/>
	</div>
	<div id="loginField" <s:if test="#attr.Front!=null">style="display:none;"</s:if>>
		用户名：<input type="text" id="user_name" class="inputstyle" name="user_name"  style="width:100px"/>
		密码：<input type="password" id="user_pass" class="inputstyle" name="user_pass"  style="width:100px"/>
		<input type="button" id="loginInBtn" class="btnstyle" value="登录"/>
		<input type="button" id="regBtn" class="btnstyle" value="注册"/>
	</div>
	
</div>
<div id="top"></div>
<div id="navMenu">
	<ul>
	  <li style="background-image:none"><a href="index.jsp">  图书浏览</a></li>
	  <li><a href="page_hobby.action">图书推荐</a></li>
	  <li><a href="page_listCard.action">购物车</a></li>
	  <li><a href='page_myInfo.action'>个人中心</a></li>
	</ul>
</div>
<script type="text/javascript" src="js/login.js"></script>