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
<link href="${conPath }/" rel="stylesheet">
<style>
	#section1{
		border:1px solid red;
		height:100%;
	}
	
	
	
</style>
</head>
<body>
	<jsp:include page="tempheader.jsp"></jsp:include>	
	
	
	<section id="section1">
	
	</section>	
					
	<section>
		 <div class="slider">
			<div>
				<img src="${conPath }/img/slide01.jpg" width="1300px">
			</div>
			<div>
				<img src="${conPath }/img/slide03.jpg" width="1300px">
			</div>
			<div>
				<img src="${conPath }/img/slide02.jpg" width="1300px">
			</div> 
		</div>  
	</section>
	
	
	<script>
	$(document).ready(function() {
		$('.slider').bxSlider({
			mode : 'fade',
			slideWidth : 1300

		});
	});
	</script>	
	
	

	<jsp:include page="tempfooter.jsp"></jsp:include>
</body>
</html>