package com.sist.board.dao;
import java.util.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("start", 1);
		map.put("end", 10);
		List<BoardVO> list = BoardDAO.boardAllData(map);
		for(BoardVO vo:list) {
			System.out.println(vo.getNo() + " " + vo.getName());
		}
	}
}
