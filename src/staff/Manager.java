package staff;

import java.util.HashMap;

import db.*;

public class Manager extends Staff{
	private static final String USER="manager";
	private static final String PASS="manager";

	public Manager(String staffID) {
		super(staffID);
	}

	//����һ��String���飬��������ΪԱ������Ϣ
	public void addStaff(String[] column,String[] values) {
		insert.insertSet(USER, PASS, "staff", column, values);
	}
	
	//����һ��String����ʾҪɾ����Ա����Ա����
	public void deleteStaff(String staff_id) {
		delete.deleteSet(USER, PASS, "staff", "staff_id", staff_id);
	}
	
	//�����������б���һ����ʾҪ�޸ĵ��У��ڶ�����ʾ�޸�����
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
