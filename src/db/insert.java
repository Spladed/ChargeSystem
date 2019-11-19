package db;

import java.sql.*;

public class insert {
	public static void insertSet(String user,String pass,String table,String[] column,String[] content) {
		Connection conn=null;	
		Statement stmt=null;
		
		try {
			Class.forName(DBINFO.JDBC_DRIVER);
			conn=DriverManager.getConnection(DBINFO.DB_URL,user,pass);
			stmt=conn.createStatement();
			String sql="insert into "+table+" "+SQLString.insertColumn(column)+" values "+SQLString.insertValues(content);
			stmt.execute(sql);
			stmt.close();
			conn.close();	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt!=null) stmt.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		String[] column= {"staff_id","staff_name","birthday","gender","address","phone","department_id","job","pwd"};
		String[] content= {"007","Ð»°²âù","1999-06-02","F","´ºÌï»¨»¨Ó×ÖÉÔ°","123333","003","M","xieanyi"};
		insertSet("manager", "manager", "staff", column, content);
	}
}
