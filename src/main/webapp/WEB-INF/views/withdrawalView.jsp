<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Withdrawal View</title>
</head>
<body>
	<c:choose>
		<c:when test="${result > 0 }">
			<script>
				alert("회원탈퇴가 성공적으로 이루어졌습니다.");
				location.href="/";
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("비밀번호를 다시 확인해주세요.");
				location.href="checkPw?num=2";
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>