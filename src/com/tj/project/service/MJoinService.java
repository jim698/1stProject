package com.tj.project.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.project.dao.MemberDao;
import com.tj.project.dto.MemberDto;

public class MJoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mpwchk = request.getParameter("mpwChk");
		String mfavorite = request.getParameter("mfavorite");
		String mname = request.getParameter("mname");
		String mtel = request.getParameter("mtel");
		String memail = request.getParameter("memail");
		String mbirthstr = request.getParameter("mbirth");
		String mgender = request.getParameter("mgender");
		String maddress = request.getParameter("maddress");		
		
		Date mbirth = null;
		if(!mbirthstr.equals("")) {
			mbirth = Date.valueOf(mbirthstr);
		}
		
		MemberDao mDao = MemberDao.getInstance();
		MemberDto member = new MemberDto(mid, mpwchk, mname, mtel, mbirth, memail, mgender, maddress, mfavorite, null);
		
		int result = mDao.joinMember(member);
		if(result == MemberDao.SUCCESS) {
			HttpSession session = request.getSession();
			session.setAttribute("mid", mid);
			
			request.setAttribute("joinResult", "회원가입 성공");
		}else {
			request.setAttribute("joinResult", "회원가입 실패");
		}
	}

}
