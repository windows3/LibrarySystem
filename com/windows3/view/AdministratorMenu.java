package com.windows3.view;

import java.util.List;
import java.util.Scanner;
import com.windows3.biz.BookBiz;
import com.windows3.biz.RecordBiz;
import com.windows3.biz.SubscribeBiz;
import com.windows3.biz.UserBiz;
import com.windows3.biz.impl.BookBizImpl;
import com.windows3.biz.impl.RecordBizImpl;
import com.windows3.biz.impl.SubscribeBizImpl;
import com.windows3.biz.impl.UserBizImpl;
import com.windows3.entity.Book;
import com.windows3.entity.Record;
import com.windows3.entity.Subscribe;
import com.windows3.entity.User;
import com.windows3.myutil.MyUtil;

public class AdministratorMenu {
	private static UserBiz userBiz = new UserBizImpl();
	private static BookBiz bookBiz = new BookBizImpl();;
	private static Scanner input = new Scanner(System.in);;
	private static RecordBiz recordBiz = new RecordBizImpl();
	private static SubscribeBiz subBiz = new SubscribeBizImpl();

	public static void mainMenu() {
		System.out.println("����Ա�˻�:"+MyUtil.ADMIN_NAME);
		System.out.println("����Ա����:"+MyUtil.ADMIN_PASSWORD);
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>��¼");
			System.out.println("0==>�˳�");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 1);

