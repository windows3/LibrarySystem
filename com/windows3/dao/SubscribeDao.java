package com.windows3.dao;

import java.util.List;

import com.windows3.entity.Subscribe;

public interface SubscribeDao {
	boolean addSub(Subscribe subscribe,int numDay);
	List<Subscribe> querySubAll();
	List<Subscribe> querySubByUid(int uid);
	List<Subscribe> querySubByBid(int bid);
	int querySubByBidReturn(int bid);//∑µªÿ £”‡ ±º‰
}
