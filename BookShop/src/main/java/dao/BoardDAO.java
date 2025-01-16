package dao;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import model.Board;
import model.DBCon;

public class BoardDAO extends DBCon {
   //부모클래스(DBCon)에서 상속받은 생성자 호출
   public BoardDAO() {
      super();
   }
   
   //board 테이블의 총 게시글 개수 구하기
   //Map<Key 자료형, Value 자료형>, map:인스턴스명
   public int selectAll(Map<String, Object> map) {
      int total = 0;
      String sql = "select count(*) from board";
      //검색어가 있으면 검색어에 해당하는 결과값만 구하기
      //searchField 제목이고, searchWord가 질문이면 조건은 where title like '%질문%'임
      if (map.get("searchWord") != null && map.get("searchField").equals("title")) {
         sql += " where " + map.get("searchField")
             + " like '%" + map.get("searchWord") + "%'";
      }
      //searchField가 내용이고, searchWord가 질문이면 조건은 where content like '%질문%'임
      if (map.get("searchWord") != null && map.get("searchField").equals("content")) {
          sql += " where content like '%" + map.get("searchWord") + "%'";
       }
      
      //searchField가 제목+내용이고 searchWord가 질문이면 조건은 where title like '%질문%' or content like '%질문%'임
      if (map.get("searchWord") != null && map.get("searchField").equals("title+content")) {
          sql += " where title like '%" + map.get("searchWord") + "%'" + " or content like '%" + map.get("searchWord") + "%'";
       }
 
      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         //ResultSet의 첫번째 결과값으로 커서 이동
         rs.next();
         total = rs.getInt(1);
         
      }catch(Exception e) {
         e.printStackTrace();
      }
      return total;
   }
   //게시글 목록 조회
   public List<Board> selectList(Map<String, Object> map, String sortObj, String sortOrder){
      //게시판 목록을 저장할 배열 객체 생성
      List<Board> boardList = new Vector<Board>();
      //페이징 기능이 있는 질의어 작성       
      String sql = "select * from "
                  + "(select Tb.*, @ROWNUM:=@ROWNUM+1 as rNum  from (select * from board ";
      //검색 조건 추가
       //searchWord입력값이 비어있지 않으면 질의어에 조건 추가
       //searchField : 제목 또는 내용 
       //searchWord : 검색어
      if (map.get("searchWord") != null && map.get("searchField").equals("title")) {
          sql += " where " + map.get("searchField")
              + " like '%" + map.get("searchWord") + "%'";
       }
       //searchField가 내용이고, searchWord가 질문이면 조건은 where content like '%질문%'임
       if (map.get("searchWord") != null && map.get("searchField").equals("content")) {
           sql += " where content like '%" + map.get("searchWord") + "%'";
        }
       
       //searchField가 제목+내용이고 searchWord가 질문이면 조건은 where title like '%질문%' or content like '%질문%'임
       if (map.get("searchWord") != null && map.get("searchField").equals("title+content")) {
           sql += " where title like '%" + map.get("searchWord") + "%'" + " or content like '%" + map.get("searchWord") + "%'";
        }
       
       sql += " order by " + sortObj + " " + sortOrder + ") Tb, "
           + "(select @ROWNUM:=0) r) T "
           + " where rNum between ? and ? ";
       
       try {
          pstmt = con.prepareStatement(sql);
          pstmt.setString(1, map.get("start").toString());
          pstmt.setString(2, map.get("end").toString());
          rs = pstmt.executeQuery();
          while(rs.next()) {
             Board board = new Board();
             board.setBoardID(rs.getInt("boardID"));
             board.setTitle(rs.getString("title"));
             board.setContent(rs.getString("content"));
             board.setUserID(rs.getString("userID"));
             board.setPostDate(rs.getDate("postDate"));
             board.setOfile(rs.getString("ofile"));
             board.setSfile(rs.getString("sfile"));
             board.setDowncount(rs.getInt("downcount"));
             board.setPass(rs.getString("pass"));
             board.setVisitcount(rs.getInt("visitcount"));
             boardList.add(board);
          }
       }catch(Exception e) {
          e.printStackTrace();
       }
      return boardList;
   }
   //게시글 쓰기
   public int boardWrite(Board board) {
      int result = 0;
      String sql = "insert into board (title, content, userID, ofile, sfile, pass) values (?, ?, ?, ?, ?, ?)";
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, board.getTitle());
         pstmt.setString(2, board.getContent());
         pstmt.setString(3, board.getUserID());
         pstmt.setString(4, board.getOfile());
         pstmt.setString(5, board.getSfile());
         pstmt.setString(6, board.getPass());
         result = pstmt.executeUpdate();
      }catch(Exception e) {
         e.printStackTrace();
      }
      return result;
   }
   
   //한 개의 게시글 조회
   public Board selectOne(int boardID) {
	   Board board = new Board();
	   String sql = "select * from board where boardID=?";
	   try {
		   pstmt = con.prepareStatement(sql);
		   pstmt.setInt(1, boardID);
		   rs= pstmt.executeQuery();
		   if(rs.next()) {
	             board.setBoardID(rs.getInt("boardID"));
	             board.setTitle(rs.getString("title"));
	             board.setContent(rs.getString("content"));
	             board.setUserID(rs.getString("userID"));
	             board.setPostDate(rs.getDate("postDate"));
	             board.setOfile(rs.getString("ofile"));
	             board.setSfile(rs.getString("sfile"));
	             board.setDowncount(rs.getInt("downcount"));
	             board.setVisitcount(rs.getInt("visitcount"));
	             
		   }
		   
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   return board;
   }
   
   //게시글 수정
   public int boardUpdate (Board board) {
	   int result = 0;
	   String sql = "update board set title = ?, content = ?, userId= ?, ofile = ?, sfile = ?, pass = ? where boardID =?";
	   try {
		   pstmt = con.prepareStatement(sql);
		   pstmt.setString(1, board.getTitle());
		   pstmt.setString(2, board.getContent());
		   pstmt.setString(3, board.getUserID());
		   pstmt.setString(4, board.getOfile());
		   pstmt.setString(5, board.getSfile());
		   pstmt.setString(6, board.getPass());
		   pstmt.setInt(7, board.getBoardID());
		   result = pstmt.executeUpdate();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   return result;
			   
			  
   }
   
   //게시글 삭제
   public int boardDelete(int boardID) {
	   int result = 0;
	   String sql = "delete from board where boardID = ?";
	   try {
		   pstmt = con.prepareStatement(sql);
		   pstmt.setInt(1, boardID);
		   result = pstmt.executeUpdate();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   return result;
   }
   
   
   //조회 수 증가
   	public void addVisitCount(int boardID) {
   		String sql = "update board set visitcount = visitcount + 1 where boardID = ?";
   		try {
   			pstmt = con.prepareStatement(sql);
   			pstmt.setInt(1, boardID);
   			pstmt.executeUpdate();
   		}catch(Exception e) {
   			e.printStackTrace();
   		}
   	}
   //파일 다운로드 수 증가
   public void downloadCount(int boardID) {
	   String sql = "update board set downcount = downcount + 1 where boardID = ?";
	   try {
		   pstmt = con.prepareStatement(sql);
		   pstmt.setInt(1, boardID);
		   pstmt.executeUpdate();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
   }
   
   //비밀번호 확인 메서드
   public boolean checkPass(int boardID, String pass) {
	   boolean result = true;
	   String sql = "select count(*) from board where boardID =? and pass =?"; 
	   try {
		   pstmt = con.prepareStatement(sql);
		   pstmt.setInt(1, boardID);
		   pstmt.setString(2, pass);
		   rs = pstmt.executeQuery();
		   rs.next();
		   if(rs.getInt(1) == 0) {
			   result=false;
		   }
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   return result;
   }
   //폴더에서 첨부파일 삭제하는 메서드
   public void deleteFile(HttpServletRequest req, String dir, String fileName) {
	   //파일이 업로드 되어있는 폴더의 절대경로를 sDir변수에 저장
	   String sDir = req.getServletContext().getRealPath(dir);
	   //매개변수로 전달받은 파일명을 가진 파일을 찾아서 file변수에 저장
	   File file = new File(sDir + File.separator + fileName);
	   //만약 파일이 존재하면 그 파일을 sDir(/upload)폴더에서 삭제
	   if(file.exists()) {
		   file.delete();
	   }
   }
  
}
