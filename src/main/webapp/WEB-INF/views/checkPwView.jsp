<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>myPage CheckPw</title>
</head>
<body>
	<c:choose>
		<c:when test="${num == '1' && result == true}">
			<script>
				location.href="toMyPage"; //마이페이지
			</script>
		</c:when>
		<c:when test="${num == '2' && result == true}">
			<script>
				location.href="toWithdrawal";//회원탈퇴
			</script>
		</c:when>
		<c:when test="${result == false }">
			<script>
				alert("비밀번호를 다시 확인해주세요");
				location.href="checkPw";
			</script>
		</c:when>
	</c:choose>
</body>
</html>