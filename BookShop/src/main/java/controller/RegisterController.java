package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import message.ShowMessage;
import model.Member;

@WebServlet("/BookShop/register.do")
public class RegisterController extends HttpServlet {
	//기본 생성자(부모클래스의 생성자 호출, httpservlet에서 기본으로 제공하는 것을 오버라이딩해서 쓸수있는것)
	public RegisterController() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("register.jsp").forward(req, res);
	}
	//view에 form만들건데 form에 있는 데이터를 여기서 호출해서 오류있으면 회원가입 실패했다, 되면 회원가입되었다 하고 설정하는 것
	//사실상 얘네는 실행을 요청하는것! 이명령어를 실행하는건 MemberDAO임
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userID = req.getParameter("userID");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		//이렇게 인스턴스 만드는 것만으로도 DB접속이 됨 -> DAO에 가보면 기본생성자가 DBCon과 연결되게 되어있음
		MemberDAO dao = new MemberDAO();
		//DTO를 생략해서 그렇지 Member가 DTO임
		//DTO에 만든 변수 가져다 인스턴스 만들겠다~
		Member member = new Member (userID, password, email);
		int result = dao.register(member);
		if(result != 0) {
			//회원가입되었다는 메세지를 보여주고 login.jsp로 이동하는것 
			ShowMessage.alertLocation(res, "회원가입 되었습니다.", "login.jsp");
		}else {
			ShowMessage.alertBack(res, "회원가입에 실패하였습니다");
		}
	}

}
