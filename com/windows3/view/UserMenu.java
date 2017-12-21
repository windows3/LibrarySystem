package com.windows3.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.windows3.biz.BookBiz;
import com.windows3.biz.RecordBiz;
import com.windows3.biz.UserBiz;
import com.windows3.biz.impl.BookBizImpl;
import com.windows3.biz.impl.RecordBizImpl;
import com.windows3.biz.impl.UserBizImpl;
import com.windows3.entity.Book;
import com.windows3.entity.User;
import com.windows3.myutil.MyUtil;

public class UserMenu {
	private static UserBiz userBiz = new UserBizImpl();
	private static BookBiz bookBiz = new BookBizImpl();;
	private static Scanner input = new Scanner(System.in);;
	private static RecordBiz recordBiz = new RecordBizImpl();;

	public static void mainMenu() {
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>ע��");
			System.out.println("2==>��¼");
			System.out.println("0==>�˳�");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 2);

			switch (choice) {
			case 1:
				registerMainMenu();
				continue;
			case 2:
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

	private static void registerMainMenu() {// ע��
		while (true) {
			System.out.println("*********************");
			// ע��
			String name = MyUtil.inputName();
			String password = MyUtil.inputPassword();
			if (userBiz.register(new User(name, password))) {
				System.out.println("ע��ɹ�");

			} else {
				System.err.println("Error");// �û����Ѵ���,ע��ʧ��
			}
			if (MyUtil.isGoOn())
				break;

		}
	}

	private static void loginMainMenu() {// ��¼
		while (true) {
			System.out.println("*********************");
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
					continue;
				case 2:
					bookMainMenu(name);// ͼ��˵�
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

	private static void leaseMainMenu() {// ����������Ϣ�˵�
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>�鿴�����ѹ黹���޼�¼");
			System.out.println("2==>�鿴����δ�黹���޼�¼");
			System.out.println("3==>�鿴�����������޼�¼");
			System.out.println("0==>������һ��");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:
				// returnedLeaseMainMenu();//�ѹ黹���޼�¼
				continue;
			case 2:
				// unreturnedLeaseMainMenu();// δ�黹���޼�¼
				continue;
			case 3:
				// allLeaseMainMenu();//�������޼�¼
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
			System.out.println("2==>ȫ��ͼ��");
			System.out.println("3==>����");
			System.out.println("0==>������һ��");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 3);
			switch (choice) {
			case 1:
				canLendMainMenu(uname);// �ɽ�ͼ����Ϣ �±�Ҳ��һ������
				continue;
			case 2:
				// allBookMainMenu();// ȫ��ͼ�� �±߻���һ������
				continue;
			case 3:
				// returnMainMenu();//����
				continue;
			case 0:
				// �˳�
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

	private static void lendBook(String uname) {
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>ͨ���鱾��id����");
			System.out.println("2==>ͨ���鱾�����ֽ���");
			System.out.println("0==>������һ��");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 1);
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
			boolean flag = bookBiz.lendBook(bname);
			boolean flag2 =recordBiz.addRecord(userBiz.queryUserByUname(uname), bookBiz.queryBookByBname(bname));
			if (flag&&flag2)
				System.out.println("���ɹ����һ����:" + bname);

			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void lendBookByBid(String uname) {
		while (true) {
			System.out.println("�������鱾��id");
			try {
				int bid = input.nextInt();
				boolean flag = bookBiz.lendBook(bid);
				boolean flag2 = recordBiz.addRecord(userBiz.queryUserByUname(uname), bid);
				if (flag && flag2)
					System.out.println("���ɹ����һ��:" + bookBiz.queryBookByBid(bid).getName());
			} catch (InputMismatchException e) {
				System.out.println("�������ݲ�ƥ��");
				continue;
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	public static void bookMenu() {// ����鱾
		System.out.println("��������Ҫ��ӵ��鱾����:");
		Scanner sc = new Scanner(System.in);
		String bname = sc.next();
		Book book = new Book(bname);
		BookBiz bookBiz = new BookBizImpl();
		if (bookBiz.addBook(book))
			System.out.println("�ɹ�");

	}

	private static void returnBookByBname(String bname) {// ��һ����
		bookBiz = new BookBizImpl();
		boolean flag = bookBiz.returnBook(bname);
		if (flag)
			System.out.println("���ɹ�����һ��:" + bname);
	}

	private static void returnBookByBid(int bid) {
		bookBiz = new BookBizImpl();
		boolean flag = bookBiz.returnBook(bid);
		if (flag)
			System.out.println("���ɹ�����һ��:");
	}

}
