package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;

import db.DBConnection;

public class BoardDAO {
	public List<BoardDTO> boardList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM may_boardview";// view만들거라서 잠시 후 수정합니다.
		// 2022-05-13
		// 서버프로그램 구현
		// 서버프로그램 구현하기
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setB_no(rs.getInt("b_no"));
				dto.setB_title(rs.getString("b_title"));// b_content를 뺀 이유는?
				dto.setB_date(rs.getString("b_date"));
				dto.setB_like(rs.getInt("b_like"));
				dto.setB_count(rs.getInt("b_count"));
				dto.setM_no(rs.getInt("m_no"));
				dto.setM_id(rs.getString("m_id"));
				dto.setM_name(rs.getString("m_name"));
				dto.setCommentcount(rs.getInt("commentcount"));
				dto.setTotalcount(rs.getInt("totalcount"));
				list.add(dto);// 붙여넣기
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return list;
	}

	public void write(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO may_board (b_title, b_content, m_no) "
				+ "VALUES (?, ?, (SELECT m_no FROM may_member WHERE m_id=?))";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getB_title());
			pstmt.setString(2, dto.getB_content());
			pstmt.setString(3, dto.getM_id());
			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public BoardDTO detail(int b_no) {
		BoardDTO dto = new BoardDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM may_boardview WHERE b_no=?";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setB_no(rs.getInt("b_no"));
				dto.setB_title(rs.getString("b_title"));
				dto.setB_content(rs.getString("b_content"));
				dto.setB_date(rs.getString("b_date"));
				dto.setB_like(rs.getInt("b_like"));
				dto.setB_count(rs.getInt("b_count"));
				dto.setM_no(rs.getInt("m_no"));
				dto.setM_id(rs.getString("m_id"));
				dto.setM_name(rs.getString("m_name"));
				dto.setCommentcount(rs.getInt("commentcount"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return dto;
	}

	public void postDel(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE may_board SET b_del=1 " + "WHERE b_no=? AND m_no="
				+ "(SELECT m_no FROM may_member WHERE m_id=?)";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getB_no());
			pstmt.setString(2, dto.getM_id());
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void update(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE may_board SET b_title=?, b_content=? " + "WHERE b_no=? AND m_no=("
				+ "SELECT m_no FROM may_member WHERE m_id=?)";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getB_title());
			pstmt.setString(2, dto.getB_content());
			pstmt.setInt(3, dto.getB_no());
			pstmt.setString(4, dto.getM_id());

			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<CommentDTO> commentList(int b_no) {
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM may_commentview WHERE b_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_no);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setC_no(rs.getInt("C_no"));
				dto.setB_no(rs.getInt("b_no"));
				dto.setC_comment(rs.getString("c_comment"));
				dto.setM_no(rs.getInt("m_no"));
				dto.setC_date(rs.getString("c_date"));
				dto.setC_like(rs.getInt("c_like"));
				dto.setM_id(rs.getString("m_id"));
				dto.setM_name(rs.getString("m_name"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	public void commentWrite(CommentDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO may_comment (b_no, c_comment, m_no) "
				+ "VALUES (?, ?, (SELECT m_no FROM may_member WHERE m_id=?))";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getB_no());
			pstmt.setString(2, dto.getC_comment());
			pstmt.setString(3, dto.getM_id());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void cdel(CommentDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE may_comment SET c_del=1 "
				+ "WHERE c_no=? AND m_no="
				+ "(SELECT m_no FROM may_member WHERE m_id=?)";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getC_no());//물음표에  
			pstmt.setString(2, dto.getM_id());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public CommentDTO commentDetail(CommentDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM may_commentview WHERE c_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getC_no());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setC_comment(rs.getString("c_comment"));
				dto.setC_like(rs.getInt("c_like"));
				dto.setC_date(rs.getString("c_date"));
				dto.setM_id(rs.getString("m_id"));
				dto.setM_name(rs.getString("m_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	public void commentUpdate(CommentDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE may_comment SET c_comment=? WHERE c_no=? "
				+ "AND m_no=(SELECT m_no FROM may_member WHERE m_id=?)";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getC_comment());
			pstmt.setInt(2, dto.getC_no());
			pstmt.setString(3, dto.getM_id());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	//b_count + 1
	public void countUp(int b_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE may_board SET b_count=b_count + 1 WHERE b_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_no);
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void like(int b_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE may_board SET b_like=b_like + 1 WHERE b_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_no);
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	

}