package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAO;
import model.Cart;

@WebServlet("/BookShop/updateNum.do")
public class UpdateNumController extends HttpServlet {
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	int cartID = Integer.parseInt(req.getParameter("cartID"));
	int num = Integer.parseInt(req.getParameter("num"));
	
	//수량 업데이트
	CartDAO cartDao = new CartDAO();
	cartDao.updateNum(cartID, num);
	//사용자 아이디가 같은 레코드만 장바구니 목록에서 찾아서 cartList로 전달
	
	HttpSession session = req.getSession();
	String userID = (String) session.getAttribute("userID");
	List<Cart> cartList = cartDao.cartList(userID);
	//총 금액 계산
	double totalAmount = 0;
	double itemPrice = 0;
	//향상된 for문, cartList 개수만큼 반복, Cart(자료형), cart(cartList목록의 한개의 레코드를 가리킴)
	for(Cart cart : cartList) {
		if(cart.getCartID() == cartID) {
			itemPrice = cart.getPrice() * cart.getNum();			
		}
		totalAmount += cart.getPrice() * cart.getNum();
	}
	//계산된 총금액을 response 내장객체로 넘김
	res.setContentType("application/json");
	res.setCharacterEncoding("UTF-8");
	res.getWriter().write("{\"itemPrice\": " + itemPrice + ", \"totalAmount\": " + totalAmount + "}");
	}
}
