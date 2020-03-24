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
<link href="${conPath }/css/adminlogin.css" rel="stylesheet">
</style>
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>

<section id="adminLogin_wrap">
		<div id="adminLogin_title">관리자 로그인</div><br>

		<form action="${conPath }/adminLogin.do" method="post">
		
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="aid" placeholder="ID를 입력하세요" id="aid"></td>				
				</tr>				
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="apw" placeholder="패스워드를 입력하세요." id="apw"></td>
				</tr>							
				<tr>
					<td colspan="2">
						<input type="submit" value="로그인" class="adminBtn_style">											
					</td>				
				</tr>				
			</table>
			
		</form>
	</section>


<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>