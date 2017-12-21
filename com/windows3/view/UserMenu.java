package com.windows3.view;

import java.util.Scanner;

import com.windows3.biz.BookBiz;
import com.windows3.biz.UserBiz;
import com.windows3.biz.impl.BookBizImpl;
import com.windows3.biz.impl.UserBizImpl;
import com.windows3.entity.Book;
import com.windows3.entity.User;
import com.windows3.myutil.MyUtil;

public class UserMenu {
	private static UserBiz userBiz = null;
	private static BookBiz bookBiz = null;
	private static Scanner input = new Scanner(System.in);;

	public static void mainMenu() {
		while (true) {
			System.out.println("1==>注册");
			System.out.println("2==>登录");
			System.out.println("0==>退出");
			System.out.println("请输入您的选择:");
			userBiz = new UserBizImpl();
			int choice = MyUtil.inputNum(0, 2);

			switch (choice) {
			case 1:
				registerMainMenu();
				break;
			case 2:
				// 登录
				loginMainMenu();
				System.out.println("请问您借什么书");
				String uname = input.next();
				UserMenu.lendBook(uname);
				break;
			case 0:
				// 退出
				break;
			}

		}
	}

	private static void registerMainMenu() {//注册
		while (true) {
			// 注册
			String name = MyUtil.inputName();
			String password = MyUtil.inputPassword();
			if (userBiz.register(new User(name, password))) {
				System.out.println("注册成功");

				break;
			} else {
				System.err.println("Error");// 用户名已存在,注册失败
			}
		}
	}

	private static void loginMainMenu() {//登录
		while (true) {
			// 登录
			String name = MyUtil.inputName();
			String password = MyUtil.inputPassword();
			User user = userBiz.login(name, password);
			if (userBiz.login(name, password) == null) {
				System.out.println("您输入的用户不存在");
			} else {
				System.out.println(user + "--登陆成功");
				System.out.println("1==>本人租赁信息");
				System.out.println("2==>图书");
				System.out.println("0==>返回上一级");
				System.out.println("请输入您的选择:");
				int choice = MyUtil.inputNum(0, 2);
				switch (choice) {
				case 1:
					leaseMainMenu();// 本人租赁信息菜单
					break;
				case 2:
					bookMainMenu();// 图书菜单
					break;
				case 0:
					// 退出
					break;
				}
				break;

			}
		}
	}

	private static void leaseMainMenu(){//本人租赁信息菜单

		System.out.println("1==>查看本人已归还租赁记录");
		System.out.println("2==>查看本人未归还租赁记录");
		System.out.println("3==>查看本人所有租赁记录");
		System.out.println("0==>返回上一级");
		System.out.println("请输入您的选择:");
		int choice = MyUtil.inputNum(0, 2);
		switch (choice) {
		case 1:
//			returnedLeaseMainMenu();//已归还租赁记录
			break;
		case 2:
//			unreturnedLeaseMainMenu();// 未归还租赁记录
			break;
		case 3:
//			allLeaseMainMenu();//所有租赁记录
			break;
		case 0:
			// 退出
			break;
		}
		
		
	}

	private static void bookMainMenu() {
		System.out.println("1==>可借图书信息");
		System.out.println("2==>全部图书");
		System.out.println("3==>还书");
		System.out.println("0==>返回上一级");
		System.out.println("请输入您的选择:");
		int choice = MyUtil.inputNum(0, 3);
		switch (choice) {
		case 1:
//			canLendMainMenu();//可借图书信息  下边也有一个租书
			break;
		case 2:
//			allBookMainMenu();// 全部图书 下边还有一个租书
			break;
		case 3:
//			returnMainMenu();//还书
			break;
		case 0:
			// 退出
			break;
		}
	}
	public static void bookMenu() {// 添加书本
		System.out.println("请输入您要添加的书本名字:");
		Scanner sc = new Scanner(System.in);
		String bname = sc.next();
		Book book = new Book(bname);
		BookBiz bookBiz = new BookBizImpl();
		bookBiz.addBook(book);

	}


	private static void lendBook(String uname) {

		bookBiz = new BookBizImpl();
		boolean flag = bookBiz.lendBook(uname);
		if (flag)
			System.out.println("您成功借出一本:" + uname);
	}

	private static void returnBook(String uname) {// 还一本书
		bookBiz = new BookBizImpl();
		boolean flag = bookBiz.returnBook(uname);
		if (flag)
			System.out.println("您成功还了一本:" + uname);
	}

	private static void returnBook(int bid) {
		bookBiz = new BookBizImpl();
		boolean flag = bookBiz.returnBook(bid);
		if (flag)
			System.out.println("您成功还了一本:");
	}

}
