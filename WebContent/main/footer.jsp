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
<link href="${conPath }/css/footer.css" rel="stylesheet">
</head>
<body>
	<footer>
	<div id="footer_info">
		<div class="left">
			<p>&nbsp;Follow</p>
			<p>
				<img src="${conPath }/img/bfacebook.png" width="20" height="20">
				<img src="${conPath }/img/binsta.png" width="20" height="20">&nbsp;
				<img src="${conPath }/img/byoutube.png" width="20" height="20">
			</p>
		</div>		
			
		<div class="center">
			<p>Contact</p>
			<p>wogur698@naver.com</p>
			<p>010-3158-8498</p>
		</div>
		
		<div class="right">
			<p>Address</p>
			<p>500 Terry Francois Street</p>
			<p>San Francisco, CA 94158</p>			
		</div>
		
	</div>
	
	<div id= "footer_bottom">
		<p>Copyright ⓒ 청년새장. All right reserved. <a href="${conPath }/adminLoginView.do">[관리자]</a> </p>
	</div>
</footer>
</body>
</html>