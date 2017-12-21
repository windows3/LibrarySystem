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
			System.out.println("1==>ע��");
			System.out.println("2==>��¼");
			System.out.println("0==>�˳�");
			System.out.println("����������ѡ��:");
			userBiz = new UserBizImpl();
			int choice = MyUtil.inputNum(0, 2);

			switch (choice) {
			case 1:
				registerMainMenu();
				break;
			case 2:
				// ��¼
				loginMainMenu();
				System.out.println("��������ʲô��");
				String uname = input.next();
				UserMenu.lendBook(uname);
				break;
			case 0:
				// �˳�
				break;
			}

		}
	}

	private static void registerMainMenu() {//ע��
		while (true) {
			// ע��
			String name = MyUtil.inputName();
			String password = MyUtil.inputPassword();
			if (userBiz.register(new User(name, password))) {
				System.out.println("ע��ɹ�");

				break;
			} else {
				System.err.println("Error");// �û����Ѵ���,ע��ʧ��
			}
		}
	}

	private static void loginMainMenu() {//��¼
		while (true) {
			// ��¼
			String name = MyUtil.inputName();
			String password = MyUtil.inputPassword();
			User user = userBiz.login(name, password);
			if (userBiz.login(name, password) == null) {
				System.out.println("��������û�������");
			} else {
				System.out.println(user + "--��½�ɹ�");
				System.out.println("1==>����������Ϣ");
				System.out.println("2==>ͼ��");
				System.out.println("0==>������һ��");
				System.out.println("����������ѡ��:");
				int choice = MyUtil.inputNum(0, 2);
				switch (choice) {
				case 1:
					leaseMainMenu();// ����������Ϣ�˵�
					break;
				case 2:
					bookMainMenu();// ͼ��˵�
					break;
				case 0:
					// �˳�
					break;
				}
				break;

			}
		}
	}

	private static void leaseMainMenu(){//����������Ϣ�˵�

		System.out.println("1==>�鿴�����ѹ黹���޼�¼");
		System.out.println("2==>�鿴����δ�黹���޼�¼");
		System.out.println("3==>�鿴�����������޼�¼");
		System.out.println("0==>������һ��");
		System.out.println("����������ѡ��:");
		int choice = MyUtil.inputNum(0, 2);
		switch (choice) {
		case 1:
//			returnedLeaseMainMenu();//�ѹ黹���޼�¼
			break;
		case 2:
//			unreturnedLeaseMainMenu();// δ�黹���޼�¼
			break;
		case 3:
//			allLeaseMainMenu();//�������޼�¼
			break;
		case 0:
			// �˳�
			break;
		}
		
		
	}

	private static void bookMainMenu() {
		System.out.println("1==>�ɽ�ͼ����Ϣ");
		System.out.println("2==>ȫ��ͼ��");
		System.out.println("3==>����");
		System.out.println("0==>������һ��");
		System.out.println("����������ѡ��:");
		int choice = MyUtil.inputNum(0, 3);
		switch (choice) {
		case 1:
//			canLendMainMenu();//�ɽ�ͼ����Ϣ  �±�Ҳ��һ������
			break;
		case 2:
//			allBookMainMenu();// ȫ��ͼ�� �±߻���һ������
			break;
		case 3:
//			returnMainMenu();//����
			break;
		case 0:
			// �˳�
			break;
		}
	}
	public static void bookMenu() {// ����鱾
		System.out.println("��������Ҫ��ӵ��鱾����:");
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
			System.out.println("���ɹ����һ��:" + uname);
	}

	private static void returnBook(String uname) {// ��һ����
		bookBiz = new BookBizImpl();
		boolean flag = bookBiz.returnBook(uname);
		if (flag)
			System.out.println("���ɹ�����һ��:" + uname);
	}

	private static void returnBook(int bid) {
		bookBiz = new BookBizImpl();
		boolean flag = bookBiz.returnBook(bid);
		if (flag)
			System.out.println("���ɹ�����һ��:");
	}

}
