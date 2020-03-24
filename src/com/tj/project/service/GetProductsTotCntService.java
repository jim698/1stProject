package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.ProductsDao;

public class GetProductsTotCntService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		ProductsDao pDao = ProductsDao.getInstance();
		int totCnt = pDao.getProductsTotCnt(pno);
		
		if(totCnt > 0) {
			request.setAttribute("totCnt", totCnt);
		}else {
			request.setAttribute("totCnt", "품절");
		}

	}

}
