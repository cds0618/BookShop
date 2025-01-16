<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마니사도서 - 도서 상세 보기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/1083064ac2.js" crossorigin="anonymous"></script>

<link href="css/style.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<div class="wrap">
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="sub-box6">
		<h2>문의 게시판 - 상세보기</h2>
		<table>
			<tr>
				<th>번호</th>
				<td>${board.boardID}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${board.title}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${board.userID}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${board.postDate}</td>
			</tr>
			<tr class="sub6-cont">
				<th>내용</th>
				<td>${board.content}</td>
			</tr>
			
			<tr>
				<th>첨부파일</th>
				<td>
					
						<c:choose>
							<c:when test = "${not empty board.ofile }">
								${board.ofile}&nbsp;&nbsp;&nbsp;
								<a href="download.do?ofile=${board.ofile}&sfile=${board.sfile}&boardID=${board.boardID}">다운로드</a><br>
								<img src ="upload/${ board.ofile}" alt="${ board.boardID }">
							</c:when>
							 <c:otherwise>
							            <!-- 기존 파일이 있을 경우 -->
							            <c:choose>
							               <c:when test="${not empty board.sfile}">
							                  <img src="upload/${board.sfile}" alt="${board.boardID}">
							               </c:when>
							               <c:otherwise>
							                  <!-- 기존 파일도 없을 때는 아무것도 출력하지 않음 -->
							                  &nbsp;
							               </c:otherwise>
							            </c:choose>
							   </c:otherwise>
						</c:choose>
						
						
					
				</td>
			</tr>
			<tr>
				<th>파일 다운로드 수</th>
				<td>${board.downcount}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${board.visitcount}</td>
			</tr>
			<tr>
				<td colspan="2" class="sub6-btn">
					<div class="buttons">
						<c:choose>
							<c:when test="${ not empty sessionScope.userID }">
								<button type="button" onclick="location.href='checkPass.do?mode=edit&boardID=${board.boardID}'">수정하기</button>
								<button type="button" onclick="location.href='checkPass.do?mode=delete&boardID=${board.boardID}'">삭제하기</button>
							</c:when>
						</c:choose>
						
					
					
						
						<button type="button" onclick="location.href='boardList.do'">목록</button>
					</div>
					
				</td>
			</tr>
		
		</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	
	
	
	</div>
</body>
</html>