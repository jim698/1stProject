package com.tj.project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.project.service.ALoginService;
import com.tj.project.service.ConfirmMidService;
import com.tj.project.service.EndOrderService;
import com.tj.project.service.GetProductsTotCntService;
import com.tj.project.service.LogoutService;
import com.tj.project.service.MJoinService;
import com.tj.project.service.MlistService;
import com.tj.project.service.MloginService;
import com.tj.project.service.MmodifyService;
import com.tj.project.service.NWriteService;
import com.tj.project.service.NcontentViewService;
import com.tj.project.service.NdeleteService;
import com.tj.project.service.NlistService;
import com.tj.project.service.OrderAllListService;
import com.tj.project.service.OrderContentViewService;
import com.tj.project.service.OrderDeleteService;
import com.tj.project.service.OrderInsertService;
import com.tj.project.service.OrderInsertViewService;
import com.tj.project.service.OrderMemberListService;
import com.tj.project.service.OrderRefunService;
import com.tj.project.service.PcagelistService;
import com.tj.project.service.PcontentViewService;
import com.tj.project.service.PdeleteService;
import com.tj.project.service.PfoodlistService;
import com.tj.project.service.PhitListViewService;
import com.tj.project.service.PinsertService;
import com.tj.project.service.PlistService;
import com.tj.project.service.PmodifyService;
import com.tj.project.service.PmodifyViewService;
import com.tj.project.service.PsalelistService;
import com.tj.project.service.PtoylistService;
import com.tj.project.service.ReContentViewService;
import com.tj.project.service.ReDeleteService;
import com.tj.project.service.ReInsertService;
import com.tj.project.service.ReInsertViewService;
import com.tj.project.service.ReReplyService;
import com.tj.project.service.ReReplyViewService;
import com.tj.project.service.ReviewAllListService;
import com.tj.project.service.Service;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		Service service = null;
		String viewPage = null;

		if (command.equals("/adminLoginView.do")) { // 글 목록

			viewPage = "admin/adminLogin.jsp";
			
		}else if(command.equals("/adminLogin.do")) {
			service = new ALoginService();
			service.execute(request, response);
			
			viewPage = "main.do";
		}else if(command.equals("/main.do")) {
			
			viewPage = "main/main.jsp";
		} else if(command.equals("/mloginView.do")) {
			
			viewPage = "member/mLogin.jsp";
		} else if(command.equals("/mLogin.do")) {
			service = new MloginService();
			service.execute(request, response);
			
			viewPage = "main.do";
		} else if(command.equals("/logout.do")) {
			service = new LogoutService();
			service.execute(request, response);
			
			viewPage = "main.do";
		} else if(command.equals("/mjoinView.do")) {			
			
			viewPage = "member/mJoin.jsp";
		} else if(command.equals("/mJoin.do")) {
			service = new MJoinService();
			service.execute(request, response);
			
			viewPage = "mloginView.do";
		} else if(command.equals("/confirmMid.do")) {
			service = new ConfirmMidService();
			service.execute(request, response);
			
			viewPage = "member/midResult.jsp";
		} else if(command.equals("/mmodifyView.do")) {			
			
			
			viewPage = "member/modifyView.jsp";
		} else if(command.equals("/mModify.do")) {
			service = new MmodifyService();
			service.execute(request, response);
			
			viewPage = "main.do";
		} else if(command.equals("/mList.do")) {
			service = new MlistService();
			service.execute(request, response);
			
			viewPage = "member/mList.jsp";
		} else if(command.equals("/nList.do")) {
			service = new NlistService();
			service.execute(request, response);
			
			viewPage = "notice/nList.jsp";
		} else if(command.equals("/nWriteView.do")) {
			
			viewPage = "notice/nWriteView.jsp";
		} else if(command.equals("/nWrite.do")) {
			service = new NWriteService();
			service.execute(request, response);
			
			viewPage = "nList.do";
		} else if(command.equals("/nContentView.do")) {
			service = new NcontentViewService();
			service.execute(request, response);
			
			viewPage = "notice/nContentView.jsp";
		} else if(command.equals("/nDelete.do")) {
			service = new NdeleteService();
			service.execute(request, response);
			
			viewPage = "nList.do";
		} else if(command.equals("/pList.do")) {
			service = new PlistService();
			service.execute(request, response);
			
			viewPage ="products/pList.jsp";
		} else if(command.equals("/pcageList.do")) {
			service = new PcagelistService();
			service.execute(request, response);
			
			viewPage = "products/pcageList.jsp";
		} else if(command.equals("/pfoodList.do")) {
			service = new PfoodlistService();
			service.execute(request, response);
			
			viewPage = "products/pfoodList.jsp";
		} else if(command.equals("/ptoyList.do")) {
			service = new PtoylistService();
			service.execute(request, response);
			
			viewPage = "products/ptoyList.jsp";
		} else if(command.equals("/psaleList.do")) {
			service = new PsalelistService();
			service.execute(request, response);
			
			viewPage = "products/psaleList.jsp";
		} else if(command.equals("/insertProductView.do")) {
			
			viewPage = "products/insertProductView.jsp";
		} else if(command.equals("/pInsert.do")) {
			service = new PinsertService();
			service.execute(request, response);
			
			viewPage = "pList.do";
		} else if(command.equals("/pContentView.do")) {
			service = new PcontentViewService();
			service.execute(request, response);
			
			viewPage = "products/pContentView.jsp";
		} else if(command.equals("/pDelete.do")) {
			service = new PdeleteService();
			service.execute(request, response);
			
			viewPage = "pList.do";	
		} else if(command.equals("/pModifyView.do")) {
			service = new PmodifyViewService();
			service.execute(request, response);
			
			viewPage = "products/pModifyView.jsp";
		} else if(command.equals("/pModify.do")) {
			service = new PmodifyService();
			service.execute(request, response);
			
			viewPage = "pList.do";
		} else if(command.equals("/reviewAllList.do")) {
			service = new ReviewAllListService();
			service.execute(request, response);
			
			viewPage = "reviews/reviewAllList.jsp";
		} else if(command.equals("/reContentView.do")) {
			service = new ReContentViewService();
			service.execute(request, response);
			
			viewPage ="reviews/reContentView.jsp";
		} else if(command.equals("/reInsertView.do")) {
			service = new ReInsertViewService();
			service.execute(request, response);
			
			viewPage = "reviews/reInsertView.jsp";
		} else if(command.equals("/reInsert.do")) {
			service = new ReInsertService();
			service.execute(request, response);
			
			viewPage = "pContentView.do";
		} else if(command.equals("/reDelete.do")) {
			service = new ReDeleteService();
			service.execute(request, response);
			
			viewPage = "reviewAllList.do";
		} else if(command.equals("/reReplyView.do")) {
			service = new ReReplyViewService();
			service.execute(request, response);
			
			viewPage = "reviews/reReplyView.jsp";
		} else if(command.equals("/reReply.do")) {
			service = new ReReplyService();
			service.execute(request, response);
			
			viewPage = "reviewAllList.do";
		} else if(command.equals("/orderAllList.do")) {
			service = new OrderAllListService();
			service.execute(request, response);
			
			viewPage = "orders/orderAllList.jsp";
		} else if(command.equals("/orderMemberList.do")) {
			service = new OrderMemberListService();
			service.execute(request, response);
			
			viewPage = "orders/orderMemberList.jsp";
		} else if(command.equals("/phitList.do")) {
			service = new PhitListViewService();
			service.execute(request, response);
			
			viewPage = "products/phitList.jsp";
		} else if(command.equals("/orderInsertView.do")) {
			service = new OrderInsertViewService();
			service.execute(request, response);
			
			viewPage = "orders/orderInsertView.jsp";
		} else if(command.equals("/orderInsert.do")) {
			service = new OrderInsertService();
			service.execute(request, response);
			
			viewPage = "pList.do";
		} else if(command.equals("/orderContentView.do")) {
			service = new OrderContentViewService();
			service.execute(request, response);
			
			viewPage = "orders/orderContentView.jsp";
		} else if(command.equals("/orderDelete.do")) {
			service = new OrderDeleteService();
			service.execute(request, response);
			
			viewPage = "orderMemberList.do";
		} else if(command.equals("/getProductsTotCnt.do")) {
			service = new GetProductsTotCntService();
			service.execute(request, response);
			
			viewPage = "products/pTotCnt.jsp";
		} else if(command.equals("/orderRefund.do")) {
			service = new OrderRefunService();
			service.execute(request, response);
			
			viewPage = "orderAllList.do";
		} else if(command.equals("/endOrder.do")) {
			service = new EndOrderService();
			service.execute(request, response);
			
			viewPage = "orderAllList.do";
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
				

	}

}
