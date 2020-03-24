package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.tj.project.dao.AdminDao;
import com.tj.project.dto.AdminDto;

public class ALoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String aid = request.getParameter("aid");
		String apw = request.getParameter("apw");

		AdminDao aDao = AdminDao.getInstance();

		int result = aDao.aLoginCheck(aid, apw);
		if (result == AdminDao.LOGIN_SUCCESS) {

			HttpSession session = request.getSession();

			AdminDto dto = aDao.getAdmin(aid);
			session.setAttribute("admin", dto);

		} else {
			
			request.setAttribute("errorMsg", "아이디와 비번을 확인하세요");
			
		}

	}

}
