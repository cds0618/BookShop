package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BookShop/logout.do")
public class LogoutController extends HttpServlet  {
	public LogoutController () {
		super();
	}
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//세션을 무효화하여 로그아웃 처리
		HttpSession session = req.getSession();
		//세션의 모든값 삭제
		session.invalidate();
		//로그아웃 후 메인페이지로 이동
		res.sendRedirect("index.jsp");
	}
	
}
