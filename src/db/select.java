package db;

import java.util.*;

import java.sql.*;

//��������ΪList<HashMap<String, Object>>,ʹ��ʱ��Ҫ���ж�Object������ǿ��ת��
public class select {
	
	//user��pass�������ݿ��ѯʱʹ�õ��˻�������
	//tableҪ��ѯ�ı������
	//contentҪ��ѯ�ľ�������
	//selectInfoԼ�����������ڹ���where��䣬��һ��Ԫ�����������ڶ���Ԫ���Ǿ�������ݡ�����Ҫ�޶�ʱ����null
	public static List<HashMap<String, Object>> selectSet(String user,String pass,String table,String[] content,String[] selectInfo) {
		Connection conn=null;	
		Statement stmt=null;
		String sql=null;
		List<HashMap<String, Object>> result=new LinkedList<HashMap<String,Object>>();
		
		try {
			Class.forName(DBINFO.JDBC_DRIVER);
			conn=DriverManager.getConnection(DBINFO.DB_URL,user,pass);
			stmt=conn.createStatement();
			
			if(selectInfo==null || selectInfo.length!=2) {
				sql="select "+SQLString.construct(content)+" from "+table;
				System.out.println(sql);
			}
			else {
				sql="select "+SQLString.construct(content)+" from "+table+" where "+selectInfo[0]+"="+selectInfo[1];
				System.out.println(sql);
			}
			
			ResultSet rs=stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				HashMap m=new HashMap<String,Object>();
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
		String[] content= {"*"};
		List<HashMap<String,Object>> recieve=selectSet("staff","staff","charge",content,null);
		System.out.println(recieve);
	}
}
