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
		<div id="NWrite_title">주문서 작성</div>
		<br>

		<form action="${conPath }/orderInsert.do" method="post">
			<input type="hidden" name="pphoto" value="${product.pphoto }">
			<input type="hidden" name="dbmaddress" value="${member.maddress }">
			<input type="hidden" name="dbmtel" value="${member.mtel}">
									
			<table>								
				<tr>
					<th>작성자</th>
					<td><input type="text" name="mid" value="${member.mid }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>상품코드</th>
					<td><input type="text" name="pno" value="${product.pno }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>상품이름</th>
					<td><input type="text" name="pname" value="${product.pname }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>배송지 주소</th>
					<td><input type="text" name="oadderss"></td>
				</tr>
				<tr>
					<th>배송지 전화</th>
					<td><input type="text" name="otel"></td>
				</tr>
				<tr>
					<th>주문수량</th>
					<td><input type="text" name="cnt" value="${cnt }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>총금액</th>
					<td><input type="text" name="cost" value="${cost }" readonly="readonly"></td>
				</tr>							
				<tr>
					<td colspan="2">
						<input type="submit" value="주문신청" class="btn">
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