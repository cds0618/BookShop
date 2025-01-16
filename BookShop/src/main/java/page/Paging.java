package page;

public class Paging {
	public static String pagingStr(int totalCount, int pageSize,
			int blockPage, int pageNum, String reqUrl) {
		//결과값 저장할 변수 선언
		String pagingStr = "";
		//전체 페이지 수 계산 (총게시글수 / 한블록당 들어가는 게시글 수)
		int totalPages = (int)(Math.ceil((double) totalCount / pageSize));
		//이전 페이지 바로가기 출력
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		if(pageTemp != 1) {
			pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[첫 페이지]</a>";
			pagingStr += "&nbsp;&nbsp;";
			pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp -1)
					  + "'>[이전]</a>";
		}
		//각 페이지 번호 출력
		int blockCount = 1;
		while(blockCount <= blockPage && pageTemp <= totalPages) {
			//만약 페이지번호가 현재 페이지번호와 같다면
			if(pageTemp == pageNum) {
				//현재 페이지는 링크 걸지 않음
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";				
			//페이지번호가 현재 페이지번호와 같지 않다면
			}else {
				//링크 걸기
				pagingStr += "&nbsp;<a href='" + reqUrl
						+ "?pageNum=" + pageTemp + "'>"
						+ pageTemp + "</a>&nbsp;";				
			}
			//페이지번호 1씩 증가
			pageTemp++;
			//블록 개수 1씩 증가
			blockCount++;
		}
		//다음 페이지 바로가기 출력
		if(pageTemp <= totalPages) {
			pagingStr += "<a href='" + reqUrl + "?pageNum=" 
					+ pageTemp + "'>[다음]</a>";
			pagingStr += "&nbsp;&nbsp;";
			pagingStr += "<a href='" + reqUrl + "?pageNum=" 
					+ totalPages + "'>[마지막 페이지]</a>";					
		}
		return pagingStr;		
	}
}
