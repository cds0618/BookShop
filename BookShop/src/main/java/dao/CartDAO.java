package dao;

import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.DBCon;

public class CartDAO extends DBCon {
   //생성자(DB 연동)
   public CartDAO() {
      super();
   }
   //장바구니에 데이터 저장하는 메서드, 나중에 String userID 추가할 예정
   public int addCart(String userID, int bookID, 
         String bookName, String imageUrl, double price) {
      int result = 0;
   
      String sql = "insert into cart (userID, bookID, "
            + "bookName, imageUrl, price) values (?, ?, ?, ?, ?)";
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, userID);
         //나중에 번호도 2,3,4,5로 수정할 예정
         pstmt.setInt(2, bookID);
         pstmt.setString(3, bookName);
         pstmt.setString(4, imageUrl);
         pstmt.setDouble(5, price);
         result = pstmt.executeUpdate();
      }catch(Exception e) {
         e.printStackTrace();
      }
      return result;
   }
      
   //장바구니 항목 조회 메서드
   public List<Cart> cartList(String userID){
      List<Cart> cartList = new ArrayList<>();
      //book테이블과 cart테이블을 join한 결과를 표시하는 질의어
      //로그인한 userID와 cart의 userID값이 같은 레코드만 검색
      String sql = "select * from cart where userID = ?";
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, userID);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            Cart cart = new Cart();
            cart.setCartID(rs.getInt("cartID"));
            cart.setUserID(rs.getString("userID"));
            cart.setBookID(rs.getInt("bookID"));
            cart.setNum(rs.getInt("num"));
            cart.setAddDate(rs.getDate("addDate"));
            cart.setBookName(rs.getString("bookName"));
            cart.setImageUrl(rs.getString("imageUrl"));
            cart.setPrice(rs.getDouble("price"));
            cartList.add(cart);
         }
      }catch(Exception e) {
         e.printStackTrace();
      }
      return cartList;
   }
   
   //cart 테이블의 수량 업데이트 메서드
   public int updateNum(int cartID, int num) {
	   int result=0;
	   String sql = "update cart set num=? where cartID =?";
	   try {
		   pstmt = con.prepareStatement(sql);
		   pstmt.setInt(1, num);
		   pstmt.setInt(2, cartID);
		   result = pstmt.executeUpdate();
	   }catch (Exception e) {
		   e.printStackTrace();
	   }
	   return result;
   }
   
   //cart 테이블에서 항목 지우기
   public int deleteCart(int cartID) {
	   int result = 0;
	   String sql ="delete from cart where cartID = ?";
	   try {
		   pstmt = con.prepareStatement(sql);
		   pstmt.setInt(1, cartID);
		   result = pstmt.executeUpdate();
		   
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   return result;
   }
   
   //장바구니에 동일한 도서가 있는지 검사하는 메서드
   public boolean isBook(String userID, int bookID) {
	   boolean result = false;
	   String sql = "select count(*) from cart where userID = ? and bookID = ?";
	   
	   try {
		   pstmt = con.prepareStatement(sql);
		   pstmt.setString(1, userID);
		   pstmt.setInt(2, bookID);
		   rs = pstmt.executeQuery();
		   //rs에 결과가 있다면 result는 false가 됨(동일한 도서가 있다)
		   if(rs.next()) {
			   result = true;
		   }
	   }catch (Exception e) {
		   e.printStackTrace();
	   }
	   return result;
   }
   //장바구니에 동일한 도서가 있으면 그 도서의 수량만 변경하는 메서드
   public int updateBookAmount(String userID, int bookID) {
	   int result = 0;
	   String sql = "update cart set num = num + 1 where userID = ? and bookID = ?";
	   
	   try {
		   pstmt = con.prepareStatement(sql);
		   pstmt.setString(1, userID);
		   pstmt.setInt(2, bookID);
		   result = pstmt.executeUpdate();
		   
	   }catch(Exception e) {
		   e.printStackTrace();
	   
	   
	   }return result;
   }
	   
   //장바구니 총 레코드 개수를 구하는 메서드
   public int selectCart() {
	   int result = 0;
	   String sql = "select count(*) from cart";
	   try {
		   pstmt = con.prepareStatement(sql);
		   rs = pstmt.executeQuery();
		   if(rs.getInt(1) == 1) {
			   result = 1;
		   }
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   return result;
   }
   
   // cart 테이블에서 cartID와 userID로 장바구니 항목 조회하는 메서드
   public Cart getCartByID(int cartID, String userID) {
      Cart cart = null;
      String sql = "select * from cart where cartID = ? and userID = ?";
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, cartID);
         pstmt.setString(2, userID);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            cart = new Cart();
            cart.setCartID(rs.getInt("cartID"));
            cart.setUserID(rs.getString("userID"));
            cart.setBookID(rs.getInt("bookID"));
            cart.setNum(rs.getInt("num"));
            cart.setAddDate(rs.getDate("addDate"));
            cart.setBookName(rs.getString("bookName"));
            cart.setImageUrl(rs.getString("imageUrl"));
            cart.setPrice(rs.getDouble("price"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return cart;
   }
   
// cart 테이블의 항목을 업데이트하는 메서드
   public int updateCart(Cart cart) {
      int result = 0;
      String sql = "update cart set userID = ?, bookID = ?, bookName = ?, imageUrl = ?, price = ?, num = ? where cartID = ?";
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, cart.getUserID());
         pstmt.setInt(2, cart.getBookID());
         pstmt.setString(3, cart.getBookName());
         pstmt.setString(4, cart.getImageUrl());
         pstmt.setDouble(5, cart.getPrice());
         pstmt.setInt(6, cart.getNum());
         pstmt.setInt(7, cart.getCartID());
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return result;
   }
   
   
}
