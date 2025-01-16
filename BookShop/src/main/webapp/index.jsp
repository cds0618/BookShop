<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 쇼핑몰 - 마니사도서</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/1083064ac2.js" crossorigin="anonymous"></script>

<link href="css/style.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
	<div class="wrap">
		<jsp:include page="nav.jsp"></jsp:include>
		<div class = "main">
		<!-- Carousel -->
		<div id="demo" class="carousel slide" data-bs-ride="carousel">
		
		  <!-- 페이지네이션 -->
		  <div class="carousel-indicators">
			  <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
			  <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
			  <button"src/main/webapp/images/card.jpg" type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
			  <button type="button" data-bs-target="#demo" data-bs-slide-to="3"></button>
			  <button type="button" data-bs-target="#demo" data-bs-slide-to="4"></button>
			  <button type="button" data-bs-target="#demo" data-bs-slide-to="5"></button>
		  </div>
		
		  <!-- 캐로우셀 이미지 -->
		  <div class="carousel-inner carousel-fade">
		  	<div class="carousel-item active cr1">
		  		<div class="carousel-in d-flex justify-content-around align-items-center">
	      			<div class="des">
	      				<a href="#">
	      					<p>김애란, 다시 이야기의 시작으로</p>
	      					<h1>이중 하나는<br> 
	      					거짓말</h1>
	      					<span>홀로그램 유리컵 증정(국내도서 2만원 이상)</span>
	      				</a>
	      			</div>		
	      			<div class="book-img">
	      				<a href="#">
	      					<span><i class="fa-solid fa-children"></i></span>
	      					<img src="img/book1.jpg" alt="이중하나는 거짓말">
	      				</a>
	      			</div>
		  		</div>
		  	</div>
		  	<div class="carousel-item cr2">
		  		<div class="carousel-in d-flex justify-content-around align-items-center">
		  		<div class="des">
	      			<a href="#">
	      				<p>대단한 내공의 늦깎이 에세이스트, 그 화려한 탄생</p>
	      				<h1>즐거운 어른</h1>
	      				<span>이옥선 산문 구매시, 아크릴 코스터 증정</span>
	      			</a>
	      		</div>
	      		<div class="book-img">
	      			<a href="#">
	      				<span><i class="fa-solid fa-person-cane"></i></span>
	      				<img src="img/book2.jpg" alt="즐거운 어른">
	      			</a>
	      			</div>
	      		</div>
		  	</div>
		  	<div class="carousel-item cr3">
		  		<div class="carousel-in d-flex justify-content-around align-items-center">
		  			<div class="des">
	      				<a href="#">
	      					<p>김종원 작가의 10대들을 위한 문장</p>
	      					<h1>너에게 들려주는<br> 
	      					단단한 말</h1>
	      					<span>투명 유리컵 증정(대상도서 1권 포함 2만원 이상 구매)</span>
	      				</a>
	      			</div>
	      			<div class="book-img">
	      				<a href="#">
	      					<span><i class="fa-solid fa-person-harassing"></i></span>
	      					<img src="img/book3.jpg" alt="너에게 들려주는 단단한 말">
	      				</a>
	      			</div>
	      		</div>
		  	</div>
		  	<div class="carousel-item cr4">
		  		<div class="carousel-in d-flex justify-content-around align-items-center">
		  			<div class="des">
	      				<a href="#">
	      					<p>그래픽으로 읽는 &lt;사피엔스&gt;</p>
	      					<h1>사피엔스<br> 
	      					:그래픽 히스토리</h1>
	      					<span>사피엔스 구매 시, 틴케이스 or 도자기 접시 증정(택1)</span>
	      				</a>
	      			</div>
	      			<div class="book-img">
	      				<a href="#">
	      					<span><i class="fa-solid fa-meteor"></i></span>
	      					<img src="img/book4.jpg" alt="사피엔스:그래픽 히스토리">
	      				</a>
	      			</div>
	      		</div>
		  	</div>
		  	<div class="carousel-item cr5">
		  		<div class="carousel-in d-flex justify-content-around align-items-center">
		  			<div class="des">
	      				<a href="#">
	      					<p>매운맛 재테크 멘탈 수업</p>
	      					<h1>돈 공부를 시작하고<br> 
	      					인생의 불안이<br>
	      					사라졌다</h1>
	      					<span>굿즈 증정(이벤트 도서 포함, 국내도서 4만원 이상)</span>
	      				</a>
	      			</div>
	      			<div class="book-img">
	      				<a href="#">
	      					<span><i class="fa-solid fa-money-check-dollar"></i></span>
	      					<img src="img/book10.jpg" alt="돈 공부를 시작하고 인생의 불안이 사라졌다">
	      				</a>
	      			</div>
	      		</div>
		  	</div>
		  	<div class="carousel-item cr6">
		  		<div class="carousel-in d-flex justify-content-around align-items-center">
		  			<div class="des">
	      				<a href="#">
	      					<p>길고 쓸모 있는 인생의 비밀을 찾아 떠난 여행</p>
	      					<h1>프랭클린<br>
	      					익스프레스</h1>
	      					<span>데스크 매트 증정(국내도서 2만원 이상)</span>
	      				</a>
	      			</div>
	      			<div class="book-img">
	      				<a href="#">
	      					<span><i class="fa-solid fa-otter"></i></span>
	      					<img src="img/book8.jpg" alt="프랭클린 익스프레스">
	      				</a>
	      			</div>
	      		</div>
		  	</div>
		  </div> 
		</div><!-- carousel 끝 -->
		<div class="main-banner">
			<div class="banner1">
				<div class="banner-img">
					<img src="img/toeic.jpg" alt="토익">
					
				</div>
				<a href="#"> 
					<div class="banner-des">
						<span>수험영어 목표 달성 챌린지</span>
						<h4>수험영어 목표 점수 적으면 교환권 증정</h4>
						<p><i class="fa-solid fa-arrow-right"></i></p>
					</div>
				</a>
			</div>
			<div class="banner2">
				<div class="banner-img">
					<img src="img/gom.png" alt="망곰이">
				</div>
				<a href="#"> 
					<div class="banner-des">
						<span>모나미와 망곰이가 쏜다!</span>
						<h4>모나미X망그러진곰 콜라보 제품 구매시 선물 증정</h4>
						<p><i class="fa-solid fa-arrow-right"></i></p>
					</div>
				</a>
			</div>
		
		</div><!-- main banner -->	
	</div><!-- main -->
	<div class="field">
		<div class="center">
			<h2>분야별 도서</h2>
			<p>찾으시는 분야의 도서를 골라보세요.</p>
			<div class="field-box">
				<ul>
					<li>
						<a href="#">
							<i class="fa-solid fa-award"></i>
							<p>베스트 셀러</p>
						</a>
					</li>
					<li>
						<a href="#">
							<i class="fa-solid fa-book"></i>
							<p>국내ㆍ외 도서</p>
						</a>
					</li>
					<li>
						<a href="#">
							<i class="fa-solid fa-rotate"></i>
							<p>중고샵</p>
						</a>
					</li>
					<li>
						<a href="#">
							<i class="fa-solid fa-tablet-screen-button"></i>
							<p>E-Book</p>
						</a>
					</li>
					<li>
						<a href="#">
							<i class="fa-solid fa-people-group"></i>
							<p>독서클럽</p>
						</a>
					</li>
					<li>
						<a href="#">
							<i class="fa-solid fa-compact-disc"></i>
							<p>CD/LP/DVD</p>
						</a>
					</li>
					<li>
						<a href="#">
							<i class="fa-solid fa-pen-ruler"></i>
							<p>문구/GIFT</p>
						</a>
					</li>			
					<li>
						<a href="#">
							<i class="fa-solid fa-ticket"></i>
							<p>티켓</p>
						</a>
					</li>				
				</ul>
			</div>
		</div>
	</div><!-- field -->
	<div class="new">
		<div class="new-bg">
		</div>
		<div class="center">
			<h2>지금 이 책!</h2>
			<p>이 주에 새롭게 등록된 신상품 중 MD가 추천하는 리스트입니다.</p>
			<div class="new-box">
				<ul>
					<li>
						<a href="">
							<div class="new-img">
								<b><i>매일 아침을 바꾼 강연</i></b>
								<div class="new-img-ani">
									<img src="img/b3.jpg" alt="우울해서 빵을 샀어">
								</div>
							</div>
							<div class="new-des">
								<span> 우울해서 빵을 샀어</span>
								<p>일상이 로맨틱 영화의 한 장면이 되는 52가지 감성 레시피</p>
							</div>
						</a>
					</li>
					<li>
						<a href="">
							<div class="new-img">
								<b><i>불안을 이기는 행복코드</i></b>
								<div class="new-img-ani">
									<img src="img/b10.jpg" alt="고전이 답했다">
								</div>
							</div>
							<div class="new-des">
								<span> 다시, 행복을 풀다</span>
								<p>행복에 대한 비밀 코드는 우리 머릿속에 있다. 행복을 추구하도록 당신의 뇌를 재프로그래밍하라! </p>
							</div>
						</a>
					</li>
					<li>
						<a href="">
							<div class="new-img">
								<b><i>치매 와도 걱정없이</i></b>
								<div class="new-img-ani">
									<img src="img/b6.jpg" alt="함께 살아가기 위한 말">
								</div>
							</div>
							<div class="new-des">
								<span>함께 살아가기 위한 말</span>
								<p>치매 돌봄은 따듯한 '말'에서 시작합니다</p>
							</div>
						</a>
					</li>
					<li>
						<a href="">
							<div class="new-img">
								<b><i>계절을 먹고 깨닫고 쓰다</i></b>
								<div class="new-img-ani">
									<img src="img/b9.jpg" alt="흙을 먹는 나날">
								</div>
							</div>
							<div class="new-des">
								<span>흙을 먹는 나날</span>
								<p>향긋한 흙내 나는 이야기로 깨운 요리 에세이</p>
							</div>
						</a>
					</li>
				</ul>
			</div>
			<div class="new-more">새로운 도서 더 보기</div>
		</div>
		
	</div><!-- new -->
	<jsp:include page="footer.jsp"></jsp:include>
</div><!-- wrap -->
</body>
</html>