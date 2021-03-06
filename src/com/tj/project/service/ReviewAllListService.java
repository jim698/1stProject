package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.ProductsDao;
import com.tj.project.dao.ReviewDao;

public class ReviewAllListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String pageNum = request.getParameter("pageNum");

		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 10, BLOCKSIZE = 3;

		int startRow = (currentPage - 1) * PAGESIZE + 1;
		int endRow = startRow + PAGESIZE - 1;

		ReviewDao rDao = ReviewDao.getInstance();
		request.setAttribute("reviewAllList", rDao.getAllReviews(startRow, endRow));

		int totCnt = rDao.getReviewTotCnt();
		int pageCnt = (int) Math.ceil((double) totCnt / PAGESIZE);

		int startPage = ((currentPage - 1) / BLOCKSIZE) * BLOCKSIZE + 1;
		int endPage = startPage + BLOCKSIZE - 1;

		if (endPage > pageCnt) {
			endPage = pageCnt;
		}

		request.setAttribute("BLOCKSIZE", BLOCKSIZE); // 페이지 관련 항목들 죄다 담았다!
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCnt", pageCnt);

	}

}
