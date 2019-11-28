package staff;

import java.util.HashMap;

import db.*;

public class Manager extends Staff{
	private static final String USER="manager";
	private static final String PASS="manager";

	public Manager(String staffID) {
		super(staffID);
	}

	//接受一个String数组，数组内容为员工的信息
	public void addStaff(String[] column,String[] values) {
		insert.insertSet(USER, PASS, "staff", column, values);
	}
	
	//接受一个String，表示要删除的员工的员工号
	public void deleteStaff(String staff_id) {
		delete.deleteSet(USER, PASS, "staff", "staff_id", staff_id);
	}
	
	//传入两个个列表，第一个表示要修改的列，第二个表示修改内容
	public void updateStaff(String[] column,String[] values,String staff_id) {
		String[] sign= {"staff_id",staff_id};
		update.updateSet(USER, PASS, "staff", column, values, sign);
	}
	
	public String getDepartmentID() {
		String[] selectInfo= {"staff_id","\""+staffID+"\""}; 
		String departmentID=(String)select.selectSet(USER, PASS, "staff", "department_id", selectInfo);
		return departmentID;
	}
	
	public String getUser() {return USER;}
	public String getPass() {return PASS;}
}
