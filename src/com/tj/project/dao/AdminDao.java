package com.tj.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.project.dto.AdminDto;

public class AdminDao {
	public static final int LOGIN_FAIL = 0;
	public static final int LOGIN_SUCCESS = 1;

	private static AdminDao instance = new AdminDao();

	public static AdminDao getInstance() {
		return instance;
	}

	private AdminDao() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();

		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	public int aLoginCheck(String aid, String apw) {
		int result = AdminDao.LOGIN_FAIL;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ADMIN WHERE AID=? AND APW = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aid);
			pstmt.setString(2, apw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = AdminDao.LOGIN_SUCCESS;
			} else {
				result = AdminDao.LOGIN_FAIL;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return result;
	}

	public AdminDto getAdmin(String aid) {
		AdminDto dto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ADMIN WHERE AID=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String apw = rs.getString("apw");
				String aname = rs.getString("aname");

				dto = new AdminDto(aid, apw, aname);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return dto;

	}

}
