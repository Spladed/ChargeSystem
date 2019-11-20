package staff;

import db.*;
import java.util.*;

public class chargeStaff extends Staff {
	private static final String USER="staff";
	private static final String PASS="staff";
	public chargeStaff(String staffID) {
		super(staffID);
	}
	public float[] charge(String houseID,int water,int electricity) {		
		/* result[0]物业费
		 * result[1]卫生费 
		 * result[2]水费
		 * result[3]电费
		 * */
		float[] result=new float[4];
		
		String[] h= {"area","room"};	//house表要查询的内容
		String[] c= {"*"};				//charge要查询的内容		
		String[] selectHouseInfo= {"house_id",houseID};
		
		//houseInfo为房屋信息，包含面积和房间数，一共一个HashMap
		//chargeInfo为收费信息，包含四项收费内容，一共一个HashMap
		List<HashMap<String,Object>> houseInfo=select.selectSet(USER, PASS, "house", h,selectHouseInfo);
		List<HashMap<String,Object>> chargeInfo=select.selectSet(USER, PASS, "charge", c,null);
		
		for(HashMap<String,Object> m:chargeInfo) {
			int i=0;
			for(String key:m.keySet()) {
				result[i++]=(Float)m.get(key);
			}
		}
		for(float f:result) 		
			System.out.println(f);
		for(HashMap<String,Object> m:houseInfo) {
			int i=0;
			for(String key:m.keySet()) {
				result[i++]*=(Float)m.get(key);
			}
		}
		result[2]*=water;
		result[3]*=electricity;
		return result;
	}
	
	public static void main(String[] args) {
		chargeStaff staff=new chargeStaff("004");
		for(float f:staff.charge("A1608", 6, 102))
			System.out.println(f);
	}
}
