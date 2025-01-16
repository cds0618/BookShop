package message;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowMessage {
	//메시지 알람칭 표시한 다음 해당 url로 이동하는 메서드
	//그냥 화면에 표시하는거라 req는 필요없음, msg는 메시지 띄워주고, 어디로 가세요하고 내가 url설정하는것
	public static void alertLocation(HttpServletResponse res, String msg, String url) throws ServletException, IOException {
		try {
			//response 콘텐츠에 설정해야함
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = res.getWriter();
			//문자열 생성하는 것 쓴것
			String script = "<script> alert('" + msg + "');" + "location.href='" + url +"';" + "</script>";
			writer.print(script);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//메시지 알림창 표시한 다음 이전 페이지로 이동하는 메서드
	//알림뜨고 다시 이전페이지로 돌아가는것
	//어디로 갈진 이미정해져있어서 매개변수에 url없음
	public static void alertBack(HttpServletResponse res, String msg) {
		try {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = res.getWriter();
			String script = "<script> alert('" + msg + "');" + "history.back();" + "</script>";
			writer.print(script);		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
