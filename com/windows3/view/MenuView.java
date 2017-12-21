package com.windows3.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.windows3.biz.BookBiz;
import com.windows3.biz.UserBiz;
import com.windows3.biz.impl.BookBizImpl;
import com.windows3.biz.impl.UserBizImpl;
import com.windows3.entity.Book;
import com.windows3.entity.User;
import com.windows3.myutil.MyUtil;

public class MenuView {
	public static void mainMenu() {
		System.out.println("1==>注册");
		System.out.println("2==>登录");
		System.out.println("0==>退出");
		System.out.println("请输入您的选择:");
		UserBiz userBiz = new UserBizImpl();
		Scanner input = new Scanner(System.in);
		int choice = MyUtil.inputNum(0, 2);
		try {
			switch (choice) {
			case 1:
				// 注册
				System.out.println("请输入您的姓名");
				String name = input.next();
				// 优化选项：先判断用户名是否可用
				System.out.println("请输入您的密码");
				String password = input.next();
				boolean res = userBiz.register(new User(name, password));
				if (res) {
					System.out.println("注册成功");
					
				} else {
					System.err.println("Error");// 用户名已存在
					// 注册失败
				}
				break;
			case 2:
				// 登录
				System.out.println("请输入您的姓名");
				String name1 = input.next();
				// 优化选项：先判断用户名是否可用
				System.out.println("请输入您的密码");
				String password1 = input.next();
				User user = userBiz.login(name1, password1);
				if (user == null) {
					System.out.println("您输入的用户不存在");
				} else {
					System.out.println(user+"--登陆成功");
					System.out.println("请问您借什么书");
					String uname=input.next();
					MenuView.lendBook(uname);	
				}
				break;
			case 0:
				// 退出
				break;

			}
		} catch (InputMismatchException e) {
			System.out.println("输入类型不合法,请输入[0,2]之间的整数");
			MenuView.mainMenu();
		}

	}

	public static void bookMenu() {// 添加书本
		System.out.println("请输入您要添加的书本名字:");
		Scanner sc = new Scanner(System.in);
		String bname = sc.next();
		System.out.println("请输入您要添加的书本的数量:");
		int bnum = sc.nextInt();
		Book book = new Book(bname, bnum);
		BookBiz bookBiz = new BookBizImpl();
		bookBiz.addBook(book);

	}
	private static BookBiz bookBiz=null;
	
	private static  void lendBook(String uname) {
		
		bookBiz=new BookBizImpl();
		boolean flag=bookBiz.lendBook(uname);
		if(flag)
		   System.out.println("您成功借出一本:"+uname);
	}
	private static void returnBook(String uname) {//还一本书
		bookBiz=new BookBizImpl();
		boolean flag=bookBiz.returnBook(uname);
		if(flag)
			   System.out.println("您成功还了一本:"+uname);
	}
    private static void returnBook(int bid) {
    	bookBiz=new BookBizImpl();
    	boolean flag=bookBiz.returnBook(bid);
    	if(flag)
			   System.out.println("您成功还了一本:");
	}

}
