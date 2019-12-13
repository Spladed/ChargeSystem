package cs;

import db.select;

public class judgingPosition {
	
	static final String USER="root";
	static final String PASS="niunian052170";
	
	public static String judge(String user) {
		String[] selectInfo= {"staff_id","\""+user+"\""};
		String m=(String)select.selectSet(USER, PASS, "staff", "job", selectInfo);		
		return m;
	}	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		String str=judge("011");
//		System.out.println(str);
//	}
}
