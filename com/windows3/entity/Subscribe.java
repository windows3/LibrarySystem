package com.windows3.entity;

import java.io.Serializable;

public class Subscribe implements Serializable{//ԤԼ��

	/**
	 * 
	 */
	private static final long serialVersionUID = 4754330986466277394L;
	private int sid;
	private int uid;
	private int bid;
	private String subTime;//ԤԼ��ʱ��
	private String subTime2;//ԤԼ����ʱ��
	public Subscribe(int uid, int bid) {
		super();
		this.uid = uid;
		this.bid = bid;
	}
	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}
	public Subscribe(int sid, int uid, int bid) {
		super();
		this.sid = sid;
		this.uid = uid;
		this.bid = bid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	public String getSubTime() {
		return subTime;
	}
	public void subTime2(String subTime) {
		this.subTime = subTime;
	}
	public String getSubTime2() {
		return subTime2;
	}
	public void setSubTime2(String subTime2) {
		this.subTime2 = subTime2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bid;
		result = prime * result + sid;
		result = prime * result + uid;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscribe other = (Subscribe) obj;
		if (bid != other.bid)
			return false;
		if (sid != other.sid)
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "��ԤԼ��� :" + sid + "�� ��ԤԼ��:" + uid + "��  ��ԤԼͼ��:" + bid + "�� ��ԤԼʱ��:"+subTime+"�� ��ԤԼ����ʱ��:"+subTime2+"��";
	}
	
	

}
