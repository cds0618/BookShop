package fileProcess;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

public class FileRun {
	//파일 업로드 처리
	public static MultipartRequest uploadFile(HttpServletRequest req, String saveDir, int maxPostSize){
		try {
			//파일 업로드
			return new MultipartRequest(req, saveDir, maxPostSize, "UTF-8");
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
