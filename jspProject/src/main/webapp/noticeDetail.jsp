<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 상세보기</title>
<link href="./css/menu.css" rel="stylesheet">
<style type="text/css">
#main{
	margin: 0 auto;
	width: 800px;
	height: 100%;
}
table {
	width: 100%;
	min-height: 300px;
	border-collapse: collapse;
}
th{
	width: 100px;
	background-color: #c1c1c1;
}
tr{
	min-height:50px;
	border-bottom: 1px red solid;
}
td{
	width : calc(100% - 100px);
}
</style>
</head>
<body>
	<%@include file="./menu.jsp"%>
	<div id="main">
		<h1>공지 상세 보기</h1>
		n_no : ${dto.n_no }<br>
		n_title : ${dto.n_title }<br>
		n_content : ${dto.n_content }<br>
		n_date : ${dto.n_date }<br>
		n_orifilename : ${dto.n_orifilename }<br>
		n_filename : ${dto.n_filename }<br>
		m_no : ${dto.m_no }<br>
		<hr>
		<img alt="업로드된 파일" src="./upload/${dto.n_filename }">
		
		
	<button onclick="location.href='./notice'">게시판으로</button>
	</div>
	
</body>
</html>



