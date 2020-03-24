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
		<div id="NWrite_title">리뷰 작성</div>
		<br>

		<form action="${conPath }/reInsert.do" method="post" enctype="multipart/form-data">
									
			<table>				
				<tr>
					<th>작성자</th>
					<td><input type="text" name="mid" value="${member.mid }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>상품코드</th>
					<td><input type="text" name="pno" value="${param.pno }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>리뷰제목</th>
					<td><input type="text" name="retitle" required="required"></td>
				</tr>
				<tr>
					<th>리뷰내용</th>
					<td><textarea rows="20" cols="20" name="recontent" required="required"></textarea></td>
				</tr>
				<tr>
					<th>리뷰사진1</th>
					<td><input type="file" name="rephoto"></td>
				</tr>
				<tr>
					<th>리뷰사진2</th>
					<td><input type="file" name="rephoto2"></td>
				</tr>				
				<tr>
					<td colspan="2">
						<input type="submit" value="리뷰등록" class="btn">
						<input type="reset" value="취소" class="btn">						
					</td>
				</tr>
			</table>
		</form>
	</section>

	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>