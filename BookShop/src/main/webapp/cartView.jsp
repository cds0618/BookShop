<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마니사도서-장바구니</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/1083064ac2.js" crossorigin="anonymous"></script>

<link href="css/style.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</head>
<body>
	<div class="wrap">
		<jsp:include page="nav.jsp"></jsp:include>
			<h2 class="box8H2">나의 장바구니</h2>
			<div class ="sub-box8">
				<div class="center">
					<table id="cart-table">
						<tr>
							<th colspan="3">상품정보</th>
							<th>수량</th>
							<th>상품금액</th>
							<th>배송정보</th>
							<th>주문</th>
						</tr>
						<c:choose>
							<c:when test="${empty cartList }">
								<tr id="empty-cart">
									<td colspan="7" class="center-box">
									장바구니가 비어있습니다.
									</td>
								</tr>
							
							</c:when>
							<c:otherwise>
								<c:forEach var="cart" items="${ cartList }">
									<tr id="cart-item-${cart.cartID }" style="width: 100%">
				                        <td style="width: 5%"><input type="checkbox" name="choice" checked onchange="updateTotalAmount()"></td>
				                        <td style="width: 10%"><img src="images/${ cart.imageUrl }" alt="${ cart.bookName }"></td>
				                        <td style="width: 35%">
				                           <span>소득공제</span>
				                           ${ cart.bookName }<br>
				                           <i>(10%)할인&nbsp;&nbsp;<b>P</b>900원</i>
				                        </td>
				                        
				                        <td style="width: 10%"><input type="number" value="${ cart.num }" min="1" onchange = "updateNum(${ cart.cartID}, this.value)"></td>
				                        <td style="width: 10%" id="item-price-${cart.cartID }" class="td5"><fmt:formatNumber value="${cart.price * cart.num }" pattern="#,###"/>원</td>
				                        <td style="width: 15%" class="one-btn">
				                           <p>내일 아침 7시 전</p>
				                           <span>16시 전에 도착 예정</span><br>
				                           <button type="button">아침배송</button>
				                        </td>
				                        <td class="sub6-btn" style="width: 15%">
				                           <button type="button" onclick="orderItem(${cart.cartID})">주문하기</button><br>
				                           <button type="button" onclick="location.href='bookList.do'">나중에 주문</button><br>
				                           <button type="button" onclick ="deleteItem(${cart.cartID})">삭제</button>
				                        </td>
				                     </tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<tr>
							<td colspan ="7" class="right-box">
								<p>총 금액 : <span id="total-amount">
									<fmt:formatNumber value="${ totalAmount }" pattern="#,###"/>원
								</span></p>
								
							</td>
						</tr>
						
					</table>
				</div>
			</div>
		<jsp:include page="footer.jsp"></jsp:include>
		<script src="http://code.jquery.com/jquery-3.7.1.js"></script>
   <script>
      //체크박스(checkbox)의 해제 유무에 따라 총금액 구하여 표시하기
      function updateTotalAmount(){
         //정수형 변수 선언 및 초기화
         let totalAmount = 0;
         //체크된 체크박스의 개수만큼 반복
         $("input[name='choice']:checked").each(function(){
            //체크박스와 가장 가까운 tr태그를 찾아서 row에 저장
            let row = $(this).closest("tr");
            //금액 계산, "원"글자나 ","글자 제거하고 숫자형으로 형변환해서 price변수에 저장
            let price = parseInt(row.find("td.td5").text().replace("원", "").replace(",",""));
            //totalAmount변수에 price(금액) 누적합
            totalAmount += price;
         });
         //총금액을 아이디가 total-amount인 영역에 표시
         $("#total-amount").text(totalAmount + "원");
      }   
      
      //수량 변경시 호출되는 함수
     //수량 변경시 호출되는 함수
		//수량 변경시 호출되는 함수
		function updateNum(cartID, num){
		    let checkbox = $("#cart-item-" + cartID).find("input[name='choice']");
		    //만약 checkbox에 체크가 되어 있지 않다면 아무것도 실행하지 않음
		    if (!checkbox.is(":checked")) {
		        return;
		    }
		
		    $.ajax({
		        type: 'POST',
		        url: 'cartView.do',
		        data: { cartID: cartID, num: num },
		        success: function(response) {
		        	// 항목별 총 가격 업데이트
	                $("#item-price-" + cartID).text(response.itemPrice.toLocaleString() + "원");
	                // 총 금액 업데이트
	                $("#total-amount").text(response.totalAmount.toLocaleString() + "원");
		        },
		        error: function() {
		            alert("수량 변경 중 오류 발생");
		        }
		    });
		}


      //장바구니에서 도서 삭제
      function deleteItem(cartID){
    	  $.ajax({
    		  type:'POST',
    		  url:"deleteCart.do",
    		  data : {cartID : cartID},
    		  success : function(response){
    			  //아이디가 cart-item-카트번호인 요소를 문서에서 삭제
    			  $("#cart-item-" + cartID).remove();
    			  //총금액 업데이트
    			  $("#total-amount").text(response.totalAmount.toLocaleString() + "원");
    			  //장바구니가 비어있으면 메시지 표시
    			  if(response.emptyCart){
    				  $("#cart-table").append("<tr id='empty-cart'><td colspan='7' class='center-box'> 장바구니가 비었습니다.</td></tr>");
    				  
    			  }
    		  },
    		  error:function(){
    			  alert("도서 삭제 중 오류 발생!");
    		  }
    		  
    	  });
    	  
    	  
      }
   
   
   
   </script>

	</div>
</body>
</html>