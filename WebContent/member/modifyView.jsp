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
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<script>
$(document).ready(function(){
	
	$('input[name="mpwChk"]').keyup(function(){
		if($('input[name="mpw"]').val() == $('input[name="mpwChk"]').val()){
			$('#confirmPwMsg').html('<b>비밀번호 일치</b>');
		}else{
			$('#confirmPwMsg').html('<b>비밀번호 불일치</b>');
		}//if		
		
	});
});	

function joinInfoChk(){
	
	var confirmPwMsg = $('#confirmPwMsg').text().trim();
	
	if(confirmPwMsg != '비밀번호 일치'){
		alert(confirmPwMsg);
		return false;
	}
}

</script>




</head>
<body>

<jsp:include page="../main/header.jsp"></jsp:include>

<section id="joinForm_wrap" class="join">
		<div id="join_title">정보 수정</div><br>

		<form action="${conPath }/mModify.do" method="post" name="frm" onsubmit="return joinInfoChk()">
		
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="mid" id="mid" value="${member.mid }" readonly="readonly"></td>				
				</tr>				
				<tr>
					<th>새 비밀번호</th>
					<td><input type="password" name="mpw" placeholder="변경할 패스워드" id="mpw"></td>
				</tr>
				<tr>
					<th>비밀번호확인</th>
					<td><input type="password" name="mpwChk" placeholder="변경 패스워드 확인" id="mpwChk"><br>
						<div id="confirmPwMsg">&nbsp; &nbsp; &nbsp;</div></td>
				</tr>								
				<tr>
					<th>이름</th>
					<td><input type="text" name="mname" id="mname" value="${member.mname }"></td>
				</tr>
				<tr>
					<th>새 전화번호</th>
					<td><input type="tel" name="mtel" placeholder="전화번호를 입력하세요" value="${member.mtel }" id="mtel"> </td>
				</tr>
				<tr>
					<th>새 이메일</th>
					<td><input type="email" name="memail" id="memail" value="${member.memail }"></td>
				</tr>				
				<tr>
					<th>주소</th>
					<td><input type="text" name="maddress" placeholder="주소를 입력하세요" value="${member.maddress }" id="maddress"></td>
				</tr>				
				<tr>
					<td colspan="2">
						<input type="submit" value="정보수정" class="joinBtn_style">
						<input type="reset" value="취소" class="joinBtn_style">
						<input type="button" value="이전" class="joinBtn_style" onclick="history.back()">
					</td>				
				</tr>			
				
			</table>
		</form>
	</section>
<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>