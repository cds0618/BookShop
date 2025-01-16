package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import message.ShowMessage;
import model.Board;

@WebServlet("/BookShop/checkPass.do")

public class CheckPassController extends HttpServlet {
	@Override 
	//get은 데이터 얻어오세요
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setAttribute("mode", req.getParameter("mode"));
		req.getRequestDispatcher("checkPass.jsp").forward(req, res);
	}
	@Override 
	//post는 다른데 쓸때
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		int boardID = Integer.parseInt(req.getParameter("boardID"));
		String mode = req.getParameter("mode");
		String pass = req.getParameter("password");
		BoardDAO dao = new BoardDAO();
		//비밀번호 확인
		boolean result = dao.checkPass(boardID, pass);
		//만약 비밀번호가 일치하면 mode가 edit인지 delete인지 검사
		if(result) {
			if(mode.equals("edit")) {
				//session 내장객체에 비밀번호를 pass속성으로 세팅
				HttpSession session = req.getSession();
				session.setAttribute("pass", pass);
				res.sendRedirect("boardUpdate.do?boardID=" + boardID);
			}else if (mode.equals("delete")) {
				dao = new BoardDAO();
				Board board = dao.selectOne(boardID);
				int deleted = dao.boardDelete(boardID);
				dao.close();
				
				if(deleted == 1) {
					String saveFileName = board.getOfile();
					dao.deleteFile(req, "/upload", saveFileName);
					ShowMessage.alertLocation(res, "게시글이 삭제되었습니다.", "boardList.do");
				}else {
					ShowMessage.alertBack(res, "게시글 삭제에 실패하였습니다.");
				}
			}
		}else {
			ShowMessage.alertBack(res, "비밀번호가 일치하지 않습니다.");
		}
		dao.close();
	}
}
