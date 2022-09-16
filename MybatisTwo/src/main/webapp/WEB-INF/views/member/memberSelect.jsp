<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div align="center">
<div><h1>멤버상세정보</h1></div>
<div>
<table border="1">

<tr>
<th width="120">아이디</th>
<td width="200">${m.memberId }</td>
</tr>

<tr>
<th width="120">비밀번호</th>
<td width="200">${m.memberPassword}</td>
</tr>

<tr>
<th width="120">이 름</th>
<td width="200">${m.memberName }</td>
</tr>

<tr>
<th width="120">연락처</th>
<td width="200">${m.memberTel }</td>
</tr>

<tr>
<th width="120">권 한</th>
<td width="200">${m.memberAuthor }</td>
</tr>


</table>
</div><br>
<div>
<form id="frm" method="post">
<input type="hidden" id="memberId" name="memberId">
<button type="button" onclick="actionForm('E')">수정</button>&nbsp;&nbsp;
<button type="button" onclick="actionForm('D')">삭제</button>&nbsp;&nbsp;
<button type="button" onclick="actionForm('L')">목록</button>&nbsp;&nbsp;
</form>
</div>
</div>
<script type="text/javascript">
function actionForm(str) {
	document.getElementById("memberId").value = ${m.memberId};
	alert(${m.memberId});
}


</script>
</body>
</html>