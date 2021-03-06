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
<link href="${conPath }/css/login.css" rel="stylesheet">

</head>
<body>
	<c:if test="${not empty joinResult }">
	<script>
		alert('${joinResult }');		
	</script>
	</c:if>
	

	<jsp:include page="../main/header.jsp"></jsp:include>

	<section id="MLogin_wrap" class="join">
		<div id="MLogin_title">회원 로그인</div><br>

		<form action="${conPath }/mLogin.do" method="post">

			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="mid" placeholder="ID를 입력하세요" id="mid" value="${mid }"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="mpw" placeholder="패스워드를 입력하세요." id="mpw"></td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="submit" value="로그인" class="loginBtn_style">
					<input type="button" value="회원가입" class="loginBtn_style" onclick="location.href='${conPath}/mjoinView.do'">
					</td>
				</tr>
			</table>
		</form>
	</section>

	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>