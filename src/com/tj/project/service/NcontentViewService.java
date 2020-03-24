package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.NoticeDao;

public class NcontentViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		NoticeDao nDao = NoticeDao.getInstance();
		request.setAttribute("ncontent", nDao.contentView(nno));
	}

}
