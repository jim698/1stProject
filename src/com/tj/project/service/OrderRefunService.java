package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.OrdersDao;
import com.tj.project.dto.OrdersDto;

public class OrderRefunService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int ono = Integer.parseInt(request.getParameter("ono"));
		
		OrdersDao oDao = OrdersDao.getInstance();
		OrdersDto order = oDao.getMemberOrder(ono);
		
		int cnt = order.getCnt();
		int pno = order.getPno();
		
		oDao.plusPcnt(cnt, pno);
		
		int result = oDao.deleteOrder(ono);
		
		if(result == OrdersDao.SUCCESS) {
			request.setAttribute("orderRefundResult", "환불 성공");
		}else {
			request.setAttribute("orderRefundResult", "환불 실패");
		}

	}

}
