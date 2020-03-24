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

import com.tj.project.dto.ProductsDto;

public class ProductsDao {

	public static final int FAIL = 0;
	public static final int SUCCESS = 1;

	private static ProductsDao instance = new ProductsDao();

	public static ProductsDao getInstance() {
		return instance;
	}

	private ProductsDao() {

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

	// 상품등록하기
	public int insertProducts(ProductsDto product) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PRODUCTS (PNO, PKINDS, PNAME, PPHOTOLOGO, PPHOTO, PPHOTO2, PCONTENT, PCNT, PPRICE, PDISCOUNT) VALUES"
				+ "(PRODUCTS_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getPkinds());
			pstmt.setString(2, product.getPname());
			pstmt.setString(3, product.getPphotologo());
			pstmt.setString(4, product.getPphoto());
			pstmt.setString(5, product.getPphoto2());
			pstmt.setString(6, product.getPcontent());
			pstmt.setInt(7, product.getPcnt());
			pstmt.setInt(8, product.getPprice());
			pstmt.setInt(9, product.getPdiscount());

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

	// pno로 product 정보 가져오기

	public ProductsDto getProduct(int pno) {
		ProductsDto product = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM PRODUCTS WHERE PNO = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				String pkinds = rs.getString("pkinds");
				String pname = rs.getString("pname");
				String pphotologo = rs.getString("pphotologo");
				String pphoto = rs.getString("pphoto");
				String pphoto2 = rs.getString("pphoto2");
				String pcontent = rs.getString("pcontent");
				int pcnt = rs.getInt("pcnt");
				int pprice = rs.getInt("pprice");
				int pdiscount = rs.getInt("pdiscount");
				Date prdate = rs.getDate("prdate");

				product = new ProductsDto(pno, pkinds, pname, pphotologo, pphoto, pphoto2, pcontent, pcnt, pprice,
						pdiscount, prdate);
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

		return product;
	}

	// 신상품 리스트 가져오기

	public ArrayList<ProductsDto> getAllNewProducts(int startRow, int endRow) {
		ArrayList<ProductsDto> products = new ArrayList<ProductsDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCTS ORDER BY PRDATE DESC) A) WHERE RN BETWEEN ? AND ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int pno = rs.getInt("pno");
				String pkinds = rs.getString("pkinds");
				String pname = rs.getString("pname");
				String pphotologo = rs.getString("pphotologo");
				String pphoto = rs.getString("pphoto");
				String pphoto2 = rs.getString("pphoto2");
				String pcontent = rs.getString("pcontent");
				int pcnt = rs.getInt("pcnt");
				int pprice = rs.getInt("pprice");
				int pdiscount = rs.getInt("pdiscount");
				Date prdate = rs.getDate("prdate");

				products.add(new ProductsDto(pno, pkinds, pname, pphotologo, pphoto, pphoto2, pcontent, pcnt, pprice,
						pdiscount, prdate));
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

