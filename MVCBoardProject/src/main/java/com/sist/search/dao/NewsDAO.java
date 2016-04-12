package com.sist.search.dao;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.net.*;
import java.io.*;

public class NewsDAO {
	public List<Item> newsAllData(String title) {
		List<Item> list = new ArrayList<Item>();
		try {
			URL url = new URL("http://newssearch.naver.com/search.naver?where=rss&query="
							+ URLEncoder.encode(title, "UTF-8"));
			JAXBContext jc = JAXBContext.newInstance(Rss.class);
			Unmarshaller um = jc.createUnmarshaller();
			Rss rss = (Rss)um.unmarshal(url);
			list = rss.getChannel().getItem();
		} catch (Exception ex) {
			System.out.println("NewsDAO ERROR " + ex.getMessage());
		}
		return list;
	}
}