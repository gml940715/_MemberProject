<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Id DupleCheck</title>
</head>
<body>
	<c:choose>
		<c:when test="${result == true }">
				${id}는 이미 존재하는 아이디 입니다.
		</c:when>
		<c:otherwise>
			${id }는 사용 가능한 아이디 입니다.
		</c:otherwise>
	</c:choose>
</body>
</html>