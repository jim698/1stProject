package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.ReviewDao;

public class ReDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int reno = Integer.parseInt(request.getParameter("reno"));

		ReviewDao rDao = ReviewDao.getInstance();
		int result = rDao.deleteReview(reno);

		if (result == ReviewDao.SUCCESS) {
			request.setAttribute("redeleteResult", "리뷰 삭제 성공");
		} else {
			request.setAttribute("redeleteResult", "리뷰 삭제 실패");
		}

	}

}
