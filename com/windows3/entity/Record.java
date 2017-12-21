package com.windows3.entity;

public class Record {
	private int rid;
	private String lendTime;
	private String returnTime;
	private int uid;
	private int bid;

	public Record(int uid, int bid) {
		this.uid = uid;
		this.bid = bid;
	}
	

	public Record(int rid, String rtime, int uid, int bid) {
		super();
		this.rid = rid;
		this.lendTime = rtime;
		this.uid = uid;
		this.bid = bid;
	}


	public String getLendTime() {
		return lendTime;
	}


	public void setLendTime(String lendTime) {
		this.lendTime = lendTime;
	}


	public String getReturnTime() {
		return returnTime;
	}


	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}


	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
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


	@Override
	public String toString() {
		return "Record [rid=" + rid + ", lendTime=" + lendTime + ", returnTime=" + returnTime + ", uid=" + uid
				+ ", bid=" + bid + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bid;
		result = prime * result + ((lendTime == null) ? 0 : lendTime.hashCode());
		result = prime * result + ((returnTime == null) ? 0 : returnTime.hashCode());
		result = prime * result + rid;
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
		Record other = (Record) obj;
		if (bid != other.bid)
			return false;
		if (lendTime == null) {
			if (other.lendTime != null)
				return false;
		} else if (!lendTime.equals(other.lendTime))
			return false;
		if (returnTime == null) {
			if (other.returnTime != null)
				return false;
		} else if (!returnTime.equals(other.returnTime))
			return false;
		if (rid != other.rid)
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}

	
}