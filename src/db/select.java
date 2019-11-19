package db;

import java.util.*;

import java.sql.*;

public class select {
	public static List<HashMap<String,String>> selectSet(String user,String pass,String table,String[] content) {
		Connection conn=null;	
		Statement stmt=null;
		List<HashMap<String, String>> result=new LinkedList<HashMap<String,String>>();
		
		try {
			Class.forName(DBINFO.JDBC_DRIVER);
			conn=DriverManager.getConnection(DBINFO.DB_URL,user,pass);
			stmt=conn.createStatement();
			String sql="select "+SQLString.construct(content)+" from "+table;
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				HashMap m=new HashMap<String,String>();
				for(int i=0;i<content.length;i++) {
					m.put(content[i], rs.getString(content[i]));
				}
				result.add(m);
			}
			rs.close();
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
		return result;
	}
	
	public static void main(String[] args) {
		String[] content= {"water","electricity"};
		List<HashMap<String,String>> recieve=selectSet("staff","staff","charge",content);
		for(HashMap<String,String> m:recieve) {
			for(int i=0;i<content.length;i++) {
				System.out.println(content[i]+":"+m.get(content[i]));
			}
		}
	}
}
