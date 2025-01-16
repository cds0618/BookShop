<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마니사도서 - 도서 수정하기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/1083064ac2.js" crossorigin="anonymous"></script>

<link href="css/style.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<div class="wrap">
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="sub-box4 upup">
		<h2>도서 수정하기</h2>
		<form name="frm" action="bookUpdate.do" method="post" enctype="multipart/form-data" onsubmit="return valfn(this);">
			<input type="hidden" name="bookID" value="${ dto.bookID }">
			<input type="hidden" name="oldImageUrl" value="${dto.imageUrl}">
		
			<table class="addTb">
				<tr>
					<td>도서번호</td>
					<td>${dto.bookID}</td>
				</tr>
				<tr>
					<td>도서명</td>
					<td><input type="text" name="bookName" value="${dto.bookName}"></td>
				</tr>
				<tr>
					<td>도서가격</td>
					<td><fmt:formatNumber value="${ dto.price }" pattern="#,###"/>원</td>
				</tr>
				<tr>
					<td>저자명</td>
					<td><input type="text" name="author" value="${dto.author}"></td>
				</tr>
				<tr>
					<td>상세설명</td>
					<td><textarea name="description" cols="70" rows="5" placeholder="200자 이내로 작성해 주세요.">${dto.description}</textarea></td>
				</tr>
				<tr>
					<td>출판사</td>
					<td><input type="text" name="publisher" value="${dto.publisher}"></td>
				</tr>
				<tr>
					<td>도서분류</td>
					<td>
						<select name="category">
						<!-- cateList는 테이블로 만든데이터 모두 가져와라 임 -->
                    		<c:forEach var="cate" items="${cateList}">
                    		<!-- 삼항연산자임 앞에 내용이 맞으면 selected 실행 아니면 '' 빈칸-->
                    		<!-- ${cate} 는 여러목록중 하나보여주세요 -->
                        		<option value="${cate}" ${cate eq dto.category ?'selected': ''}>${cate}</option>
                     		</c:forEach>
                  		</select>
					</td>
				</tr>
				<tr>
					<td>재고량</td>
					<td><input type="text" name="unitsInStock" value="${dto.unitsInStock}"></td>
				</tr>
				<tr>
					<td>총페이지수</td>
					<td><input type="text" name="totalPages" value="${dto.totalPages}">페이지</td>
				</tr>
				<tr>
					<td>출판일</td>
					<td><input type="text" name="releaseDate" value="${dto.releaseDate}" placeholder="예) 2025-01-01"></td>
				</tr>
				<tr>
					<td>도서표지(이미지)</td>
					<td>
						<input type="file" name="imageUrl" id="imageInput" onchange="preview(event)">
						<!-- 기존 이미지 파일명 표시 -->
						<div>
							<span id="fileName">${dto.imageUrl != null ? dto.imageUrl : '선택된 파일 없음' }</span>
						</div>
						<!-- 기존 이미지 표시되는 부분 -->
						<div>
							<img id = "bookImage" src="images/${dto.imageUrl}" alt="${dto.bookName}">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="three-btn">
						<button type="submit">수정하기</button>
						<button type="reset">다시입력</button>
						<button type="button" onclick="location.href='bookList.do'" class="list-btn">목록</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</div>

	<script>
				function valfn(form){
					if(form.bookName.value == ""){
						alert("도서명를 입력해 주세요.");
						form.bookName.focus();
						return false;	
					}
					if(form.price.value ==""){
						alert("도서가격을 입력해 주세요.");
						form.price.focus();
						return false;
					}
					if(form.author.value ==""){
						alert("저자명을 입력하세요.");
						form.author.focus();
						return false;
					}
					
				}
				
		//도서 표지 이미지 표시하기, 이미지가 변경되면 
		function preview(event){
			//onchange 이벤트가 발생한 객체를 전달받아서 input변수에 저장
			let input = event.target;
			//아이디가 fileName인 객체를 fileNameField변수에 저장
			let fileNameField = document.getElementById("fileName");
			//아이디가 bookImage인객체를 imageField변수에 저장
			let imageField = document.getElementById("bookImage");
			//기존 이미지 파일명 표시
			if(input.files.length > 0){
				fileNameField.textContent = input.files[0].name;
			}else{
				fileNameField.textContent = "${dto.imageUrl != null ? imageUrl : '선택된 파일 없음'}";
			}
			//기존 이미지 파일 표시하거나 새로 첨부한 이미지 표시
			let reader = new FileReader();
			reader.onload = function(){
				//readyState가 2다 하면 읽을준비 됬는지 상태를 나타내는것
				if(reader.readyState === 2){
					imageField.src = reader.result;
					
				}
			}
			//input입력란에 있는 파일 내용을 읽어서 dataURL 형식의 문자열로 저장 
			if(input.files.length > 0){
				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>
</body>
</html>