<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마니사 도서-비밀번호 확인</title>
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
					<h2>비밀번호 확인</h2>
				</div>
				<div class ="sub-box10">
					<div class="center">
						<form name = "frm" method="post" action="pass.do" onsubmit="return valfn(this);">
						<!-- 여기서 param(매개변수)은 bookdetail에서 받아오는 값임, 2개의 매개변수를 전달받는다 -->
							<input type="hidden" name="bookID" value="${param.bookID }">
							<input type="hidden" name="mode" value="${param.mode }">
							<table>
								<tr>
									<td style="width:100px;">비밀번호</td>
									<td><input type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="2" class="login-btn2">
										<div class="passbtns">
											<button type="submit">비밀번호 확인</button>
											<button type="reset">다시 쓰기</button>
											<button type="button" onclick="location.href='bookList.do'">도서 목록</button>
										</div>
									</td>
								</tr>
							</table>
						
						</form>
					</div>
				</div>		
			<script>
			function valfn(form){
				if(form.password.value == ""){
					alert("비밀번호를 입력해 주세요");
					form.password.focus();
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