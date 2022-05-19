package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poseidon.dto.JoinDTO;
import com.poseidon.dto.LoginDTO;

import db.DBConnection;

public class LoginDAO {
	//사용자 정보 가져오는 메소드 userInfo
	public JoinDTO userInfo(JoinDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM may_member WHERE m_id=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setName(rs.getString("m_name"));
				dto.setPasswd(rs.getString("m_pw"));
				dto.setAddr(rs.getString("m_juso"));
				dto.setEmail(rs.getString("m_email"));
				dto.setTel(rs.getString("m_tel"));
				dto.setAge(rs.getInt("m_age"));
				//dto.setDate(rs.getString("m_date"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				try {
					if(rs != null) {rs.close();}
					if(pstmt != null) {pstmt.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return dto;
	}
	
	
	//로그인
	public LoginDTO login(LoginDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT count(*) as count, m_name FROM may_member WHERE m_id=? AND m_pw=?";
		ResultSet rs = null;

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getM_id());
			pstmt.setString(2, dto.getM_pw());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto.setCount(rs.getInt("count"));
				dto.setM_name(rs.getString("m_name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				//if(con != null) {con.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
}
