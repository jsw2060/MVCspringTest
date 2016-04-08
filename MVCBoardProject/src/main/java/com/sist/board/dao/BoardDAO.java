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
   //��ü�������б�
   public static List<BoardVO> boardAllData(Map map){
	  SqlSession session = ssf.openSession();
      List<BoardVO> list = session.selectList("boardAllData",map);
      session.close();
      return list;
   }
   public static int boardTotalPage() {
	   SqlSession session = ssf.openSession();
	   int count=session.selectOne("boardRowCount");
	   // �����Ͱ� �ϳ��� ���� ���� selectOne
	   // �����Ͱ� ������ ���� ���� selectList
	   session.close();
	   return (int)(Math.ceil(count/10.0));
   }
   public static BoardVO boardContentData(int no) {
	   SqlSession session = ssf.openSession(true);
	   // true�̸� commit�� ���޵ȴ�. update�̹Ƿ�, auto commit�� �ƴϱ� ������ ����Ͽ��� �Ѵ�.
	   session.update("boardHitIncrement", no);
	   session.close();
	   session=ssf.openSession();
	   BoardVO vo = session.selectOne("boardContentData", no);	// ���׸��� ������ ����. vo�� ���� ����ȯ���� ������ �ִ�.
	   session.close();
	   return vo;
   }
   public static void boardInsert(BoardVO vo) {
	   SqlSession session = ssf.openSession(true);
	   session.insert("boardInsert", vo);
	   session.close();
   }
   public static BoardVO boardUpdate(int no) {
	   SqlSession session = ssf.openSession();
	   BoardVO vo = session.selectOne("boardContentData", no);
	   session.close();
	   return vo;
   }
   public static boolean boardUpdateOk(BoardVO vo) {
	   boolean bCheck=false;
	   SqlSession session = ssf.openSession();
	   String pwd = session.selectOne("boardGetPwd", vo.getNo());
	   session.close();
	   if(pwd.equals(vo.getPwd())) {
		   bCheck=true;
		   session=ssf.openSession(true);
		   session.update("boardUpdate", vo);
		   session.close();
	   }
	   else {
		   bCheck=false;
	   }
	   return bCheck;
   }
}