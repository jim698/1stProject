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
<link href="${conPath }/css/join.css" rel="stylesheet">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">	
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>


<script>

var confirmIdMsg = null;
var confirmPwMsg = null;

$(document).ready(function(){
	
	$( function() { // 데이트 피커
    	$( '#datepicker' ).datepicker({
	    	dateFormat : 'yy-mm-dd',
	    	monthNames : ['1월', '2월', '3월', '4월','5월', '6월', '7월', '8월','9월', '10월', '11월', '12월'],
	    	showMonthAfterYear : true,
	    	yearSuffix : '년', // "2020년 3월"
	    	showOtherMonths : true,
	    	dayNamesMin : ['일', '월', '화', '수', '목', '금', '토']    	
    	});
	});
		
	
	$('#mid').keyup(function(){
		$.ajax({
			url : '${conPath}/confirmMid.do',
			type : 'get',
			dataType : 'html',
			data : 'mid='+$('input[name="mid"]').val(),
			success : function(data, status){
				$('#confirmIdMsg').html(data);
			}		
		});		
				
	});
	
	
	$('input[name="mpwChk"]').keyup(function(){
		if($('input[name="mpw"]').val() == $('input[name="mpwChk"]').val()){
			$('#confirmPwMsg').html('<b>비밀번호 일치</b>');
		}else{
			$('#confirmPwMsg').html('<b>비밀번호 불일치</b>');
		}//if		
		
	});	
	
});

function joinInfoChk(){
	confirmIdMsg = $('#confirmIdMsg').text().trim();
	confirmPwMsg = $('#confirmPwMsg').text().trim();
	
	if(confirmIdMsg != '사용 가능한 ID입니다.'){
		alert(confirmIdMsg);
		return false;
	}else if(confirmPwMsg != '비밀번호 일치'){
		alert(confirmPwMsg);
		return false;
	}
}

</script>

</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<section id="joinForm_wrap" class="join">
		<div id="join_title">회원가입</div><br>

		<form action="${conPath }/mJoin.do" method="post" name="frm" onsubmit="return joinInfoChk()">
		
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="mid" placeholder="ID 를 입력하세요" id="mid"><br>
						<div id="confirmIdMsg">&nbsp; &nbsp; &nbsp;</div></td>				
				</tr>				
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="mpw" placeholder="패스워드를 입력하세요." id="mpw" required="required"></td>
				</tr>
				<tr>
					<th>비밀번호확인</th>
					<td><input type="password" name="mpwChk" placeholder="패스워드를 확인하세요." id="mpwChk"><br>
						<div id="confirmPwMsg">&nbsp; &nbsp; &nbsp;</div></td>
				</tr>
				<tr>
					<th>키우는 앵무새</th>
					<td><input type="text" name="mfavorite" placeholder="어떤 새를 키우시나요?" id="mfavorite"></td>
				</tr>				
				<tr>
					<th>이름</th>
					<td><input type="text" name="mname" placeholder="이름을 입력하세요" id="mname" required="required"></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="tel" name="mtel" placeholder="000-0000-0000" id="mtel"> </td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="memail" placeholder="이메일 @ 도메인" id="memail"></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><input type="text" name="mbirth" id="datepicker" class="mbirth"></td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<input type="radio" name="mgender" value="m" checked="checked"><label for="m">남자</label>
						<input type="radio" name="mgender" value="f" ><label for="f">여자</label>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="maddress" placeholder="주소를 입력하세요" id="maddress"></td>
				</tr>				
				<tr>
					<td colspan="2">
						<input type="submit" value="회원가입" class="joinBtn_style">
						<input type="reset" value="취소" class="joinBtn_style">
						<input type="button" value="로그인" class="joinBtn_style">
					</td>				
				</tr>			
				
			</table>
		</form>
	</section>	

<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>