		return products;
	}

	// 분류별 상품 가져오기
	public ArrayList<ProductsDto> getAllPkindsProducts(int startRow, int endRow, String pkinds) {
		ArrayList<ProductsDto> products = new ArrayList<ProductsDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCTS WHERE PKINDS=? ORDER BY PRDATE DESC) A ) WHERE RN BETWEEN ? AND ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pkinds);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int pno = rs.getInt("pno");
				String pname = rs.getString("pname");
				String pphotologo = rs.getString("pphotologo");
				String pphoto = rs.getString("pphoto");
				String pphoto2 = rs.getString("pphoto2");
				String pcontent = rs.getString("pcontent");
				int pcnt = rs.getInt("pcnt");
				int pprice = rs.getInt("pprice");
				int pdiscount = rs.getInt("pdiscount");
				Date prdate = rs.getDate("prdate");

				products.add(new ProductsDto(pno, pkinds, pname, pphotologo, pphoto, pphoto2, pcontent, pcnt, pprice,
						pdiscount, prdate));
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

		return products;
	}

	// 세일 상품 리스트 보기
	public ArrayList<ProductsDto> getAllSalesProducts(int startRow, int endRow) {
		ArrayList<ProductsDto> products = new ArrayList<ProductsDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCTS WHERE PDISCOUNT > 0 ORDER BY PRDATE DESC) A ) WHERE RN BETWEEN ? AND ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int pno = rs.getInt("pno");
				String pkinds = rs.getString("pkinds");
				String pname = rs.getString("pname");
				String pphotologo = rs.getString("pphotologo");
				String pphoto = rs.getString("pphoto");
				String pphoto2 = rs.getString("pphoto2");
				String pcontent = rs.getString("pcontent");
				int pcnt = rs.getInt("pcnt");
				int pprice = rs.getInt("pprice");
				int pdiscount = rs.getInt("pdiscount");
				Date prdate = rs.getDate("prdate");

				products.add(new ProductsDto(pno, pkinds, pname, pphotologo, pphoto, pphoto2, pcontent, pcnt, pprice,
						pdiscount, prdate));
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

		return products;
	}

	// 등록된 신상품 갯수

	public int getProductsTotCnt() {

		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM PRODUCTS";

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

	// 분류별 상품 갯수 불러오기
	public int getPkindsProductsTotCnt(String pkinds) {

		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM PRODUCTS WHERE PKINDS=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pkinds);
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

	// 세일 상품 갯수 구하기
	public int getSalesProductsTotCnt() {

		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM PRODUCTS WHERE PDISCOUNT > 0";

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

	// 상품 수정하기
	public int modifyProduct(String pname, String pphotologo, String pphoto, String pphoto2, String pcontent, int pcnt,
			int pprice, int pdiscount, int pno) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PRODUCTS SET PNAME=?, PPHOTOLOGO=?, PPHOTO=?, PPHOTO2=?, PCONTENT=?,"
				+ "PCNT = ?, PPRICE = ?, PDISCOUNT=? WHERE PNO = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pname);
			pstmt.setString(2, pphotologo);
			pstmt.setString(3, pphoto);
			pstmt.setString(4, pphoto2);
			pstmt.setString(5, pcontent);
			pstmt.setInt(6, pcnt);
			pstmt.setInt(7, pprice);
			pstmt.setInt(8, pdiscount);
			pstmt.setInt(9, pno);

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

	// 상품 삭제하기
	public int deleteProduct(int pno) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM PRODUCTS WHERE PNO = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
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

	// 히트 상품들 리스트 뿌리기
	public ArrayList<ProductsDto> getAllHitProducts(int startRow, int endRow) {
		ArrayList<ProductsDto> products = new ArrayList<ProductsDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, B.* FROM(SELECT P.* FROM (SELECT PNO, COUNT(*) FROM ORDERS GROUP BY PNO ORDER BY COUNT(*) DESC) A,"
				+ "    PRODUCTS P WHERE A.PNO = P.PNO) B) WHERE RN BETWEEN ? AND ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int pno = rs.getInt("pno");
				String pkinds = rs.getString("pkinds");
				String pname = rs.getString("pname");
				String pphotologo = rs.getString("pphotologo");
				String pphoto = rs.getString("pphoto");
				String pphoto2 = rs.getString("pphoto2");
				String pcontent = rs.getString("pcontent");
				int pcnt = rs.getInt("pcnt");
				int pprice = rs.getInt("pprice");
				int pdiscount = rs.getInt("pdiscount");
				Date prdate = rs.getDate("prdate");

				products.add(new ProductsDto(pno, pkinds, pname, pphotologo, pphoto, pphoto2, pcontent, pcnt, pprice,
						pdiscount, prdate));
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

		return products;
	}

	public int getProductsTotCnt(int pno) {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT PCNT FROM PRODUCTS WHERE PNO = ?";

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

			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return totCnt;
	}

}
