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
<link href="${conPath }/css/list.css" rel="stylesheet">
</head>
<body>

	<c:if test="${not empty nwriteResult }">
		<script>
			alert('${nwriteResult}');
		</script>
	</c:if>

	<c:if test="${not empty ndeleteResult }">
		<script>
			alert('${ndeleteResult}');
		</script>
	</c:if>


	<jsp:include page="../main/header.jsp"></jsp:include>

	<section id="mList_wrap">
		<div id="mList_title">
			공지사항
			
			<c:if test="${not empty admin }">
				<div id="write_wrap">
					<input type="button" value="글쓰기" id="write"
						onclick="location.href='${conPath}/nWriteView.do'">
				</div>
			</c:if>

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
					<th class="th2">글쓴이</th>
					<th class="th3">글제목</th>
					<!-- <th>글본문</th> -->
					<th class="th4">조회수</th>
					<th class="th5">글쓴시간</th>
				</tr>

				<c:if test="${nList.size() eq 0 }">
					<!-- empty 하냐고 물어보면 안된당! 값이 없어도 주소값이 들어온당! -->
					<tr>
						<td colspan="5">회원이 없습니다.</td>
					</tr>
				</c:if>

				<c:if test="${nList.size() != 0 }">
					<c:forEach var="dto" items="${nList }">
						<tr>
							<td>${dto.nno }</td>
							<td>${dto.aid }</td>
							<td><a
								href="${conPath }/nContentView.do?nno=${dto.nno}&pageNum=${pageNum}">${dto.ntitle }</a></td>
							<%-- <td>${dto.ncontent }</td> --%>
							<td>${dto.nhit }</td>
							<td>${dto.nrdate }</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>

			<div class="paging">
				<a href="${conPath }/nList.do?pageNum=1">&lt;&lt;</a> &nbsp; &nbsp;
				&nbsp;
				<c:if test="${startPage>BLOCKSIZE }">
					<a href="${conPath }/nList.do?pageNum=${startPage-1}">&lt;</a>
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
						[ <a href="${conPath }/nList.do?pageNum=${i}">${i }</a> ]
					</c:if>
				</c:forEach>
				&nbsp; &nbsp; &nbsp;
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/nList.do?pageNum=${endPage+1}">&gt;</a>
				</c:if>
				<c:if test="${endPage == pageCnt }">
					&gt;
				</c:if>
				&nbsp; &nbsp; &nbsp; <a
					href="${conPath }/nList.do?pageNum=${pageCnt}">&gt;&gt;</a>
			</div>
		</form>
	</section>

	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>