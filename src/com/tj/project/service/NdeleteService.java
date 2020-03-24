package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.NoticeDao;

public class NdeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		NoticeDao nDao = NoticeDao.getInstance();
		int result = nDao.delete(nno);
		
		if(result == NoticeDao.SUCCESS) {
			request.setAttribute("ndeleteResult", "글 삭제 성공");
		}else {
			request.setAttribute("ndeleteResult", "글 삭제 실패");
		}
		
		
		

	}

}
