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
<style>
header #gnb {
	display:none;
}
</style>
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>

	<section id="NWrite_wrap">
		<div id="NWrite_title">공지 글쓰기</div><br>

		<form action="#">
			<table>				
				<tr>
					<th>작성자</th>
					<td><input type="text" name="aid" required="required" value="${admin.aid }"></td>
				</tr>
				<tr>
					<th>글제목</th>
					<td><input type="text" name="atitle" required="required"></td>
				</tr>
				<tr>
					<th>본문</th>
					<td><textarea rows="15" cols="20" name="acontent"></textarea></td>
				</tr>				
				<tr>
					<td colspan="2">
						<input type="submit" value="글쓰기" class="btn">
						<input type="reset" value="취소" class="btn"> 
						<input type="button" value="목록" class="btn" onclick="location.href='#'">
					</td>
				</tr>
			</table>
		</form>
	</section>
	
<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>