package com.sist.board.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.util.*;

public class BoardDAO {
   private static SqlSessionFactory ssf;

   static {
      try {
         Reader reader = Resources.getResourceAsReader("Config.xml");
         ssf = new SqlSessionFactoryBuilder().build(reader);
      } catch (Exception ex) {
         System.out.println(ex.getMessage());
      }
   }
   //전체데이터읽기
   public static List<BoardVO> boardAllData(Map map){
	  SqlSession session = ssf.openSession();
      List<BoardVO> list = session.selectList("boardAllData",map);
      session.close();
      return list;
   }
   public static int boardTotalPage() {
	   SqlSession session = ssf.openSession();
	   int count=session.selectOne("boardRowCount");
	   // 데이터가 하나만 들어올 때는 selectOne
	   // 데이터가 여러개 들어올 때는 selectList
	   session.close();
	   return (int)(Math.ceil(count/10.0));
   }
   public static BoardVO boardContentData(int no) {
	   SqlSession session = ssf.openSession(true);
	   // true이면 commit이 전달된다. update이므로, auto commit이 아니기 때문에 명시하여야 한다.
	   session.update("boardHitIncrement", no);
	   session.close();
	   session=ssf.openSession();
	   BoardVO vo = session.selectOne("boardContentData", no);
	   session.close();
	   return vo;
   }
}