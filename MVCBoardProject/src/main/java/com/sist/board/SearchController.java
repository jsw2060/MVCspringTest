package com.sist.board;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.search.dao.NewsDAO;
import com.sist.search.dao.NewsVO;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
@Controller("sc")
public class SearchController {
	@RequestMapping("search.do")
	public String newsSearch(HttpServletRequest req) throws Exception {
		req.setCharacterEncoding("EUC-KR");
		String title = req.getParameter("title");
		if(title == null)
			title = "¼±°Å";
		String page=req.getParameter("page");
		if(page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		NewsDAO dao = new NewsDAO();
		dao.newsAllData(title);
		List<NewsVO> list = dao.newsAllData(curpage);
		int totalpage=dao.newsTotalpage();
		req.setAttribute("list", list);
		req.setAttribute("curpage", curpage);
		req.setAttribute("totalpage", totalpage);
		req.setAttribute("title", title);
		return "search/search.jsp";
	}
}