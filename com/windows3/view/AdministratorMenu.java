package com.windows3.view;

import java.util.List;
import java.util.Scanner;
import com.windows3.biz.BookBiz;
import com.windows3.biz.RecordBiz;
import com.windows3.biz.UserBiz;
import com.windows3.biz.impl.BookBizImpl;
import com.windows3.biz.impl.RecordBizImpl;
import com.windows3.biz.impl.UserBizImpl;
import com.windows3.entity.Book;
import com.windows3.entity.Record;
import com.windows3.entity.User;
import com.windows3.myutil.MyUtil;

public class AdministratorMenu {
	private final static String ADMIN_NAME = "��˼ũ";
	private final static String ADMIN_PASSWORD = "hebei208@";
	private static UserBiz userBiz = new UserBizImpl();
	private static BookBiz bookBiz = new BookBizImpl();;
	private static Scanner input = new Scanner(System.in);;
	private static RecordBiz recordBiz = new RecordBizImpl();

	public static void mainMenu() {
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
			if (!(name.equals(ADMIN_NAME) || !password.equals(ADMIN_PASSWORD))) {
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
			System.out.println("3==>�����¼");
			System.out.println("0==>������һ��");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 3);
			switch (choice) {
			case 1:
				manageBook();
				continue;
			case 2:
				manageUser();// �����û�
				continue;
			case 3:
				manageRecord();// �����¼
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())
				break;

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
				System.out.println("4==>�����û�");
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
					// freezeUser();// ��ûд��
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

	private static void queryRecordUnreturned(int uid) {
		if (userBiz.queryUserByUid(uid) == null) {
			System.out.println("���޴���");
		} else {
			List<Record> rList = recordBiz.queryRecordByUidUnreturned(uid);
			if (rList == null)
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
			if (rList == null)
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
			if (rList == null)
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
			System.out.println("0==>������һ��");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:
				addBook();
				continue;
			case 2:
				delBook();
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())
				break;
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
