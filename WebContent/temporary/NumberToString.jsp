<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
		$(document).ready(function(){
			$('input[name="sum"]').val($('span').text());
			$('input[type="number"]').on('change',function(){
				var sum = 1000;
				$('input[type="number"]').each(function(idx, data){
					sum += ($(this).val()*100*(idx+1));
				});
				$('input[name="sum"]').val(comma(sum));
				$('span').html("₩"+comma(sum));
			});
		});
		function comma(x) {
			x = x+"";
		  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}
	</script>
</head>
<body>
	<form action="">
		<p>기본요금 : <fmt:formatNumber value="1000" groupingUsed="true" type="currency"/></p>
		<p>100원짜리 : <input type="number" name="n1" min="0" value="1"> = <b>100</b></p>
		<p>200원짜리 : <input type="number" name="n2" min="0" value="1"> = <b>200</</b></p>
		<p><input type="text" name="sum" value=""></p>
			<span></span>
		<p><input type="submit">
	</form>
</body>
</html>