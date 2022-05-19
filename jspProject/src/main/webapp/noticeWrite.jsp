<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/menu.css" rel="stylesheet">
<style type="text/css">
#main{
   margin: 0 auto;
   width: 800px;
   height: 100%;
}
#noticeWriteFrom{
   margin: 0 auto;
   margin-top:100px;
   padding:10px;
   min-height: 500px;
   width: 500px;
   background-color: green;
}
#noticeWriteFrom input{
   margin: 0;
   padding: 0;
   line-height:30px; /* 위아래 중앙정렬 높이만큼 줌 */
   width: 100%;
   height: 30px;
   margin-bottom: 5px;
   box-sizing: border-box; /* 패딩넣으면 넓어지니까 자신의 사이즈로 고정 */
   padding-left: 5px;
}
#noticeWriteFrom textarea {
   margin: 0; /* 여백이 있어서 없앴어요  */
   padding: 5px;
   width: 100%;
   height: 400px;
   margin-bottom: 5px; /* 아래 여백 */
   box-sizing: border-box;
}
</style>

</head>
<body>
	<%@include file="./menu.jsp"%>
	<div id="main">
	
		<div id="noticeWriteFrom">
			<form action="./noticeWrite" method="post" enctype="multipart/form-data">
				<input type="text" name="title" required="required">
				<textarea name="content"></textarea>
				<input type="file" name="file" accept="image/*">
				<button type="submit">공지등록</button>
			</form>
		
		</div>
	</div>
</body>
</html>