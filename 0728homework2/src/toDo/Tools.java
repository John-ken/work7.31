package toDo;

import java.util.Scanner;

public class Tools {
	
	public static void print(){
		System.out.println("---- To Do 应用管理系统 ----\n"
				+ "请选择对应的选项\n"
				+ "(a)ll items\n"
				+ "(f)ind a specific item\n"
				+ "(i)nsert a new item\n"
				+ "(u)pdate an existing item\n"
				+ "(d)elete an existing item\n"
				+ "(e)xit");
	}
	
	public static void start(){
		print();
		Scanner in = new Scanner(System.in);
		char c = in.next().charAt(0);
		switch(c){
		case 'a':
			Tool.all();
			break;
		case 'f':
			Tool.find();
			break;
		case 'i':
			Tool.insert();
			break;
		case 'u':
			break;
		case 'd':
			break;
		case 'e':
			break;
			default:
				
		}
	}
	
}
