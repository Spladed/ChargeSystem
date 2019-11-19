package staff;

import java.sql.Connection;
import java.sql.Statement;
import java.util.LinkedList;

import db.DBINFO;

public class Manager extends Staff{
	private static final String USER="manager";
	private static final String PASS="manager";
	private Connection conn=null;
	private Statement stmt=null;
	public Manager(String staffID) {
		super(staffID);
	}

	//接受一个String数组，数组内容为员工的信息
	public boolean addStaff(String[] info) {
		
		return true;
	}
	
	//接受一个String，表示要删除的员工的员工号
	public boolean dropStaff() {
		return true;
	}
	
	//传入两个个列表，第一个表示要修改的列，第二个表示修改内容
	public boolean updateStaff(LinkedList<String> column,LinkedList<String> data) {
		return true;
	}
	
}
