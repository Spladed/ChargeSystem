package chargeStaffDisplay;
import db.select;
import staff.chargeStaff;
import java.util.*;

public class Main {
	public static void Display(chargeStaff cs) {
		String user=cs.getUser();
		String pass=cs.getPass();
		String[] content= {};
		String[] selectInfo= {};
		List<HashMap<String,Object>> recieve=select.selectSet(user, pass, "bill", content, selectInfo);
	}
}
