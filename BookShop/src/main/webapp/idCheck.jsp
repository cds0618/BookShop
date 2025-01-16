<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 코어 태그 쓰려면 이거써아햠ㅁ -->
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>아이디 중복 확인</h3>
	<!--  idcheckcontroller에서 setattribute했던 값을 읽어오는 것!! -->
	<c:if test="${result == 1 }">
		<p>${username } 아이디는 이미 사용중인 아이디입니다.</p>
		<button type = "button" onclick="nofn()">닫기</button>
	</c:if>
	<c:if test="${result == 0 }">
		<p>${username } 아이디는 사용 가능한 아이디입니다.</p>
		<button type = "button" onclick="okfn()">닫기</button>
	</c:if>
	
	<script>
		function nofn(){
			//현재 팝업창을 띠운 부모 페이지의 form의 usernam입력란 비우기
			//문서 => frm은 register에서 form에 지정한 name임 
			opener.document.frm.username.value="";
			self.close();
			
		}
		//얘는 사용할수 있는 아이디니까 팝업창 닫으라고 하는것만 있으면 돼
		function okfn(){
			//현재 팝업창을 띠운 부모 페이지의 form의 idDoubleCheck값을 idCheck로 변경
			opener.document.frm.idDoubleCheck.value = "idCheck";
			self.close();
		}
	</script>
</body>
</html>