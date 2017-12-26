package com.windows3.biz;

import java.util.List;

import com.windows3.entity.Subscribe;

public interface SubscribeBiz {

	boolean querySubByBid(int bid);//找找有没有这条记录,时间对不对的上

	boolean addSub(Subscribe subscribe,int numDay);//增加记录

	int querySubByBidReturn(int bid);//根据书的记录剩余借书的时间

	Subscribe querySubByUid(int uid);//判断当前时间有没有预约的数

	List<Subscribe> querySubAll();//全部

	List<Subscribe> querySubUnexpire();//未到期

	List<Subscribe> querySubExpire();//到期
}
