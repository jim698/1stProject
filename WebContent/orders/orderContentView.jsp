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
<link href="${conPath }/css/oContent.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../main/header.jsp"></jsp:include>
	<section id="oContent_wrap">
		<div id="oContent_title">주문 상세보기</div>
		<br>		
									
			<table>								
				<tr>
					<th>주문자</th>
					<td>${order.mid }</td>
				</tr>
				<tr>
					<th>주문상품</th>
					<td>${order.pname }</td>
				</tr>				
				<tr>
					<th>배송지 주소</th>
					<td>${order.oaddress }</td>
				</tr>
				<tr>
					<th>배송지 전화</th>
					<td>${order.otel }</td>
				</tr>
				<tr>
					<th>주문수량</th>
					<td>${order.cnt }</td>
				</tr>
				<tr>
					<th>총금액</th>
					<td>￦<fmt:formatNumber value="${order.cost }" pattern="#,###"/></td>
				</tr>
				<tr>
					<th>주문결제일</th>
					<td>${order.ordate }</td>
				</tr>
				<tr>
					<th>처리상태</th>
					<td>${order.ostate }</td>
				</tr>
											
				<tr>
					<c:if test="${not empty member }">
						<td colspan="2">
							<input type="button" value="주문취소" class="btn" onclick="location.href='${conPath}/orderDelete.do?ono=${order.ono }'">						
							<input type="button" value="뒤로" class="btn" onclick="history.back();">					
						</td>
					</c:if>
					
					<c:if test="${not empty admin }">
						<td colspan="2">
							<input type="button" value="주문처리" class="btn" onclick="location.href='${conPath}/endOrder.do?ono=${order.ono }'">						
							<input type="button" value="환불" class="btn" onclick="location.href='${conPath}/orderRefund.do?ono=${order.ono }'">
							<input type="button" value="뒤로" class="btn" onclick="history.back();">					
						</td>
					</c:if>
				</tr>
			</table>		
	</section>

	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>