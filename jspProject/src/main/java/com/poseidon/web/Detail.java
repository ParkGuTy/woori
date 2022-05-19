package com.poseidon.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

@WebServlet("/detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Detail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//b_no와요? 이거 숫자인가요?
		if(request.getParameter("b_no") != null 
				&& Util.str2Int(request.getParameter("b_no"))) {
			int b_no = Integer.parseInt(request.getParameter("b_no"));
			//해당 글을 읽어오기 전에 미리 조횟수를 +1
			BoardDAO dao = new BoardDAO();
			//2022-05-18 // 서버프로그램 구현 // 배치 프로그램 구현하기
			dao.countUp(b_no);//조회수 -> 자신의 조횟수에 +1 하는 메소드
			BoardDTO detail = dao.detail(b_no);
			//System.out.println("!!!!!!!");
			//System.out.println(detail.getCommentcount());
			if(detail.getCommentcount() > 0) {
				//System.out.println("댓글이 있습니다!!!");
				//댓글 읽어오는 작업 = List<CommentDTO>
				List<CommentDTO> commList = dao.commentList(b_no);
				request.setAttribute("commList", commList);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
			request.setAttribute("detail", detail);
			rd.forward(request, response);
			
		} else {
			response.sendRedirect("./board");//없으니 다른 곳으로 이동
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
