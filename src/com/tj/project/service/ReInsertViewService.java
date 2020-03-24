package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.ProductsDao;
import com.tj.project.dto.ProductsDto;

public class ReInsertViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pno = Integer.parseInt(request.getParameter("pno"));
		ProductsDao pDao = ProductsDao.getInstance();
		ProductsDto product = pDao.getProduct(pno);
		request.setAttribute("product", product);

	}

}
