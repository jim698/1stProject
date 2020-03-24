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
<link href="${conPath }/temporary/tempheader.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
	#lnb_menubtn{
		position:absolute;
		top:10px; left:5%;
		float:left;
		width:30px;
		height:30px;
		cursor: pointer;
		
	}
	
	#lnb_menubtn span{
		width:100%;
		height:2px;
		margin:7px 0;
		background: white;
		display:block;
	}
	
	nav{
		position: fixed;
		top:0; left:-100%;
		height:100%;
		width:20%;
		background: #f7c377;
		z-index: 99999;
		transition:.5s;
	}
	nav.open{
		position: fixed;
		top:0; left:0;
		height:100%;
		width:20%;
		background: #f7c377;
		z-index: 99999;
		transition:.5s;
	}
	
	#close_nav{
		position:absolute;
		top:10px; right:5%;
		float:left;
		width:30px;
		height:30px;
		cursor: pointer;
	}
	
	#close_nav span{
		position:absolute;
		width:100%;
		height:2px;
		top:14px;
		background: white;
		display:block;
	}
	
	#close_nav span:first-child{transform:rotate(45deg);}
	#close_nav span:last-child{transform:rotate(-45deg);}
	
	#nav_gnb{
		position:absolute;
		top:24%;
		width:80%;
		left:8%;
	}
	
	#nav_gnb li{
		position:relative;
		width:100%;
		padding:10px;
		text-align: center;
		margin: 20px 0;
	}
	
	#nav_gnb li a{
		color:#fff;
		font-size: 16pt;
	}
</style>
</head>
<body>
	<header>
		<script>
			$(function(){
				$("#lnb_menubtn").click(function(){
					$('nav').addClass('open');
				});
				$("#close_nav").click(function(){
					$('nav').removeClass('open');
				});
			});
		</script>
		
		<c:if test="${empty member and empty admin }">


			<div id="lnb">
				<div id="lnb_menubtn">
					<span></span>
					<span></span>	
					<span></span>											
				</div>
				<ul>
					<li><a href="${conPath }/main.do">Home</a></li>
					<li><a href="${conPath }/mloginView.do">Login</a></li>
					<li><a href="${conPath }/mjoinView.do">Join</a></li>
					<li><a href="#" id="myPage" onclick="alert('로그인이 필요합니다')">MyPage</a></li>
					<li><a href="#" id="order" onclick="alert('로그인이 필요합니다')">Order</a></li>					
				</ul>
			</div>

			<div id="logo">
				<a href="${conPath }/main.do"><img src="${conPath }/img/logo.jpg"></a>
			</div>

		</c:if>


		<c:if test="${empty member and not empty admin }">


			<div id="lnb">
				<ul>
					<li>${admin.aid}님 ▶ </li>					
					<li><a href="${conPath }/mList.do">회원관리</a></li>
					<li><a href="${conPath }/orderAllList.do">주문관리</a></li>
					<li><a href="${conPath }/reviewAllList.do">리뷰관리</a></li>
					<li><a href="${conPath }/logout.do">로그아웃</a></li>					
				</ul>
			</div>

			<div id="logo">
				<a href="${conPath }/main.do"><img src="${conPath }/img/logo.jpg"></a>
			</div>
				
		</c:if>
		
		
		<c:if test="${not empty member and empty admin }">


			<div id="lnb">
				<ul>
					<li>${member.mid }님 ▶</li>
					<li><a href="${conPath }/orderMemberList.do?mid=${member.mid}">주문내역</a></li>								
					<li><a href="${conPath }/mmodifyView.do">내페이지</a></li>
					<li><a href="${conPath }/logout.do">로그아웃</a></li>								
				</ul>
			</div>

			<div id="logo">
				<a href="${conPath }/main.do"><img src="${conPath }/img/logo.jpg"></a>
			</div>
				
		</c:if>
		
	</header>
	
	<nav>
		<div id="close_nav">
			<span></span>
			<span></span>
		</div>
		
		<div id="nav_gnb">
				<ul>
					<li><a href="${conPath }/pList.do">NEW</a></li>
					<li><a href="${conPath }/pcageList.do?pkinds=새장">CAGE</a></li>
					<li><a href="${conPath }/pfoodList.do?pkinds=모이">FOOD</a></li>
					<li><a href="${conPath }/ptoyList.do?pkinds=장난감">TOY</a></li>
					<li><a href="${conPath }/phitList.do">BEST</a></li>
					<li><a href="${conPath }/psaleList.do">SALE</a></li>
					<li><a href="#">BIRD</a></li>					
					<li><a href="${conPath }/nList.do">NOTICE</a></li>
				</ul>
		</div>	
	</nav>
	
	
</body>
</html>