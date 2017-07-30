import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCtool {

	private static String user ;
	private static String password ;
	private static String url ;
	private static String driverClass;
	
	private JDBCtool(){}
	private static JDBCtool instance = null;
	
	//静态代码块，从properties文件中加载数据库信息，注册驱动
	static{
			try {
				Properties pro = new Properties();
				InputStream ins = JDBCtool.class.getClassLoader().getResourceAsStream("jdbc.properties");
				pro.load(ins);
				user = pro.getProperty("user");
				password = pro.getProperty("password");
				url = pro.getProperty("url");
				driverClass = pro.getProperty("driverClass");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		/*user = "root";
		password = "886887";
		url = "jdbc:mysql:///srs?useUniceode=true&characterEncoding=utf-8";
		driverClass = "com.mysql.jdbc.Driver";*/
		//注册驱动
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 链接数据库
	 */
	public static Connection getConn(){
		
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 释放资源
	 */
	public static void frees(ResultSet rs, Statement sta, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (sta != null) {
					sta.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//提供方法对外返回JDBCtool实例
	public static JDBCtool getIstance(){
		if(instance!=null){
			return instance;
		}else {
			synchronized(JDBCtool.class){
				instance = new JDBCtool();
			}
		}
		return instance ;
	}
	
	
}
