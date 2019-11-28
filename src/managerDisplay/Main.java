package managerDisplay;
import staff.Manager;

import java.util.*;

import db.select;

public class Main {
	public static void Display(Manager manager) {
		String user=manager.getUser();
		String pass=manager.getPass();
		String[] content= {"staff_id","staff_name","phone","gender"};
		String[] selectInfo= {"department_id",manager.getDepartmentID()};
		List<HashMap<String,Object>> recieve=select.selectSet(user, pass, "staff", content, selectInfo);
	}
}
