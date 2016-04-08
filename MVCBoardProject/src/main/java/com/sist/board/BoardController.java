package com.sist.board;

import javax.servlet.http.HttpServletRequest;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.board.dao.*;
/*
 * 	JSP => 요청 => content.do
 * 	Model	@RequestMapping()
 * 	DB		
 * 	JSP => 해당 JSP로 이동
 */
@Controller("bc")
public class BoardController {
	@RequestMapping("list.do")		// list.do가 들어오면, 이 메소드를 호출하라
	public String boardListData(HttpServletRequest req) {
		String page=req.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<BoardVO> list = BoardDAO.boardAllData(map);
		int totalpage = BoardDAO.boardTotalPage();
		req.setAttribute("list", list);
		req.setAttribute("curpage", curpage);
		req.setAttribute("totalpage", totalpage);
		return "freeboard/list.jsp";	// jsp파일명
	}
	@RequestMapping("content.do")		// list.do가 들어오면, 이 메소드를 호출하라
	public String boardContentData(HttpServletRequest req) {
		String no=req.getParameter("no");
		String page=req.getParameter("page");
		// DB 연동
		BoardVO vo = BoardDAO.boardContentData(Integer.parseInt(no));
		// 데이터 결과값 => jsp
		req.setAttribute("no", no);
		req.setAttribute("page", page);
		req.setAttribute("vo", vo);
		
		return "freeboard/content.jsp";	// jsp파일명
	}
	@RequestMapping("insert.do")
	public String boardInsert(HttpServletRequest req) {
		return "freeboard/insert.jsp";
	}
}
