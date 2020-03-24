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

import com.tj.project.dto.OrdersDto;

public class OrdersDao {

	public static final int FAIL = 0;
	public static final int SUCCESS = 1;

	private static OrdersDao instacne = new OrdersDao();

	public static OrdersDao getInstance() {
		return instacne;
	}

	private OrdersDao() {

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

	// 관리자가 보는 주문서 list
	public ArrayList<OrdersDto> getAllOrders(int startrow, int endrow) {
		ArrayList<OrdersDto> orders = new ArrayList<OrdersDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM ( SELECT O.*, M.MADDRESS, M.MTEL, P.PNAME, P.PPHOTO "
				+ "FROM ORDERS O, MEMBER M, PRODUCTS P WHERE O.PNO = P.PNO AND O.MID = M.MID ORDER BY OSTATE ) A) WHERE RN BETWEEN ? AND ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int ono = rs.getInt("ono");
				String mid = rs.getString("mid");
				int pno = rs.getInt("pno");
				String oaddress = rs.getString("oaddress");
				String otel = rs.getString("otel");
				int cnt = rs.getInt("cnt");
				int cost = rs.getInt("cost");
				Date ordate = rs.getDate("ordate");
				String ostate = rs.getString("ostate");
				String pname = rs.getString("pname");
				String pphoto = rs.getString("pphoto");
				String maddress = rs.getString("maddress");
				String mtel = rs.getString("mtel");

				orders.add(new OrdersDto(ono, mid, pno, oaddress, otel, cnt, cost, ordate, ostate, pname, pphoto,
						maddress, mtel));
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

		return orders;
	}

	// 특정 고객이 보는 주문서 list
	public ArrayList<OrdersDto> getMemberOrders(int startrow, int endrow, String mid) {
		ArrayList<OrdersDto> orders = new ArrayList<OrdersDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM ( SELECT O.*, M.MADDRESS, M.MTEL, P.PNAME, P.PPHOTO" + 
				"    FROM ORDERS O, MEMBER M, PRODUCTS P WHERE O.PNO = P.PNO AND O.MID = M.MID AND M.MID=? ORDER BY ORDATE DESC) A) WHERE RN BETWEEN ? AND ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int ono = rs.getInt("ono");
				int pno = rs.getInt("pno");
				String oaddress = rs.getString("oaddress");
				String otel = rs.getString("otel");
				int cnt = rs.getInt("cnt");
				int cost = rs.getInt("cost");
				Date ordate = rs.getDate("ordate");
				String ostate = rs.getString("ostate");
				String pname = rs.getString("pname");
				String pphoto = rs.getString("pphoto");
				String maddress = rs.getString("maddress");
				String mtel = rs.getString("mtel");

				orders.add(new OrdersDto(ono, mid, pno, oaddress, otel, cnt, cost, ordate, ostate, pname, pphoto, maddress, mtel));
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

		return orders;

	}

	// 주문서 갯수(전체) - 관리자
	public int getAllOrdersTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM ORDERS";

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

	// 고객별 주문서 갯수

	public int getMemberOrdersTotCnt(String mid) {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM ORDERS WHERE MID = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
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

	// 주문서 작성하기
	public int orderInsert(String mid, int pno, String oaddress, String otel, int cnt, int cost) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ORDERS (ONO, MID, PNO, OADDRESS, OTEL, CNT, COST) VALUES"
				+ "(ORDERS_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setInt(2, pno);
			pstmt.setString(3, oaddress);
			pstmt.setString(4, otel);
			pstmt.setInt(5, cnt);
			pstmt.setInt(6, cost);

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

	// ono로 주문서 가져오기
	public OrdersDto getMemberOrder(int ono) {
		OrdersDto order = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT O.*, M.MADDRESS, M.MTEL, P.PNAME, P.PPHOTO" + 
				"    FROM ORDERS O, MEMBER M, PRODUCTS P WHERE O.PNO = P.PNO AND O.MID = M.MID AND ONO=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ono);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				String mid = rs.getString("mid");
				int pno = rs.getInt("pno");
				String oaddress = rs.getString("oaddress");
				String otel = rs.getString("otel");
				int cnt = rs.getInt("cnt");
				int cost = rs.getInt("cost");
				Date ordate = rs.getDate("ordate");
				String ostate = rs.getString("ostate");
				String pname = rs.getString("pname");
				String pphoto = rs.getString("pphoto");
				String maddress = rs.getString("maddress");
				String mtel = rs.getString("mtel");

				order = new OrdersDto(ono, mid, pno, oaddress, otel, cnt, cost, ordate, ostate, pname, pphoto, maddress,
						mtel);
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

		return order;
	}

	// 주문 취소
	public int deleteOrder(int ono) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM ORDERS WHERE ONO = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ono);

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

	// 주문 시 재고 감소
	public int minusPcnt(int cnt, int pno) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PRODUCTS SET PCNT = PCNT - ? WHERE PNO = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnt);
			pstmt.setInt(2, pno);

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

	// 취소시 재고 증가
	public int plusPcnt(int cnt, int pno) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PRODUCTS SET PCNT = PCNT + ? WHERE PNO = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnt);
			pstmt.setInt(2, pno);

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

	// 주문처리 하기
	public int endOrder(int ono) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ORDERS SET OSTATE = '완료' WHERE ONO = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ono);

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
	
	
	

}
