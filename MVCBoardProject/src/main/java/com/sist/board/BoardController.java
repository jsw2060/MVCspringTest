package com.sist.board;

import javax.servlet.http.HttpServletRequest;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.board.dao.*;
/*
 * 	JSP => ��û => content.do
 * 	Model	@RequestMapping()
 * 	DB		
 * 	JSP => �ش� JSP�� �̵�
 */
@Controller("bc")
public class BoardController {
	@RequestMapping("list.do")		// list.do�� ������, �� �޼ҵ带 ȣ���϶�
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
		return "freeboard/list.jsp";	// jsp���ϸ�
	}
	@RequestMapping("content.do")		// list.do�� ������, �� �޼ҵ带 ȣ���϶�
	public String boardContentData(HttpServletRequest req) {
		String no=req.getParameter("no");
		String page=req.getParameter("page");
		// DB ����
		BoardVO vo = BoardDAO.boardContentData(Integer.parseInt(no));
		// ������ ����� => jsp
		req.setAttribute("no", no);
		req.setAttribute("page", page);
		req.setAttribute("vo", vo);
		
		return "freeboard/content.jsp";	// jsp���ϸ�
	}
	@RequestMapping("insert.do")
	public String boardInsert(HttpServletRequest req) {
		return "freeboard/insert.jsp";
	}
	@RequestMapping("insert_ok.do")
	public String boardInserOk(HttpServletRequest req) throws Exception {
		req.setCharacterEncoding("EUC-KR");
		String name=req.getParameter("name");
		String subject=req.getParameter("subject");
		String content=req.getParameter("content");
		String pwd=req.getParameter("pwd");
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		// DB����
		BoardDAO.boardInsert(vo);
		return "index.jsp";
	}
}
