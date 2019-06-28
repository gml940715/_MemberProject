<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Write Veiw</title>
</head>
<body>
	<c:choose>
		<c:when test="${result > 0}">
			<script>
				alert("글이 성공적으로 등록되었습니다.");
				location.href="toBoardList";
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("글등록이 완료되지 못하였습니다.");
				location.href="toBoardList";
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>