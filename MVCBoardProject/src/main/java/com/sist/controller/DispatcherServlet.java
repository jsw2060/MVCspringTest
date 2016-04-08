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
		// WebApplicationContext = class������ = �����̳� <==> HandlerMapping (Class)
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
				// Ŭ������ ���� ���� �ͺ��� �޼ҵ带 ���� ���� ���� ���� ������
				Method[] methods = clsName.getDeclaredMethods();
				for(Method m:methods) {
					RequestMapping rm = m.getAnnotation(RequestMapping.class);
					if(rm.value().equals(cmd)) {
						String jsp = (String)m.invoke(obj, request);
						// ��ü�� �Ҵ�� �޼ҵ带 �����Ͷ�(request�� �Ʊ� �Ҵ��������ϱ� �װ� �����ͼ� �޼ҵ带 �ҷ��´�)
						// �̶�, �Ű������� ��������. ������ ���� �� �ִ�.
						RequestDispatcher rd = request.getRequestDispatcher(jsp);
						rd.forward(request, response);
						return;	// �޼ҵ� �ϳ��� ã������, �׸� ã��, �ý��� ����
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("ServiceMethodError "+ex.getMessage());
		}
	}

}
