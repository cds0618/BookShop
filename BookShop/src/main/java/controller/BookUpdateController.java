package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import dao.BookDAO;
import message.ShowMessage;
import model.Book;


//fileSizeThreshold : 메모리에 저장되는 임시 파일 크기(2MB)
//maxFileSize : 업로드할 파일의 최대 크기(10MB)
//maxRequestSize : request(파일 요청)시의 최대 크기(50MB)
@WebServlet("/BookShop/bookUpdate.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class BookUpdateController extends HttpServlet {
	//파일 업로드, 다운로드에 사용되는 폴더 지정
	private static final String IMAGE_DIR = "images";
	//BookDAO 클래스를 사용하여 dao 인스턴스 생성
	private BookDAO dao = new BookDAO();
	
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		int bookID = Integer.parseInt(req.getParameter("bookID"));
		BookDAO dao = new BookDAO();
		Book dto = dao.selectOne(bookID);
		//카테고리 목록을 category테이블에서 가져와서 cate에 저장
		List<String> cateList = dao.cateList();
		req.setAttribute("dto", dto);
		req.setAttribute("cateList", cateList);
		req.getRequestDispatcher("bookUpdate.jsp").forward(req, res);
		
	}
	
	//부모클래스에서 상속받은 doPost메서드 오버라이딩
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		//한글깨짐방지
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		//입력폼에서 데이터 가져와서 각 변수에 저장하기
		int bookID = Integer.parseInt(req.getParameter("bookID"));
		String bookName = req.getParameter("bookName");
		//string 자료형을 double 자료형으로 변환
		Double price = Double.parseDouble(req.getParameter("price"));
		String author = req.getParameter("author");
		String description = req.getParameter("description");
		String publisher = req.getParameter("publisher");
		String category = req.getParameter("category");
		Long unitsInStock = Long.parseLong(req.getParameter("unitsInStock"));
		Long totalPages = Long.parseLong(req.getParameter("totalPages"));
		Date releaseDate =Date.valueOf(req.getParameter("releaseDate"));
		
		
		
		
		//기존 이미지 파일명을 문자열형으로 가져오기
		String oldImageUrl = req.getParameter("oldImageUrl");
		//파일 가져오기 
		Part part = req.getPart("imageUrl");
		
		String fileName = null;
		//이미지가 업로드된 경우 처리
		if(part != null && part.getSize() > 0) {
			//가져온 파일이 이미지인지 아닌지 판별하는 명령어 이미지가 맞으면 if문 다음 명령어 실행
			String mimeType = part.getContentType();
			if(mimeType == null || !mimeType.startsWith("image/")){
			ShowMessage.alertBack(res, "이미지 파일만 업로드 가능합니다.");
			return;
			}
		
		
		//extractFileName 메서드 호출
		fileName = extractFileName(part);
		//image폴더의 절대 경로를 구해서 savePath변수에 저장
		String savePath = getServletContext().getRealPath("") + File.separator + IMAGE_DIR;
		//savePath 경로의 파일을 filesaveDir변수에 저장
		File fileSaveDir = new File(savePath);
		//만약 파일이 존재하지 않으면 새로운 폴더 생성
		if(!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		
		//이미지 파일 저장
		try { 
			//'절대경로/파일명'으로 이루어진 전체 파일명을 part객체에 저장
			part.write(savePath + File.separator + fileName);
		//파일 저장 중 예외 처리
		}catch(Exception e) {
			e.printStackTrace();
			//응답 -> 에러났을때, 에러를 알림
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "파일 저장 중 오류 발생");
			return;
		}
		
		}else {
			fileName=oldImageUrl;
		}
		
		
		
		Book book = new Book(bookID, bookName, price, author, description, publisher, category, unitsInStock, totalPages, releaseDate, fileName);
		//dao객체의 addBook메서드 호출
		int result = dao.updateBook(book);
		//result값이 1이면 도서 등록 성공, 1이 아니면 도서 등록 실패
		
		if(result == 1) {
			ShowMessage.alertLocation(res, "내용이 수정되었습니다.", "bookList.do");
						
		}else {
			ShowMessage.alertBack(res, "수정이 완료되지 않았습니다.");
		}
	}
	//파일 이름만 추출하기
	//part : multipart/form-data는 application/x-www.form-urlencoded와 비교해서 매우 복잡하고 각 part로 나뉘어져 있다.
	//이런 part를 getHeader()나 getContentType()등의 메서드를 사용해서 얻어올 수 있다.
	private String extractFileName(Part part) {
		//contentDisp 변수에 form-data:name="fileName";filename="추가한 파일 이름"
		//예) form-data:name="fileName";fileName="slide1.jpg"
		//items[0] = form-data:name="fileName"
		//items[1] = fileName="slide1.jpg"
		String contentDisp = part.getHeader("content-disposition");
		//contentDisp변수에 저장된 문자열을 ;을 기준으로 분리하여 items에 배열로 저장
		String[] items = contentDisp.split(";");
		//items배열의 원소 개수 만큼 반복, s는 items 배열의 원소를 가리킴
		//원소 
		for(String s : items) {
			//만약 items배열의 원소값에서 공백(trim)을 제거한 파일명이 있다면
			//trim():공백을 제거하는 메서드
			if(s.trim().startsWith("filename")) {
				//파일명에서 =기호 다음의 문자열만 추출하여 반환 indexOf = > 몇번 인덱스에 있는지 확인하는 것
				//길이에서 하나빼 왜? 큰 따옴표만 제거해서 파일명만 얻으려고! 그래서 filename에다가 넣는것! 
				//substring(5, 10) : 전체 문자열에서 5번째 문자부터 10-1번째 문자까지만 추출하는 메서드
				//indexOf("문자") : 문자가 위치한 인덱스 번호를 구하는 메서드
				return s.substring(s.indexOf("=") + 2 , s.length() - 1); 
			}
		}
		return "";
	}

}
