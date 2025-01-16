package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BookDAO;
import model.Book;

@WebServlet("/BookShop/bookDetail.do")
public class BookDetailController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // BookDAO 객체 생성
        BookDAO dao = new BookDAO();
        
        // bookID를 받아서 Book 객체를 조회
        int bookID = Integer.parseInt(req.getParameter("bookID"));
        Book dto = dao.selectOne(bookID);

        // 정렬 기준과 순서를 요청 파라미터에서 받기
        String sortObj = req.getParameter("sortObj"); // 정렬 기준 (bookName, author 등)
        String sortOrder = req.getParameter("sortOrder"); // 정렬 순서 (ASC, DESC)

        // 기본값 설정 (요청 파라미터가 없으면 기본값 사용)
        if (sortObj == null || sortObj.isEmpty()) {
            sortObj = "bookName"; // 기본값
        }

        if (sortOrder == null || sortOrder.isEmpty()) {
            sortOrder = "ASC"; // 기본값
        }

        // 페이징 관련 매개변수 설정 (예시로 start=1, end=10)
        Map<String, Object> map = new HashMap<>();
        map.put("start", 1);  // start 값
        map.put("end", 10);   // end 값

        // bookList를 가져옴
        List<Book> bookList = dao.bookList(map, sortObj, sortOrder);
        int totalCount = dao.selectCount(); // 도서의 총 개수 가져오기

        // 필요한 데이터를 request에 저장
        req.setAttribute("dto", dto);
        req.setAttribute("bookLists", bookList);
        req.setAttribute("totalCount", totalCount);

        // bookDetail.jsp로 포워딩
        req.getRequestDispatcher("bookDetail.jsp").forward(req, res);
    }
}
