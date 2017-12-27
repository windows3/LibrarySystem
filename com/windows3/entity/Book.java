package com.windows3.entity;

import java.io.Serializable;

import com.windows3.entity.Book;

public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5254700085702955953L;
	private int id;
	private String name;
	private int bcount;
	private int status;//状态 1为在馆,0是被借走
	
	public Book() {
	}

	public Book(String name) {
		super();
		this.name = name;
		
	}

	public Book(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		
	}

	public int getBcount() {
		return bcount;
	}

	public void setBcount(int bcount) {
		this.bcount = bcount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		Book other = (Book) obj;
		if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if(this.status==1) {		
			return "【图书编号:" + id + "】  【 书名:" + name + "】  【在馆/可借】 【被借次数:"+bcount+"】";
		}else {
			return "【图书编号:" + id + "】  【 书名=" + name + "】, 【不在馆/不可借】 【被借次数:"+bcount+"】";
		}
	}
	
	
}
