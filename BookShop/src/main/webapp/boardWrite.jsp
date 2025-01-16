<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 게시판 - 글쓰기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="css/style.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/9c5636788c.js" crossorigin="anonymous"></script>
</head>
<body>
	<div class="wrap">
		<jsp:include page="nav.jsp"></jsp:include>
		<div class ="sub-box2">
			<h2>글쓰기</h2>
		   <form name="frm" method="post" enctype="multipart/form-data" action="/BookShop/boardWrite.do" onsubmit="return valfn(this);">
		      <table class="addTb">
		         <tr>
		            <td>제목</td>
		            <td><input type="text" name="title"></td>
		         </tr>
		         <tr>
		            <td>내용</td>
		            <td><textarea name="content" cols="70" rows="5" placeholder="200자 이내로 작성해 주세요."></textarea>
		            </td>
		         </tr>
		         <tr>
		            <td>첨부 파일<br>(200MB 이하)</td>
		            <td><input type="file" name="ofile"></td>
		         </tr>
		         <tr>
		            <td>작성자</td>
		            <td><!-- 세션에서 userName 가져오기 -->
						<c:if test="${not empty sessionScope.userID}">
						<p>${sessionScope.userID}</p>
						</c:if>
						
						<c:if test="${empty sessionScope.userID}">
						    <p>로그인 후 글쓰기가 가능합니다.</p>
						</c:if>

					</td>
		         </tr>
		         <tr>
		            <td>비밀번호</td>
		            <td><input type="password" name="pass"></td>            
		         </tr>
		         <tr>
		            <td colspan="2" class="sub6-btn">
		            	<div class="buttons">
			               <button type="submit">글쓰기</button>
			               <button type="reset">다시 쓰기</button>
			               <button type="button" onclick="location.href='boardList.do'">목록</button>
		               </div>
		            </td>
		         </tr>
		      </table>
		   </form>  
		</div>
   
    
   <jsp:include page="footer.jsp"></jsp:include>
   
   <script>
      function valfn(form){
         if(form.title.value == ""){
            alert("제목을 입력해 주세요.");
            form.title.focus();
            return false;
         }
         if(form.content.value == ""){
            alert("내용을 입력해 주세요.");
            form.content.focus();
            return false;
         }
         if(form.userID.value == ""){
            alert("아이디를 입력해 주세요.");
            form.userID.focus();
            return false;
         }
         if(form.pass.value == ""){
            alert("비밀번호을 입력해 주세요.");
            form.pass.focus();
            return false;
         }
      }
   </script>
   
   
   
   </div>
</body>
</html>