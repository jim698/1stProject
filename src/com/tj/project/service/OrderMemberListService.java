package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.project.dao.OrdersDao;
import com.tj.project.dto.MemberDto;

public class OrderMemberListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String pageNum = request.getParameter("pageNum");
		/*
		 * HttpSession session = request.getSession(); String mid =
		 * ((MemberDto)session.getAttribute("member")).getMid();
		 */
			
		String mid;
		if(request.getParameter("mid") == null) {
			
			HttpSession session = request.getSession();
			mid = ((MemberDto)session.getAttribute("member")).getMid();
			
		}else {
			
			mid = request.getParameter("mid");
			
		}
			
		
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 5, BLOCKSIZE = 3;

		int startRow = (currentPage - 1) * PAGESIZE + 1;
		int endRow = startRow + PAGESIZE - 1;

		OrdersDao oDao = OrdersDao.getInstance();		
		
		request.setAttribute("orderMemberList", oDao.getMemberOrders(startRow, endRow, mid));

		int totCnt = oDao.getMemberOrdersTotCnt(mid);
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
