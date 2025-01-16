<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마니사도서 - 도서목록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/1083064ac2.js" crossorigin="anonymous"></script>

<link href="css/style.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
	<div class="wrap">
		<jsp:include page = "nav.jsp"></jsp:include>
		<div class="sub-box3">
			<h2>도서 목록</h2>
			<form method="get">
			<table>
				<tr>
					<td class="center-box">
						<select name="searchField">
							<option value="bookName">제목</option>
							<option value="author">저자</option>
						</select>
						<input type="text" name="searchWord">
						<input type="submit" value="검색" class="search"> 			
					</td>
				</tr>
			</table>
		</form>
		
			<table>
				<tr>
					<td colspan="6" class="right-box">
						전체 도서 수 : ${ map.total }권
					</td>
				</tr>		
				<tr>
					<th>
						<a title="번호순 정렬" href="?sortObj=bookID&sortOrder=${map.sortOrder == 'asc' ? 'desc' : 'asc' }">도서번호</a>
					</th>
					<th>도서명</th>
					<th>저자명</th>
					<th>출판사</th>
					<th>출판일</th>
					<th>도서표지</th>					
				</tr>
				<c:choose>
					<c:when test="${ empty bookList }">
						<tr>
							<td colspan="6" class="center-btn">
								등록된 도서 목록이 없습니다.
							</td>
						</tr>
					</c:when>
					<c:otherwise>		
						<c:forEach var="book" items ="${bookList}" varStatus="loop">
							<tr>
								<td>${map.total - (((map.pageNum -1) * map.pageSize) + loop.index)}</td>
								<td class="bookName">
								<!-- 제목 클릭했을때 bookId를 통해서 상세보기 데이터를 가져오는 것! -->
									<a href="bookDetail.do?bookID=${ book.bookID }">${ book.bookName }</a>
									
								</td>
								<td>${ book.author }</td>
								<td>${ book.publisher }</td>
								<td>${ book.releaseDate }</td>
								<td>
									<img src="images/${ book.imageUrl }">
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>		
				</c:choose>
			<tr class="pageNum"> 
				<td colspan="6">
					<!-- 페이지 번호 표시 -->
					${map.pagingVar}
				</td>
			</tr>
			</table>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>	
</body>
</html>