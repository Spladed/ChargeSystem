package staff;

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
	public void delete(String staff_id) {
		delete.deleteSet(USER, PASS, "staff", "staff_id", staff_id);
	}
	
	//�����������б���һ����ʾҪ�޸ĵ��У��ڶ�����ʾ�޸�����
	public void updateStaff(String[] column,String[] values,String staff_id) {
		update.updateSet(USER, PASS, "staff", column, values, staff_id);
	}
	
}
