package com.windows3.dao;

import java.util.List;

import com.windows3.entity.Subscribe;

public interface SubscribeDao {
	boolean addSub(Subscribe subscribe, int numDay);

	List<Subscribe> querySubAll();

	List<Subscribe> querySubByUid(int uid);

	List<Subscribe> querySubByBid(int bid);

	int querySubByBidReturn(int bid);// 返回剩余时间

	List<Subscribe> querySubUnexpire();

	List<Subscribe> querySubExpire();
	List<Subscribe> querySubUnexpire(int uid);//某人所有未到期预约信息
	List<Subscribe> querySubExpire(int uid);//某人所有到期预约信息
}
