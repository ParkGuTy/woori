package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.poseidon.dto.JoinDTO;

import db.DBConnection;

public class JoinDAO {
	public int join(JoinDTO dto) {
		//가입 여부를 int로 확인해보겠습니다.
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO may_member "
				+ "(m_id, m_pw, m_name, m_age, m_juso, m_email, m_tel) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(	4, dto.getAge());
			pstmt.setString(5, dto.getAddr());
			pstmt.setString(6, dto.getEmail());
			pstmt.setString(7, dto.getTel());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int idCheck(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM may_member WHERE m_id=?";
		int result = 1;//기본값으로 1 = 이미 등록된 ID입니다.
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
				//System.out.println("결과는 : " + result + "개가 나왔습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	
	
	
	
	
	
}
