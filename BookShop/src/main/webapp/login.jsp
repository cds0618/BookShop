<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마니사도서-로그인</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/1083064ac2.js" crossorigin="anonymous"></script>

<link href="css/style.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</head>
<body>
<div class = "reg-bg">
	<div class="wrap">
		<jsp:include page="nav.jsp"></jsp:include>
			<div class="loginForm">
				<div class="reg_two">
					<h2>로그인</h2>
					<h5>회원정보를 입력해 주세요.</h5>
				</div>
					<div class ="sub-box">
						<div class="center">
							<form action="login.do" method="post" onsubmit="return valfn(this);">
								<table class="in-table">
									<tr>
										<td><input type="text" name="userID" placeholder="아이디"></td>
									</tr>
									<tr>
										<td><input type="password" name="password" placeholder="비밀번호"></td>
									</tr>
									<tr>
										<td colspan="2" class="login-btn">
											<button type="submit">로그인</button>
										</td>	
									</tr>
									<tr>
										<td colspan="2" class="login-btn">
											<button type="button" onclick="location.href='register.jsp'">회원가입</button>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				
				<script>
				function valfn(form){
					if(form.userID.value == ""){
						alert("아이디를 입력하세요.");
						form.userID.focus();
						return false;	
					}
					if(form.password.value ==""){
						alert("비밀번호를 입력하세요.");
						form.password.focus();
						return false;
					}
					if(form.email.value ==""){
						alert("이메일을 입력하세요.");
						form.email.focus();
						return false;
					}
				}
				</script>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>	
</body>
</html>