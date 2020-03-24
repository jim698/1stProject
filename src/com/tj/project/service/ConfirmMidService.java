package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.dao.MemberDao;

public class ConfirmMidService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");

		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.midConfirm(mid);

		if (result == MemberDao.EXISTENT) {
			request.setAttribute("midResult", "중복된 ID입니다.");
		} else {
			request.setAttribute("midResult", "사용 가능한 ID입니다.");
		}

	}

}
