package com.tj.project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.project.dto.ReviewDto;

public class ReviewDao {

	public static final int FAIL = 0;
	public static final int SUCCESS = 1;

	private static ReviewDao instance = new ReviewDao();

	public static ReviewDao getInstance() {
		return instance;
	}

	private ReviewDao() {

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

	// 리뷰 등록하기

	public int insertReview(String mid, int pno, String retitle, String recontent, String rephoto, String rephoto2) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REVIEW (RENO, MID, AID, PNO, RETITLE, RECONTENT, REPHOTO, REPHOTO2, REREF, RERE_STEP, RERE_LEVEL) VALUES"
				+ "(REVIEW_SEQ.NEXTVAL, ?, null, ?, ?, ?, ?, ?, REVIEW_SEQ.CURRVAL, 0, 0)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setInt(2, pno);
			pstmt.setString(3, retitle);
			pstmt.setString(4, recontent);
			pstmt.setString(5, rephoto);
			pstmt.setString(6, rephoto2);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			try {

				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}

		return result;
	}

	// 글 갯수 고객들이 보는

	public int getMemberReviewTotCnt(int pno) {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM REVIEW WHERE PNO = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totCnt = rs.getInt(1);
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
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}

		return totCnt;

	}

	// 글 갯수 - 관리자
	public int getReviewTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM REVIEW";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totCnt = rs.getInt(1);
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
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}

		return totCnt;

	}

	// 고객들이 보는 글 목록(startRow, endRow, pno)
	public ArrayList<ReviewDto> getMemberReviews(int startrow, int endrow, int pno) {
		ArrayList<ReviewDto> reviews = new ArrayList<ReviewDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM"
				+ "(SELECT V.*, PNAME, PPHOTO FROM REVIEW V, PRODUCTS P WHERE V.PNO = P.PNO AND P.PNO=? ORDER BY REREF DESC, RERE_STEP) A)"
				+ "WHERE RN BETWEEN ? AND ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int reno = rs.getInt("reno");
				String mid = rs.getString("mid");
				String aid = rs.getString("aid");
				String retitle = rs.getString("retitle");
				String recontent = rs.getString("recontent");
				String rephoto = rs.getString("rephoto");
				String rephoto2 = rs.getString("rephoto2");
				int rehit = rs.getInt("rehit");
				int reref = rs.getInt("reref");
				int rere_step = rs.getInt("rere_step");
				int rere_level = rs.getInt("rere_level");
				Date rerdate = rs.getDate("rerdate");
				String pname = rs.getString("pname");
				String pphoto = rs.getString("pphoto");

				reviews.add(new ReviewDto(reno, mid, aid, pno, retitle, recontent, rephoto, rephoto2, rehit, reref,
						rere_step, rere_level, rerdate, pname, pphoto));

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
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}

		return reviews;
	}

	// 관리자가 보는 리뷰 목록
	public ArrayList<ReviewDto> getAllReviews(int startrow, int endrow) {
		ArrayList<ReviewDto> reviews = new ArrayList<ReviewDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM"
				+ "(SELECT V.*, PNAME, PPHOTO FROM REVIEW V, PRODUCTS P WHERE V.PNO = P.PNO ORDER BY REREF DESC, RERE_STEP) A)"
				+ "WHERE RN BETWEEN ? AND ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int reno = rs.getInt("reno");
				String mid = rs.getString("mid");
				String aid = rs.getString("aid");
				int pno = rs.getInt("pno");
				String retitle = rs.getString("retitle");
				String recontent = rs.getString("recontent");
				String rephoto = rs.getString("rephoto");
				String rephoto2 = rs.getString("rephoto2");
				int rehit = rs.getInt("rehit");
				int reref = rs.getInt("reref");
				int rere_step = rs.getInt("rere_step");
				int rere_level = rs.getInt("rere_level");
				Date rerdate = rs.getDate("rerdate");
				String pname = rs.getString("pname");
				String pphoto = rs.getString("pphoto");

				reviews.add(new ReviewDto(reno, mid, aid, pno, retitle, recontent, rephoto, rephoto2, rehit, reref,
						rere_step, rere_level, rerdate, pname, pphoto));

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
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}

		return reviews;
	}

	// reno로 리뷰 가져오기
	public ReviewDto getReview(int reno) {
		ReviewDto review = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT R.*, PNAME, PPHOTO FROM REVIEW R, PRODUCTS P WHERE R.PNO = P.PNO AND RENO = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reno);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				String mid = rs.getString("mid");
				String aid = rs.getString("aid");
				int pno = rs.getInt("pno");
				String retitle = rs.getString("retitle");
				String recontent = rs.getString("recontent");
				String rephoto = rs.getString("rephoto");
				String rephoto2 = rs.getString("rephoto2");
				int rehit = rs.getInt("rehit");
				int reref = rs.getInt("reref");
				int rere_step = rs.getInt("rere_step");
				int rere_level = rs.getInt("rere_level");
				Date rerdate = rs.getDate("rerdate");
				String pname = rs.getString("pname");
				String pphoto = rs.getString("pphoto");

				review = new ReviewDto(reno, mid, aid, pno, retitle, recontent, rephoto, rephoto2, rehit, reref,
						rere_step, rere_level, rerdate, pname, pphoto);
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
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}

		return review;
	}

	// 히트업하기
	public int hitUp(int reno) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW SET REHIT = REHIT + 1 WHERE RENO = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reno);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			try {

				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}

		return result;
	}

	// 답글 달기 전 스텝 A
	public void preReplyStepA(int reref, int rere_step) { // 스텝 A
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE REVIEW SET RERE_STEP = RERE_STEP + 1 WHERE REREF = ? AND RERE_STEP>?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reref);
			pstmt.setInt(2, rere_step);
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

	}

	// 관리자 답글 달기
	public int reply(String aid, int pno, String retitle, String recontent, int reref, int rere_step, int rere_level) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO REVIEW (RENO, MID, AID, PNO, RETITLE, RECONTENT, REPHOTO, REPHOTO2, REREF, RERE_STEP, RERE_LEVEL) VALUES"
				+ "(REVIEW_SEQ.NEXTVAL, null, ?, ?, ?, ?, null, null, ?, ?, ?)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aid);
			pstmt.setInt(2, pno);
			pstmt.setString(3, retitle);
			pstmt.setString(4, recontent);
			pstmt.setInt(5, reref);
			pstmt.setInt(6, rere_step + 1);
			pstmt.setInt(7, rere_level + 1);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {

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

	public int deleteReview(int reno) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM REVIEW WHERE RENO = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reno);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {

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

}
