package db;

import java.sql.*;

public class update {
	public static void updateSet(String user,String pass,String table,String[] column,String content[],String[] sign) {
		Connection conn=null;	
		Statement stmt=null;
		
		try {
			Class.forName(DBINFO.JDBC_DRIVER);
			conn=DriverManager.getConnection(DBINFO.DB_URL,user,pass);
			stmt=conn.createStatement();
			String sql;
			for(int i=0;i<column.length;i++) {
				sql="update "+table+" set "+column[i]+"="+content[i]+" where "+sign[0]+"="+"\""+sign[1]+"\"";
				System.out.println(sql);
				stmt.execute(sql);
			}
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
//		String[] col= {"phone","pwd"};
//		String[] content= {"777","'asdf'"};
//		String[] sign= {"staff_id","123"};
//		updateSet("manager","manager","staff",col,content,sign);
//	}
}
