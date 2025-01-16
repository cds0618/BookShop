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

@WebServlet("/JSPBookShop/cartView.do")
public class CartViewController extends HttpServlet {
    
   // 장바구니 조회 시 GET 요청 처리
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
      HttpSession session = req.getSession();
      // 세션에서 사용자 아이디 가져오기
      String userID = (String) session.getAttribute("userID");
      // 만약 userID가 없으면 로그인 페이지로 이동
      if (userID == null) {
         session.setAttribute("prevPage", "cartView.do");
         res.sendRedirect("login.jsp");
      } else {
         // CartDAO를 사용하여 장바구니의 데이터 가져오기
         CartDAO cartDao = new CartDAO();
         List<Cart> cartList = cartDao.cartList(userID);
         // 총 금액 계산
         double totalAmount = 0;
         for (Cart cart : cartList) {
            totalAmount += cart.getPrice() * cart.getNum();
         }
         // request내장객체에 cart를 세팅
         req.setAttribute("cartList", cartList);
         req.setAttribute("totalAmount", totalAmount);
         // 장바구니 페이지로 이동
         req.getRequestDispatcher("cartView.jsp").forward(req, res);
      }
   }

   // 장바구니 수량 변경 처리 POST 요청
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
      HttpSession session = req.getSession();
      String userID = (String) session.getAttribute("userID");
      
      if (userID == null) {
         res.sendRedirect("login.jsp");
         return;
      }

      // cartID와 변경된 수량 받기
      int cartID = Integer.parseInt(req.getParameter("cartID"));
      int num = Integer.parseInt(req.getParameter("num"));

      // CartDAO를 사용하여 장바구니 항목 수정
      CartDAO cartDao = new CartDAO();
      Cart cart = cartDao.getCartByID(cartID, userID); // cartID로 카트 항목 가져오기
      cart.setNum(num); // 수량 업데이트

      // 장바구니 항목 가격 재계산
      cartDao.updateCart(cart); // 장바구니에 수량 업데이트

      // 총 금액 계산
      List<Cart> cartList = cartDao.cartList(userID);
      double totalAmount = 0;
      for (Cart c : cartList) {
         totalAmount += c.getPrice() * c.getNum();
      }

      // 응답 데이터로 itemPrice와 totalAmount를 JSON 형식으로 반환
      res.setContentType("application/json");
      res.setCharacterEncoding("UTF-8");
      String jsonResponse = "{ \"itemPrice\": " + (cart.getPrice() * num) + ", \"totalAmount\": " + totalAmount + " }";
      res.getWriter().write(jsonResponse);
   }
}
