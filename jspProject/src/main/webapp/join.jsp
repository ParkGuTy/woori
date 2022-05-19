<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link href="./css/menu.css" rel="stylesheet">
<style type="text/css">
#main{
	margin: 0 auto;
	width: 800px;
	height: 100%;
}
#joinbox{
	margin: 0 auto;
	width: 430px;
	min-height: 550px;
	background-color: #c1c1c1;
	padding: 10px;
	box-sizing: border-box;
	text-align: center;
	border-radius: 15px;
}
#joinbox input{
	margin-bottom: 10px;
	height: 40px;
}
#joinbox button{
	width: 150px;
	border: 0px;
	font-size: large;
	font-weight:bold;
	height: 40px;
}
</style>
<script type="text/javascript">
//주석처리 : 자바스크립트 영역
function idCheck(){
	//alert("수정되었습니다.");
	var id = $("#id").val();//id에 입력된 값을 가져오기
	//alert(id + "라고 입력했습니다.");//jquery넣어주세요.
	if(id == "" || id.length < 4){
		$("#checkResult").css("color", "red");
		$("#checkResult").text("4글자 이상이어야 합니다.");
	}else{
		//$("#checkResult").css("color", "blue");
		//$("#checkResult").text(id + "라고 입력했습니다.");
		$.ajax({
			url : "./idCheck",
			type : "POST",
			dataType : "html",
			data : {"id" : id},
			success : function(data){
				//alert(data);
				if(data == 0){
					$("#checkResult").css("color", "green");
					$("#checkResult").text(id + "는 가입할 수 있습니다.");
					$("#joinBtn").attr("disabled", false);
				}else{
					$("#checkResult").css("color", "red");
					$("#checkResult").text(id + "는 이미 등록된 ID입니다.");
					$("#joinBtn").attr("disabled", true);
					$("#id").focus();
				}
			},
			error : function(){
				alert("서버가 동작하지 않습니다.");
			}
		});
	}
	
}

function check() {
   var form = document.joinform;
   var id = form.id;
   if(id.value.length == 0){
      alert("아이디를 입력하세요");
      id.focus();
      return false;
   }
   var passwd = form.passwd;
   if(passwd.value.length == 0){
      alert("비밀번호를 입력하세요");
      passwd.focus();
      return false;
   }
   var name = form.name;
   if(name.value.length == 0){
      alert("이름을 입력하세요");
      name.focus();
      return false;
   }
   var age = form.age;
   if(age.value.length == 0){
      alert("나이를 입력하세요");
      age.focus();
      return false;
   }
   var email = form.email;
   if(email.value.length == 0){
      alert("이메일을 입력하세요");
      email.focus();
      return false;
   }
   var tel = form.tel;
   if(tel.value.length == 0){
      alert("전화번호를 입력하세요");
      tel.focus();
      return false;
   }
   //return false;
}
</script>
</head>
<body>
	<%@include file="./menu.jsp"%>
	<div id="main">
		<h1>회원가입</h1>
		
		<div id="joinbox">
			<h1>가입하기</h1>
			빠르고 쉽습니다.
			<hr>
			<form name="joinform" action="./join" method="post" onsubmit="return check()">
			<input type="text" id="id" name="id" placeholder="아이디를 입력하세요" class="form-control" onchange="idCheck()">
			<div id="checkResult">아이디를 확인중입니다.</div>
			<input type="password" name="passwd" placeholder="비밀번호를 입력하세요" class="form-control">
			<input type="text" name="name" placeholder="이름을 입력하세요" class="form-control">
			<input type="number" name="age" placeholder="나이를 입력하세요" class="form-control">
			<input type="text" name="addr" placeholder="주소를 입력하세요" class="form-control">
			<input type="email" name="email" placeholder="이메일을 입력하세요" class="form-control">
			<input type="tel" name="tel" placeholder="전화버호를 입력하세요" class="form-control">
			<button type="submit" id="joinBtn" class="btn btn-success" disabled="disabled">가입하기</button>
			</form>
		</div>
		
		
		
		
		
		
		
	</div>
</body>
</html>