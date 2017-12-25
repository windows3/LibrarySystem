package com.windows3.entity;

import java.io.Serializable;

import com.windows3.entity.User;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3599166662858452717L;
	private int id;
	private String name;
	private String password;
	private int money;
	private int status;
	
	public User() {
	}

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public User(int id, String name, String password,int money) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.money=money;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
		return "【用户编号:" + id + "】 【 用户名:" + name + "】  【积分剩余:" + money + "】";
	}
	
	
}
