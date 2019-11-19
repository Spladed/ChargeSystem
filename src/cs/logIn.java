package cs;

import java.sql.*;

public class logIn {
	static final String USER="root";
	static final String PASS="niunian052170";
	
	public static boolean in(String user,String pass) {
		Connection c=null;
		Statement s=null;
		
		try {
			Class.forName(dbInfo.JDBC_DRIVER);
			c=DriverManager.getConnection(dbInfo.DB_URL,USER,PASS);
			s=c.createStatement();
			String sql;
			sql="select pwd from staff where staff_id="+user;
			ResultSet rs=s.executeQuery(sql);
			if(rs.next())
				if(rs.getString("pwd").equals(pass))
					return true;
			rs.close();
			s.close();
			c.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(s!=null) s.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(c!=null) c.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		if(in("011","niunian"))
			System.out.println("success!");
		else
			System.out.println("wrong!");
	}
}
