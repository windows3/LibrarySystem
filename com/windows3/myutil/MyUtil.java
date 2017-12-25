package com.windows3.myutil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyUtil {
	public final static String ADMIN_NAME = "张思农";
	public final static String ADMIN_PASSWORD = "hebei208@";
	private final static String INQUIRY_NAME="请输入名字:";
	private final static String INQUIRY_PASSWORD="请输入您的密码:";
	private static Scanner input = new Scanner(System.in);
	public static int inputNum(int min,int max){
		do{
			try {
				int res = input.nextInt();
				if(res >= min && res <= max){
					return res;
				}else{
					System.err.println("数据范围不合法，请重新输入");
				}
			} catch (InputMismatchException e) {
				System.err.println("数据类型不合法，请重新输入！:");
				input.nextLine();// 清空缓冲区
			}

		}while(true);
	}
	public static String inputName() {
		System.out.println(INQUIRY_NAME);
		String name=input.next();
		return name;	
	}
	public static String inputPassword() {
		System.out.println(INQUIRY_PASSWORD);
		String password=input.next();
		return password;	
	}

	public static boolean isGoOn() {
		System.out.println("请问是否继续,n(N)不返回,其他默认返回");
		String choose = input.next();
		if (choose.equals("n") || choose.equals("N"))
			return false;
		
		return true;
	}
	public static int inputNum(){
		while(true) {
			try {
				int num=input.nextInt();
				return num;
			}catch (InputMismatchException e) {
				System.out.println("输入数据不匹配,请重新输入");
			}
		}
	}

}
