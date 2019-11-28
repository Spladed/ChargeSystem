package cs;

import db.*;

public class logIn {
	static final String USER="root";
	static final String PASS="niunian052170";
	
	public static boolean in(String user,String pass) {
		String[] selectInfo= {"staff_id","\""+user+"\""};
		String m=(String)select.selectSet(USER, PASS, "staff", "pwd", selectInfo);
		if(m==null)
			return false;
		if(m.equals(pass))
			return true;
		return false;
	}
	
	public static void main(String[] args) {

	}
}
