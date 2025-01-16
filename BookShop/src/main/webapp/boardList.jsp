<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 게시판</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<link href="css/style.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/1083064ac2.js" crossorigin="anonymous"></script>

</head>
<body>
<div class="wrap">
	<jsp:include page ="nav.jsp"></jsp:include>
	<div class="sub-box5">
		<h2>문의 게시판</h2>
		<form method="get">
			<table>
				<tr>
					<td class="center-box">
						<select name="searchField">
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="title+content">제목+내용</option>
						</select>
						<input type="text" name="searchWord">
						<input type="submit" value="검색" class="search"> 			
					</td>
				</tr>
			</table>
		</form>
	<table class = "res-list">
		<tr>
			<td colspan="6" class="total-count">
				<h5>총 게시글 수 : ${map.total}</h5>
			</td>
		</tr>
		<tr>
			<th>
				<a title="번호순 정렬" href="?sortObj=boardID&sortOrder=${map.sortOrder == 'asc' ? 'desc' : 'asc' }">번호</a>
			</th>
			<th>
			<a title="제목순 정렬" href="?sortObj=title&sortOrder=${map.sortOrder == 'asc' ? 'desc' : 'asc' }">제목</a>
			</th>
			<th><a title="작성자순 정렬" href="?sortObj=userID&sortOrder=${map.sortOrder == 'asc' ? 'desc' : 'asc' }">작성자</a></th>
			<th><a title="조회수순 정렬" href="?sortObj=visitcount&sortOrder=${map.sortOrder == 'asc' ? 'desc' : 'asc' }">조회수</a></th>
			<th><a title="작성일순 정렬" href="?sortObj=postDate&sortOrder=${map.sortOrder == 'asc' ? 'desc' : 'asc' }">작성일</a></th>
			<th>첨부파일</th>
		</tr>
		<c:choose> <%-- choose와 when과 otherwise는 한세트임! 각자쓸수없어 -->
		<%-- 조건을 만족할때는 when 그냥 실행 --%>
			<c:when test="${ empty boardList }">
				<tr>
					<td colspan="6" class="list-box">
						등록된 게시글이 없습니다.
					</td>
				</tr>
			</c:when>
			<%-- when 조건 만족하지 못할때 otherwise 실행! --%>
			<c:otherwise>
					
				<c:forEach var="list" varStatus="loop" items="${boardList}">
					<tr>
						<td>${map.total - (((map.pageNum -1) * map.pageSize) + loop.index)}</td>
						<td class="title"><a href="boardDetail.do?boardID=${list.boardID}">${list.title}</a></td>
						<td>${list.userID}</td>
						<td>${list.visitcount}</td>
						<td>${list.postDate}</td>
						<td>
							<!-- 만약 원본파일명이 비어있지 않으면 -->
							<c:if test="${ not empty list.ofile }">
								<a href="download.do?ofile=${list.ofile}&sfile=${list.sfile}&boardID=${list.boardID}">다운로드</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>	
			<tr class="write-button">
				<td colspan="6" class="center-box" >
					<!-- 글쓰기버튼 -->
					<button type="button" onclick="location.href='boardWrite.do'">글쓰기</button>
				</td>
			</tr>
			<tr class="pageNum"> 
				<td>
					<!-- 페이지 번호 표시 -->
					${map.pagingVar}
				</td>
			</tr>
		
	</table>
	</div>
	<jsp:include page ="footer.jsp"></jsp:include>
</div>	
</body>
</html>