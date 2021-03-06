package com.windows3.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.windows3.biz.SubscribeBiz;
import com.windows3.dao.impl.SubscribeDaoImpl;
import com.windows3.entity.Subscribe;

public class SubscribeBizImpl implements SubscribeBiz {
	SubscribeDaoImpl subDao=new SubscribeDaoImpl();

	@Override
	public boolean querySubByBid(int bid) {//找找有没有这条记录,时间对不对的上
		List<Subscribe> sList=subDao.querySubByBid(bid);
		if(sList==null||sList.isEmpty())
			return true;
		else {
			return false;
		}
	}

	@Override
	public boolean addSub(Subscribe subscribe,int numDay) {
		if(subscribe==null||numDay<0) {
			return false;
		}else {
			
			return subDao.addSub(subscribe, numDay);
		}
		
	}

	@Override
	public int querySubByBidReturn(int bid) {
		
		return subDao.querySubByBidReturn(bid);
	}

	@Override
	public Subscribe querySubByUid(int uid) {
		List<Subscribe> sList= subDao.querySubByUid(uid);
		if(sList==null||sList.isEmpty()) {
			return null;
		}else {
			for (Subscribe subscribe : sList) {
				String time1=subscribe.getSubTime2();
				String nowTime = new SimpleDateFormat("yyyyMMddhh").format(new Date());
				int d1 = Integer.parseInt(time1);
				int d2 = Integer.parseInt(nowTime);
				if (d1<d2) {
					return subscribe;
				}
			}		
			return null;
		}
	}

	@Override
	public List<Subscribe> querySubAll() {
		
		return subDao.querySubAll();
	}

	@Override
	public List<Subscribe> querySubUnexpire() {
		return subDao.querySubUnexpire();
	}

	@Override
	public List<Subscribe> querySubExpire() {
		// TODO Auto-generated method stub
		return subDao.querySubExpire();
	}

	@Override
	public List<Subscribe> querySubByUidAll(int uid) {
		if(uid<1) {
			return null;
		}
		return subDao.querySubByUid(uid);
	}

	@Override
	public List<Subscribe> querySubUnexpire(int uid) {
		if(uid<1) {
			return null;
		}
		return subDao.querySubUnexpire(uid);
	}

	@Override
	public List<Subscribe> querySubExpire(int uid) {
		if(uid<1) {
			return null;
		}
		return subDao.querySubExpire(uid);
	}
	
}
