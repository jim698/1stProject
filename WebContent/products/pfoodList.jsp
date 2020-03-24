<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/plist.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../main/header.jsp"></jsp:include>

	<section>
		<table>
			<tr>
				<c:forEach var='dto' items='${pfoodList }' varStatus="status">

					<td><a href="${conPath }/pContentView.do?pno=${dto.pno}&pageNum=${pageNum}">					
						<div id="img_wrap">
							<img src="${conPath }/productsImg/${dto.pphoto}" width="300">
						</div></a><br>	
					
						<p>${dto.pname }</p> 
						<c:if test="${dto.pdiscount eq 0 }">
							<p><fmt:formatNumber value="${dto.pprice }" pattern="￦#,###"/></p>
						</c:if>
						<c:if test="${dto.pdiscount != 0 }">
							<del><fmt:formatNumber value="${dto.pprice }" pattern="￦#,###"/> </del><br>
							<p><fmt:formatNumber value="${(dto.pprice)*((100-dto.pdiscount)/100)}" pattern="￦#,###"/></p>					
						</c:if>				
					</td>

					<c:if test="${status.count%3 == 0 }">
			</tr>
			<tr>
				</c:if>

				</c:forEach>
			</tr>

		</table>

		<div class="paging">
			<a href="${conPath }/pfoodList.do?pkinds=모이&pageNum=1">&lt;&lt;</a> &nbsp;
			&nbsp; &nbsp;
			<c:if test="${startPage>BLOCKSIZE }">
				<a href="${conPath }/pfoodList.do?pkinds=모이&pageNum=${startPage-1}">&lt;</a>
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
						[ <a href="${conPath }/pfoodList.do?pkinds=모이&pageNum=${i}">${i }</a> ]
					</c:if>
			</c:forEach>
			&nbsp; &nbsp; &nbsp;
			<c:if test="${endPage < pageCnt }">
				<a href="${conPath }/pfoodList.do?pkinds=모이&pageNum=${endPage+1}">&gt;</a>
			</c:if>
			<c:if test="${endPage == pageCnt }">
					&gt;
				</c:if>
			&nbsp; &nbsp; &nbsp; <a
				href="${conPath }/pfoodList.do?pkinds=모이&pageNum=${pageCnt}">&gt;&gt;</a>
		</div>
	</section>

	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>