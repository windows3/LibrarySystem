package com.windows3.view;

import java.text.SimpleDateFormat;
import java.util.Date;
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

public class UserMenu {
	private static UserBiz userBiz = new UserBizImpl();
	private static BookBiz bookBiz = new BookBizImpl();;
	private static Scanner input = new Scanner(System.in);;
	private static RecordBiz recordBiz = new RecordBizImpl();
	private static SubscribeBiz subBiz = new SubscribeBizImpl();// 预约类
	private static int uid = 0;

	public static void mainMenu() {
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>注册");
			System.out.println("2==>登录");
			System.out.println("0==>退出");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 2);

			switch (choice) {
			case 1:
				registerMainMenu();
				continue;
			case 2:
				// 登录
				loginMainMenu();
				continue;
			case 0:
				// 退出
				break;
			}
			break;

		}
	}

	private static void registerMainMenu() {// 注册
		while (true) {
			System.out.println("*********************");
			// 注册
			String name = MyUtil.inputName();
			String password = MyUtil.inputPassword();
			System.out.println("请重复输入您的密码");
			String password2 = input.next();
			if (password.equals(password2)) {
			} else {
				System.out.println("您两次输入的密码不一样");
				System.out.println("请重新注册");
				continue;
			}
			if (userBiz.register(new User(name, password))) {
				System.out.println("注册成功");
				break;
			} else
				System.err.println("Error");// 为了不让人用穷举法,用户名已存在,注册失败

			if (MyUtil.isGoOn())
				break;

		}
	}

	private static void loginMainMenu() {// 登录菜单
		while (true) {
			System.out.println("*********************");
			// 登录
			String name = MyUtil.inputName();
			String password = MyUtil.inputPassword();
			User user = userBiz.login(name, password);

			if (userBiz.login(name, password) == null) {
				System.out.println("您输入的用户不存在");
			} else {

				uid = userBiz.queryUserByUname(name);
				boolean flag1 = userBiz.queryUserByUname_Status(name);// 查询是否可登陆
				System.out.println(userBiz.queryUserByUid(1));
				if (!flag1) {
					System.out.println("您的账户已被冻结,请联系管理员");
				} else {
					boolean flag = userBiz.queryUserByUname_Money(name);

					if (!flag) {
						System.out.println("您的积分已不足50,请联系管理员充值");
						reMoney();
					} else {
						System.out.println(user + "--登陆成功");
						login(user, name);

					}
				}
			}
		}
	}

	private static void reMoney() {
		while (true) {
			System.out.println("1==>充值");
			System.out.println("2==>退出程序");
			System.out.println("0==>返回上一级");
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
		System.out.println("请输入您要充值的金额");
		int money = MyUtil.inputNum();
		boolean flag = userBiz.rechargeUserByUname(uid, money);// 指定冻结用户变成1
		if (flag) {
			System.out.println("冲值成功");
		} else {
			System.out.println("充值失败");
		}

	}

	private static void login(User user, String name) {
		Subscribe sub = subBiz.querySubByUid(uid);
		if (sub != null) {
			String time = sub.getSubTime2();
			String nowTime = new SimpleDateFormat("yyyyMMddhh").format(new Date());
			int d1 = Integer.parseInt(time);
			int d2 = Integer.parseInt(nowTime);
			if (d1 > d2) {
				System.out.println("您预约的编号为" + sub.getBid() + "的书还有" + d1 / d2 + "天到馆,到点请及时取书");
			}
		} else {
			while (true) {
				System.out.println("******************");
				List<Book> bList = bookBiz.queryBookAll();
				if (bList == null || bList.isEmpty()) {
					System.out.println("书架上还没有添加图书,请联系管理员添加");
				} else {
					// subBiz.querySubByUid();
					System.out.println("1==>本人租赁信息");
					System.out.println("2==>图书");
					System.out.println("0==>返回上一级");
					System.out.println("请输入您的选择:");
					int choice = MyUtil.inputNum(0, 2);
					switch (choice) {
					case 1:
						leaseMainMenu(name);// 本人租赁信息菜单
						continue;
					case 2:
						bookMainMenu(name);// 图书菜单
						continue;
					case 0:
						// 退出
						break;
					}
				}
				if (MyUtil.isGoOn())
					break;
			}
		}
	}

	private static void leaseMainMenu(String uname) {// 本人租赁信息菜单
		while (true) {
			int uid = userBiz.queryUserByUname(uname);
			List<Record> rList = recordBiz.queryRecordByUid(uid);
			if (rList == null || rList.isEmpty()) {
				System.out.println("您没有租赁记录");
			} else {

				System.out.println("*********************");
				System.out.println("1==>查看本人已归还租赁记录");
				System.out.println("2==>查看本人未归还租赁记录");
				System.out.println("3==>查看本人所有租赁记录");
				System.out.println("0==>返回上一级");
				System.out.println("请输入您的选择:");
				int choice = MyUtil.inputNum(0, 3);
				switch (choice) {
				case 1:
					returnedLeaseMainMenu();// 已归还租赁记录
					continue;
				case 2:
					unreturnedLeaseMainMenu(uname);// 未归还租赁记录
					continue;
				case 3:
					allLeaseMainMenu();// 所有租赁记录
					continue;
				case 0:
					// 退出
					break;
				}
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void allLeaseMainMenu() {// 本人所有租赁记录
		System.out.println("********************");
		List<Record> rList = recordBiz.queryRecordByUid(uid);
		System.out.println(rList);
	}

	private static void returnedLeaseMainMenu() {// 查看本人已归还租赁记录
		List<Record> rList = recordBiz.queryRecordByUidReturned(uid);
		if (rList == null || rList.isEmpty())
			System.out.println("您没有已归还记录");
		else
			System.out.println(rList);
	}

	private static void unreturnedLeaseMainMenu(String uname) {// 查看本人未归还租赁记录
		List<Record> rList = recordBiz.queryRecordByUidUnreturned(uid);
		if (rList == null || rList.isEmpty()) {
			System.out.println("您没有未归还记录");
		} else {

			while (true) {
				System.out.println(rList);
				System.out.println("*********************");
				System.out.println("1==>还书");
				System.out.println("2==>续借");
				System.out.println("0==>返回上一级");
				System.out.println("请输入您的选择:");
				int choice = MyUtil.inputNum(0, 2);
				switch (choice) {
				case 1:
					returnBook(uname);// 还书
					continue;
				case 2:
					renew(uname);// 还书
					continue;
				case 0:
					// 退出
					break;
				}
				if (MyUtil.isGoOn())
					break;
			}
		}
	}

	private static void renew(String uname) {
		System.out.println("请输入您要续借的书的编号");
		int bid = MyUtil.inputNum();
		Book book = bookBiz.queryBookByBid(bid);
		if (book == null) {
			System.out.println("请输入正确的编号");
		} else {
			boolean flag4 = subBiz.querySubByBid(bid);
			if (!flag4) {
				System.out.println("当前图书已被预约");
			} else {
				System.out.println("请输入您要续借的天数");
				int numDays = MyUtil.inputNum();
				boolean flag3 = userBiz.updateMoneyByBname(book.getName(), numDays);
				if (!flag3) {
					System.out.println("您的积分不足,续借失败");
				} else {
					// boolean flag2 = recordBiz.addRecord(userBiz.queryUserByUname(uname), bid,
					// numDays);
					boolean flag2 = recordBiz.addRecordRenew(userBiz.queryUserByUname(uname), bid, numDays);
					if (flag2)
						System.out.println("您成功续借了:" + book.getName());
					else
						System.out.println("续借失败");
				}

			}
		}
	}

	private static void returnBook(String uname) {// 还书
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>通过书本名字还书");
			System.out.println("2==>通过书本id还书");
			System.out.println("0==>返回上一级");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:

				returnBookByBname(uname);// 还书
				continue;
			case 2:
				returnBookByBid(uname);// 还书
				continue;
			case 0:
				// 退出
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void bookMainMenu(String uname) {
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>可借图书信息");
			System.out.println("2==>还书");
			System.out.println("3==>前五热门图书信息");
			System.out.println("0==>返回上一级");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 3);
			switch (choice) {
			case 1:
				canLendMainMenu(uname);// 可借图书信息 下边也有一个租书
				continue;
			case 2:
				returnBook(uname);// 还书
				continue;
			case 3:
				queryBookByBcount(uname);
				continue;
			case 0:
				// 退出
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
			System.out.println("暂时没有排行");
		} else {
			System.out.println(bookBiz.queryByBcount(5));
			while (true) {
				System.out.println("*********************");
				System.out.println("1==>借书");
				System.out.println("0==>返回上一级");
				System.out.println("请输入您的选择:");
				int choice = MyUtil.inputNum(0, 1);
				switch (choice) {
				case 1:

					lendBook(uname);// 可借图书信息 下边也有一个租书
					continue;
				case 0:
					// 退出
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
			System.out.println("没有可借图书");
		else {
			System.out.println(bList);
			while (true) {
				System.out.println("*********************");
				System.out.println("1==>借书");
				System.out.println("0==>返回上一级");
				System.out.println("请输入您的选择:");
				int choice = MyUtil.inputNum(0, 1);
				switch (choice) {
				case 1:

					lendBook(uname);// 可借图书信息 下边也有一个租书
					continue;
				case 0:
					// 退出
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
			System.out.println("1==>通过书本的id借书");
			System.out.println("2==>通过书本的名字借书");
			System.out.println("0==>返回上一级");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:
				lendBookByBname(uname);// 可借图书信息 下边也有一个租书
				continue;
			case 2:
				lendBookByBid(uname);// 可借图书信息 下边也有一个租书
				continue;
			case 0:
				// 退出
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void lendBookByBname(String uname) {
		while (true) {
			System.out.println("请输入书本的名字");
			String bname = input.next();
			int bid = bookBiz.queryBookByBname(bname);
			if (bid == -1) {
				System.out.println("没有这本书");
			} else {
				System.out.println("请输入您要借的天数");
				int numDays = MyUtil.inputNum();
				boolean flag6 = userBiz.queryUserByMoney_NumMoneys(uid, numDays);
				if (!flag6) {
					System.out.println("您的积分不足以租" + numDays + "天,请及时充值");
					moneyOrNumDays(uname);
				} else {

					boolean flag3 = userBiz.updateMoneyByBname(bname, numDays);
					if (!flag3) {
						System.out.println("您的积分不足,借书失败");
					} else {
						boolean flag = bookBiz.lendBook(bname);
						if (flag) {
							boolean flag2 = recordBiz.addRecord(bid, bookBiz.queryBookByBname(bname), numDays);
							if (flag2)
								System.out.println("您成功借出一本书:" + bname);
						} else {
							System.out.println("没有这本书");
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
		System.out.println("1==>充值");
		System.out.println("2==>重新借书");
		int choice = MyUtil.inputNum();// 确定
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
			System.out.println("请输入书本的id");
			int bid = MyUtil.inputNum();
			Book book = bookBiz.queryBookByBid(bid);
			if (book == null) {
				System.out.println("没有这本书");
			} else {
				boolean flag4 = subBiz.querySubByBid(bid);
				if (!flag4) {
					System.out.println("当前图书已被预约");
				} else {

					System.out.println("请输入您要借的天数");
					int numDays = MyUtil.inputNum();
					boolean flag3 = userBiz.updateMoneyByBname(book.getName(), numDays);
					if (!flag3) {
						System.out.println("您的积分不足,借书失败");
					} else {
						boolean flag = bookBiz.lendBook(bid);
						if (flag) {
							boolean flag2 = recordBiz.addRecord(userBiz.queryUserByUname(uname), bid, numDays);
							if (flag2)
								System.out.println("您成功借出一本:" + bookBiz.queryBookByBid(bid).getName());
						} else {

							System.out.println("借书失败");

							while (true) {
								System.out.println("1==>预约");
								System.out.println("0==>返回");
								int choice = MyUtil.inputNum(0, 1);
								switch (choice) {
								case 1:
									int uid = userBiz.queryUserByUname(uname);
									subBook(uid, bid);// 预约
									continue;
								case 0:
									break;
								}
								if (MyUtil.isGoOn())
									break;
							}
						}
					}
				}
				if (MyUtil.isGoOn())
					break;
			}
		}
	}

	private static void subBook(int uid, int bid) {
		Subscribe sub = subBiz.querySubByUid(uid);
		if (sub != null) {
			System.out.println("您预约的其他书籍还没取,不能预约当前书籍");
		} else {
			int numDay1 = subBiz.querySubByBidReturn(bid);
			System.out.println("此本书" + numDay1 + "天后回馆");
			System.out.println("您是否预约");
			int confirm = MyUtil.inputNum();// 确定
			System.out.println("1==>确定");
			System.out.println("0==>返回");
			while (true) {
				switch (confirm) {
				case 1:
					System.out.println("请输入您要预约在几天之后取");
					int numDay2 = MyUtil.inputNum();
					if (numDay2 > numDay1) {
						boolean flag5 = subBiz.addSub(new Subscribe(uid, bid), numDay2);
						if (flag5) {
							System.out.println("预约成功");
						} else {
							System.out.println("预约失败");
						}
					} else {
						System.out.println("请预约在此书回馆之后的时间,谢谢合作");
						continue;
					}
					break;
				case 0:
					break;
				}
				if (MyUtil.isGoOn())
					break;
			}
		}

	}

	private static void returnBookByBname(String uname) {// 还一本书
		while (true) {
			System.out.println("**********************");
			int uid = userBiz.queryUserByUname(uname);
			List<Record> uList = recordBiz.queryRecordByBid(uid);
			if (uList.isEmpty() || uList == null) {
				System.out.println("您没有借书记录");
			} else {
				System.out.println("请输入书本的名字");
				String bname = input.next();
				int bid = bookBiz.queryBookByBname(bname);
				if (bid == -1) {
					System.out.println("没有这本书");
				} else {
					for (Record record : uList) {
						if (record.getUid() == uid && record.getBid() == bid && record.getReturnTime().equals("未归还")) {
							boolean flag = bookBiz.returnBook(bname);
							boolean flag2 = recordBiz.updateRecord(uid, bid);
							if (flag && flag2)
								System.out.println("您成功还了一本:" + bname);
							else
								System.out.println("还书失败");
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
			if (rList.isEmpty() || rList.isEmpty()) {
				System.out.println("您没有借书记录");
			} else {
				System.out.println("请输入书本的id");
				int bid = MyUtil.inputNum();
				Book book = bookBiz.queryBookByBid(bid);
				if (book == null) {
					System.out.println("没有这本书");
				} else {
					for (Record record : rList) {
						if (record.getUid() == uid && record.getBid() == bid && record.getReturnTime().equals("未归还")) {
							boolean flag = bookBiz.returnBook(bid);
							if (flag) {
								boolean flag2 = recordBiz.updateRecord(userBiz.queryUserByUname(uname), bid);
								if (flag2)
									System.out.println("您成功还了一本:");
							} else {
								System.out.println("还书失败");
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
