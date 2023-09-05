<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	out.println("JdbcTemplate : Hello World");
%>
<br>
<c:forEach var="dto" items="${users}">
	${dto.id}/${dto.name}<br>
</c:forEach>
<!-- users라는 컬렉션 개체의 요소들을 반복하면서
	각 요소의 id와 name속성을 출력하는 코드입니다. -->
</body>
</html>