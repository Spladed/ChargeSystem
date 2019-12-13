package managerDisplay;

import db.delete;
import db.insert;
import db.select;
import db.update;

import java.util.*;

public class DAO {
	//给主界面Main显示员工用的
	public static Object[][] getStaffInfo(String user,String pass,String departmentID){
		String[] content= {"staff_id","staff_name","phone","gender"};
		String[] selectInfo= {"department_id","\""+departmentID+"\""+"and job='S'"};
		List<HashMap<String,Object>> recieve = select.selectSet(user, pass, "staff", content, selectInfo);
		Object[][] obj=new Object[recieve.size()][content.length+1];
		for(int i=0;i<recieve.size();i++) {
			for(int j=0;j<content.length+1;j++)
				obj[i][j]="";
		}
		for(int i=0;i<recieve.size();i++) {
			HashMap<String,Object> m=recieve.get(i);
			for(int j=0;j<m.size();j++) {
				obj[i][j]=m.get(content[j]);
			}
		}
		return obj;
	}
	
	//给updateStaff显示信息用的
	public static Object[] getStaffDetail(String user,String pass,String staffID) {
		String[] content= {"staff_id","staff_name","birthday","gender","address","phone","pwd"};
		String[] selectInfo= {"staff_id","\""+staffID+"\""};
		HashMap<String,Object> recieve = select.selectSet(user, pass, "staff", content, selectInfo).get(0);
		Object[] obj=new Object[content.length];
		for(int i=0;i<content.length;i++) {
			obj[i]=recieve.get(content[i]);
		}
		return obj;
	}
	
	//用来执行update操作的
	public static void updateStaff(String user,String pass,String staffID,String[] value) {
		String[] column= {"staff_id","staff_name","birthday","gender","address","phone","pwd"};
		String[] sign= {"staff_id",staffID};
		update.updateSet(user, pass, "staff", column, value, sign);
	}
	
	//给addStaff用的
	public static void addStaff(String user,String pass,String[] value) {
		String[] column= {"staff_id","staff_name","birthday","gender","address","phone","pwd","department_id","job"};
		insert.insertSet(user, pass, "staff", column, value);
	}
	
	//删除员工用的
	public static void deleteStaff(String user,String pass,String staffID) {
		delete.deleteSet(user, pass, "staff", "staff_id", staffID);
	}
}
