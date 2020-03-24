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
<link href="${conPath }/css/list.css" rel="stylesheet">
</head>
<body>
	<c:if test="${not empty orderRefundResult}">
		<script>
			alert('${orderRefundResult}');
		</script>
	</c:if>
	
	<c:if test="${not empty endOrderResult }">
		<script>
			alert('${endOrderResult}');
		</script>
	</c:if>


	<jsp:include page="../main/header.jsp"></jsp:include>

	<section id="mList_wrap">
		<div id="mList_title">
			주문 LIST
		</div>
		<br>

		<form action="#" method="post">

			<table>				
				<tr>
					<th class="th1">주문번호</th>					
					<th class="">구매상품</th>
					<th class="th2">구매자</th>
					<th class="th3">총금액</th>					
					<th class="th4">수량</th>
					<th class="th5">결제일</th>
					<th>처리상태</th> 
				</tr>

				<c:if test="${orderAllList.size() eq 0 }">
					<!-- empty 하냐고 물어보면 안된당! 값이 없어도 주소값이 들어온당! -->
					<tr>
						<td colspan="7">주문 리스트가 없습니다.</td>
					</tr>
				</c:if>

				<c:if test="${orderAllList.size() != 0 }">
					<c:forEach var="dto" items="${orderAllList }">
						<tr>
							<td>${dto.ono }</td>
							<td>
								<a href="${conPath }/orderContentView.do?ono=${dto.ono}">
									<img src="${conPath}/productsImg/${dto.pphoto }" width="100">
									<p>${dto.pname}</p>
								</a>							
							</td>
							<td>${dto.mid }</td>
							<td>￦<fmt:formatNumber value="${dto.cost }" pattern="#,###"/></td>							
							<td>${dto.cnt }</td>
							<td>${dto.ordate }</td>
							<td>${dto.ostate }</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>

			<div class="paging">
				<a href="${conPath }/orderAllList.do?pageNum=1">&lt;&lt;</a> &nbsp; &nbsp;
				&nbsp;
				<c:if test="${startPage>BLOCKSIZE }">
					<a href="${conPath }/orderAllList.do?pageNum=${startPage-1}">&lt;</a>
				</c:if>
				<c:if test="${startPage<=BLOCKSIZE }">
					&lt;
				</c:if>
				&nbsp; &nbsp; &nbsp;
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i == pageNum }">
						[ <b>${i }</b> ]
					</c:if>
					<c:if test="${i != pageNum }">
						[ <a href="${conPath }/orderAllList.do?pageNum=${i}">${i }</a> ]
					</c:if>
				</c:forEach>
				&nbsp; &nbsp; &nbsp;
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/orderAllList.do?pageNum=${endPage+1}">&gt;</a>
				</c:if>
				<c:if test="${endPage == pageCnt }">
					&gt;
				</c:if>
				&nbsp; &nbsp; &nbsp; 
				<a href="${conPath }/orderAllList.do?pageNum=${pageCnt}">&gt;&gt;</a>
			</div>
		</form>
	</section>

	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>