package com.sist.controller;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.*;

public class WebApplicationContext {
	List<String> list = new ArrayList<String>();
	
	public WebApplicationContext(String path) {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			HandlerMapping hm = new HandlerMapping();
			sp.parse(new File(path), hm);
			list = hm.list;
		} catch (Exception ex) {}
	}
	public List<String> getFileName()
	   {
	      List<String> packList=new ArrayList<String>();
	      for(String pack:list)
	      {
	         List<String> fList=FileConfig.getFileName(pack);
	         for(String s:fList)
	         {
	            packList.add(s);
	         }
	      }
	      return packList;
	   }
}
