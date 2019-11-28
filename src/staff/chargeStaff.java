package staff;

import db.*;
import java.util.*;

public class chargeStaff extends Staff {
	private static final String USER="staff";
	private static final String PASS="staff";
	private static final double[] chargeStandard= {1,10,0.7,0.8};
	
	public chargeStaff(String staffID) {
		super(staffID);
	}
	
	//用户的用水量和用电量
	public double[] charge(String houseID) {		
		/* result[0]物业费
		 * result[1]卫生费 
		 * result[2]水费
		 * result[3]电费
		 * */
		double[] result=new double[4];
		
		String[] content= {"area","room","watercost","electricitycost"};	//house表要查询的内容
		String[] selectHouseInfo= {"house_id",houseID};
		
		//chargeInfo为收费信息，包含四项收费内容，一共一个HashMap
		HashMap<String,Object> chargeInfo=select.selectSet(USER, PASS, "house", content, selectHouseInfo).get(0);
		for(int i=0;i<content.length;i++) {
			result[i]=chargeStandard[i]*(Double)chargeInfo.get(content[i]);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		chargeStaff staff=new chargeStaff("004");
		for(double f:staff.charge("A1608"))
			System.out.printf("%.2f\n",f);			
	}
}
