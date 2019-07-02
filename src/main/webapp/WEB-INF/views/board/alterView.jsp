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
		<c:when test="${result > 0}">
			<script>
				alert("수정이 완료되었습니다.");
				location.href="toContent?seq=${seq}";
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("수정에 실패하였습니다.");
				location.href="toContent?seq=${seq}";
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>