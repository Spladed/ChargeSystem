package staff;

import java.util.HashMap;

import db.*;

public class Manager extends Staff{
	private static final String USER="manager";
	private static final String PASS="manager";

	public Manager(String staffID) {
		super(staffID);
	}
	
	//�ж�Ա���Ƿ����Լ��������ŵ�
	private boolean confirm(String staff_id) {
		String thisSelectInfo[]= {"staff_id",this.staffID};
		String SelectInfo[]= {"staff_id",staff_id};
		String thisDpID=(String)select.selectSet(USER, PASS, "staff", "department_id", thisSelectInfo);
		String staffDpID=(String)select.selectSet(USER, PASS, "staff", "department_id", SelectInfo);
		if(thisDpID.equals(staffDpID))
			return true;
		return false;
	}

	//����һ��String���飬��������ΪԱ������Ϣ
	public boolean addStaff(String[] column,String[] values) {
		if(confirm(values[0])) {
			insert.insertSet(USER, PASS, "staff", column, values);
			return true;
		}
		return false;
	}
	
	//����һ��String����ʾҪɾ����Ա����Ա����
	public boolean deleteStaff(String staff_id) {
		if(confirm(staff_id)) {
			delete.deleteSet(USER, PASS, "staff", "staff_id", staff_id);
			return true;
		}
		return false;
	}
	
	//�����������б���һ����ʾҪ�޸ĵ��У��ڶ�����ʾ�޸�����
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
