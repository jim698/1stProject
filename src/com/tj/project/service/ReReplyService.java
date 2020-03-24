package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.ReviewDao;

public class ReReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String aid = request.getParameter("aid");
		int pno = Integer.parseInt(request.getParameter("pno"));
		String retitle = request.getParameter("retitle");
		String recontent = request.getParameter("recontent");		
		int reref = Integer.parseInt(request.getParameter("reref"));
		int rere_step = Integer.parseInt(request.getParameter("rere_step"));
		int rere_level = Integer.parseInt(request.getParameter("rere_level"));				
		
		ReviewDao rDao = ReviewDao.getInstance();
		int result = rDao.reply(aid, pno, retitle, recontent, reref, rere_step, rere_level);
		
		if(result == ReviewDao.SUCCESS) {
			request.setAttribute("rereplyResult", "답글 작성 성공");
		}else {
			request.setAttribute("rereplyResult", "답글 작성 실패");
		}
	}

}
