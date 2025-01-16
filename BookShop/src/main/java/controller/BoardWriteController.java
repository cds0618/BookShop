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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;

import com.oreilly.servlet.MultipartRequest;

import dao.BoardDAO;
import message.ShowMessage;
import model.Board;

@WebServlet("/BookShop/boardWrite.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
            maxFileSize = 1024 * 1024 * 200,
            maxRequestSize = 1024 * 1024 * 400)
public class BoardWriteController extends HttpServlet {
   //파일 업로드, 다운로드에 사용되는 폴더 지정
   private static final String FILES_DIR = "upload";
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse res)
       throws ServletException, IOException {      
      //한글깨짐방지
      req.setCharacterEncoding("UTF-8");
      res.setContentType("text/html;charset=UTF-8");
      
      HttpSession session = req.getSession();
      String userID = (String) session.getAttribute("userID");
      
      // 로그인된 사용자가 아니라면, 로그인 페이지로 리디렉션
      if (userID == null) {
         res.sendRedirect("index.jsp");
         return;
      }
      
      //boardWrite.jsp페이지의 입력폼에서 데이터를 읽어와서 각 변수에 저장
      String title = req.getParameter("title");
      String content = req.getParameter("content");
      String pass = req.getParameter("pass");
      
      
      
      //파일 업로드 처리
      //boardWrite.jsp페이지에서 원본파일을 가져와서 part에 저장
      Part part = req.getPart("ofile");   
      //용량 초과 검사
      if (part.getSize() > (1024 * 1024 * 2)) { 
            ShowMessage.alertBack(res, "파일 크기가 허용된 용량을 초과하였습니다.");
            return;
        }
            
      //part객체에 들어있는 파일의 파일명만 fileName에 저장
      String fileName = extractFileName(part);
      //새로운 파일명을 저장하는 변수 선언
      String newFileName = null;
      //만약 파일명이 비어 있지 않다면(첨부파일이 있다면)
      if(fileName != null && !fileName.isEmpty()) {
         //현재 날짜를 yyyyMMdd형식의 문자열로 변환하여 now에 저장
         String now = new SimpleDateFormat("yyyyMMdd").format(new Date());
         //원본파일명에서 확장자만 떼어내어 ext변수에 저장
         String ext = fileName.substring(fileName.lastIndexOf("."));
         //원본파일명에서 확장자를 제외한 파일명을 str변수에 저장
         String str = fileName.substring(0, fileName.lastIndexOf("."));         
         //새로운 파일명을 만들어서 newFileName에 저장
         //star.png(ofile) -> 20240926-star.png(sfile)
         newFileName = now + "-" + str + ext;
         //경로가 포함된 원본파일명
         File oldFile = new File(FILES_DIR + File.separator + fileName);
         //경로가 포함된 새파일명
         File newFile = new File(FILES_DIR + File.separator + newFileName);
         //파일명 변경
         oldFile.renameTo(newFile);
         //절대경로 구해서 saveDir변수에 저장(/JSPBookShop/upload)
         String saveDir = getServletContext().getRealPath("")
                  + File.separator + FILES_DIR;
         //saveDir경로의 폴더를 fileSaveDir변수에 저장
         File fileSaveDir = new File(saveDir);
         //만약 폴더가 존재하지 않으면 새로운 폴더 생성
         if(!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
         }
         try {
            part.write(saveDir + File.separator + fileName);
         }catch(SizeLimitExceededException e) {
            ShowMessage.alertBack(res, "업로드하려는 파일의 크기가 허용된 용량을 초과하였습니다.");
         }catch(Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "파일 처리 중 오류가 발생했습니다.");
         }
      }
      //파일이 첨부되지 않았으면 fileName과 newFileName은 값이 null임
      if(part == null || part.getSize() == 0) {
         fileName = null;
         newFileName = null;
      }
      Board board = new Board(title, content, userID, fileName, newFileName, pass);
      
      BoardDAO dao = new BoardDAO();
      int result = dao.boardWrite(board);
      //글쓰기 성공 여부
      if(result == 1) {
         ShowMessage.alertLocation(res, "글이 작성되었습니다", "boardList.do");
      }else {
         ShowMessage.alertBack(res, "글쓰기 실패하셨습니다!");
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
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       HttpSession session = req.getSession();
       String userID = (String) session.getAttribute("userID");

       if (userID == null) {
           // 로그인되지 않으면 로그인 페이지로 리디렉션
    	   session.setAttribute("prevPage", req.getRequestURL().toString());
           res.sendRedirect("login.jsp");
       } else {
           // 로그인된 경우 글쓰기 페이지로 이동
           req.getRequestDispatcher("boardWrite.jsp").forward(req, res);
       }
   }
}
