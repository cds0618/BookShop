package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import model.Member;

@WebServlet("/BookShop/idCheck.do")
public class IdCheckController extends HttpServlet {
	public IdCheckController() {
		super();
	}
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//변수명을 만들어야 받아와서 아래 result에 들어가지 그럼 어디서 받아올건데? form의 name="username"에서 매개변수로 받은값
		String userID = req.getParameter("userID");
		MemberDAO dao = new MemberDAO();
		//변수명 넣을때는 큰따옴표 없어야함 "username"
		//이걸 던지면 누가 받아? DAO에서 받아 public int idCheck(String username) { 여기!! 매개변수! 
		int result = dao.idCheck(userID);
		//request 내장 객체에 username속성으로 세팅
		//이러면 브라우저에서 username, result의값을 읽어올수 있어
		req.setAttribute("userID", userID);
		req.setAttribute("result", result);
		//req에 내가 만든 속성, 값을 가지고 idcheck.jsp로 이동하는 구나!
		//request내장객체의 모든 속성을 갖고 idCheck.jsp 페이지로 이동
		req.getRequestDispatcher("idCheck.jsp").forward(req, res);
	}
}
