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
<link href="${conPath }/css/nwrite.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../main/header.jsp"></jsp:include>

	<section id="NWrite_wrap">
		<div id="NWrite_title">답글 작성</div>
		<br>

		<form action="${conPath }/reReply.do" method="post"">			
			<input type="hidden" name="reref" value="${replyreview.reref }">
			<input type="hidden" name="rere_step" value="${replyreview.rere_step }">
			<input type="hidden" name="rere_level" value="${replyreview.rere_level }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">;			
			
			<table>				
				<tr>
					<th>작성자</th>
					<td><input type="text" name="aid" value="${admin.aid }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>상품코드</th>
					<td><input type="text" name="pno" value="${replyreview.pno }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>답글제목</th>
					<td><input type="text" name="retitle" required="required"></td>
				</tr>
				<tr>
					<th>답글내용</th>
					<td><textarea rows="20" cols="20" name="recontent" required="required"></textarea></td>
				</tr>							
				<tr>
					<td colspan="2">
						<input type="submit" value="답글등록" class="btn">
						<input type="reset" value="취소" class="btn">	
						<input type="button" value="뒤로" class="btn" onclick="history.back();">					
					</td>
				</tr>
			</table>
		</form>
	</section>

	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>