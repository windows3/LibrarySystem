package com.windows3.view;

import java.util.List;
import java.util.Scanner;
import com.windows3.biz.BookBiz;
import com.windows3.biz.RecordBiz;
import com.windows3.biz.SubscribeBiz;
import com.windows3.biz.UserBiz;
import com.windows3.biz.impl.BookBizImpl;
import com.windows3.biz.impl.RecordBizImpl;
import com.windows3.biz.impl.SubscribeBizImpl;
import com.windows3.biz.impl.UserBizImpl;
import com.windows3.entity.Book;
import com.windows3.entity.Record;
import com.windows3.entity.Subscribe;
import com.windows3.entity.User;
import com.windows3.myutil.MyUtil;

public class AdministratorMenu {
	private static UserBiz userBiz = new UserBizImpl();
	private static BookBiz bookBiz = new BookBizImpl();;
	private static Scanner input = new Scanner(System.in);;
	private static RecordBiz recordBiz = new RecordBizImpl();
	private static SubscribeBiz subBiz = new SubscribeBizImpl();

	public static void mainMenu() {
		System.out.println("管理员账户:"+MyUtil.ADMIN_NAME);
		System.out.println("管理员密码:"+MyUtil.ADMIN_PASSWORD);
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>登录");
			System.out.println("0==>退出");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 1);

