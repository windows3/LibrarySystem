package com.windows3.myutil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyUtil {
	public final static String ADMIN_NAME = "��˼ũ";
	public final static String ADMIN_PASSWORD = "hebei208@";
	private final static String INQUIRY_NAME="����������:";
	private final static String INQUIRY_PASSWORD="��������������:";
	private static Scanner input = new Scanner(System.in);
	public static int inputNum(int min,int max){
		do{
			try {
				int res = input.nextInt();
				if(res >= min && res <= max){
					return res;
				}else{
					System.err.println("���ݷ�Χ���Ϸ�������������");
				}
			} catch (InputMismatchException e) {
				System.err.println("�������Ͳ��Ϸ������������룡:");
				input.nextLine();// ��ջ�����
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
		System.out.println("�����Ƿ����,n(N)������,����Ĭ�Ϸ���");
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
				System.out.println("�������ݲ�ƥ��,����������");
			}
		}
	}

}
