package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.project.dao.MemberDao;
import com.tj.project.dto.MemberDto;

public class MloginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		MemberDao mDao = MemberDao.getInstance();
		
		int result = mDao.loginCheck(mid, mpw);
		
		if(result==MemberDao.LOGIN_SUCCESS) {
			HttpSession session = request.getSession();
			MemberDto member = mDao.getMember(mid);
			
			session.setAttribute("member", member);
			
		}else {
			request.setAttribute("errorMsg", "id와 비밀번호를 확인하세요");
		}

	}

}
