package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.NoticeDao;

public class NWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aid = request.getParameter("aid");
		String ntitle = request.getParameter("ntitle");
		String ncontent = request.getParameter("ncontent");

		NoticeDao nDao = NoticeDao.getInstance();
		int result = nDao.insert(aid, ntitle, ncontent);

		if (result == NoticeDao.SUCCESS) {
			request.setAttribute("nwriteResult", "글 작성 성공");
		} else {
			request.setAttribute("nwriteResult", "글 작성 실패");
		}

	}

}
