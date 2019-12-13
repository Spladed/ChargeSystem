package managerDisplay;

import db.select;
import java.util.*;

public class DAL {
	//给主界面显示员工用的
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
	
	//给update用的
	public static Object[] getStaffDetail(String user,String pass,String staffID) {
		String[] content= {"staff_id","staff_name","birthday","gender","address","phone","pwd"};
		String[] selectInfo= {"staff_id","\""+staffID+"\""};
		List<HashMap<String,Object>> recieve = select.selectSet(user, pass, "staff", content, selectInfo);
		return null;
	}
	
	//给addStaff用的
}
