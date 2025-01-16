<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-body">
		<div class="container header">
			<a class="navbar-brand" href="index.jsp"><img src="img/booklogo.png"></a>
    		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
	    <div class="collapse navbar-collapse" id="collapsibleNavbar">
	      	<ul class="navbar-nav">
	        	<li class="nav-item">
	         		<a class="nav-link" href="addBook.do">도서등록</a>
	        	</li>
	       		<li class="nav-item">
	          		<a class="nav-link" href="bookList.do">도서목록</a>
	        	</li>
	        	<li class="nav-item">
	          		<a class="nav-link" href="boardList.do">문의게시판</a>
	        	</li>
	      	</ul>
	    </div>
	    <div class ="top-menu">
	    	<ul class="navbar-nav">
	    	<%
	    		//session에서 로그인 여부 확인 (내장객체에 있는 session가지고 와서 넣어라 아래 session이랑 다른것)
	    		HttpSession loginSession = request.getSession();
	    		//우리가 어디서 만든 session이 있는데 거기서 username을 가져와서 문자로 username에 넣는것
	    		String userID = (String) session.getAttribute("userID");
	    		//만약 로그인한 아이디가 존재하면 
	    		if(userID != null) {
	    			%>
	    			<!-- 이사이에 태그써야해서 스크립틀릿 닫아줘, 브라우저에 아이디를 표시하고, 로그아웃 아이콘을 표시 -->
	    			<li class="nav-item">
	    				<span class="nav-link"><%=userID %>님, 로그인 되었습니다.</span>
	    			</li>
	    			<li class = "nav-item">
	    				<a class="nav-link" href="logout.do"><i class="fa-solid fa-right-from-bracket"></i></a>
	    			</li>
	    			<%
	    		//로그인한 아이디가 없으면	
	    		}else {
					%>
					<li class="nav-item">
						<a href="login.jsp" class="nav-link">
	    					<i class="fa-solid fa-user"></i>
	    				</a>
	    			</li>
	    			<li class="nav-item">
	    				<a href="register.jsp" class="nav-link">
	    					<i class="fa-solid fa-user-plus"></i>
	    				</a>
	    			</li>
					<%	    			
	    		}
	    	
	    	%>
	    		
	    		<li class="nav-item"><a href="cartView.do" class="nav-link">
	    			<i class="fa-solid fa-cart-shopping"></i>
	    		</a></li>
	    		<li class="nav-item"><a href="#" class="nav-link">
	    			<i class="fa-solid fa-magnifying-glass"></i>
	    		</a></li>
	    	</ul>
	    </div>
		</div>
	</nav> <!-- header -->
		
</body>
</html>