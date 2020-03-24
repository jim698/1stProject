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
<link href="${conPath }/css/pcontentView.css" rel="stylesheet">
<link href="${conPath }/css/list.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<script>
$(document).ready(function(){
	
	$('#cnt').click(function(){
		var discount = ${product.pdiscount};		
		var price = ${product.pprice };	
		
		if(discount == 0){
			$('#cost').val(price*$('#cnt').val());
			$('#visibleCost').html("₩"+comma(price*$('#cnt').val()));
		}else{
			$('#cost').val(price*((100-discount)/100)*$('#cnt').val());
			$('#visibleCost').html("₩"+comma(price*((100-discount)/100)*$('#cnt').val()));
		}		
				
	});
	
	var pno = ${product.pno}	
	
	$.ajax({		
		
		url : '${conPath}/getProductsTotCnt.do',
		type : 'get',
		dataType :'html',
		data : 'pno='+pno,
		success : function(data, status){
			$('#stock').html(data);
		}
		
	});
});

function orderInsertChk(){
	var cnt = $('#cnt').val();
	var stock = $('#stock').text().trim();
	
	if(stock == '품절'){
		alert('품절입니다')
		return false;
	}else if(cnt > stock){
		alert('재고보다 많은 수량입니다.')
		return false;
	}
	
}

