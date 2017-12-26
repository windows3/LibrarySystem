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
			return false;
		else {
			for (Subscribe subscribe : sList) {
				if(subscribe.getBid()==bid) {
					String nowTime = new SimpleDateFormat("yyyyMMdd").format(new Date()); //格式化为 hhmmss
					int nowTime2 = Integer.parseInt(nowTime);
					int subTime2=Integer.parseInt(subscribe.getSubTime2());
					if(nowTime2>subTime2) {
						return true;
					}
				}
			}
			return false;
		}
	}

	@Override
	public boolean addSub(Subscribe subscribe,int numDay) {
		
		return subDao.addSub(subscribe, numDay);
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
	
}
