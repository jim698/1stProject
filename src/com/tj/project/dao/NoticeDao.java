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

import com.tj.project.dto.NoticeDto;

public class NoticeDao {

	public static final int FAIL = 0;
	public static final int SUCCESS = 1;

	private static NoticeDao instance = new NoticeDao();

	public static NoticeDao getInstance() {
		return instance;
	}

	private NoticeDao() {

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

	public int getNoticeTotCnt() {

		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM NOTICE";

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

	// 글 목록

	public ArrayList<NoticeDto> getAllNotice(int startRow, int endRow) {
		ArrayList<NoticeDto> notices = new ArrayList<NoticeDto>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM NOTICE ORDER BY NRDATE DESC) A) WHERE RN BETWEEN ? AND ?";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int nno = rs.getInt("nno");
				String aid = rs.getString("aid");
				String ntitle = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				int nhit = rs.getInt("nhit");
				Date nrdate = rs.getDate("nrdate");

				notices.add(new NoticeDto(nno, aid, ntitle, ncontent, nhit, nrdate));

			}

		} catch (Exception e) {
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

		return notices;
	}

	// NNO로 글 DTO 가져오기
	public NoticeDto getNotice(int nno) {
		NoticeDto notice = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM NOTICE WHERE NNO = ?";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				String aid = rs.getString("aid");
				String ntitle = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				int nhit = rs.getInt("nhit");
				Date nrdate = rs.getDate("nrdate");

				notice = new NoticeDto(nno, aid, ntitle, ncontent, nhit, nrdate);

			}

		} catch (Exception e) {
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

		return notice;
	}

	// 히트업하기
	public void hitUp(int nno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICE SET NHIT = NHIT + 1 WHERE NNO = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
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

	// 공지글 상세보기 - hitup 적용
	public NoticeDto contentView(int nno) {
		hitUp(nno);
		NoticeDto notice = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM NOTICE WHERE NNO = ?";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				String aid = rs.getString("aid");
				String ntitle = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				int nhit = rs.getInt("nhit");
				Date nrdate = rs.getDate("nrdate");

				notice = new NoticeDto(nno, aid, ntitle, ncontent, nhit, nrdate);

			}

		} catch (Exception e) {
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

		return notice;

	}

	// 공지글 modifyView dto 가져오기
	public NoticeDto modifyView(int nno) {

		NoticeDto notice = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM NOTICE WHERE NNO = ?";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				String aid = rs.getString("aid");
				String ntitle = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				int nhit = rs.getInt("nhit");
				Date nrdate = rs.getDate("nrdate");

				notice = new NoticeDto(nno, aid, ntitle, ncontent, nhit, nrdate);

			}

		} catch (Exception e) {
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

		return notice;

	}

	// 공지글 작성
	public int insert(String aid, String ntitle, String ncontent) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO NOTICE (NNO, AID, NTITLE, NCONTENT, NHIT) VALUES"
				+ "(NOTICE_SEQ.NEXTVAL, ?, ?, ?, 0)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aid);
			pstmt.setString(2, ntitle);
			pstmt.setString(3, ncontent);

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

	// 공지글 수정하기

	public int modify(String ntitle, String ncontent, int nno) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE NOTICE SET NTITLE='바뀐제목', NCONTENT='바뀐내용' WHERE NNO = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);

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

	// 글 삭제하기

	public int delete(int nno) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM NOTICE WHERE NNO = ?";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);

			result = pstmt.executeUpdate();

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

		return result;
	}

}
