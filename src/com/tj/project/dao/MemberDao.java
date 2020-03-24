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

import com.tj.project.dto.MemberDto;

public class MemberDao {

	public static final int EXISTENT = 0;
	public static final int NONEXISTENT = 1;

	public static final int LOGIN_FAIL = 0;
	public static final int LOGIN_SUCCESS = 1;

	public static final int FAIL = 0;
	public static final int SUCCESS = 1;

	private static MemberDao instance = new MemberDao();

	public static MemberDao getInstance() {
		return instance;
	}

	private MemberDao() {

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

	// 1. 아이디(Mid) 중복확인
	public int midConfirm(String mid) {
		int result = EXISTENT;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE MID = ?";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = EXISTENT;
			} else {
				result = NONEXISTENT;
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

		return result;
	}

	// 2. 회원가입
	public int joinMember(MemberDto member) {
		int result = FAIL;

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO MEMBER (MID, MPW, MNAME, MTEL, MBIRTH, MEMAIL, MGENDER, MADDRESS, MFAVORITE) VALUES"
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMid());
			pstmt.setString(2, member.getMpw());
			pstmt.setString(3, member.getMname());
			pstmt.setString(4, member.getMtel());
			pstmt.setDate(5, member.getMbirth());
			pstmt.setString(6, member.getMemail());
			pstmt.setString(7, member.getMgender());
			pstmt.setString(8, member.getMaddress());
			pstmt.setString(9, member.getMfavorite());

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

	// 3. 로그인체크
	public int loginCheck(String mid, String mpw) {
		int result = LOGIN_FAIL;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE MID =? AND MPW =?";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, mpw);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = LOGIN_SUCCESS;
			} else {
				result = LOGIN_FAIL;
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

		return result;
	}

	// 4. 세션에 넣기위해 mId로 member dto 가져오기
	public MemberDto getMember(String mid) {
		MemberDto member = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM MEMBER WHERE mID = ?";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				String mpw = rs.getString("mpw");
				String mname = rs.getString("mname");
				String mtel = rs.getString("mtel");
				Date mbirth = rs.getDate("mbirth");
				String memail = rs.getString("memail");
				String mgender = rs.getString("mgender");
				String maddress = rs.getString("maddress");
				String mfavorite = rs.getString("mfavorite");
				Date mrdate = rs.getDate("mrdate");

				member = new MemberDto(mid, mpw, mname, mtel, mbirth, memail, mgender, maddress, mfavorite, mrdate);

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

		return member;
	}

	// 5. 회원정보수정
	public int modifyMember(MemberDto member) {
		int result = FAIL;

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE MEMBER SET MPW = ?, MNAME = ?, MTEL=?, MEMAIL = ?, MADDRESS=?" + " WHERE MID = ?";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMpw());
			pstmt.setString(2, member.getMname());
			pstmt.setString(3, member.getMtel());
			pstmt.setString(4, member.getMemail());
			pstmt.setString(5, member.getMaddress());
			pstmt.setString(6, member.getMid());

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

	// 6. 회원정보 출력하기
	public ArrayList<MemberDto> getAllMember(int startrow, int endrow) {
		ArrayList<MemberDto> members = new ArrayList<MemberDto>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM MEMBER ORDER BY MRDATE DESC) A) WHERE RN BETWEEN ? AND ?";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				String mid = rs.getString("mid");
				String mpw = rs.getString("mpw");
				String mname = rs.getString("mname");
				String mtel = rs.getString("mtel");
				Date mbirth = rs.getDate("mbirth");
				String memail = rs.getString("memail");
				String mgender = rs.getString("mgender");
				String maddress = rs.getString("maddress");
				String mfavorite = rs.getString("mfavorite");
				Date mrdate = rs.getDate("mrdate");

				members.add(new MemberDto(mid, mpw, mname, mtel, mbirth, memail, mgender, maddress, mfavorite, mrdate));

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

		return members;
	}

	// 7. 등록한 회원의 수 출력
	public int getMemberTotCnt() {
		int totCnt = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(*) FROM MEMBER";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totCnt = rs.getInt(1);
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

		return totCnt;
	}

}
