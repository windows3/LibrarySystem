package com.windows3.biz;

import com.windows3.entity.Subscribe;

public interface SubscribeBiz {

	boolean querySubByBid(int bid);//������û��������¼,ʱ��Բ��Ե���

	boolean addSub(Subscribe subscribe,int numDay);//���Ӽ�¼

	int querySubByBidReturn(int bid);//������ļ�¼ʣ������ʱ��

	Subscribe querySubByUid(int uid);//�жϵ�ǰʱ����û��ԤԼ����
}
