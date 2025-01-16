package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import message.ShowMessage;

@WebServlet("/BookShop/login.do")
public class LoginController extends HttpServlet{
	public LoginController() {
		super();
	}
	@Override
	//get은 얻어오는것, post는 쓰는것 (블로그에 포스트한다 글쓴다처럼)
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//session 내장객체에서 session 정보를 가져와서 session 변수에 저장
		HttpSession session = req.getSession();
		
		//로그인 페이지로 오기 전에 사용자가 요청한 url을 session에 저장
		String prevPage = req.getHeader("Referer");
		if(prevPage != null && !prevPage.contains("/login.do")) {
			session.setAttribute("prevPage", prevPage);
		}
		
		//내장객체 request, response의 모든 속성을 login.jsp에 전달하면서 페이지 이동
//		req.getRequestDispatcher("login.jsp").forward(req, res);
		res.sendRedirect("login.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		//로그인화면에서 아이디 비번쓴거 각각가지고와서 넣어주는것
		String userID = req.getParameter("userID");
		String password = req.getParameter("password");
		//memberDAO클래스를 사용하여 dao인스턴스 생성
		MemberDAO dao = new MemberDAO();
		//dao인스턴스의 login메서드 호출하면서 username과 password값을 매개변수로 전달
		//login메서드에서 처리한 결과값을 result변수에 저장
		int result = dao.login(userID, password);
		//만약 result값이 1이면 로그인 성공
		
		if (result == 1) {
		session.setAttribute("userID", userID);
		System.out.println("로그인 성공, userID: " + userID); 
		
		
		//세션 내장객체에서 prevPage속성값을 가져와서 prevPage변수에 저장
		String prevPage = (String) session.getAttribute("prevPage");
		//만약 prevPage가 null이 아니라면 prevPage속성값을 제거하고, prevPage로 이동
		if(prevPage != null) {
			session.removeAttribute("prevPage");
			res.sendRedirect(prevPage);
			//prevPage값이 null이면 index페이지로 이동
		}else {
			res.sendRedirect("index.jsp");
		}
		//index.jsp페이지로 이동
		//얘도 그냥 이동하세요 = a태그(html)와 같은역할인데 얘는 자바에서 이동시키는애! 

		
		//로그인 못했으면 메시지 표시하고 로그인화면으로 돌아감
		}else {
		ShowMessage.alertBack(res, "아이디 또는 비밀번호가 맞지 않습니다.");
		
		}
	}
}
