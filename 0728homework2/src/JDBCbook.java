import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCbook {

	public static void save(String str[]){
		Connection conn = null;
		Statement sta = null;
		
		try {
			conn = JDBCtool.getConn();
			sta = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "insert into book values('"+str[0]+"','"+str[1]+"','"+str[2]+"','"+str[3]+"','"+str[4]+"');";
		try {
			sta.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCtool.frees(null,sta,conn);
		}
	}
	
	public static void saves(){
		File file = new File("books.txt");
		
		BufferedReader bfr = null;
		String strs = null;
				try {
					bfr = new BufferedReader(new FileReader(file)) ;
					while((strs = bfr.readLine())!=null){
							String[] str = strs.split("\\s+");
							/*for(String s : str){
							System.out.println(s);
							}*/
							save(str);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
	public static void main(String[] args) {
		saves();
	}
	
}
