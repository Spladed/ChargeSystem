package db;

import java.util.*;

import java.sql.*;

//返回类型为List<HashMap<String, Object>>,使用时需要自行对Object对象做强制转换
public class select {
	
	//user、pass进行数据库查询时使用的账户和密码
	//table要查询的表的名字
	//content要查询的具体内容
	//selectInfo约束条件，用于构造where语句，第一个元素是列名，第二个元素是具体的内容。不需要where语句时传入null
	public static Object selectSet(String user,String pass,String table,String content,String[] selectInfo) {
		if(content.equals("*")) {
			String[] temp= {content};
			return selectSet(user, pass, table, temp, selectInfo);
		}
		
		Connection conn=null;	
		Statement stmt=null;
		String sql=null;
		
		try {
			Class.forName(DBINFO.JDBC_DRIVER);
			conn=DriverManager.getConnection(DBINFO.DB_URL,user,pass);
			stmt=conn.createStatement();
			
			if(selectInfo==null) {
				sql="select "+content+" from "+table;
			}
			else {
				sql="select "+content+" from "+table+" where "+selectInfo[0]+"="+"\""+selectInfo[1]+"\"";
			}
			
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()) {
				return rs.getObject(content);
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
		return null;
	}
	
	public static List<HashMap<String, Object>> selectSet(String user,String pass,String table,String[] content,String[] selectInfo) {
		Connection conn=null;	
		Statement stmt=null;
		String sql=null;
		List<HashMap<String, Object>> result=new LinkedList<HashMap<String,Object>>();
		
		try {
			Class.forName(DBINFO.JDBC_DRIVER);
			conn=DriverManager.getConnection(DBINFO.DB_URL,user,pass);
			stmt=conn.createStatement();
			
			if(selectInfo==null) {
				sql="select "+SQLString.construct(content)+" from "+table;
			}
			else {
				sql="select "+SQLString.construct(content)+" from "+table+" where "+selectInfo[0]+"="+"\""+selectInfo[1]+"\"";
			}
			
			ResultSet rs=stmt.executeQuery(sql);
						
			while(rs.next()) {
				HashMap m=new HashMap<String,Object>();
				//如果要传入的查询内容为*,因为不知道列的名字，所以需要构造ResultSetMetaData来获取列名存入HashMap
				if(content.length==1 && content[0].equals("*")) {
					ResultSetMetaData metaData=rs.getMetaData();		//获取列集
					for(int i=0;i<metaData.getColumnCount();i++) {		//根据列数量循环
						String str=metaData.getColumnName(i+1);
						m.put(str, rs.getObject(str));	//列的名字、根据列的名字获取值
					}
				}
				//知道传入的列的名字，可以直接利用列名获取数据
				else
					for(int i=0;i<content.length;i++) {
						m.put(content[i], rs.getObject(content[i]));
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
		String[] content= {"area","room"};
		String[] selectHouseInfo= {"house_id","A1608"};
		List<HashMap<String,Object>> recieve=(List<HashMap<String, Object>>) selectSet("staff","staff","house","*",null);
		for(HashMap<String,Object> m:recieve) {
			System.out.println(m);		
		}
	}
}
