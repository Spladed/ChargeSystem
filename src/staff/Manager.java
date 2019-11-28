package staff;

import java.util.HashMap;

import db.*;

public class Manager extends Staff{
	private static final String USER="manager";
	private static final String PASS="manager";

	public Manager(String staffID) {
		super(staffID);
	}
	
	//判断员工是否是自己所属部门的
	private boolean confirm(String staff_id) {
		String thisSelectInfo[]= {"staff_id",this.staffID};
		String SelectInfo[]= {"staff_id",staff_id};
		String thisDpID=(String)select.selectSet(USER, PASS, "staff", "department_id", thisSelectInfo);
		String staffDpID=(String)select.selectSet(USER, PASS, "staff", "department_id", SelectInfo);
		if(thisDpID.equals(staffDpID))
			return true;
		return false;
	}

	//接受一个String数组，数组内容为员工的信息
	public boolean addStaff(String[] column,String[] values) {
		if(confirm(values[0])) {
			insert.insertSet(USER, PASS, "staff", column, values);
			return true;
		}
		return false;
	}
	
	//接受一个String，表示要删除的员工的员工号
	public boolean deleteStaff(String staff_id) {
		if(confirm(staff_id)) {
			delete.deleteSet(USER, PASS, "staff", "staff_id", staff_id);
			return true;
		}
		return false;
	}
	
	//传入两个个列表，第一个表示要修改的列，第二个表示修改内容
	public boolean updateStaff(String[] column,String[] values,String staff_id) {
		String[] sign= {"staff_id",staff_id};
		if(confirm(staff_id)) {
			update.updateSet(USER, PASS, "staff", column, values, sign);
			return true;
		}
		return false;
	}
	
	public String getDepartmentID() {
		String[] selectInfo= {"staff_id",staffID}; 
		String departmentID=(String)select.selectSet(USER, PASS, "staff", "department_id", selectInfo);
		return departmentID;
	}
	
	public String getUser() {return USER;}
	public String getPass() {return PASS;}
}
