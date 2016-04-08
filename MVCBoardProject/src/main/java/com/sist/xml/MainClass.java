package com.sist.xml;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.util.*;

public class MainClass {

	public static void main(String[] args) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(Member.class);
		Unmarshaller um = jc.createUnmarshaller();
		Member m = (Member)um.unmarshal(new File("C:\\Users\\sist\\git\\SpringBoardtest\\MVCBoardProject\\src\\main\\java\\com\\sist\\xml\\member.xml"));
		List<Memberlist> list = m.getMemberlist();
		for(Memberlist mm:list) {
			System.out.println(mm.getId() + " " + mm.getName() + " " + mm.getSex());
		}
	}

}
