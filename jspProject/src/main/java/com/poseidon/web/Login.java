package com.poseidon.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.LoginDAO;
import com.poseidon.dto.LoginDTO;

//web.xml -> 
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 로그인 요청하네요.
		if (request.getParameter("id") != null 
								&& request.getParameter("pw") != null) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			//System.out.println(id);
			//System.out.println(pw);
			LoginDTO dto = new LoginDTO();
			dto.setM_id(id);
			dto.setM_pw(pw);
			//DAO
			LoginDAO dao = new LoginDAO();
			dto = dao.login(dto);
			
			//System.out.println(dto.getCount());
			//System.out.println(dto.getM_name());
			if(dto.getCount() == 1) {
				response.sendRedirect("./main.jsp");
				//주소표시줄까지 같이 바꿉니다.
				//세션 쿠키
				HttpSession session = request.getSession();
				session.setAttribute("m_name", dto.getM_name());
				session.setAttribute("m_id", dto.getM_id());
				
				String m_name = (String) session.getAttribute("m_name");
				String m_id = (String) session.getAttribute("m_id");
			//	System.out.println(m_name);
				//System.out.println(m_id);
				
			} else {
				response.sendRedirect("./index.jsp?error=1024");
			}
			
		} else {
			response.sendRedirect("./index.jsp");
		}

	}

}