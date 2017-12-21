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
		System.out.println("1==>ע��");
		System.out.println("2==>��¼");
		System.out.println("0==>�˳�");
		System.out.println("����������ѡ��:");
		UserBiz userBiz = new UserBizImpl();
		Scanner input = new Scanner(System.in);
		int choice = MyUtil.inputNum(0, 2);
		try {
			switch (choice) {
			case 1:
				// ע��
				System.out.println("��������������");
				String name = input.next();
				// �Ż�ѡ����ж��û����Ƿ����
				System.out.println("��������������");
				String password = input.next();
				boolean res = userBiz.register(new User(name, password));
				if (res) {
					System.out.println("ע��ɹ�");
					
				} else {
					System.err.println("Error");// �û����Ѵ���
					// ע��ʧ��
				}
				break;
			case 2:
				// ��¼
				System.out.println("��������������");
				String name1 = input.next();
				// �Ż�ѡ����ж��û����Ƿ����
				System.out.println("��������������");
				String password1 = input.next();
				User user = userBiz.login(name1, password1);
				if (user == null) {
					System.out.println("��������û�������");
				} else {
					System.out.println(user+"--��½�ɹ�");
					System.out.println("��������ʲô��");
					String uname=input.next();
					MenuView.lendBook(uname);	
				}
				break;
			case 0:
				// �˳�
				break;

			}
		} catch (InputMismatchException e) {
			System.out.println("�������Ͳ��Ϸ�,������[0,2]֮�������");
			MenuView.mainMenu();
		}

	}

	public static void bookMenu() {// ����鱾
		System.out.println("��������Ҫ��ӵ��鱾����:");
		Scanner sc = new Scanner(System.in);
		String bname = sc.next();
		System.out.println("��������Ҫ��ӵ��鱾������:");
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
		   System.out.println("���ɹ����һ��:"+uname);
	}
	private static void returnBook(String uname) {//��һ����
		bookBiz=new BookBizImpl();
		boolean flag=bookBiz.returnBook(uname);
		if(flag)
			   System.out.println("���ɹ�����һ��:"+uname);
	}
    private static void returnBook(int bid) {
    	bookBiz=new BookBizImpl();
    	boolean flag=bookBiz.returnBook(bid);
    	if(flag)
			   System.out.println("���ɹ�����һ��:");
	}

}
