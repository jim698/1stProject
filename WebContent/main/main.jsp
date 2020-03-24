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
<link href="${conPath }/css/section.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Marck+Script&display=swap" rel="stylesheet">

</head>
<body>	

	<c:if test="${not empty errorMsg }">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	
	<c:if test="${not empty mmodifyResult }">
		<script>
			alert('${mmodifyResult}');			
		</script>		
	</c:if>	


	<jsp:include page="header.jsp"></jsp:include>	
					
					
	<section id="section1">		
		<h1>Your Best Friend</h1>
		<h1>Deserves</h1>
		<h1>The Best Love</h1>
		<img src = "${conPath}/img/logomain.png" width="300">		
	</section>	
	

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>