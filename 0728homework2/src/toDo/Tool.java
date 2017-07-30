package toDo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Tool {

	public static void all() {
		Connection con = null;
		Statement sta = null;
		ResultSet res = null;
		try {
			con = jdbc.getConn();
			sta = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String str = "select id,name from todo;";
		String strb = "create table tod(id tinyint , name varchar 30);";
		String ss = "insert into todo(id,name) values (1,'陪女朋友吃饭')";
		int rsb = 0;
		int ssb = 0;
		int s = -1;
//		String t = null; 
		try {
//			rsb = sta.executeUpdate(strb);
//			ssb = sta.executeUpdate(ss);
			res = sta.executeQuery(str);
//			s = rs.getInt(1);
//			t = rs.getString(2);
		} catch (SQLException e) {
		}
		if(ssb!=0){
			System.out.println("插入成功！");
		}
			try {
				boolean boo = false;
				//必须要用 ‘.next’    why？
				if(!(boo=res.next())){
					System.out.println("没有任务！ 走 酒吧放松");
				}else{
					do{
						System.out.println(res.getInt(1)+": "+res.getString(2)+"[completed： false]");
					}while (res.next());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		jdbc.frees(res, sta, con);
		
		System.out.println();
		Tools.start();
		return ;
	}

	public static void find() {
		
		System.out.println("请输入待完成项目的ID：");
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		Connection con = null;
		Statement sta = null;
		ResultSet rs = null;
		String str = "select id,name from todo;";
		
		try {
			con = jdbc.getConn();
			sta = con.createStatement();
			rs = sta.executeQuery(str);
			int e = 0;
			while(rs.next()){
				if(a == rs.getInt(1)){
					System.out.println(rs.getInt(1)+": "+rs.getString(2)+"[completed： false]");
					e = 1;
				}
			}
			if(e==0){
				System.out.println("您还没有相应ID的待办事项！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc.frees(rs, sta, con);
		
		System.out.println();
		Tools.start();
		return ;
		
	}

	public static void insert() {

		System.out.println("请输入待完成项目的名称：");
		Scanner in = new Scanner (System.in);
		String s = in.next();
		Connection con = null;
		Statement sta = null;
		ResultSet rs = null;
		int a = -1 ;
		
		String str = "insert into todo(id,name) values(id,'"+s+"');";
		String sql = "select id from todo";
		try {
			con = jdbc.getConn();
			sta = con.createStatement();
			a = sta.executeUpdate(str);
			rs = sta.executeQuery(sql);
			if(a!=-1){
				int  m = 0;
				while(rs.next()){
					m = rs.getInt(1);
				}
				System.out.println("成功插入ID为"+m+"的项目");
			}else{
				System.out.println("插入失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbc.frees(rs, sta, con);
		}
		System.out.println();
		Tools.start();
		return ;
		
	}

}
