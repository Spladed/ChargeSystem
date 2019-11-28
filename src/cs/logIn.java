package cs;

import db.*;

public class logIn {
	static final String USER="root";
	static final String PASS="niunian052170";
	
	public static boolean in(String user,String pass) {
		String[] selectInfo= {"staff_id",user};
		String m=(String)select.selectSet(USER, PASS, "staff", "pwd", selectInfo);
		if(m==null)
			return false;
		if(m.equals(pass))
			return true;
		return false;
	}
	
	public static char jobInfo(String staff_id) {
		String[] selectInfo= {"staff_id",staff_id};
		String job=(String)select.selectSet(USER, PASS, "staff", "job", selectInfo);
		if(job.equals("M"))
			return 'M';
		else if(job.equals("S"))
			return 'S';
		return 'N';
	}
	
	public static void main(String[] args) {
		switch(jobInfo("011")) {
		case 'M':System.out.println("Manager");break;
		case 'S':System.out.println("Staff");break;
		default:System.out.println("wrong!");break;
		}
	}
}
