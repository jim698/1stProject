package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.ProductsDao;
import com.tj.project.dto.ProductsDto;

public class OrderInsertViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pno = Integer.parseInt(request.getParameter("pno"));
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		int cost = Integer.parseInt(request.getParameter("cost"));
		
		ProductsDao pDao = ProductsDao.getInstance();
		ProductsDto pDto = pDao.getProduct(pno);
		request.setAttribute("product", pDao.getProduct(pno));
		request.setAttribute("cnt", cnt);
		request.setAttribute("cost", cost);
	}

}
