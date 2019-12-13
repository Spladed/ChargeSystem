package db;

import java.sql.*;

public class delete {
	public static void deleteSet(String user,String pass,String table,String column,String sign) {
		Connection conn=null;	
		Statement stmt=null;
		
		try {
			Class.forName(DBINFO.JDBC_DRIVER);
			conn=DriverManager.getConnection(DBINFO.DB_URL,user,pass);
			stmt=conn.createStatement();
			String sql="delete from "+table+" where "+column+"="+sign;
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
	
//	public static void main(String[] args) {
//		deleteSet("manager", "manager", "staff", "staff_id", "007");
//	}
}
