package com.sist.search.dao;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.mongodb.*;

import java.net.*;
import java.io.*;

public class NewsDAO {
	/*public static void main(String[] args) {
		try {
			NewsDAO dao = new NewsDAO();
			dao.newsAllData("선거");
			for(Item item:list) {
				String t=item.getTitle();
				String desc=item.getDescription();
				desc=desc.replaceAll(".", "");
				desc=desc.replaceAll("[A-Za-z]", "");
				desc=desc.replace("'", "");
				System.out.println(t+":"+desc);
				// System.out.println(item.getTitle()+":"+item.getDescription());
			}
		} catch (Exception ex) {
			System.out.println("NewsDAO ERROR " + ex.getMessage());
		}
	}*/
	private MongoClient mc;		// Connection
	private DB db;				// Database (ORCL) => mydb (use mydb)
	private DBCollection dbc;	// table
	// collection은 정형화 되지 않은 데이터가 저장가능하다. ORACLE은 정형화된, 규격에 맞는 데이터만 저장할 수 있다.
	public NewsDAO() {
		try {
			//getConnection
			mc = new MongoClient("localhost");	// 포트번호
			db = mc.getDB("mydb");
			dbc = db.getCollection("news");
			if(dbc!=null || dbc.count()>0) {
				dbc.drop();
			}
		} catch (Exception ex) {
			System.out.println("NewsDAO ERROR " + ex.getMessage());
		}
	}
	public void newsInsert(NewsVO vo) {
		try {
			DBCursor cursor = dbc.find(); // SELECT * FROM news	
			// ResultSet
			int max=0;
			// {no:1, title:"aaa", msg:"aaa"} JSON 저장방식 => BasicDBObject
			while(cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject)cursor.next();
				int no=obj.getInt("no");
				if(max<no)
					max=no;
			}
			cursor.close();
			BasicDBObject obj = new BasicDBObject();
			obj.put("no", max+1);
			obj.put("title", vo.getTitle());
			obj.put("msg", vo.getMsg());
			dbc.insert(obj);
		} catch (Exception ex) {
			System.out.println("newsInsert ERROR " + ex.getMessage());
		}
	}
	public void newsAllData(String title) {
		List<Item> list = new ArrayList<Item>();
		try {
			URL url = new URL("http://newssearch.naver.com/search.naver?where=rss&query="
							+ URLEncoder.encode(title, "UTF-8"));
			JAXBContext jc = JAXBContext.newInstance(Rss.class);
			Unmarshaller um = jc.createUnmarshaller();
			Rss rss = (Rss)um.unmarshal(url);
			list = rss.getChannel().getItem();
			for(Item item:list) {
				NewsVO vo = new NewsVO();
				vo.setTitle(item.getTitle());
				String desc = item.getDescription();
				desc=desc.replace(".", "");
				desc=desc.replaceAll("[A-Za-z]", "");
				desc=desc.replace("'", "");
				vo.setMsg(desc);
				newsInsert(vo);
			}
		} catch (Exception ex) {
			System.out.println("NewsDAO ERROR " + ex.getMessage());
		}
		return;
	}
	public List<NewsVO> newsAllData(int page) {
		List<NewsVO> list = new ArrayList<NewsVO>();
		try {
			int rowSize=10;
			int skip=(page*rowSize)-rowSize;
			//	dbc.find().sort({no:-1})   {} == BasicDBObject    -1은 DESC
			DBCursor cursor = dbc.find().sort(new BasicDBObject("no", -1)).skip(skip).limit(rowSize);		// 10개 버리고, 10개 가져와 => 페이지 넘어가는 것 구현
			while(cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject)cursor.next();
				NewsVO vo = new NewsVO();
				vo.setNo(obj.getInt("no"));
				vo.setTitle(obj.getString("title"));
				vo.setMsg(obj.getString("msg"));
				list.add(vo);
			}
			cursor.close();
		} catch (Exception ex) {
			System.out.println("newsAllData ERROR " + ex.getMessage());
		}
		return list;
	}
	public int newsTotalpage() {
		int total=0;
		try {
			DBCursor cursor = dbc.find();
			int count = cursor.count();
			cursor.close();
			total=(int)(Math.ceil(count/10.0));
		} catch (Exception ex) {
			System.out.println("newsTotalpage ERROR " + ex.getMessage());
		}
		return total;
	}
}