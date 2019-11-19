package db;

import java.sql.*;

public class STAFFOPERATE {
	public static float[] operate(String user,String pass,String houseID,int water,int electricity) {
		/* result[0]物业费
		 * result[1]卫生费 
		 * result[2]水费
		 * result[3]电费
		 * */
		float[] result=new float[4];
		Connection conn=null;	
		Statement stmt=null;
		float area = 0;
		int room = 0;
		
		try {
			Class.forName(DBINFO.JDBC_DRIVER);
			conn=DriverManager.getConnection(DBINFO.DB_URL,user,pass);
			stmt=conn.createStatement();
			String sql="select area,room from house where house_id=\""+houseID+"\"";
			ResultSet rs=stmt.executeQuery(sql);			
			if(rs.next()) {
				area=rs.getFloat("area");
				room=rs.getInt("room");
			}
			sql="select * from charge";
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				result[0]=rs.getFloat("property") * area;
				result[1]=rs.getFloat("clean") * room;
				result[2]=rs.getFloat("water") * water;
				result[3]=rs.getFloat("electricity") * electricity;
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
}
