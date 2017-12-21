package com.windows3.entity;

import java.io.Serializable;

import com.windows3.entity.User;

public class User implements Serializable{
	private int id;
	private String name;
	private String password;
	
	public User() {
	}

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public User(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		User other = (User) obj;
		if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", ÓÃ»§Ãû=" + name + ", password=" + password + "]";
	}
	
	
}
