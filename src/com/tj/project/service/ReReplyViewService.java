package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.ReviewDao;
import com.tj.project.dto.ReviewDto;

public class ReReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int reno = Integer.parseInt(request.getParameter("reno"));
		
		ReviewDao rDao = ReviewDao.getInstance();
		ReviewDto review = rDao.getReview(reno);
		
		request.setAttribute("replyreview", review);
		
	}

}
