<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마니사도서-회원가입</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/1083064ac2.js" crossorigin="anonymous"></script>

<link href="css/style.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</head>
<body>
<div class = "reg-bg">
   <div class="wrap">
      <jsp:include page="nav.jsp"></jsp:include>
         <div class ="sub-box">
            <div class="center">
               <div class="joinform">
                  <div class="reg_two">
                     <h2>회원가입하기</h2>
                     <h3><a href="index.jsp"><i class="fa-solid fa-house"></i></a></h3>
                     <p>회원가입을 위한 정보를<br>입력해 주세요.</p>
                  </div>
                  <form name="frm" action="register.do" method="post" onsubmit="return valfn(this);">
                     <table class="reg-table">
                     	<tr>
                     		<td class="first-td"><p>기본정보<span>(필수)</span></p></td>
                     	</tr>
                        <tr>
                           <td>
                           <input type="text" name="userID" placeholder="아이디" class="reg-id">
                           <button type="button" class="check" name="idCheck" onclick="idCheckfn()">중복확인</button>
                           	<input type ="hidden" name="idDoubleCheck" value="idUnCheck">
                           </td>
                        </tr>
                        <tr>
                           <td><input type="password" name="password" placeholder="비밀번호"></td>
                        </tr>
                        <tr>
                           <td><input type="email" name="email" placeholder="이메일"></td>
                        </tr>
                        <tr>
                        	<td>　</td>
                        </tr>
                        <tr class="three-btn">
                           <td colspan="2">
                              <button type="submit">가입</button>
                              <button type="reset">삭제</button>
                              <button type="button" onclick="location.href='login.jsp'">로그인</button>
                           </td>
                        </tr>
                     </table>
                  </form>
               </div><!-- joinform -->
            </div>
         </div>
     
   </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>  
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
			
			//만약 아이디 중복확인을 하지않으면 메시지 출력
			if(form.idDoubleCheck.value != "idCheck"){
				alert("아이디 중복 확인을 해 주세요!");
				return false;
			}
		}
		
		//아이디 중복검사
		function idCheckfn(){
			let form = document.frm;
			let id = form.userID.value;
			if(id.length == 0 || id == ""){
				alert("아이디를 입력해 주세요")
				form.userID.focus();
			}else{
				//작은 팝업창 띄우기
				window.open("idCheck.do?userID=" + id, "", "width=300, height=200");
			}
		}
	</script> 
</body>
</html>