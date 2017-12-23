package com.windows3.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.windows3.biz.BookBiz;
import com.windows3.biz.RecordBiz;
import com.windows3.biz.UserBiz;
import com.windows3.biz.impl.BookBizImpl;
import com.windows3.biz.impl.RecordBizImpl;
import com.windows3.biz.impl.UserBizImpl;
import com.windows3.entity.Record;
import com.windows3.entity.User;
import com.windows3.myutil.MyUtil;

public class UserMenu {
	private static UserBiz userBiz = new UserBizImpl();
	private static BookBiz bookBiz = new BookBizImpl();;
	private static Scanner input = new Scanner(System.in);;
	private static RecordBiz recordBiz = new RecordBizImpl();
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
				System.out.println("请从新登陆");
				continue;
			}
			if (userBiz.register(new User(name, password)))
				System.out.println("注册成功");
			else
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
				System.out.println(user + "--登陆成功");
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
				if (MyUtil.isGoOn())
					break;

			}
		}
	}

	private static void leaseMainMenu(String uname) {// 本人租赁信息菜单
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>查看本人已归还租赁记录");
			System.out.println("2==>查看本人未归还租赁记录");
			System.out.println("3==>查看本人所有租赁记录");
			System.out.println("0==>返回上一级");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 2);
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
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void allLeaseMainMenu() {// 本人所有租赁记录
		System.out.println("********************");
		System.out.println(recordBiz.queryRecordByUid(uid));
	}

	private static void returnedLeaseMainMenu() {// 查看本人已归还租赁记录
		System.out.println(recordBiz.queryRecordByUidReturned(uid));

	}

	private static void unreturnedLeaseMainMenu(String uname) {// 查看本人未归还租赁记录
		System.out.println(recordBiz.queryRecordByUidUnreturned(uid));
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>还书");
			System.out.println("0==>返回上一级");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 1);
			switch (choice) {
			case 1:
				returnBook(uname);// 还书
				continue;
			case 0:
				// 退出
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void returnBook(String uname) {// 还书
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>通过书本id还书");
			System.out.println("2==>通过书本名字还书");
			System.out.println("0==>返回上一级");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 1);
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
			System.out.println("2==>全部图书");
			System.out.println("3==>还书");
			System.out.println("4==>前五热门图书信息");
			System.out.println("0==>返回上一级");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 3);
			switch (choice) {
			case 1:
				canLendMainMenu(uname);// 可借图书信息 下边也有一个租书
				continue;
			case 2:
				// allBookMainMenu();// 全部图书 下边还有一个租书
				continue;
			case 3:
				// returnMainMenu();//还书
				continue;
			case 4:
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

	private static void canLendMainMenu(String uname) {
		System.out.println(bookBiz.queryByStatus(1));
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

	private static void lendBook(String uname) {
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>通过书本的id借书");
			System.out.println("2==>通过书本的名字借书");
			System.out.println("0==>返回上一级");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 1);
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
			boolean flag = bookBiz.lendBook(bname);
			if (flag) {
				boolean flag2 = recordBiz.addRecord(userBiz.queryUserByUname(uname), bookBiz.queryBookByBname(bname));
				if (flag2)
					System.out.println("您成功借出一本书:" + bname);
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void lendBookByBid(String uname) {
		while (true) {
			System.out.println("**********************");
			System.out.println("请输入书本的id");
			try {
				int bid = input.nextInt();
				boolean flag = bookBiz.lendBook(bid);
				boolean flag2 = recordBiz.addRecord(userBiz.queryUserByUname(uname), bid);
				if (flag && flag2)
					System.out.println("您成功借出一本:" + bookBiz.queryBookByBid(bid).getName());
			} catch (InputMismatchException e) {
				System.out.println("输入数据不匹配");
				continue;
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void returnBookByBname(String uname) {// 还一本书
		while (true) {
			System.out.println("**********************");
			System.out.println("请输入书本的名字");
			String bname = input.next();
			List<Record> uList = recordBiz.queryRecordByBid(userBiz.queryUserByUname(uname));
			for (Record record : uList) {
				if (record.getUid() == userBiz.queryUserByUname(uname)
						&& record.getBid() == bookBiz.queryBookByBname(bname)&&record.getReturnTime().equals("未归还")) {
					boolean flag = bookBiz.returnBook(bname);
					if (flag)
						System.out.println("您成功还了一本:" + bname);
					else
						System.out.println("还书失败");
				}
			}
			if (MyUtil.isGoOn())
				break;

		}
	}

	private static void returnBookByBid(String uname) {
		while (true) {
			System.out.println("请输入书本的id");
			int bid = MyUtil.inputNum();
			List<Record> uList = recordBiz.queryRecordByBid(userBiz.queryUserByUname(uname));
			for (Record record : uList) {
				if (record.getUid() == userBiz.queryUserByUname(uname)
						&& record.getBid() == bid&&record.getReturnTime().equals("未归还")) {
					boolean flag = bookBiz.returnBook(bid);
					if (flag)
						System.out.println("您成功还了一本:");
					else {
						System.out.println("还书失败");
					}
				}
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

}
