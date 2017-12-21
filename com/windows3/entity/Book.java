package com.windows3.entity;

import java.io.Serializable;

import com.windows3.entity.Book;

public class Book implements Serializable{
	private int id;
	private String name;
	private int bnum;
	
	public Book() {
	}

	public Book(String name, int bnum) {
		super();
		this.name = name;
		this.bnum = bnum;
	}

	public Book(int id, String name, int bnum) {
		super();
		this.id = id;
		this.name = name;
		this.bnum = bnum;
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

	

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
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
		return "Book [id=" + id + ", 书名=" + name + ", 书的剩余数量=" + bnum + "]";
	}
	
	
}
