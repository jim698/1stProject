package com.tj.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.project.dao.MemberDao;
import com.tj.project.dto.MemberDto;

public class MmodifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		String mid = request.getParameter("mid");		
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String mtel = request.getParameter("mtel");
		String memail = request.getParameter("memail");
		String maddress = request.getParameter("maddress");
		
		MemberDao mDao = MemberDao.getInstance();
		MemberDto dto = mDao.getMember(mid);
		
		if(mid.equals("")) {
			mid = dto.getMpw();
		}
		
		if(mtel.equals("")) {
			mtel = dto.getMtel();
		}
		
		if(maddress.equals("")) {
			maddress = dto.getMaddress();
		}
		
		MemberDto member = new MemberDto(mid, mpw, mname, mtel, dto.getMbirth(), memail, dto.getMgender(), maddress, dto.getMfavorite(), null);
		
		int result = mDao.modifyMember(member);
		
		if(result == MemberDao.SUCCESS) {
			
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			request.setAttribute("mmodifyResult", "정보 수정 성공");
			
		}else {
			
			request.setAttribute("mmodifyResult", "정보 수정 실패");
			
		}
		

	}

}
