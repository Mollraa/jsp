<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 자바구문 -->
	<%
	request.setCharacterEncoding("utf-8");
	%>
	
	<!-- if문(else 없다) -->
	<h1>넘어온 id 데이터는 = ${param.id}</h1>
	<c:if test="${not empty param.name}">
		<h1>넘어온 name 데이터는 = ${param.name}</h1>
	</c:if>
	<h1>넘어온 password 데이터는 = ${param.password}</h1>

	<!-- for문 -->
	<c:forEach var="i" begin="1" end="10">
		2 * ${i} = ${2 * i}<br>
	</c:forEach>

	<!-- choose문 / otherwise(else 대신)-->
	<c:choose>
	<c:when test="${empty param.id }">
	<h2>아이디 값이 비어있다.</h2>
	</c:when>
	<c:when test="${empty param.name }">
	<h2>이름 값이 비어있다.</h2>
	</c:when>
	<c:when test="${empty param.password }">
	<h2>비밀번호 값이 비어있다.</h2>
	</c:when>
	<c:otherwise>
	<h2>정상동작 되었다.</h2>
	</c:otherwise>
	</c:choose>

</body>
</html>