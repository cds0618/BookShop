package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import model.Board;

@WebServlet("/BookShop/boardDetail.do")
public class BoardDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		int boardID = Integer.parseInt(req.getParameter("boardID"));
		Board board = dao.selectOne(boardID);
		
		//조회수 증가
		dao.addVisitCount(boardID);
		
		dao.close();
		req.setAttribute("board", board);
		req.getRequestDispatcher("boardDetail.jsp").forward(req, res);
	}

}
