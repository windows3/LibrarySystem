package com.windows3.view;

import com.windows3.myutil.MyUtil;

public class Menu {
	public static void mainMenu() {
		
		System.out.println("********��ӭʹ�����̼��ϵͳ********");
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>�û�");
			System.out.println("2==>����Ա");
			System.out.println("0==>�˳�");
			System.out.println("����������ѡ��:");
			int choice = MyUtil.inputNum(0, 2);

			switch (choice) {
			case 1:
				UserMenu.mainMenu();
				continue;
			case 2:
				// ��¼
				AdministratorMenu.mainMenu(); 
				continue;
			case 0:
				// �˳�
				break;
			}
			System.out.println("��������Ϊ����,�ټ�!");
			break;

		}

	}
}
