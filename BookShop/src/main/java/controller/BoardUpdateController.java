package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.BoardDAO;
import message.ShowMessage;
import model.Board;
import model.Book;

@WebServlet("/JSPBookShop/boardUpdate.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
					maxFileSize = 1024 * 1024 * 200,
					maxRequestSize = 1024 * 1024 * 400)
public class BoardUpdateController extends HttpServlet {
	private static final String FILES_DIR = "upload";
	
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int boardID = Integer.parseInt(req.getParameter("boardID"));
		BoardDAO dao = new BoardDAO();
		Board board = dao.selectOne(boardID);
		req.setAttribute("board", board);
		req.getRequestDispatcher("boardUpdate.jsp").forward(req, res);
	}
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//한글깨짐 방지
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		
		
		
		//매개변수의 값 저장
		int boardID = Integer.parseInt(req.getParameter("boardID"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String userID = req.getParameter("userID");
		String pass = req.getParameter("pass");
		
		
		//파일 수정해서 업로드하기
		Part part = req.getPart("ofile");
		String fileName = null;
		String newFileName = null;
		
		if(part != null && part.getSize() > 0) {
			fileName = extractFileName(part);
			
			if(fileName != null && !fileName.isEmpty()) {
				String now = new SimpleDateFormat("yyyyMMdd").format(new Date());
				String ext = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : "";
				String str = fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf(".")) : fileName;
				
				newFileName = now + "-" + str + ext;
				
				//파일 저장할 경로 설정
				String saveDir = getServletContext().getRealPath("") + File.separator + FILES_DIR;
				File fileSaveDir = new File(saveDir);
				if (!fileSaveDir.exists()) {
					fileSaveDir.mkdir();
				}
				//새로운 파일명으로 저장
				part.write(saveDir + File.separator + newFileName);
			}
		}
		
		// 웹에서 접근할 수 있는 경로 (ex: /upload/파일명) 설정
	    String fileUrl = "/upload/" + newFileName;
		
		 Board board = new Board(boardID, title, content, userID, fileName, newFileName, pass);
	      
	      BoardDAO dao = new BoardDAO();
	      int result = dao.boardUpdate(board);
	      //글쓰기 성공 여부
	      if(result == 1) {
	         ShowMessage.alertLocation(res, "글이 수정되었습니다", "boardList.do");
	      }else {
	         ShowMessage.alertBack(res, "글수정이 실패하였습니다.");
	      }
	   }
	   //파일 이름만 추출하기
	   //Part : multipart/form-data는 application/x-www.form-urlencoded와 비교해서 매우 복잡하고 각 part로 나뉘어져 있다.
	   //이런 part를 getHeader()나 getContentType()등의 메서드를 사용해서 얻어올 수 있다.
	   private String extractFileName(Part part) {
	      //contentDisp 변수에 form-data:name="fileName";filename="추가한 파일 이름"
	      //예) form-data:name="fileName";filename="slide1.jpg"
	      String contentDisp = part.getHeader("content-disposition");
	      //contentDisp변수에 저장된 문자열을 ;을 기준으로 분리하여 items배열에 저장
	      //split(";") : 전체 문자열을 ;을 기준으로 분리하는 메서드  
	      String[] items = contentDisp.split(";");
	      //items배열의 원소 개수만큼 반복, s는 items배열의 원소를 가리킴
	      for(String s : items) {
	         //만약 items배열의 원소값에서 공백을 제거한 파일명이 있다면
	         //trim() : 공백을 제거하는 메서드
	         if(s.trim().startsWith("filename")) {
	            //파일명에서 =기호 다음의 문자열만 추출하여 반환
	            //substring(5, 10) : 전체 문자열에서 5번째 문자부터 10-1번째 문자까지만 추출하는 메서드
	            //indexOf("문자") : 문자가 위치한 인덱스번호를 구하는 메서드
	            return s.substring(s.indexOf("=") + 2 , s.length() - 1);
	         }
	      }
	      return "";
	     
	
	}
}