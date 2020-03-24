package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.OrdersDao;

public class OrderInsertService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {		
		String pphoto = request.getParameter("pphoto");
		String dbmaddress = request.getParameter("dbmaddress");
		String dbmtel = request.getParameter("dbmtel");
		String mid = request.getParameter("mid");
		int pno = Integer.parseInt(request.getParameter("pno"));
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		int cost = Integer.parseInt(request.getParameter("cost"));
		
		String oaddress = request.getParameter("oaddress");
		String otel = request.getParameter("otel");
		
		oaddress = ( oaddress == null ? dbmaddress : oaddress);
		otel = ( otel == "" ? dbmtel : otel);
		
		OrdersDao oDao = OrdersDao.getInstance();
		
		oDao.minusPcnt(cnt, pno); // 주문수량 만큼 재고를 줄인다.		
		
		int result = oDao.orderInsert(mid, pno, oaddress, otel, cnt, cost);
		
		if(result == OrdersDao.SUCCESS) {
			request.setAttribute("orderInsert", "주문 작성 완료");
		}else {
			request.setAttribute("orderInsert", "주문 작성 실패");
		}
	}

}
