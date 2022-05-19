<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div id="menu">
		<div id="navi">
			<ul>
				<li>
					<a href="./notice"><img alt="rainbow" src="./img/rainbow.png">공지사항</a>
				</li>
			
				<li>
					<a href="./main"><img src="./img/news.png">메인</a>
				</li>
				
				<li>
					<a href="./board"><img src="./img/print.png">보드</a>
				</li>
			<% if(session.getAttribute("m_name") != null){ %>
				<li>
					<a href="./userInfo">
						<img src="./img/users.png">${sessionScope.m_name }님 반갑습니다.
					</a>
				</li>
				<li>
					<img src="./img/logout.png" onclick="logout()"></li>
			<%} else { %>
				<li>
					<img src="./img/login.png" onclick="location.href='./index'"></li>
			<% } %>
			</ul>
		</div>
	</div>
<script type="text/javascript">
function logout(){
	if(      confirm("로그아웃 하시겠습니까?")    ){
		//alert("로그아웃을 진행합니다.");
		location.href="./logout";
	}
	
}










</script>