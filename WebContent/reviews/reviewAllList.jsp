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
<style>
header #gnb {
	display:none;
}
</style>
</head>
<body>

	<c:if test="${not empty redeleteResult }">
		<script>
			alert('${redeleteResult}');
		</script>
	</c:if>
	
	<c:if test="${not empty rereplyResult }">
		<script>
			alert('${rereplyResult}');
		</script>
	</c:if>	

	<jsp:include page="../main/header.jsp"></jsp:include>

	<section id="mList_wrap">
		<div id="mList_title">
			리뷰 LIST
		</div>
		<br>

		<form action="#" method="post">

			<table>
				<!-- <tr>
					<td colspan="8" id="search">						
						<input type="text" name="#">
						<input type="" value="검색">
					</td>
				</tr> -->
				<tr>
					<th class="th1">글번호</th>
					<th class="">구매상품</th>
					<th class="th2">작성자</th>
					<th class="th3">글제목</th>
					<!-- <th>글본문</th> -->
					<th class="th4">조회수</th>
					<th class="th5">글쓴시간</th>
				</tr>

				<c:if test="${reviewAllList.size() eq 0 }">
					<!-- empty 하냐고 물어보면 안된당! 값이 없어도 주소값이 들어온당! -->
					<tr>
						<td colspan="6">아직 리뷰가 없습니다.</td>
					</tr>
				</c:if>

				<c:if test="${reviewAllList.size() != 0 }">
					<c:forEach var="dto" items="${reviewAllList }">
						<tr>
							<td>${dto.reno }</td>
							<td><img src="${conPath}/productsImg/${dto.pphoto }" width="100">
								<p>${dto.pname}</p>
							</td>
							<td>${dto.mid != null? dto.mid : dto.aid }</td>
							<td class="left">
								<c:forEach var="i" begin="1" end="${dto.rere_level }">
								
									<c:if test="${i != dto.rere_level }">
										&nbsp; &nbsp; &nbsp;
									</c:if>
									<c:if test="${i eq dto.rere_level }">
										&nbsp; &nbsp; &nbsp; └─ <img src="${conPath }/reviewsImg/reply.png" width="50">							
									</c:if>
								</c:forEach>							
								<a href="${conPath }/reContentView.do?reno=${dto.reno}&pageNum=${pageNum}">${dto.retitle }</a>
							</td>
							<%-- <td>${dto.ncontent }</td> --%>
							<td>${dto.rehit }</td>
							<td>${dto.rerdate }</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>

			<div class="paging">
				<a href="${conPath }/reviewAllList.do?pageNum=1">&lt;&lt;</a> &nbsp; &nbsp;
				&nbsp;
				<c:if test="${startPage>BLOCKSIZE }">
					<a href="${conPath }/reviewAllList.do?pageNum=${startPage-1}">&lt;</a>
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
						[ <a href="${conPath }/reviewAllList.do?pageNum=${i}">${i }</a> ]
					</c:if>
				</c:forEach>
				&nbsp; &nbsp; &nbsp;
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/reviewAllList.do?pageNum=${endPage+1}">&gt;</a>
				</c:if>
				<c:if test="${endPage == pageCnt }">
					&gt;
				</c:if>
				&nbsp; &nbsp; &nbsp; 
				<a href="${conPath }/reviewAllList.do?pageNum=${pageCnt}">&gt;&gt;</a>
			</div>
		</form>
	</section>

	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>