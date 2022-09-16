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
		<div>
			<h1>멤버 가입</h1>
		</div>
		<div>
			<form id="frm" action="memberInsert.do" onsubmit="return formCheck()"
				method="post">
				<div>
					<table border="1">
						<tr>
							<th>아이디</th>
							<td width="200"><input type="text" id="memberId"
								name="memberId" required="required">&nbsp;
								<button type="button" id="btn" value="No" onclick="idCheck()">중복체크</button>
							</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td colspan="3"><input type="password" id="memberPassword"
								name="memberPassword" required="required"></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td colspan="3"><input type="password"
								id="memberPasswordConfirm" required="required"></td>
						</tr>
						<tr>
							<th>이름</th>
							<td><input type="text" id="memberName" name="memberName"
								required="required"></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td colspan="3"><input type="tel" id="memberTel"
								name="memberTel"></td>
						</tr>
					</table>
				</div>
				<br>
				<div>
					<input type="hidden" name="memberAuthor" value="USER"> <input
						type="submit" value="등록">&nbsp;&nbsp; <input type="reset"
						value="취소">&nbsp;&nbsp; <input type="button" value="목록"
						onclick="location.href = 'memberSelectList.do'">

				</div>
			</form>

		</div>

	</div>
	<script type="text/javascript">
		function formCheck() {
			let pass1 = document.getElementById("memberPassword").value;
			let pass2 = document.getElementById("pwc").value;
			// = frm.memberPassword.value
			let idChecked = document.getElementById("btn").value;

			if (idChecked == 'No') {
				alert("아이디 중복체크를 해주세요.");
				return false;
			}

			if (pass1 != pass2) {
				alert("패스워드가 일치하지 않습니다.");
				document.getElementById("memberPassword").value = "";
				document.getElementById("pwc").value = "";
				document.getElementById("memberPassword").focus();
				return false;

			}

			return true;
		}

		function idCheck() { //Ajax를 이용해서 id 중복체크를 한다.

			let id = document.getElementById("memberId").value;

			// Create an XMLHttpRequest object
			//const xhttp = new XMLHttpRequest();
		/* 	xhttp.onload = function() {
				// Here you can use the Data

				if (this.readyState == 4 && this.status == 200) {
					if(this.responseText == '1');
					alert("사용할 수 있는 아이디 입니다.");
					document.getElementById("btn").value= "Yes";
					document.getElementById("memberPassword").focus();
				} else {
					alert("사용할 수 없는 아이디 입니다.");
					document.getElementById("memberId").value="";
					document.getElementById("memberId").focus();
					
				}
				// }이거하나없고, 실패하면 else 이후 CallBack함수
			
			// Send a request
			xhttp.open("GET", "ajaxMemberIdCheck.do?id=" + id);
			xhttp .send(); */
			
			
			//fetch를 사용한 ajax
			fetch('ajaxMemberIdCheck.do?id='+id)
			.then(response => response.text());
			.then(data => responseResult(data)); //이곳에 Call Back함수를 작성하면 됨
			}
		
			
			function result(data) { //fetch처리 callback함수
				if (this.readyState == 4 && this.status == 200) {
					if(this.responseText == '1');
					alert("사용할 수 있는 아이디 입니다.");
					document.getElementById("btn").value= "Yes";
					document.getElementById("memberPassword").focus();
				} else {
					alert("사용할 수 없는 아이디 입니다.");
					document.getElementById("memberId").value="";
					document.getElementById("memberId").focus();	
				}
			}
	</script>
</body>
</html>