package com.windows3.view;

import com.windows3.myutil.MyUtil;

public class Menu {
	public static void mainMenu() {
		
		System.out.println("********欢迎使用智商检测系统********");
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>用户");
			System.out.println("2==>管理员");
			System.out.println("0==>退出");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 2);

			switch (choice) {
			case 1:
				UserMenu.mainMenu();
				continue;
			case 2:
				// 登录
				AdministratorMenu.mainMenu(); 
				continue;
			case 0:
				// 退出
				break;
			}
			System.out.println("您的智商为正数,再见!");
			break;

		}

	}
}
