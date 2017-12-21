package com.windows3.myutil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyUtil {
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
	public static int numInspect() {
		int num=input.nextInt();
		return 0;
	}

}
