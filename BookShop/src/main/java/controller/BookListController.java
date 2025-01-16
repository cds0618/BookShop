package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import model.Board;
import model.Book;
import page.Paging;

@WebServlet("/BookShop/bookList.do")
public class BookListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 		
		//BookDAO 클래스를 사용하여 DAO인스턴스 생성
		BookDAO dao = new BookDAO();
		
		//bookList 페이지에 전달할 매개변수용 배열 생성
	      Map<String, Object> map = new HashMap<String, Object>();
	      String searchField = req.getParameter("searchField");
	      String searchWord = req.getParameter("searchWord");
	      if(searchWord != null) {
	         map.put("searchField", searchField);
	         map.put("searchWord", searchWord);
	      }
	    //정렬 처리
	      String sortObj = req.getParameter("sortObj");
	      String sortOrder = req.getParameter("sortOrder");
	      //기본값 설정(정렬기준과 방향이 없으면 게시글 번호를 기준으로 내림차순함)
	      if(sortObj == null) {sortObj = "bookID";}
	      if(sortOrder == null) {sortOrder = "desc";}
		
	      
	    //paging 처리
	      //BoardDAO의 selectAll메서드를 호출해서 구한 결과를 total 변수에 저장
	      int total = dao.selectAll(map);
	      System.out.println("총 도서 수: " + total);
	      //한 페이지에 출력되는 게시글 수 
	      int pageSize = 10;
	      //한블록에 보이는 페이지 번호 개수 1 2 3 4 5 -> 하고 아래 보이는 페이지 번호
	      int blockPage = 5;
	      //현재 페이지 확인 (처음 들어가면 현재 페이지는 일단 1페이지니까 1로 초기화 시켜두는 것)
	      int pageNum = 1;
	      //매개 변수로 페이지번호를 전달받아서 pageTemp에 저장
	      String pageTemp = req.getParameter("pageNum");
	      //만약 페이지번호가 비어 있지 않으면 요청받은 페이지수(문자열)를 정수형으로 변환하여 pageNum에 다시 저장
	      //현재 페이지가 몇 페이지인지 표시해야하니까 1페이지 -> 1이 입력되면 정수로 바꿔서 temp에 보관한뒤 다시 pagenum에 넣어주는것 /2페이지 3페이지 일때도 똑같음!!
	      if(pageTemp != null && !pageTemp.equals("")) {
	    	  pageNum = Integer.parseInt(pageTemp);
	      }
	      //한페이지에 표시되는 마지막 게시글 번호
	      int start = (pageNum - 1) * pageSize + 1;
	      //한 페이지에 표시되는 처음 게시글 번호
	      int end = pageNum * pageSize;
	      //map객체에 start와 end 추가
	      map.put("start", start);
	      map.put("end", end);
	      map.put("sortObj", sortObj);
	      map.put("sortOrder", sortOrder);
	      
		
		
		
		
		//dao인스턴스의 bookList()메서드를 호출하고, 호출한 결과값을 bookList에 저장
		//게시글 목록 구하기
		List<Book> bookList = dao.bookList(map, sortObj, sortOrder);
		if (bookList == null || bookList.isEmpty()) {
		    System.out.println("책 목록이 없습니다.");
		}
		
	      String pagingVar = Paging.pagingStr(total, pageSize, blockPage, pageNum, "bookList.do");
	      map.put("pagingVar", pagingVar);
	      map.put("pageSize", pageSize);
	      map.put("pageNum", pageNum);
	      map.put("total", total);
	      //boardList.jsp에 map과 total 연결
	      req.setAttribute("map", map);
	      req.setAttribute("bookList", bookList);
	      req.getRequestDispatcher("bookList.jsp").forward(req, res);
	}

}
