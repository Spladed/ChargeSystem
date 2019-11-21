package cs;

import java.sql.*;
import java.util.HashMap;

import db.*;

public class logIn {
	static final String USER="root";
	static final String PASS="niunian052170";
	
	public static boolean in(String user,String pass) {
		Connection c=null;
		Statement s=null;
		String[] content= {"pwd"};
		String[] selectInfo= {"staff_id",user};
		HashMap<String,Object> m=select.selectSet(USER, PASS, "staff", content, selectInfo).get(0);
		String pwd=(String)m.get(content[0]);
		if(pwd.equals(pass))
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		if(in("011","niunian"))
			System.out.println("success!");
		else
			System.out.println("wrong!");
	}
}
