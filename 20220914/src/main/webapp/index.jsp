<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>시작하는 곳</h1>


	<div align="center">
		<div>
			<jsp:include page="menu.jsp"/>
			</div>
		<div>
			<h1>로 그 인</h1>
		</div>
		<div>
			<form action="FirstServlet" method="post">
				<div>
					<table border="1">
						<tr>
							<th width="150">아이디</th>
							<td width="200"><input type="text" id="id" name="id"
								placeholder="Enter Your ID.... "></td>
						</tr>
						<tr>
							<th width="150">이름</th>
							<td width="200"><input type="text" id="name" name="name"
								placeholder="Enter Your NAME.... "></td>
						</tr>

						<tr>
							<th>패스워드</th>
							<td><input type="password" id="password" name="password"
								placeholder="Enter Your password.... "></td>
						</tr>
					</table>
				</div>
				<br>
				<div>
					<input type="submit" value="로그인">&nbsp;&nbsp;
					<input type="reset" value="취소">
				</div>
			</form>
		</div>
		<!--  get방법인데 안쓰는게 좋음 ㅎ 콩밥먹고싶지 않으면^^
			<form action="FirstServlet" method="get">
				<div>
					<table border="1">
						<tr>
							<th width="150">아이디</th>
							<td width="200"><input type="text" id="id" name="id"
								placeholder="Enter Your ID.... "></td>
						</tr>
						<tr>
							<th>패스워드</th>
							<td><input type="password" id="password" name="password"
								placeholder="Enter Your password.... "></td>
						</tr>
					</table>
				</div>
				<br>
				<div>
					<input type="submit" value="로그인">&nbsp;&nbsp; <input
						type="reset" value="취소">
				</div>
			</form>
		-->
	</div>
	<a href="home.jsp">홈페이지</a>
</body>
</html>