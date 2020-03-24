package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.ProductsDao;

public class PdeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		ProductsDao pDao = ProductsDao.getInstance();
		int result = pDao.deleteProduct(pno);
		
		if(result == ProductsDao.SUCCESS) {
			request.setAttribute("pdeleteResult", "상품 삭제 성공");
		}else {
			request.setAttribute("pdeleteResult", "상품 삭제 실패");
		}

	}

}
