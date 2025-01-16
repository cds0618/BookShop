package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.CartDAO;
import message.ShowMessage;
import model.Book;

@WebServlet("/JSPBookShop/addCart.do")
public class AddCartController extends HttpServlet {
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
      //한글깨짐방지
      req.setCharacterEncoding("UTF-8");
      res.setContentType("text/html;charset=UTF-8");
      
      //세션정보 가져옴
      HttpSession session = req.getSession();
      //도서 번호 가져옴
      String bookIDs = req.getParameter("bookID");
      //만약 도서 번호가 비어 있으면 도서 목록으로 이동
      if(bookIDs == null || bookIDs.isEmpty()) {
         res.sendRedirect("bookList.do");
         return;
      }
      //문자열로 가져온 bookIDs값을 정수형으로 변환하여 bookID에 저장
      int bookID = Integer.parseInt(bookIDs);
      //session내장객체에 들어있던 userID속성값을 문자열로 변환하여 userID변수에 저장
      String userID = (String) session.getAttribute("userID");
      //bookName, imageUrl, price도 매개변수로 전달받기
      String bookName = req.getParameter("bookName");
      String imageUrl = req.getParameter("imageUrl");
      double price = Double.parseDouble(req.getParameter("price"));
      
      //CartDAO클래스를 사용하여 cartDao인스턴스 생성
      CartDAO cartDao = new CartDAO();
      //장바구니의 전체 레코드 수 가져오기
      int cartNum = cartDao.selectCart();
      
      if (cartNum == 0) {
         addBookToCart(res, userID, bookID, bookName, imageUrl, price);
      }else {
         //만약 장바구니에 추가하려는 도서와 같은 도서가 있다면 도서의 수량만 증가
         //같은 도서가 없으면 장바구니에 새로 도서 추가
         boolean isBook = cartDao.isBook(userID, bookID);
         if (isBook) {
            //수량 추가
            int commit = cartDao.updateBookAmount(userID, bookID);         
            if (commit == 1) {
               ShowMessage.alertLocation(res, "도서 수량이 변경되었습니다", "cartView.do");
            }else {
               ShowMessage.alertBack(res, "도서 수량이 변경되지 않았습니다.");
            }
         }else {
            addBookToCart(res, userID, bookID, bookName, imageUrl, price);
         }   
      }         
   }
   //장바구니에 새로운 도서 추가하는 메서드
   public void addBookToCart(HttpServletResponse res, String userID, int bookID, String bookName, String imageUrl, double price) throws IOException {
      CartDAO cartDao = new CartDAO();
      //새로운 도서 추가
      //Book클래스로 만들어진 book인스턴스에 DB의 cart테이블에
      //bookID값에 해당하는 레코드를 찾아서 저장      
      int result = cartDao.addCart(userID, bookID, bookName, imageUrl, price);
      //도서를 장바구니에 추가하는데 성공했다면
      if (result == 1) {
         res.setContentType("text/html; charset=UTF-8");
         PrintWriter out = res.getWriter();
         out.println("<script>");
         out.println("if(confirm('장바구니에 추가했습니다.\\n장바구니로 이동하시겠습니까?')){");
         out.println("window.location.href='cartView.do';");
         out.println("} else {");
         out.println("window.location.href='bookDetail.do?bookID=" + bookID + "';");
         out.println("}");
         out.println("</script>");         
      }else {
         ShowMessage.alertBack(res, "장바구니에 추가되지 않았습니다.");
      }
   }
}
