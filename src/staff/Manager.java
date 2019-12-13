package staff;

import java.util.HashMap;

import db.*;

public class Manager extends Staff{
	private static final String USER="manager";
	private static final String PASS="manager";

	public Manager(String staffID) {
		super(staffID);
	}
	
	public String getName() {
		String[] selectInfo= {"staff_id","\""+staffID+"\""};
		String name=(String)select.selectSet(USER, PASS, "staff", "staff_name", selectInfo);
		return name;
	}
	
	public String getDepartmentID() {
		String[] selectInfo= {"staff_id","\""+staffID+"\""}; 
		String departmentID=(String)select.selectSet(USER, PASS, "staff", "department_id", selectInfo);
		return departmentID;
	}
	
	public String getUser() {return USER;}
	public String getPass() {return PASS;}
}
