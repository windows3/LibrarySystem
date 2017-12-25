package com.windows3.dao.impl;

import java.util.Date;
import java.util.List;

import com.windows3.dao.SubscribeDao;
import com.windows3.entity.Subscribe;

import java.io.File;
import java.text.SimpleDateFormat;

public class SubscribeDaoImpl<T> extends BaseDao<T> implements SubscribeDao {
	public SubscribeDaoImpl() {
		file = new File("subscribe.txt");
	}

	@Override
	public boolean addSub(Subscribe subscribe, int numDay) {// ����Ǽ�����
		if (subscribe == null) {
			return false;
		}
		// ���ļ��ж������е�User
		List<Subscribe> uList = (List<Subscribe>) read();
		// �����û�id+1
		if (uList.isEmpty()) {
			subscribe.setSid(1);
			String subTime = new SimpleDateFormat("yyyyMMddhh").format(new Date());
			subscribe.setSubTime(subTime);
			int d1Number1 = Integer.parseInt(subTime) + numDay;
			Integer a = d1Number1;
			String subTime2 = a.toString();
			subscribe.setSubTime2(subTime2);
		} else {
			int newSid = uList.get(uList.size() - 1).getSid() + 1;
			subscribe.setSid(newSid);
		}
		uList.add(subscribe);
		return write((List<T>) uList);

	}

	@Override
	public List<Subscribe> querySubAll() {
		List<Subscribe> uList = (List<Subscribe>) read();
		// �����û�id+1
		if (uList.isEmpty()) {
			return null;

		} else {
			return uList;
		}
	}

	@Override
	public List<Subscribe> querySubByUid(int uid) {
		if (uid < 1) {
			return null;
		} else {
			List<Subscribe> uList = (List<Subscribe>) read();
			List<Subscribe> uList2 = (List<Subscribe>) read();
			// �����û�id+1
			if (uList.isEmpty()||uList==null) {
				return null;
			} else {
				for (Subscribe sub : uList) {
					if (sub.getUid() == uid)
						uList2.add(sub);
				}
				return uList;
			}
		}
	}

	@Override
	public List<Subscribe> querySubByBid(int bid) {
		if (bid < 1) {
			return null;
		} else {
			List<Subscribe> uList = (List<Subscribe>) read();
			List<Subscribe> uList2 = (List<Subscribe>) read();
			// �����û�id+1
			if (uList.isEmpty()) {
				return null;
			} else {
				for (Subscribe sub : uList) {
					if (sub.getBid() == bid)
						uList2.add(sub);
				}
				return uList;
			}
		}
	}

	public int querySubByBidReturn(int bid) {
		if (bid < 1) {
			return -1;
		} else {
			List<Subscribe> uList = (List<Subscribe>) read();
			// �����û�id+1
			if (uList == null || uList.isEmpty()) {
				return -1;
			} else {
				for (Subscribe sub : uList) {
					String subTime2 = sub.getSubTime2();
					String nowTime = new SimpleDateFormat("yyyyMMddhh").format(new Date());
					int d1 = Integer.parseInt(subTime2);
					int d2 = Integer.parseInt(nowTime);
					if (sub.getBid() == bid&&d1>d2) {
						return (d1-d2)/100;
					}
				}
				return -1;
			}
		}
	}

}