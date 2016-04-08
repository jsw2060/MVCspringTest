package com.sist.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
/*
 *  태그와 태그 사이에 있으면 엘리먼트
 *  태그와 속성값 사이에는 애트리뷰트
 */
public class Memberlist {
	private int no;
	private String name;
	private String sex;
	private String id;
	
	public String getId() {
		return id;
	}
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
	public int getNo() {
		return no;
	}
	@XmlElement
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	@XmlElement
	public void setSex(String sex) {
		this.sex = sex;
	}
}
