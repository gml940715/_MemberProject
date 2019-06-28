<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${result > 0 }">
			<script>
				alert("회원가입에 성공적으로 완료되었습니다.");
				location.href="/";
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("회원가입에 실패하였습니다.");
				location.href="signUpForm";
			</script>
			
		</c:otherwise>
	</c:choose>
</body>
</html>