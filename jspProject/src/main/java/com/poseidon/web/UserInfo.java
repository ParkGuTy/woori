package com.poseidon.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.LoginDAO;
import com.poseidon.dto.JoinDTO;

@WebServlet("/userInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("UserInfo -> get");
		//한글처리
		response.setCharacterEncoding("UTF-8");
		//세션가져오기
		HttpSession session = request.getSession();
		if(session.getAttribute("m_id") != null) {
			//데이터베이스로 보낼겁니다. -> 그 m_id에 대한 정보를 가져오니까요 
			//오는 값은? DTO
			JoinDTO dto = new JoinDTO((String) session.getAttribute("m_id"));
			//여기부터는 DAO가 작업합니다.
			/*
			 * 1. jsp에서 데이터베이스 접속, sql, 결과담기 등등등 작업을 모두 다 함.
			 * 2. DTO구분, DAO가 데이터베이스 작업을 하고 결과를 넘겨주면 jsp에서 출력.
			 * 3. Servlet이 DAO, DAO를 불러와서 작업하고 그 결과를 넘겨주는 방법.
			 */
			LoginDAO dao = new LoginDAO();
			dto = dao.userInfo(dto);
//			System.out.println(dto.getAddr());
//			System.out.println(dto.getAge());
//			System.out.println(dto.getEmail());
//			System.out.println(dto.getId());
//			System.out.println(dto.getName());
//			System.out.println(dto.getPasswd());
//			System.out.println(dto.getTel());
			
			PrintWriter pw = response.getWriter();
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<title>" + dto.getId() +"님의 정보</title>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<h1>" + dto.getId() +"님의 정보보기</h1>");
			pw.println("아이디 : " + dto.getId() + "<br>");
			pw.println("암호 : "+ dto.getPasswd() + "<br>");
			pw.println("이름 : "+ dto.getName() + "<br>");
			pw.println("주소 : "+ dto.getAddr() + "<br>");
			pw.println("전화번호 : "+ dto.getTel() + "<br>");
			pw.println("나이 : "+ dto.getAge() + "<br>");
			pw.println("<hr>");
			pw.println("</body>");
			pw.println("</html>");//rd를 사용하면 pw는 필요 없습니다.
			RequestDispatcher rd = request.getRequestDispatcher("./userInfo.jsp");
			//주소표시줄을 유지하고 화면만 변경합니다.
			//값 가져가
			request.setAttribute("userInfo", dto);// 이름, 값
			rd.forward(request, response);//변경시작
			
		} else {
			//없다 -> 로그인하세요
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("UserInfo -> post");
	}

}
