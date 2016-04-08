package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WebApplicationContext wc;
	private List<String> list = new ArrayList<String>();
	
	public void init(ServletConfig config) throws ServletException {
		String path=config.getInitParameter("contextConfigLocation");
		// WebApplicationContext = class관리자 = 컨테이너 <==> HandlerMapping (Class)
		wc=new WebApplicationContext(path);
		list = wc.getFileName();		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String cmd = request.getRequestURI();
			System.out.println(cmd); // freeboard/list.do
			cmd = cmd.substring(request.getContextPath().length()+1);
			System.out.println(cmd); // list.do
			for(String strCls:list) {
				Class clsName = Class.forName(strCls);
				if(clsName.isAnnotationPresent(Controller.class) == false)
					continue;
				Object obj = clsName.newInstance();
				System.out.println(clsName);	// class com.sist.board.BoardController
				System.out.println(obj);		// com.sist.board.BoardController@6c2a8ee0
				// 클래스를 많이 쓰는 것보다 메소드를 여럿 쓰는 것이 낫기 때문에
				Method[] methods = clsName.getDeclaredMethods();
				for(Method m:methods) {
					RequestMapping rm = m.getAnnotation(RequestMapping.class);
					if(rm.value().equals(cmd)) {
						String jsp = (String)m.invoke(obj, request);
						// 객체에 할당된 메소드를 가져와라(request를 아까 할당해줬으니까 그걸 가져와서 메소드를 불러온다)
						// 이때, 매개변수가 가변형임. 여러개 넣을 수 있다.
						RequestDispatcher rd = request.getRequestDispatcher(jsp);
						rd.forward(request, response);
						return;	// 메소드 하나를 찾았으니, 그만 찾고, 시스템 종료
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("ServiceMethodError "+ex.getMessage());
		}
	}

}