function comma(x) {
	x = x+"";
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
</script>
</head>
<body>
	<c:if test="${empty member and empty admin}">
		<script>
			alert('로그인이 필요합니다');
			history.back();
		</script>
	</c:if>
	
	<c:if test="${not empty reinsertResult }">
		<script>
			alert('${reinsertResult}');
		</script>
	</c:if>



	<jsp:include page="../main/header.jsp"></jsp:include>
	<section id="PContentView_wrap">
		<div id="PContentView_title">상품 상세보기</div>
		<br>

		<div id="PContentView_buy">
			<div id="inner_img">
				<img src="${conPath }/productsImg/${product.pphoto}" width="300">
			</div>
			<div id="inner_info">
			
				<form action="${conPath }/orderInsertView.do" method="post" onsubmit="return orderInsertChk()">					
					<input type="hidden" name="pno" value="${product.pno }">
					<input type="hidden" name="cost" id="cost">
					
				
					<table>
						<tr>
							<td colspan="2"><h2>${product.pname }</h2></td>
						</tr>
						<tr>
							<td>판매가격</td>
							<td class="price" id="price">
								<c:if test="${product.pdiscount == 0 }">
									￦<fmt:formatNumber value="${product.pprice }" pattern="#,###"/>
								</c:if>
								<c:if test="${product.pdiscount != 0 }">
									￦<fmt:formatNumber value="${(product.pprice)*((100-product.pdiscount)/100) }" pattern="#,###"/>
								</c:if>								
							</td>
						</tr>
						<tr>
							<td>수량</td>
							<td><input type="number" name="cnt" min="1" placeholder="수량을 입력하세요" id="cnt" required="required"></td>
						</tr>
						<tr>
							<td colspan="2" id="visibleCost"></td>
							<!-- <input type="text" name="cost" placeholder="총금액" id="cost"> -->
							
						</tr>
						<tr>
							<td>재고</td>
							<td id="stock"></td>
						</tr>
						<tr>														
							<c:if test="${not empty member }">	
								<td colspan="2" class="td_btn">
									<input type="submit" value="주문하기" class="btn"> 
									<input type="button" value="리뷰작성"	class="btn" onclick="location.href='${conPath}/reInsertView.do?pno=${param.pno}'">  
									<input type="button" value="뒤로가기"	class="btn" onclick="history.back();">								
								</td>
							</c:if>
							<c:if test="${not empty admin }">
								<td colspan="2" class="td_btn">
									<input type="button" value="상품수정" class="btn" onclick="location.href='${conPath}/pModifyView.do?pno=${param.pno }&pageNum=${param.pageNum }'"> 
									<input type="button" value="상품삭제"	class="btn" onclick="location.href='${conPath}/pDelete.do?pno=${param.pno }'"> 																
								</td>
							</c:if>							
						</tr>												
					</table>	
				</form>			
			</div>

		</div>
		
	</section>
	
		<h3>
			<img src="${conPath }/productsImg/detail_product.PNG">
		</h3>
		<p class="pphotologo">
			<img src="${conPath }/productsImg/youngman.jpg" width="400">
		</p>
		<div id="bottom_info">
			<h1>${product.pname }</h1>
			<c:if test="${not empty product.pphoto }">
				<p><img src="${conPath }/productsImg/${product.pphoto}" width="400"></p>
			</c:if>
			<p><pre>${product.pcontent }</pre></p>
			<c:if test="${not empty product.pphoto2 }">
				<p><img src="${conPath }/productsImg/${product.pphoto2}" width="800"></p>
			</c:if>
		</div>
		
		<!-- 리뷰 화면 로직~ -->
		
		<section id="mList_wrap">
		<div id="mList_title">
			리뷰 LIST
			
			<c:if test="${not empty member }">
				<div id="write_wrap">
					<input type="button" value="리뷰작성" id="write" onclick="location.href='${conPath}/reInsertView.do?pno=${param.pno}'">
				</div>
			</c:if>

		</div>
		<br>

		<form action="#" method="post">

			<table>
				<!-- <tr>
					<td colspan="8" id="search">						
						<input type="text" name="#">
						<input type="" value="검색">
					</td>
				</tr> -->
				<tr>
					<th class="th1">글번호</th>
					<th>구매상품</th>
					<th class="th2">작성자</th>
					<th class="th3">글제목</th>
					<!-- <th>글본문</th> -->
					<th class="th4">조회수</th>
					<th class="th5">글쓴시간</th>
				</tr>

				<c:if test="${reviewMemberList.size() eq 0 }">
					<!-- empty 하냐고 물어보면 안된당! 값이 없어도 주소값이 들어온당! -->
					<tr>
						<td colspan="6">아직 리뷰가 없습니다.</td>
					</tr>
				</c:if>

				<c:if test="${reviewMemberList.size() != 0 }">
					<c:forEach var="dto" items="${reviewMemberList }">
						<tr>
							<td>${dto.reno }</td>
							<td><img src="${conPath}/productsImg/${dto.pphoto }" width="100">
								<p>${dto.pname}</p>
							</td>
							<td>${dto.mid != null? dto.mid : dto.aid }</td>
							<td class="left">
								<c:forEach var="i" begin="1" end="${dto.rere_level }">
								
									<c:if test="${i != dto.rere_level }">
										&nbsp; &nbsp; &nbsp;
									</c:if>
									<c:if test="${i eq dto.rere_level }">
										&nbsp; &nbsp; &nbsp; └─<img src="${conPath }/reviewsImg/reply.png" width="50">							
									</c:if>
								</c:forEach>							
								<a href="${conPath }/reContentView.do?reno=${dto.reno}&pageNum=${pageNum}">${dto.retitle }</a>
							</td>
							<%-- <td>${dto.ncontent }</td> --%>
							<td>${dto.rehit }</td>
							<td>${dto.rerdate }</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>

			<div class="paging">
				<a href="${conPath }/pContentView.do?pno=${param.pno }&pageNum=1">&lt;&lt;</a> &nbsp; &nbsp;
				&nbsp;
				<c:if test="${startPage>BLOCKSIZE }">
					<a href="${conPath }/pContentView.do?pno=${param.pno }&pageNum=${startPage-1}">&lt;</a>
				</c:if>
				<c:if test="${startPage<=BLOCKSIZE }">
					&lt;
				</c:if>
				&nbsp; &nbsp; &nbsp;
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i == pageNum }">
						[ <b>${i }</b> ]
					</c:if>
					<c:if test="${i != pageNum }">
						[ <a href="${conPath }/pContentView.do?pno=${param.pno }&pageNum=${i}">${i }</a> ]
					</c:if>
				</c:forEach>
				&nbsp; &nbsp; &nbsp;
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/pContentView.do?pno=${param.pno }&pageNum=${endPage+1}">&gt;</a>
				</c:if>
				<c:if test="${endPage == pageCnt }">
					&gt;
				</c:if>
				&nbsp; &nbsp; &nbsp; 
				<a href="${conPath }/pContentView.do?pno=${param.pno }&pageNum=${pageCnt}">&gt;&gt;</a>
			</div>
		</form>
	</section>
		
			
	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>













