<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/recontentView.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../main/header.jsp"></jsp:include>
	<section id="PContentView_wrap">
		<div id="PContentView_title">리뷰 상세보기</div>
		<br>

		<div id="PContentView_buy">
			<div id="inner_img">
				<img src="${conPath }/productsImg/${review.pphoto}" width="300">
			</div>
			<div id="inner_info">
				<form action="#">
					<table>
						<tr>
							<td>상품코드 : </td>
							<td class="td2">${review.pno }</td>
						<tr>
						<tr>
							<td>구매품목 : </td>
							<td class="td2">${review.pname }</td>
						<tr>
						<tr>
							<td>작성자 : </td>
							<td class="td2">${review.mid != null? review.mid : review.aid }</td>
						<tr>
						<tr>
							<td>글 조회수 : </td>
							<td class="td2">${review.rehit }</td>						
						</tr>						
						<tr>														
							<c:if test="${not empty member }">	
								<td colspan="2" class="td_btn">									 
									<input type="button" value="뒤로가기"	class="btn" onclick="history.back();">								
								</td>
							</c:if>
							<c:if test="${not empty admin }">
								<td colspan="2" class="td_btn">
									<input type="button" value="답글작성" class="btn" onclick="location.href='${conPath}/reReplyView.do?reno=${param.reno }&pageNum=${param.pageNum }'"> 
									<input type="button" value="리뷰삭제"	class="btn" onclick="location.href='${conPath}/reDelete.do?reno=${param.reno }'">
									<input type="button" value="뒤로가기"	class="btn" onclick="history.back();"> 													 																
								</td>
							</c:if>							
						</tr>
					</table>	
				</form>			
			</div>
		</div>		
	</section>
	
		<h3>
			<img src="${conPath }/productsImg/detail_product.PNG">
		</h3>		
		<div id="bottom_info">
			<c:if test="${not empty review.rephoto }">			
				<p><img src="${conPath }/reviewsImg/${review.rephoto}" width="400"></p>
			</c:if>
			<c:if test="${not empty review.rephoto2 }">			
				<p><img src="${conPath }/reviewsImg/${review.rephoto2}" width="400"></p>
			</c:if>
			<p><pre>${review.recontent }</pre></p>
		</div>	
	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>