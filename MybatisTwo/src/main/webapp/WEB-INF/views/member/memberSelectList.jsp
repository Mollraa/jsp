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
	<div align="center">
		<div>
			<h1>맴버 전체 목록</h1>
		</div>
		<div>
			<table border="1">
				<thead>
					<tr>
						<td width="150">아이디</td>
						<td width="150">비밀번호</td>
						<td width="150">이름</td>
						<td width="150">전화번호</td>
						<td width="150">권한</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${members}" var="m">
						<tr onmouseover="this.style.backgroundColor='pink';"
							onmouseout="this.style.backgroundColor='skyblue';"
							onclick="selectMember('${m.memberId}')">
							<td>${m.memberId}</td>
							<td>${m.memberPassword}</td>
							<td>${m.memberName}</td>
							<td>${m.memberTel}</td>
							<td>${m.memberAuthor}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br>
		<div>
			<button type="button" onclick="location.href='memberJoinForm.do'">맴버추가</button> &nbsp;&nbsp;
			<button type="button" onclick="location.href='main.do'">홈</button>
		</div>
		<div>
			<form id="frm" method="post">
				<input type="hidden" id="memberId" name="memberId">
			</form>
		</div>
	</div>
	<script type="text/javascript">
	function selectMember(id) {
		document.getElementById("memberId").value = id;
		frm.action = "memberSelect.do"
		frm.submit();	
	}
	
	</script>
</body>
</html>