package staff;

import java.sql.*;

import db.DBINFO;

public class chargeStaff extends Staff {
	private static final String USER="staff";
	private static final String PASS="staff";
	public chargeStaff(String staffID) {
		super(staffID);
	}
	public float[] charge(String houseID,int water,int electricity) {		
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
			conn=DriverManager.getConnection(DBINFO.DB_URL,USER,PASS);
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
	
	public static void main(String[] args) {
		chargeStaff staff=new chargeStaff("004");
		for(float f:staff.charge("A1608", 6, 102))
			System.out.println(f);
	}
}
