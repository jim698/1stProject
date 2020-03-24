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
		<div id="NWrite_title">상품 수정</div>
		<br>

		<form action="${conPath }/pModify.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="dbpphoto" value="${modifyProduct.pphoto }">
			<input type="hidden" name="dbpphoto2" value="${modifyProduct.pphoto2 }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="pno" value="${modifyProduct.pno }">
			
			<table>				
				<tr>
					<th>상품분류</th>
					<td><input type="text" name="pkinds" value="${modifyProduct.pkinds }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>상품이름</th>
					<td><input type="text" name="pname" value="${modifyProduct.pname }"></td>
				</tr>
				<tr>
					<th>상품로고</th>
					<td><input type="file" name="pphotologo"></td>
				</tr>
				<tr>
					<th>상품메인사진</th>
					<td><input type="file" name="pphoto"></td>
				</tr>
				<tr>
					<th>상품서브사진</th>
					<td><input type="file" name="pphoto2"></td>
				</tr>			
				<tr>
					<th>상품설명</th>
					<td><textarea rows="15" cols="20" name="pcontent" value="${modifyProduct.pcontent }"></textarea></td>
				</tr>				
				<tr>
					<th>상품수량</th>
					<td><input type="text" name="pcnt" value="${modifyProduct.pcnt }"></td>
				</tr>
				<tr>
					<th>상품가격</th>
					<td><input type="text" name="pprice" value="${modifyProduct.pprice }"></td>
				</tr>
				<tr>
					<th>할인가</th>
					<td><input type="text" name="pdiscount" value="${modifyProduct.pdiscount }"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="상품수정" class="btn">
						<input type="reset" value="취소" class="btn"> 
						<input type="button" value="목록" class="btn" onclick="location.href='${conPath}/pList.do'">
					</td>
				</tr>
			</table>
		</form>
	</section>

	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>