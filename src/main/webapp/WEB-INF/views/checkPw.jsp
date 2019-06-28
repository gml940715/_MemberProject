<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<script>
	$(function(){
		$(".Btn").on("click",function(){
			if($(".checkPw").val() == ""){
				alert("비밀번호를 입력해주세요.");
				}else{
			$("#checkForm").submit();}
		})
		$(".toHome-btn").on("click",function(){
			location.href="/";
		})
		
	})
</script>
<title>Insert title here</title>
</head>
<body>
	비밀번호 확인<br>
	<form action="checkPwProc" id="checkForm" method="post">
	<input type="password" class="checkPw" name="pw"><input type="button" class="Btn" value="확인">
	<input type="button" class="toHome-btn" value="홈으로">
		</form>
</body>
</html>