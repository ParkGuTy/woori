<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 정보보기</title>
<link href="./css/menu.css" rel="stylesheet">
<style type="text/css">
#main{
	margin: 0 auto;
	width: 800px;
	height: 100%;
}
#loginform{
	margin:0 auto;
	margin-top:150px;
	height: 450px;
	width: 300px;
	background-color: gray;
	padding: 10px;
}
#loginform input, button{
	margin: 0;
	padding: 0;
	background-color: white;
	border: 0;
	height: 40px;
	width: 100%;
}
#loginform input{
	margin-bottom: 10px;
}
#loginform input:hover, button:hover{
	background-color: green;
}
</style>
</head>
<body>
	<%@include file="./menu.jsp"%>
	<div id="main">
		서버에서 오는 값 : ${userInfo }<br>
		아이디 : ${userInfo.id }<br>
		비밀번호 : ${userInfo.passwd }<br>
		이름 : ${userInfo.name }<br>
		주소 : ${userInfo.addr }<br>
		이메일 : ${userInfo.email }<br>
		전화번호 : ${userInfo.tel }<br>
		가입일 : <br>
	</div>
</body>
</html>



