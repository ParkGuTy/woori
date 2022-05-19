package com.poseidon.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("get으로 들어옵니다.");
		//세션 불러오기 위해서 
		//세션 종료 ---- 2개 : m_name / m_id			
		HttpSession session = request.getSession();
		if(session.getAttribute("m_name") != null) {
			session.removeAttribute("m_name");//세션정보 삭제하기
		}
		if(session.getAttribute("m_id") != null) {
			session.removeAttribute("m_id");
		}
		session.invalidate();//세션 정보 초기화
		
		//페이지 이동
		response.sendRedirect("./index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
/*
invalidate() 메소드는 세션의 모든 속성 값을 제거하기 때문에 
removeAttribute()메소드를 사용할 때 처럼 각 속성 값들을 
하나씩 제거할 필요가 없다.
invalidate() 메소드는 모든 속성을 제거하기 때문에 세션 유지 시간이 지났을 때 
세션이 초기화되는 것과 같은 효과를 가져온다.

초기화가 되지않는다면 서버입장에서는 세션정보를 계속 가지고있게되어 
부담이 되고 또한 보안상의 문제가 있다.
따라서 일반적으로 삭제보다는 초기화를 많이 사용한다.
주로 로그아웃기능시 사용한다.
*/

