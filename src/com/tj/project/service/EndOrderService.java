package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.OrdersDao;

public class EndOrderService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int ono = Integer.parseInt(request.getParameter("ono"));

		OrdersDao oDao = OrdersDao.getInstance();
		int result = oDao.endOrder(ono);

		if (result == OrdersDao.SUCCESS) {
			request.setAttribute("endOrderResult", "주문 처리 완료");
		} else {
			request.setAttribute("endOrderResult", "주문 처리 실패");
		}

	}

}
