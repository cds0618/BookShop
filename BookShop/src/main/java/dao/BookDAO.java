package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.sql.Date;

import model.Board;
import model.Book;
import model.DBCon;
public class BookDAO extends DBCon {
	public BookDAO() {
		//부모클래스의 생성자 호출(DB연동)
		super();
	}
	//board 테이블의 총 게시글 개수 구하기
	   //Map<Key 자료형, Value 자료형>, map:인스턴스명
	   public int selectAll(Map<String, Object> map) {
	      int total = 0;
	      String sql = "select count(*) from book";
	      //검색어가 있으면 검색어에 해당하는 결과값만 구하기
	      //searchField 제목이고, searchWord가 질문이면 조건은 where title like '%질문%'임
	      if (map.get("searchWord") != null && map.get("searchField").equals("bookName")) {
	         sql += " where " + map.get("searchField")
	             + " like '%" + map.get("searchWord") + "%'";
	      }
	      //searchField가 내용이고, searchWord가 질문이면 조건은 where content like '%질문%'임
	      if (map.get("searchWord") != null && map.get("searchField").equals("author")) {
	          sql += " where author like '%" + map.get("searchWord") + "%'";
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
	//도서등록
	public int addBook(Book book) {
	    int result = 0;
	    System.out.println("Attempting to add a book...");
	    String sql = "insert into book (bookName, price, author, description, publisher, category, unitsInStock, totalPages, releaseDate, imageUrl) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    try {
	        if (con == null) {
	            System.out.println("Database connection is null!");
	        } else {
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, book.getBookName());
	            pstmt.setDouble(2, book.getPrice());
	            pstmt.setString(3, book.getAuthor());
	            pstmt.setString(4, book.getDescription());
	            pstmt.setString(5, book.getPublisher());
	            pstmt.setString(6, book.getCategory());
	            pstmt.setLong(7, book.getUnitsInStock());
	            pstmt.setLong(8, book.getTotalPages());
	            pstmt.setDate(9, book.getReleaseDate());
	            pstmt.setString(10, book.getImageUrl());
	            result = pstmt.executeUpdate();
	            System.out.println("Book added: " + (result > 0));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	//게시글 목록 조회
	   public List<Book> bookList(Map<String, Object> map, String sortObj, String sortOrder){
	      //게시판 목록을 저장할 배열 객체 생성
		  List<Book> bookList = new Vector<Book>();
	      //페이징 기능이 있는 질의어 작성       
	      String sql = "select * from "
	                  + "(select Tb.*, @ROWNUM:=@ROWNUM+1 as rNum  from (select * from book ";
	      //검색 조건 추가
	       //searchWord입력값이 비어있지 않으면 질의어에 조건 추가
	       //searchField : 제목 또는 내용 
	       //searchWord : 검색어
	      if (map.get("searchWord") != null && map.get("searchField").equals("bookName")) {
		         sql += " where " + map.get("searchField")
		             + " like '%" + map.get("searchWord") + "%'";
		      }
		  //searchField가 내용이고, searchWord가 질문이면 조건은 where content like '%질문%'임
		  if (map.get("searchWord") != null && map.get("searchField").equals("author")) {
		         sql += " where author like '%" + map.get("searchWord") + "%'";
		     }
	       
		  	sql += " order by " + sortObj + " " + sortOrder + ") Tb, "
		           + "(select @ROWNUM:=0) r) T "
		           + " where rNum between ? and ? ";
	       
		    try {
		    	pstmt = con.prepareStatement(sql);
		          pstmt.setString(1, map.get("start").toString());
		          pstmt.setString(2, map.get("end").toString());
		          rs = pstmt.executeQuery();

		   	        while (rs.next()) {
		            Book book = new Book();
		            book.setBookID(rs.getInt("bookID"));
		            book.setBookName(rs.getString("bookName"));
		            book.setPrice(rs.getDouble("price"));
		            book.setAuthor(rs.getString("author"));
		            book.setDescription(rs.getString("description"));
		            book.setPublisher(rs.getString("publisher"));
		            book.setCategory(rs.getString("category"));
		            book.setUnitsInStock(rs.getLong("unitsInStock"));
		            book.setTotalPages(rs.getLong("totalPages"));
		            book.setReleaseDate(rs.getDate("releaseDate"));
		            book.setImageUrl(rs.getString("imageUrl"));
		            bookList.add(book);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return bookList;
	   }
	
	//도서 수정
	public int updateBook(Book book) {
		int result = 0;
		String sql ="update book set bookName=?, price=?, author=?, description=?, publisher=?, category=?, unitsInStock=?, totalPages=?, releaseDate=?, imageUrl=? where bookID=? ";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book.getBookName());
			pstmt.setDouble(2, book.getPrice());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getDescription());
			pstmt.setString(5, book.getPublisher());
			pstmt.setString(6, book.getCategory());
			pstmt.setLong(7, book.getUnitsInStock());
			pstmt.setLong(8, book.getTotalPages());
			pstmt.setDate(9, book.getReleaseDate());
			pstmt.setString(10, book.getImageUrl());
			pstmt.setInt(11, book.getBookID());
			result=pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
	//도서 삭제
	public int deletePosts(int bookID) {
		int result = 0;
		String sql = "delete from book where bookID = ?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bookID);
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	//도서 목록의 총 개수 구하기
	public int selectCount() {
		int total = 0;
		String sql = "select count(*) from book";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				total=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	//도서 상세 보기
	public Book selectOne(int bookID) {
		//Book클래스를 사용하여 dto인스턴스 생성
		Book dto = new Book();
		String sql = "select * from book where bookID =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setBookID(rs.getInt(1));
				dto.setBookName(rs.getString(2));
				dto.setPrice(rs.getDouble(3));
				dto.setAuthor(rs.getString(4));
				dto.setDescription(rs.getString(5));
				dto.setPublisher(rs.getString(6));
				dto.setCategory(rs.getString(7));
				dto.setUnitsInStock(rs.getLong(8));
				dto.setTotalPages(rs.getLong(9));
				dto.setReleaseDate(rs.getDate(10));
				dto.setImageUrl(rs.getString(11));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	//비밀번호 검증하기
	public boolean confirmPass(String pass) {
		boolean result = true;
		String sql = "select count(*) from member where password = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pass);
			//resultSet(rs)객체에 질의어(sql) 실행한 결과값을 저장
			rs = pstmt.executeQuery();
			//만약 rs객체에서 가져온 정수값이 0이면(실행결과가 없으면)
			//result변수에 false를 저장
			rs.next();
			if(rs.getInt(1) == 0) {
				result = false;
			}			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
				
	}
	
	//카테고리 목록 가져오기 메서드
	public List<String> cateList(){
		List<String> cateList = new ArrayList<>();
		String sql = "select cateName from category";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cateList.add(rs.getString("cateName"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cateList;
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