			switch (choice) {
			case 1:
				// ��¼
				loginMainMenu();
				continue;
			case 0:
				// �˳�
				break;
			}
			System.out.println("��л����ʹ��,�ټ�!");
			break;

		}
	}

	private static void loginMainMenu() {// ��¼�˵�
		while (true) {
			System.out.println("*********************");
			// ��¼
			String name = MyUtil.inputName();
			String password = MyUtil.inputPassword();
			if (!(name.equals(MyUtil.ADMIN_NAME) || !password.equals(MyUtil.ADMIN_PASSWORD))) {
				System.out.println("��¼ʧ��");
			} else {
				loginSucceed();
				break;
			}
		}
	}

	private static void loginSucceed() {
		System.out.println("******��ӭ����Ա��˼ũ��½�ɹ�*****");
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>����ͼ��");
			System.out.println("2==>�����û�");
			System.out.println("3==>��������¼");
			System.out.println("4==>����ԤԼ��¼");
			System.out.println("0==>������һ��");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 4);
			switch (choice) {
			case 1:
				manageBook();
				continue;
			case 2:
				manageUser();// �����û�
				continue;
			case 3:
				manageRecord();// ��������¼
				continue;
			case 4:
				manageSub();// ����ԤԼ��¼
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())
				break;

		}

	}

	private static void manageSub() {
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>��ѯȫ��ԤԼ��¼");
			System.out.println("2==>��ѯδ����ԤԼ��¼");
			System.out.println("3==>��ѯ����ԤԼ��¼");
			System.out.println("0==>������һ��");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 3);
			switch (choice) {
			case 1:

				querySubAll();
				continue;
			case 2:
				querySubUnexpire();// δ����
				continue;
			case 3:
				querySubExpire();// ����
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void querySubExpire() {
		List<Subscribe> sList = subBiz.querySubExpire();
		if (sList == null || sList.isEmpty()) {
			System.out.println("û�е��ڼ�¼ԤԼ��¼");
		} else {
			System.out.println(sList);
		}

	}

	private static void querySubUnexpire() {
		List<Subscribe> sList = subBiz.querySubUnexpire();
		if (sList == null || sList.isEmpty()) {
			System.out.println("û��δ���ڼ�¼ԤԼ��¼");
		} else {
			System.out.println(sList);
		}
	}

	private static void querySubAll() {
		List<Subscribe> sList = subBiz.querySubAll();
		if (sList == null || sList.isEmpty()) {
			System.out.println("û��ԤԼ��¼");
		} else {
			System.out.println(sList);
		}
	}

	private static void manageRecord() {
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>��ѯ��¼");
			System.out.println("0==>������һ��");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 1);
			switch (choice) {
			case 1:
				queryRecord();
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void queryRecord() {
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>ͨ��uid��ѯ��¼");
			System.out.println("2==>ͨ��bid��ѯ��¼");
			System.out.println("3==>ͨ��uid��bid��ѯ��¼");
			System.out.println("0==>������һ��");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 3);
			switch (choice) {
			case 1:
				System.out.println("�������û���id");
				int uid = MyUtil.inputNum();
				queryRecordByUid(uid);
				continue;
			case 2:
				System.out.println("������ͼ��ĵ�id");
				int bid = MyUtil.inputNum();
				queryRecordByBid(bid);
				continue;
			case 3:
				queryRecordByUid_Bid();
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void queryRecordByUid_Bid() {
		while (true) {
			System.out.println("********************");
			System.out.println("�������û���id");
			int uid = MyUtil.inputNum();
			if (userBiz.queryUserByUid(uid) == null) {
				System.out.println("���޴���");
				break;
			} else {
				System.out.println("������ͼ��ĵ�id");
				int bid = MyUtil.inputNum();
				if (bookBiz.queryBookByBid(bid) == null) {
					System.out.println("���޴���");
				} else {
					List<Record> rList = recordBiz.queryRecordByUid_Bid(uid, bid);
					if (rList == null)
						System.out.println("���û�û��δ�����¼");
					else
						System.out.println(rList);
					if (MyUtil.isGoOn())
						break;
				}
			}
		}

	}

	private static void queryRecordByBid(int bid) {
		System.out.println("*********************");
		if (bookBiz.queryBookByBid(bid) == null) {
			System.out.println("û�д�ID��ͼ��");
		} else {
			List<Record> rList = recordBiz.queryRecordByBid(bid);
			if (rList == null)
				System.out.println("��IDͼ��û�б����Ĺ�");
			else
				System.out.println(rList);
		}
	}

	private static void manageUser() {// �����û�
		while (true) {
			System.out.println("*********************");
			List<User> uList=userBiz.queryUserAll();
			if(uList==null||uList.isEmpty()) {
				System.out.println("�����û�");
			}else {
				
				System.out.println("1==>ͨ��uid�����û�");
				System.out.println("2==>ͨ��uname�����û�");
				System.out.println("0==>������һ��");
				System.out.println("����������ѡ��:");
				int choice = MyUtil.inputNum(0, 3);
				switch (choice) {
				case 1:
					System.out.println("��������Ҫ�������û�id");
					int uid = MyUtil.inputNum();
					manageUserByUid(uid);
					continue;
				case 2:
					manageUserByUname();// �����û�
					continue;
				case 0:
					// �˳�
					break;
				}
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void manageUserByUname() {
		String uname = MyUtil.inputName();
		// ͵����һ�� Y(^_^)Y
		int uid = userBiz.queryUserByUname(uname);
		if (uid == -1)
			System.out.println("���޴���");
		else
			manageUserByUid(uid);
	}

	private static void manageUserByUid(int uid) {
		while (true) {
			User user = userBiz.queryUserByUid(uid);
			if (user == null) {
				System.out.println("���޴���");
				break;
			} else {
				System.out.println("1==>��ѯ���û���ȫ�������¼");
				System.out.println("2==>��ѯ���û����ѻ������¼");
				System.out.println("3==>��ѯ���û���δ�������¼");
				System.out.println("4==>����/�ⶳ/��ֵ");

				System.out.println("0==>������һ��");
				int choice = MyUtil.inputNum(0, 4);
				switch (choice) {
				case 1:
					queryRecordByUid(uid);
					continue;
				case 2:
					queryRecordReturned(uid);
					continue;
				case 3:
					queryRecordUnreturned(uid);
					continue;
				case 4:
					freeUser();// ��ûд�� ����,�ⶳ
					continue;
				case 0:
					// �˳�
					break;
				}
				if (MyUtil.isGoOn())

					break;
			}
		}
	}

	private static void freeUser() {
		while (true) {
			System.out.println("****************************************");
			System.out.println("1==>�����û�");
			System.out.println("2==>�ⶳ�û�");
			System.out.println("3==>���û���ֵ");
			System.out.println("0==>������һ��");
			int choice = MyUtil.inputNum(0, 3);
			switch (choice) {
			case 1:
				freezeUser();// ����
				continue;
			case 2:
				UnfreezeUser();// �ⶳ
				continue;
			case 3:
				rechargeUser();// ��ֵ
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())

				break;
		}

	}

	private static void rechargeUser() {
		while (true) {
			System.out.println("*************************");
			System.out.println("1==>�����û�����ֵ");
			System.out.println("2==>�����û�id��ֵ");
			System.out.println("0==>������һ��");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:
				rechargeUserByUname();// �����û�����ֵ
				continue;
			case 2:
				rechargeUserById();// �����û�id��ֵ
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())

				break;

		}

	}

	private static void rechargeUserById() {
		while (true) {
			System.out.println("*********************");
			System.out.println("�������û�id");
			int uid = MyUtil.inputNum();
			User user = userBiz.queryUserByUid(uid);
			if (user == null) {
				System.out.println("���޴���");
			} else {
				System.out.println("�������ֵ���");
				int money = MyUtil.inputNum();
				boolean flag = userBiz.rechargeUserByUname(uid, money);// ��ֵ
				if (flag) {
					System.out.println("��ֵ�ɹ�");
				} else {
					System.out.println("��ֵʧ��");
				}
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void rechargeUserByUname() {
		while (true) {
			System.out.println("*********************");
			String uname = MyUtil.inputName();
			int uid = userBiz.queryUserByUname(uname);
			if (uid == -1) {
				System.out.println("���޴���");
			} else {
				System.out.println("�������ֵ���");
				int money = MyUtil.inputNum();
				boolean flag = userBiz.rechargeUserByUname(uid, money);// ָ�������û����1
				if (flag) {
					System.out.println("��ֵ�ɹ�");
				} else {
					System.out.println("��ֵʧ��");
				}
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void UnfreezeUser() {
		while (true) {
			System.out.println("****************************************");
			System.out.println("1==>�����û����ⶳ�û�");
			System.out.println("2==>�����û�id�ⶳ�û�");
			System.out.println("0==>������һ��");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:
				unFreezeUserByUname();// �����û����ⶳ�û�
				continue;
			case 2:
				unFreezeUserById();// �����û�id�ⶳ�û�
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())

				break;
		}

	}

	private static void unFreezeUserById() {
		while (true) {
			System.out.println("*********************");
			System.out.println("�������û�id");
			int uid = MyUtil.inputNum();
			User user = userBiz.queryUserByUid(uid);
			if (user == null) {
				System.out.println("���޴���");
			} else {
				boolean flag = userBiz.updateStatus(uid, 1);// ָ�������û����0
				if (flag) {
					System.out.println("����ɹ�");
				} else {
					System.out.println("����ʧ��");
				}
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void unFreezeUserByUname() {
		while (true) {
			System.out.println("*********************");
			String uname = MyUtil.inputName();
			int uid = userBiz.queryUserByUname(uname);
			if (uid == -1) {
				System.out.println("���޴���");
			} else {
				boolean flag = userBiz.updateStatus(uid, 1);// ָ�������û����0
				if (flag) {
					System.out.println("����ɹ�");
				} else {
					System.out.println("����ʧ��");
				}
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void freezeUser() {// �����û�
		while (true) {
			System.out.println("****************************************");
			System.out.println("1==>�����û��������û�");
			System.out.println("2==>�����û�id�����û�");
			System.out.println("0==>������һ��");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:
				freezeUserByUname();// �����û��������û�
				continue;
			case 2:
				freezeUserById();// �����û�id�����û�
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())

				break;
		}

	}

	private static void freezeUserById() {// �����û�id�����û�
		while (true) {
			System.out.println("*********************");
			System.out.println("�������û�id");
			int uid = MyUtil.inputNum();
			User user = userBiz.queryUserByUid(uid);
			if (user == null) {
				System.out.println("���޴���");
			} else {
				boolean flag = userBiz.updateStatus(uid, 0);// ָ�������û����0
				if (flag) {
					System.out.println("����ɹ�");
				} else {
					System.out.println("����ʧ��");
				}
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void freezeUserByUname() {// �����û��������û�
		while (true) {
			System.out.println("*********************");
			String uname = MyUtil.inputName();
			int uid = userBiz.queryUserByUname(uname);
			if (uid == -1) {
				System.out.println("���޴���");
			} else {
				boolean flag = userBiz.updateStatus(uid, 0);// ָ�������û����0
				if (flag) {
					System.out.println("����ɹ�");
				} else {
					System.out.println("����ʧ��");
				}
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void queryRecordUnreturned(int uid) {
		if (userBiz.queryUserByUid(uid) == null) {
			System.out.println("���޴���");
		} else {
			List<Record> rList = recordBiz.queryRecordByUidUnreturned(uid);
			if (rList == null||rList.isEmpty())
				System.out.println("���û�û��δ�����¼");
			else
				System.out.println(rList);
		}
	}

	private static void queryRecordReturned(int uid) {
		if (userBiz.queryUserByUid(uid) == null) {
			System.out.println("���޴���");
		} else {
			List<Record> rList = recordBiz.queryRecordByUidReturned(uid);
			if (rList == null||rList.isEmpty())
				System.out.println("���û�û�л����¼");
			else
				System.out.println(rList);
		}
	}

	private static void queryRecordByUid(int uid) {
		if (userBiz.queryUserByUid(uid) == null) {
			System.out.println("���޴���");
		} else {
			List<Record> rList = recordBiz.queryRecordByUid(uid);
			if (rList == null||rList.isEmpty())
				System.out.println("���û�û�н����¼");
			else
				System.out.println(rList);
		}
	}

	private static void manageBook() {// ����ͼ��
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>���ͼ��");
			System.out.println("2==>ɾ��ͼ��");
			System.out.println("3==>�鿴ͼ��");
			System.out.println("0==>������һ��");
			int choice = MyUtil.inputNum(0, 3);
			switch (choice) {
			case 1:
				addBook();
				continue;
			case 2:
				delBook();
				continue;
			case 3:
				queryBook();
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void queryBook() {
		List<Book> bList=bookBiz.queryBookAll();
		if(bList==null||bList.isEmpty()) {
			System.out.println("ͼ�������,�����������");
		}else{
			System.out.println(bList);
		}
		
	}

	private static void addBook() {// ����鱾
		while (true) {

			System.out.println("��������Ҫ��ӵ��鱾����:");

			String bname = input.next();
			Book book = new Book(bname);
			if (bookBiz.addBook(book))
				System.out.println("�ɹ�");
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void delBook() {// ɾ����
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>��������ɾ��");
			System.out.println("2==>����idɾ��");
			System.out.println("0==>������һ��");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:
				delBookByBname();
				continue;
			case 2:
				delBookByBid();
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void delBookByBid() {
		System.out.println("*********************");
		System.out.println("��������Ҫɾ�������id");
		int bid = MyUtil.inputNum();
		System.out.println(bookBiz.queryBookByBid(bid));
		boolean flag = bookBiz.delBookByBid(bid);
		if (flag) {
			System.out.println("ɾ���ɹ�");
		} else {
			System.err.println("ɾ��ʧ��");
		}

	}

	private static void delBookByBname() {
		String bname = MyUtil.inputName();
		boolean flag = bookBiz.delBookByBname(bname);
		if (flag)
			System.out.println("ɾ���ɹ�");
		else
			System.out.println("ɾ��ʧ��");
	}

}
