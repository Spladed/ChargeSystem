package managerDisplay;

import db.select;
import java.util.*;

public class DAL {
	public static List<HashMap<String,Object>> getStaffInfo(String user,String pass,String departmentID){
		String[] content= {"staff_id","staff_name","phone","gender"};
		String[] selectInfo= {"department_id","\""+departmentID+"\""};
		return select.selectSet(user, pass, "staff", content, selectInfo);
	}
}
