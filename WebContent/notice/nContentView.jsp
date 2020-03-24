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
<link href="${conPath }/css/nwrite.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../main/header.jsp"></jsp:include>

	<section id="NWrite_wrap" class="join">
		<div id="NWrite_title">공지글 내용</div>
		<br>

		<table>
			<tr>
				<th>작성자</th>
				<td>${ncontent.aid }</td>
			</tr>			
			<tr>
				<th>제목</th>
				<td>${ncontent.ntitle}</td>
			</tr>
			<tr>
				<th>본문</th>
				<td><textarea rows="15" cols="20" name="ncontent" >${ncontent.ncontent }</textarea></td>
			</tr>
			<tr>
				<th>히트수</th>
				<td>${ncontent.nhit}</td>
			</tr>			
			<tr>
				<td colspan="2">	
				
				<c:if test="${not empty admin }">				
					<button class="btn"
						onclick="location.href='${conPath}/nDelete.do?nno=${param.nno }'">삭제</button>	
				</c:if>				
					<button class="btn"
						onclick="location.href='${conPath}/nList.do?pageNum=${param.pageNum }'">뒤로가기</button>
				</td>
			</tr>			
		</table>

	</section>

	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>