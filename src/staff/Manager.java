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

	//����һ��String���飬��������ΪԱ������Ϣ
	public boolean addStaff(String[] info) {
		
		return true;
	}
	
	//����һ��String����ʾҪɾ����Ա����Ա����
	public boolean dropStaff() {
		return true;
	}
	
	//�����������б���һ����ʾҪ�޸ĵ��У��ڶ�����ʾ�޸�����
	public boolean updateStaff(LinkedList<String> column,LinkedList<String> data) {
		return true;
	}
	
}
