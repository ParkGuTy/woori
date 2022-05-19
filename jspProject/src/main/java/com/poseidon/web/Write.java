package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;

@WebServlet("/write")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Write() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("m_id") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("./write.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//한글
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String re = null;
		if (session.getAttribute("m_id") != null) {
			if (request.getParameter("title") != null 
					&& request.getParameter("content") != null) {
				//다있다.
				//데이터 베이스에 저장하기 DAO, DTO
				BoardDTO dto = new BoardDTO();
				dto.setB_content(request.getParameter("content"));
				dto.setB_title(Util.HTML2str(request.getParameter("title")));
				dto.setM_id((String) session.getAttribute("m_id"));
				BoardDAO dao = new BoardDAO();
				dao.write(dto);
				
				//이동하기
				re = "./board";
			} else {
				re = "./board";
			}
		} else {
			re = "./index.jsp";
		}
		response.sendRedirect(re);
	}

}
