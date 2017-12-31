package com.windows3.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.print.DocFlavor.READER;

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

public class UserMenu {
	private static UserBiz userBiz = new UserBizImpl();
	private static BookBiz bookBiz = new BookBizImpl();;
	private static Scanner input = new Scanner(System.in);;
	private static RecordBiz recordBiz = new RecordBizImpl();
	private static SubscribeBiz subBiz = new SubscribeBizImpl();// ԤԼ��
	private static int uid = 0;

	public static void mainMenu() {
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>ע��");
			System.out.println("2==>��¼");
			System.out.println("3==>�һ�����");
			System.out.println("0==>�˳�");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 3);

			switch (choice) {
			case 1:
				registerMainMenu();
				continue;
			case 2:
				// ��¼
				loginMainMenu();
				continue;
			case 3:
				// ��¼
				verification();// �����һ�
				continue;
			case 0:
				// �˳�
				break;
			}
			break;

		}
	}

	private static void verification() {
		String uname = MyUtil.inputName();
		int bid = userBiz.queryUserByUname(uname);

		String ver = MyUtil.verification();
		System.out.println(ver);
		System.out.println("��������֤��:");
		String ver2 = input.next();
		if (bid == -1 || ver2.equals(ver)) {// ��һ������,���ܸ����û���û������˻�
			User user = userBiz.queryUserByUid(bid);
			String password = MyUtil.decode(user.getPassword());
			System.out.println("����������");
			System.out.println(password);
		} else {
			System.out.println("�һ�ʧ��");

		}

	}

	private static void registerMainMenu() {// ע��
		while (true) {
			System.out.println("*********************");
			// ע��
			String name = MyUtil.inputName();
			String password = MyUtil.inputPassword();
			System.out.println("���ظ�������������");
			String password2 = input.next();
			if (password.equals(password2)) {
			} else {
				System.out.println("��������������벻һ��");
				System.out.println("������ע��");
				continue;
			}
			if (userBiz.register(new User(name, password))) {
				System.out.println("ע��ɹ�");
				break;
			} else
				System.err.println("Error");// Ϊ�˲���������ٷ�,�û����Ѵ���,ע��ʧ��

			if (MyUtil.isGoOn())
				break;

		}
	}

	private static void loginMainMenu() {// ��¼�˵�
		List<User> uList = userBiz.queryUserAll();
		if (uList == null || uList.isEmpty()) {
			System.out.println("û���û�");
		} else {

			while (true) {
				System.out.println("*********************");
				// ��¼
				String name = MyUtil.inputName();
				String password = MyUtil.inputPassword();
				User user = userBiz.login(name, password);

				if (userBiz.login(name, password) == null) {
					System.out.println("��������û�������");
				} else {

					uid = userBiz.queryUserByUname(name);
					boolean flag1 = userBiz.queryUserByUname_Status(name);// ��ѯ�Ƿ�ɵ�½
					// System.out.println(userBiz.queryUserByUid(1));
					if (!flag1) {
						System.out.println("�����˻��ѱ�����,����ϵ����Ա");
						break;
					} else {
						boolean flag = userBiz.queryUserByUname_Money(name);
						if (!flag) {
							System.out.println("���Ļ����Ѳ���50,����ϵ����Ա��ֵ");
							reMoney();
						} else {
							System.out.println(user + "--��½�ɹ�");
							login(user, name);
							break;
						}
					}
				}
			}
		}
	}

	private static void reMoney() {
		while (true) {
			System.out.println("1==>��ֵ");
			System.out.println("2==>�˳�����");
			System.out.println("0==>������һ��");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:
				reMoney2();
				continue;
			case 2:
				System.exit(0);
			case 0:
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void reMoney2() {
		System.out.println("��������Ҫ��ֵ�Ľ��");
		int money = MyUtil.inputNum();
		boolean flag = userBiz.rechargeUserByUname(uid, money);// ָ�������û����1
		if (flag) {
			System.out.println("��ֵ�ɹ�");
		} else {
			System.out.println("��ֵʧ��");
		}

	}

	private static void subAgain(Subscribe sub, String uname) {
		String time = sub.getSubTime2();
		String nowTime = new SimpleDateFormat("yyyyMMddhh").format(new Date());
		int d1 = Integer.parseInt(time);
		int d2 = Integer.parseInt(nowTime);
		if (d1 / 100 > d2 / 100) {
			System.out.println("��ԤԼ�ı��Ϊ" + sub.getBid() + "���黹��" + (d1 - d2) / 100 + "�쵽��,�����뼰ʱȡ��");
		} else if (d1 / 100 == d2 / 100) {
			System.out.println("��ԤԼ�ı��Ϊ" + sub.getBid() + "����,���쵽��,�뼰ʱȡ��");
		} else {
			int bid = sub.getBid();
			Book book = bookBiz.queryBookByBid(bid);
			if (book.getStatus() == 1) {
				System.out.println("��ԤԼ�����ѵ���,��ȡ��");
				System.out.println("1==>ȡ��");
				System.out.println("0==>������һ��");
				System.out.println("����������ѡ��:");
				int choice = MyUtil.inputNum(0, 1);
				switch (choice) {
				case 1:
					fetchBook(bid, uname);
					break;
				case 0:
					break;
				}
			} else {
				System.out.println("��ԤԼ���鵽��,���˽���,�´��뼰ʱȡ��");
			}
		}
	}

	private static void fetchBook(int bid, String uname) {
		Book book = bookBiz.queryBookByBid(bid);
		System.out.println("��������Ҫ�������");
		int numDays = MyUtil.inputNum();
		boolean flag6 = userBiz.queryUserByMoney_NumMoneys(uid, numDays);
		if (!flag6) {
			System.out.println("���Ļ��ֲ�������" + numDays + "��,�뼰ʱ��ֵ");
			moneyOrNumDays(uname);
		} else {
			boolean flag3 = userBiz.updateMoneyByBname(uname, numDays);
			if (flag3) {
				boolean flag = bookBiz.lendBook(bid);
				if (flag) {
					boolean flag2 = recordBiz.addRecord(userBiz.queryUserByUname(uname), bid, numDays);
					if (flag2)
						System.out.println("���ɹ����һ��:" + book.getName());
				} else {

					System.out.println("����ʧ��");
					subBookNow(bid, uname);// Ԥ
				}
			}
		}

	}

	private static void login(User user, String name) {
		Subscribe sub = subBiz.querySubByUid(uid);
		if (sub != null) {
			subAgain(sub, name);
		} else {
			while (true) {
				System.out.println("******************");
				List<Book> bList = bookBiz.queryBookAll();
				if (bList == null || bList.isEmpty()) {
					System.out.println("����ϻ�û�����ͼ��,����ϵ����Ա���");
				} else {
					// subBiz.querySubByUid();
					System.out.println("1==>������Ϣ");
					System.out.println("2==>ͼ����Ϣ");
					System.out.println("3==>ԤԼ��Ϣ");

					System.out.println("0==>������һ��");
					System.out.println("����������ѡ��:");
					int choice = MyUtil.inputNum(0, 3);
					switch (choice) {
					case 1:

						leaseMainMenu(name);// ������Ϣ�˵�
						continue;
					case 2:
						bookMainMenu(name);// ͼ��˵�
						continue;
					case 3:
						querySub();// ԤԼ��Ϣ
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
	}

	private static void querySub() {
		while (true) {
			System.out.println("**********************");
			System.out.println("1==>ȫ��ԤԼ��Ϣ");
			System.out.println("2==>δ����ԤԼ��Ϣ");
			System.out.println("3==>����ԤԼ��Ϣ");
			System.out.println("4==>ԤԼ");
			System.out.println("0==>������һ��");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 4);
			switch (choice) {
			case 1:
				querySubAll();
				continue;
			case 2:
				querySubUnexpire();
				continue;
			case 3:
				querySuExpire();
				continue;
			case 4:
				subBookNow();
				continue;
			case 0:
				break;
			}
			if (MyUtil.isGoOn()) {
				break;
			}
		}
	}

	private static void subBookNow() {

		Subscribe sub = subBiz.querySubByUid(uid);
		if (sub != null) {
			System.out.println("��ԤԼ�������鼮��ûȡ,����ԤԼ��ǰ�鼮");
		} else {
			System.out.println("��������ҪԤԼ�����id");
			int bid = MyUtil.inputNum();
			if (bookBiz.queryBookByBid(bid) == null) {
				System.out.println("���鲻����");
			} else {
				boolean flag = subBiz.querySubByBid(bid);
				if (!flag) {
					System.out.println("�ѱ�ԤԼ,����ԤԼ");
				} else {
					int numDay1 = subBiz.querySubByBidReturn(bid);
					System.out.println("�˱���-" + numDay1 + "--���ع�");
					System.out.println("���Ƿ�ԤԼ");
					System.out.println("1==>ȷ��");
					System.out.println("0==>����");
					int confirm = MyUtil.inputNum(0, 1);// ȷ��
					while (true) {
						switch (confirm) {
						case 1:
							System.out.println("��������ҪԤԼ�ڼ���֮��ȡ");
							int numDay2 = MyUtil.inputNum();
							if (numDay2 > numDay1) {
								boolean flag5 = subBiz.addSub(new Subscribe(uid, bid), numDay2);
								if (flag5) {
									System.out.println("ԤԼ�ɹ�");
									System.out.println("��׼ʱȡ��");
									break;
								} else {
									System.out.println("ԤԼʧ��");
								}
							} else {
								System.out.println("��ԤԼ�ڴ���ع�֮���ʱ��,лл����");
								continue;
							}
						case 0:
							break;
						}
					}
				}
			}
		}

	}

	private static void querySuExpire() {
		List<Subscribe> sList = subBiz.querySubExpire(uid);
		if (sList == null || sList.isEmpty()) {
			System.out.println("��û�е��ڵ�ԤԼ��Ϣ");
		} else {
			MyUtil.println(sList);
		}

	}

	private static void querySubUnexpire() {
		List<Subscribe> sList = subBiz.querySubUnexpire(uid);
		if (sList == null || sList.isEmpty()) {
			System.out.println("��û��δ���ڵ�ԤԼ��Ϣ");
		} else {
			MyUtil.println(sList);
		}

	}

	private static void querySubAll() {
		List<Subscribe> sList = subBiz.querySubByUidAll(uid);
		if (sList == null || sList.isEmpty()) {
			System.out.println("��û��ԤԼ��Ϣ");
		} else {
			MyUtil.println(sList);
		}
	}

	private static void leaseMainMenu(String uname) {// ����������Ϣ�˵�
		while (true) {
			int uid = userBiz.queryUserByUname(uname);
			List<Record> rList = recordBiz.queryRecordByUid(uid);
			if (rList == null || rList.isEmpty()) {
				System.out.println("��û�����޼�¼");
			} else {

				System.out.println("*********************");
				System.out.println("1==>�鿴�����ѹ黹���޼�¼");
				System.out.println("2==>�鿴����δ�黹���޼�¼");
				System.out.println("3==>�鿴�����������޼�¼");
				System.out.println("0==>������һ��");
				System.out.println("����������ѡ��:");
				int choice = MyUtil.inputNum(0, 3);
				switch (choice) {
				case 1:
					returnedLeaseMainMenu();// �ѹ黹���޼�¼
					continue;
				case 2:
					unreturnedLeaseMainMenu(uname);// δ�黹���޼�¼
					continue;
				case 3:
					allLeaseMainMenu();// �������޼�¼
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

	private static void allLeaseMainMenu() {// �����������޼�¼
		System.out.println("********************");
		List<Record> rList = recordBiz.queryRecordByUid(uid);
		if (rList == null || rList.isEmpty()) {
			System.out.println("û�м�¼");
		} else {

			MyUtil.println(rList);
		}
	}

	private static void returnedLeaseMainMenu() {// �鿴�����ѹ黹���޼�¼
		List<Record> rList = recordBiz.queryRecordByUidReturned(uid);
		if (rList == null || rList.isEmpty())
			System.out.println("��û���ѹ黹��¼");
		else
			MyUtil.println(rList);
	}

	private static void unreturnedLeaseMainMenu(String uname) {// �鿴����δ�黹���޼�¼
		List<Record> rList = recordBiz.queryRecordByUidUnreturned(uid);
		if (rList == null || rList.isEmpty()) {
			System.out.println("��û��δ�黹��¼");
		} else {

			while (true) {
				System.out.println(rList);
				System.out.println("*********************");
				System.out.println("1==>����");
				System.out.println("2==>����");
				System.out.println("0==>������һ��");
				System.out.println("����������ѡ��:");
				int choice = MyUtil.inputNum(0, 2);
				switch (choice) {
				case 1:
					returnBook(uname);// ����
					continue;
				case 2:
					renew(uname);// ����
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

	private static void renew(String uname) {
		List<Record> rList = recordBiz.queryRecordByUidUnreturned(uid);
		if (rList == null || rList.isEmpty())
			System.out.println("��û���������,����������");
		else {
			System.out.println("��������Ҫ�������ı��");
			int bid = MyUtil.inputNum();
			Book book = bookBiz.queryBookByBid(bid);
			if (book == null) {
				System.out.println("��������ȷ�ı��");
			} else {
				boolean flag4 = subBiz.querySubByBid(bid);
				if (!flag4) {
					System.out.println("��ǰͼ���ѱ�ԤԼ");
				} else {
					System.out.println("��������Ҫ���������");
					int numDays = MyUtil.inputNum();
					boolean flag3 = userBiz.updateMoneyByBname(book.getName(), numDays);
					if (!flag3) {
						System.out.println("���Ļ��ֲ���,����ʧ��");
					} else {
						// boolean flag2 = recordBiz.addRecord(userBiz.queryUserByUname(uname), bid,
						// numDays);
						boolean flag2 = recordBiz.addRecordRenew(userBiz.queryUserByUname(uname), bid, numDays);
						if (flag2)
							System.out.println("���ɹ�������:" + book.getName());
						else
							System.out.println("����ʧ��");
					}

				}
			}
		}
	}

	private static void returnBook(String uname) {// ����
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>ͨ���鱾���ֻ���");
			System.out.println("2==>ͨ���鱾id����");
			System.out.println("0==>������һ��");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:

				returnBookByBname(uname);// ����
				continue;
			case 2:
				returnBookByBid(uname);// ����
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void bookMainMenu(String uname) {
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>�ɽ�ͼ����Ϣ");
			System.out.println("2==>����");
			System.out.println("3==>ǰ������ͼ����Ϣ");
			System.out.println("0==>������һ��");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 3);
			switch (choice) {
			case 1:
				canLendMainMenu(uname);// �ɽ�ͼ����Ϣ �±�Ҳ��һ������
				continue;
			case 2:
				returnBook(uname);// ����
				continue;
			case 3:
				queryBookByBcount(uname);
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void queryBookByBcount(String uname) {
		System.out.println("*********************");
		List<Book> bList = bookBiz.queryByBcount(5);
		if (bList == null || bList.isEmpty()) {
			System.out.println("��ʱû������");
		} else {
			System.out.println(bookBiz.queryByBcount(5));
			while (true) {
				System.out.println("*********************");
				System.out.println("1==>����");
				System.out.println("0==>������һ��");
				System.out.println("����������ѡ��:");
				int choice = MyUtil.inputNum(0, 1);
				switch (choice) {
				case 1:

					lendBook(uname);// �ɽ�ͼ����Ϣ �±�Ҳ��һ������
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

	private static void canLendMainMenu(String uname) {
		List<Book> bList = bookBiz.queryByStatus(1);
		if (bList == null || bList.isEmpty())
			System.out.println("û�пɽ�ͼ��");
		else {

			MyUtil.println(bList);
			while (true) {
				System.out.println("*********************");
				System.out.println("1==>����");
				System.out.println("0==>������һ��");
				System.out.println("����������ѡ��:");
				int choice = MyUtil.inputNum(0, 1);
				switch (choice) {
				case 1:

					lendBook(uname);// �ɽ�ͼ����Ϣ �±�Ҳ��һ������
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

	private static void lendBook(String uname) {
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>ͨ���鱾�����ֽ���");
			System.out.println("2==>ͨ���鱾��id����");
			System.out.println("0==>������һ��");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:
				lendBookByBname(uname);// �ɽ�ͼ����Ϣ �±�Ҳ��һ������
				continue;
			case 2:
				lendBookByBid(uname);// �ɽ�ͼ����Ϣ �±�Ҳ��һ������
				continue;
			case 0:
				// �˳�
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void lendBookByBname(String uname) {
		while (true) {
			System.out.println("�������鱾������");
			String bname = input.next();
			int bid = bookBiz.queryBookByBname(bname);
			if (bid == -1) {
				System.out.println("û���Ȿ��");
			} else {
				boolean flag4 = queryBookStatus(bid, uname);
				if (flag4) {
				} else {
					System.out.println("��������Ҫ�������");
					int numDays = MyUtil.inputNum();
					boolean flag6 = userBiz.queryUserByMoney_NumMoneys(uid, numDays);
					if (!flag6) {
						System.out.println("���Ļ��ֲ�������" + numDays + "��,�뼰ʱ��ֵ");
						moneyOrNumDays(uname);
					} else {
						boolean flag3 = userBiz.updateMoneyByBname(uname, numDays);
						if (!flag3) {
							System.out.println("���Ļ��ֲ���,����ʧ��");
						} else {
							boolean flag = bookBiz.lendBook(bname);
							if (flag) {
								boolean flag2 = recordBiz.addRecord(bid, bookBiz.queryBookByBname(bname), numDays);
								if (flag2)
									System.out.println("���ɹ����һ����:" + bname);
							} else {
								System.out.println("û���Ȿ��");
							}
						}
					}
				}
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void moneyOrNumDays(String uname) {
		System.out.println("*********************");
		System.out.println("1==>��ֵ");
		System.out.println("2==>���½���");
		int choice = MyUtil.inputNum();// ȷ��
		switch (choice) {
		case 1:
			reMoney();

		case 2:
			lendBookByBname(uname);
		}
	}

	private static void lendBookByBid(String uname) {
		while (true) {
			System.out.println("**********************");
			System.out.println("�������鱾��id");
			int bid = MyUtil.inputNum();
			Book book = bookBiz.queryBookByBid(bid);
			if (book == null) {
				System.out.println("û���Ȿ��");
			} else {
				boolean flag4 = subBiz.querySubByBid(bid);
				if (flag4) {
					System.out.println("��������Ҫ�������");
					int numDays = MyUtil.inputNum();
					boolean flag6 = userBiz.queryUserByMoney_NumMoneys(uid, numDays);
					if (!flag6) {
						System.out.println("���Ļ��ֲ�������" + numDays + "��,�뼰ʱ��ֵ");
						moneyOrNumDays(uname);
					} else {
						boolean flag9 = queryBookStatus(bid, uname);
						if (!flag9) {
							System.out.println("�ѱ���");
						} else {

							boolean flag3 = userBiz.updateMoneyByBname(uname, numDays);
							if (flag3) {
								boolean flag = bookBiz.lendBook(bid);
								if (flag) {
									boolean flag2 = recordBiz.addRecord(userBiz.queryUserByUname(uname), bid, numDays);
									if (flag2)
										System.out.println("���ɹ����һ��:" + book.getName());
								} else {

									System.out.println("����ʧ��");

								}
							}
						}
					}

				} else {
					System.out.println("��ǰͼ���ѱ�ԤԼ");

				}
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static boolean queryBookStatus(int bid, String uname) {
		Book book = bookBiz.queryBookByBid(bid);
		if (book == null) {
			System.out.println("û���Ȿ��");
			return false;
		} else {
			int status = book.getStatus();
			if (status == 0) {
				System.out.println(book.getName() + "�˱����ѱ�����");
				boolean flag8 = recordBiz.queryRecordByUidBidToBoolean(uid, bid);
				if (flag8) {
					System.out.println("�Ȿ�鱻��������,������ԤԼ");
				} else {
					subBookNow(bid, uname);// ԤԼ
				}
				return false;
			} else {
				return true;
			}
		}
		// TODO Auto-generated method stub

	}

	private static void subBookNow(int bid, String uname) {
		while (true) {

			System.out.println("1==>ԤԼ");
			System.out.println("0==>����");
			int choice = MyUtil.inputNum(0, 1);
			switch (choice) {
			case 1:
				int uid = userBiz.queryUserByUname(uname);
				subBook(uid, bid);// ԤԼ
				continue;
			case 0:
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void subBook(int uid, int bid) {

		Subscribe sub = subBiz.querySubByUid(uid);
		if (sub != null) {
			System.out.println("��ԤԼ�������鼮��ûȡ,����ԤԼ��ǰ�鼮");
		} else {
			boolean flag = subBiz.querySubByBid(bid);
			if (!flag) {
				System.out.println("�ѱ�ԤԼ,����ԤԼ");
			} else {
				int numDay1 = subBiz.querySubByBidReturn(bid);
				System.out.println("�˱���-" + numDay1 + "--���ع�");
				System.out.println("���Ƿ�ԤԼ");
				System.out.println("1==>ȷ��");
				System.out.println("0==>����");
				int confirm = MyUtil.inputNum();// ȷ��
				while (true) {
					switch (confirm) {
					case 1:
						System.out.println("��������ҪԤԼ�ڼ���֮��ȡ");
						int numDay2 = MyUtil.inputNum();
						if (numDay2 > numDay1) {
							boolean flag5 = subBiz.addSub(new Subscribe(uid, bid), numDay2);
							if (flag5) {
								System.out.println("ԤԼ�ɹ�");
								System.out.println("��׼ʱȡ��");
								break;
							} else {
								System.out.println("ԤԼʧ��");
							}
						} else {
							System.out.println("��ԤԼ�ڴ���ع�֮���ʱ��,лл����");
							continue;
						}
					case 0:
						break;
					}
				}
			}
		}

	}

	private static void returnBookByBname(String uname) {// ��һ����
		while (true) {
			System.out.println("**********************");
			int uid = userBiz.queryUserByUname(uname);
			List<Record> uList = recordBiz.queryRecordByBid(uid);
			if (uList.isEmpty() || uList == null) {
				System.out.println("��û�н����¼");
			} else {
				System.out.println("�������鱾������");
				String bname = input.next();
				int bid = bookBiz.queryBookByBname(bname);
				if (bid == -1) {
					System.out.println("û���Ȿ��");
				} else {
					for (Record record : uList) {
						if (record.getUid() == uid && record.getBid() == bid && record.getReturnTime().equals("δ�黹")) {
							boolean flag = bookBiz.returnBook(bname);
							boolean flag2 = recordBiz.updateRecord(uid, bid);
							if (flag && flag2)
								System.out.println("���ɹ�����һ��:" + bname);
							else
								System.out.println("����ʧ��");
						}
					}
				}
			}
			if (MyUtil.isGoOn())
				break;

		}
	}

	private static void returnBookByBid(String uname) {
		while (true) {
			int uid = userBiz.queryUserByUname(uname);
			List<Record> rList = recordBiz.queryRecordByBid(uid);
			if (rList == null || rList.isEmpty()) {
				System.out.println("��û�н����¼");
			} else {
				System.out.println("�������鱾��id");
				int bid = MyUtil.inputNum();
				Book book = bookBiz.queryBookByBid(bid);
				if (book == null) {
					System.out.println("û���Ȿ��");
				} else {
					for (Record record : rList) {
						if (record.getUid() == uid && record.getBid() == bid && record.getReturnTime().equals("δ�黹")) {
							boolean flag = bookBiz.returnBook(bid);
							if (flag) {
								boolean flag2 = recordBiz.updateRecord(userBiz.queryUserByUname(uname), bid);
								if (flag2)
									System.out.println("���ɹ�����һ��:" + book.getName());
								else
									System.out.println("���Ѿ������Ȿ����");
							} else {
								System.out.println("����ʧ��");
							}
						}
					}
				}
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

}