			switch (choice) {
			case 1:
				// 登录
				loginMainMenu();
				continue;
			case 0:
				// 退出
				break;
			}
			System.out.println("感谢您的使用,再见!");
			break;

		}
	}

	private static void loginMainMenu() {// 登录菜单
		while (true) {
			System.out.println("*********************");
			// 登录
			String name = MyUtil.inputName();
			String password = MyUtil.inputPassword();
			if (!(name.equals(MyUtil.ADMIN_NAME) || !password.equals(MyUtil.ADMIN_PASSWORD))) {
				System.out.println("登录失败");
			} else {
				loginSucceed();
				break;
			}
		}
	}

	private static void loginSucceed() {
		System.out.println("******欢迎管理员张思农登陆成功*****");
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>管理图书");
			System.out.println("2==>管理用户");
			System.out.println("3==>管理借书记录");
			System.out.println("4==>管理预约记录");
			System.out.println("0==>返回上一级");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 4);
			switch (choice) {
			case 1:
				manageBook();
				continue;
			case 2:
				manageUser();// 管理用户
				continue;
			case 3:
				manageRecord();// 管理借书记录
				continue;
			case 4:
				manageSub();// 管理预约记录
				continue;
			case 0:
				// 退出
				break;
			}
			if (MyUtil.isGoOn())
				break;

		}

	}

	private static void manageSub() {
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>查询全部预约记录");
			System.out.println("2==>查询未到期预约记录");
			System.out.println("3==>查询到期预约记录");
			System.out.println("0==>返回上一级");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 3);
			switch (choice) {
			case 1:

				querySubAll();
				continue;
			case 2:
				querySubUnexpire();// 未到期
				continue;
			case 3:
				querySubExpire();// 到期
				continue;
			case 0:
				// 退出
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void querySubExpire() {
		List<Subscribe> sList = subBiz.querySubExpire();
		if (sList == null || sList.isEmpty()) {
			System.out.println("没有到期记录预约记录");
		} else {
			System.out.println(sList);
		}

	}

	private static void querySubUnexpire() {
		List<Subscribe> sList = subBiz.querySubUnexpire();
		if (sList == null || sList.isEmpty()) {
			System.out.println("没有未到期记录预约记录");
		} else {
			System.out.println(sList);
		}
	}

	private static void querySubAll() {
		List<Subscribe> sList = subBiz.querySubAll();
		if (sList == null || sList.isEmpty()) {
			System.out.println("没有预约记录");
		} else {
			System.out.println(sList);
		}
	}

	private static void manageRecord() {
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>查询记录");
			System.out.println("0==>返回上一级");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 1);
			switch (choice) {
			case 1:
				queryRecord();
				continue;
			case 0:
				// 退出
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void queryRecord() {
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>通过uid查询记录");
			System.out.println("2==>通过bid查询记录");
			System.out.println("3==>通过uid和bid查询记录");
			System.out.println("0==>返回上一级");
			System.out.println("请输入您的选择:");
			int choice = MyUtil.inputNum(0, 3);
			switch (choice) {
			case 1:
				System.out.println("请输入用户的id");
				int uid = MyUtil.inputNum();
				queryRecordByUid(uid);
				continue;
			case 2:
				System.out.println("请输入图书的的id");
				int bid = MyUtil.inputNum();
				queryRecordByBid(bid);
				continue;
			case 3:
				queryRecordByUid_Bid();
				continue;
			case 0:
				// 退出
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void queryRecordByUid_Bid() {
		while (true) {
			System.out.println("********************");
			System.out.println("请输入用户的id");
			int uid = MyUtil.inputNum();
			if (userBiz.queryUserByUid(uid) == null) {
				System.out.println("查无此人");
				break;
			} else {
				System.out.println("请输入图书的的id");
				int bid = MyUtil.inputNum();
				if (bookBiz.queryBookByBid(bid) == null) {
					System.out.println("查无此书");
				} else {
					List<Record> rList = recordBiz.queryRecordByUid_Bid(uid, bid);
					if (rList == null)
						System.out.println("此用户没有未还书记录");
					else
						System.out.println(rList);
					if (MyUtil.isGoOn())
						break;
				}
			}
		}

	}

	private static void queryRecordByBid(int bid) {
		System.out.println("*********************");
		if (bookBiz.queryBookByBid(bid) == null) {
			System.out.println("没有此ID的图书");
		} else {
			List<Record> rList = recordBiz.queryRecordByBid(bid);
			if (rList == null)
				System.out.println("此ID图书没有被借阅过");
			else
				System.out.println(rList);
		}
	}

	private static void manageUser() {// 管理用户
		while (true) {
			System.out.println("*********************");
			List<User> uList=userBiz.queryUserAll();
			if(uList==null||uList.isEmpty()) {
				System.out.println("暂无用户");
			}else {
				
				System.out.println("1==>通过uid管理用户");
				System.out.println("2==>通过uname管理用户");
				System.out.println("0==>返回上一级");
				System.out.println("请输入您的选择:");
				int choice = MyUtil.inputNum(0, 3);
				switch (choice) {
				case 1:
					System.out.println("请输入您要操作的用户id");
					int uid = MyUtil.inputNum();
					manageUserByUid(uid);
					continue;
				case 2:
					manageUserByUname();// 管理用户
					continue;
				case 0:
					// 退出
					break;
				}
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void manageUserByUname() {
		String uname = MyUtil.inputName();
		// 偷懒了一把 Y(^_^)Y
		int uid = userBiz.queryUserByUname(uname);
		if (uid == -1)
			System.out.println("查无此人");
		else
			manageUserByUid(uid);
	}

	private static void manageUserByUid(int uid) {
		while (true) {
			User user = userBiz.queryUserByUid(uid);
			if (user == null) {
				System.out.println("查无此人");
				break;
			} else {
				System.out.println("1==>查询此用户的全部借书记录");
				System.out.println("2==>查询此用户的已还借书记录");
				System.out.println("3==>查询此用户的未还借书记录");
				System.out.println("4==>冻结/解冻/充值");

				System.out.println("0==>返回上一级");
				int choice = MyUtil.inputNum(0, 4);
				switch (choice) {
				case 1:
					queryRecordByUid(uid);
					continue;
				case 2:
					queryRecordReturned(uid);
					continue;
				case 3:
					queryRecordUnreturned(uid);
					continue;
				case 4:
					freeUser();// 还没写呢 冻结,解冻
					continue;
				case 0:
					// 退出
					break;
				}
				if (MyUtil.isGoOn())

					break;
			}
		}
	}

	private static void freeUser() {
		while (true) {
			System.out.println("****************************************");
			System.out.println("1==>冻结用户");
			System.out.println("2==>解冻用户");
			System.out.println("3==>给用户充值");
			System.out.println("0==>返回上一级");
			int choice = MyUtil.inputNum(0, 3);
			switch (choice) {
			case 1:
				freezeUser();// 冻结
				continue;
			case 2:
				UnfreezeUser();// 解冻
				continue;
			case 3:
				rechargeUser();// 充值
				continue;
			case 0:
				// 退出
				break;
			}
			if (MyUtil.isGoOn())

				break;
		}

	}

	private static void rechargeUser() {
		while (true) {
			System.out.println("*************************");
			System.out.println("1==>根据用户名充值");
			System.out.println("2==>根据用户id充值");
			System.out.println("0==>返回上一级");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:
				rechargeUserByUname();// 根据用户名充值
				continue;
			case 2:
				rechargeUserById();// 根据用户id充值
				continue;
			case 0:
				// 退出
				break;
			}
			if (MyUtil.isGoOn())

				break;

		}

	}

	private static void rechargeUserById() {
		while (true) {
			System.out.println("*********************");
			System.out.println("请输入用户id");
			int uid = MyUtil.inputNum();
			User user = userBiz.queryUserByUid(uid);
			if (user == null) {
				System.out.println("查无此人");
			} else {
				System.out.println("请输入充值金额");
				int money = MyUtil.inputNum();
				boolean flag = userBiz.rechargeUserByUname(uid, money);// 充值
				if (flag) {
					System.out.println("冲值成功");
				} else {
					System.out.println("充值失败");
				}
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void rechargeUserByUname() {
		while (true) {
			System.out.println("*********************");
			String uname = MyUtil.inputName();
			int uid = userBiz.queryUserByUname(uname);
			if (uid == -1) {
				System.out.println("查无此人");
			} else {
				System.out.println("请输入充值金额");
				int money = MyUtil.inputNum();
				boolean flag = userBiz.rechargeUserByUname(uid, money);// 指定冻结用户变成1
				if (flag) {
					System.out.println("冲值成功");
				} else {
					System.out.println("充值失败");
				}
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void UnfreezeUser() {
		while (true) {
			System.out.println("****************************************");
			System.out.println("1==>根据用户名解冻用户");
			System.out.println("2==>根据用户id解冻用户");
			System.out.println("0==>返回上一级");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:
				unFreezeUserByUname();// 根据用户名解冻用户
				continue;
			case 2:
				unFreezeUserById();// 根据用户id解冻用户
				continue;
			case 0:
				// 退出
				break;
			}
			if (MyUtil.isGoOn())

				break;
		}

	}

	private static void unFreezeUserById() {
		while (true) {
			System.out.println("*********************");
			System.out.println("请输入用户id");
			int uid = MyUtil.inputNum();
			User user = userBiz.queryUserByUid(uid);
			if (user == null) {
				System.out.println("查无此人");
			} else {
				boolean flag = userBiz.updateStatus(uid, 1);// 指定冻结用户变成0
				if (flag) {
					System.out.println("冻结成功");
				} else {
					System.out.println("冻结失败");
				}
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void unFreezeUserByUname() {
		while (true) {
			System.out.println("*********************");
			String uname = MyUtil.inputName();
			int uid = userBiz.queryUserByUname(uname);
			if (uid == -1) {
				System.out.println("查无此人");
			} else {
				boolean flag = userBiz.updateStatus(uid, 1);// 指定冻结用户变成0
				if (flag) {
					System.out.println("冻结成功");
				} else {
					System.out.println("冻结失败");
				}
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void freezeUser() {// 冻结用户
		while (true) {
			System.out.println("****************************************");
			System.out.println("1==>根据用户名冻结用户");
			System.out.println("2==>根据用户id冻结用户");
			System.out.println("0==>返回上一级");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:
				freezeUserByUname();// 根据用户名冻结用户
				continue;
			case 2:
				freezeUserById();// 根据用户id冻结用户
				continue;
			case 0:
				// 退出
				break;
			}
			if (MyUtil.isGoOn())

				break;
		}

	}

	private static void freezeUserById() {// 根据用户id冻结用户
		while (true) {
			System.out.println("*********************");
			System.out.println("请输入用户id");
			int uid = MyUtil.inputNum();
			User user = userBiz.queryUserByUid(uid);
			if (user == null) {
				System.out.println("查无此人");
			} else {
				boolean flag = userBiz.updateStatus(uid, 0);// 指定冻结用户变成0
				if (flag) {
					System.out.println("冻结成功");
				} else {
					System.out.println("冻结失败");
				}
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void freezeUserByUname() {// 根据用户名冻结用户
		while (true) {
			System.out.println("*********************");
			String uname = MyUtil.inputName();
			int uid = userBiz.queryUserByUname(uname);
			if (uid == -1) {
				System.out.println("查无此人");
			} else {
				boolean flag = userBiz.updateStatus(uid, 0);// 指定冻结用户变成0
				if (flag) {
					System.out.println("冻结成功");
				} else {
					System.out.println("冻结失败");
				}
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void queryRecordUnreturned(int uid) {
		if (userBiz.queryUserByUid(uid) == null) {
			System.out.println("查无此人");
		} else {
			List<Record> rList = recordBiz.queryRecordByUidUnreturned(uid);
			if (rList == null||rList.isEmpty())
				System.out.println("此用户没有未还书记录");
			else
				System.out.println(rList);
		}
	}

	private static void queryRecordReturned(int uid) {
		if (userBiz.queryUserByUid(uid) == null) {
			System.out.println("查无此人");
		} else {
			List<Record> rList = recordBiz.queryRecordByUidReturned(uid);
			if (rList == null||rList.isEmpty())
				System.out.println("此用户没有还书记录");
			else
				System.out.println(rList);
		}
	}

	private static void queryRecordByUid(int uid) {
		if (userBiz.queryUserByUid(uid) == null) {
			System.out.println("查无此人");
		} else {
			List<Record> rList = recordBiz.queryRecordByUid(uid);
			if (rList == null||rList.isEmpty())
				System.out.println("此用户没有借书记录");
			else
				System.out.println(rList);
		}
	}

	private static void manageBook() {// 管理图书
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>添加图书");
			System.out.println("2==>删除图书");
			System.out.println("3==>查看图书");
			System.out.println("0==>返回上一级");
			int choice = MyUtil.inputNum(0, 3);
			switch (choice) {
			case 1:
				addBook();
				continue;
			case 2:
				delBook();
				continue;
			case 3:
				queryBook();
				continue;
			case 0:
				// 退出
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}
	}

	private static void queryBook() {
		List<Book> bList=bookBiz.queryBookAll();
		if(bList==null||bList.isEmpty()) {
			System.out.println("图书馆无书,请您尽快添加");
		}else{
			System.out.println(bList);
		}
		
	}

	private static void addBook() {// 添加书本
		while (true) {

			System.out.println("请输入您要添加的书本名字:");

			String bname = input.next();
			Book book = new Book(bname);
			if (bookBiz.addBook(book))
				System.out.println("成功");
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void delBook() {// 删除书
		while (true) {
			System.out.println("*********************");
			System.out.println("1==>根据书名删书");
			System.out.println("2==>根据id删书");
			System.out.println("0==>返回上一级");
			int choice = MyUtil.inputNum(0, 2);
			switch (choice) {
			case 1:
				delBookByBname();
				continue;
			case 2:
				delBookByBid();
				continue;
			case 0:
				// 退出
				break;
			}
			if (MyUtil.isGoOn())
				break;
		}

	}

	private static void delBookByBid() {
		System.out.println("*********************");
		System.out.println("请输入您要删除的书的id");
		int bid = MyUtil.inputNum();
		System.out.println(bookBiz.queryBookByBid(bid));
		boolean flag = bookBiz.delBookByBid(bid);
		if (flag) {
			System.out.println("删除成功");
		} else {
			System.err.println("删除失败");
		}

	}

	private static void delBookByBname() {
		String bname = MyUtil.inputName();
		boolean flag = bookBiz.delBookByBname(bname);
		if (flag)
			System.out.println("删除成功");
		else
			System.out.println("删除失败");
	}

}
