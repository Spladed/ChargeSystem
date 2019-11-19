package staff;

import java.sql.*;
import db.STAFFOPERATE;

public class chargeStaff extends Staff {
	private static final String USER="staff";
	private static final String PASS="staff";
	public chargeStaff(String staffID) {
		super(staffID);
	}
	public float[] charge(String houseID,int water,int electricity) {		
		return STAFFOPERATE.operate(USER, PASS, houseID, water, electricity);
	}
	
	public static void main(String[] args) {
		chargeStaff staff=new chargeStaff("004");
		for(float f:staff.charge("A1608", 6, 102))
			System.out.println(f);
	}
}
