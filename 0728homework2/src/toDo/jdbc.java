package toDo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class jdbc {
	private static String user ;
	private static String password ;
	private static String url ;
	private static String driverClass;
	
	static {
		Properties prop = new Properties();
		InputStream ins = jdbc.class.getClassLoader().getResourceAsStream("pro.properties");
		try {
			prop.load(ins);
		} catch (IOException e) {
			e.printStackTrace();
		}
		user = prop.getProperty("user");
		password = prop.getProperty("password");
		url = prop.getProperty("url");
		driverClass = prop.getProperty("driverClass");
		
		//注册驱动
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//连接数据库
	public static Connection getConn(){
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//关闭连接
	public static void frees(ResultSet rs,Statement sta , Connection conn){
		try{
			if(rs!=null){
				rs.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
				try {
					if(sta!=null){
					sta.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					if(conn!=null){
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
		}
		
	}
	
}
