package com.poseidon.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

@WebServlet("/cdel")
public class Cdel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cdel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//System.out.println(request.getParameter("b_no"));
		//System.out.println(request.getParameter("c_no"));
		//System.out.println(session.getAttribute("m_id"));
		//꼭 검사하세요.
		if(request.getParameter("b_no") != null 
				&& request.getParameter("c_no") != null 
				&& session.getAttribute("m_id") != null) {
			//데이터베이스로 보내기
			CommentDTO dto = new CommentDTO();
			dto.setB_no(Integer.parseInt(request.getParameter("b_no")));
			dto.setC_no(Integer.parseInt(request.getParameter("c_no")));
			dto.setM_id((String) session.getAttribute("m_id"));
			
			BoardDAO dao = new BoardDAO();
			dao.cdel(dto);
			
		}else {
			//System.out.println("에러입니다.");
		}		
		response.sendRedirect("./detail?b_no="
								+ request.getParameter("b_no"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
