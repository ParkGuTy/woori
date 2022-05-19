package com.poseidon.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;

@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("b_no") != null 
				&& session.getAttribute("m_id") != null
				&& Util.str2Int(request.getParameter("b_no"))) {
			
			BoardDTO dto = new BoardDTO();
			dto.setB_no(Integer.parseInt(request.getParameter("b_no")));
			dto.setM_id((String)session.getAttribute("m_id"));
			
			BoardDAO dao = new BoardDAO();
			dao.postDel(dto);
			response.sendRedirect("./board");
			
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
