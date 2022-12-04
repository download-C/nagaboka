<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<style type="text/css">

</style>
	<title></title>
<!-- ${pageContext.request.contextPath} -->

<%
// 로그인 여부 확인
if(session!=null) {
	String loginID = (String)session.getAttribute("loginID");
	if(loginID!=null) {
		
	} %>
	
		
	

</head>
<body>

<h1> header.jsp </h1>
<hr>