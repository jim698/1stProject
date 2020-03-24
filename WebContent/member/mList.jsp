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
	<c:if test="${not empty mmodifyResult }">
		<script>
			alert('${mmodifyResult}');
		</script>
	</c:if>	
	

	<jsp:include page="../main/header.jsp"></jsp:include>

	<section id="mList_wrap">
		<div id="mList_title">회원 리스트</div><br>

		<form action="#" method="post">

			<table>
				<!-- <tr>
					<td colspan="8" id="search">						
						<input type="text" name="#">
						<input type="" value="검색">
					</td>
				</tr> -->
				<tr>
					<th>아이디</th><th>이름</th><th>전화번호</th><th>이메일</th>
					<th>좋아하는앵무</th><th>주소</th><th>생일</th><th>가입일</th>
				</tr>
				
				<c:if test="${mList.size() eq 0 }"> <!-- empty 하냐고 물어보면 안된당! 값이 없어도 주소값이 들어온당! -->
				<tr><td colspan="8">회원이 없습니다.</td></tr>		
				</c:if>
				
				<c:if test="${mList.size() != 0 }">
					<c:forEach var="dto" items="${mList }">
						<tr>
							<td>${dto.mid }</td>
							<td>${dto.mname }</td>
							<td>${dto.mtel }</td>
							<td>${dto.memail }</td>
							<td>${dto.mfavorite }</td>
							<td>${dto.maddress }
							<td>${dto.mbirth }</td>
							<td>${dto.mrdate }</td>							
						</tr>					
					</c:forEach>	
				</c:if>				
			</table>
			
			<div class="paging">
				<a href="${conPath }/mList.do?pageNum=1">&lt;&lt;</a>
				&nbsp; &nbsp; &nbsp;
				<c:if test="${startPage>BLOCKSIZE }">
					<a href="${conPath }/mList.do?pageNum=${startPage-1}">&lt;</a>
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
						[ <a href="${conPath }/mList.do?pageNum=${i}">${i }</a> ]
					</c:if>
				</c:forEach>
				&nbsp; &nbsp; &nbsp;
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/mList.do?pageNum=${endPage+1}">&gt;</a>
				</c:if>
				<c:if test="${endPage == pageCnt }">
					&gt;
				</c:if>
				&nbsp; &nbsp; &nbsp;
				<a href="${conPath }/mList.do?pageNum=${pageCnt}">&gt;&gt;</a>
			</div>
		</form>
	</section>
	
	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>