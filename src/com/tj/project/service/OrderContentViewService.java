package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.OrdersDao;
import com.tj.project.dto.OrdersDto;

public class OrderContentViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int ono = Integer.parseInt(request.getParameter("ono"));
		
		OrdersDao oDao = OrdersDao.getInstance();
		OrdersDto oDto = oDao.getMemberOrder(ono);
		request.setAttribute("order", oDao.getMemberOrder(ono));		

	}

}
