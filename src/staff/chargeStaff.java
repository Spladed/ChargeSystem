package staff;

import db.*;

import java.util.*;

public class chargeStaff extends Staff {
	private static final String USER="staff";
	private static final String PASS="staff";
	private static final double[] chargeStandard= {1,10};
	
	public chargeStaff(String staffID) {
		super(staffID);
	}
	
	//获取收费金额
	public double[] Charge(String houseID) {		
		//result[0]物业费、result[1]卫生费
		double[] recieve=new double[2];		
		String[] content= {"area","room"};	//house表要查询的内容
		String[] selectHouseInfo= {"house_id","\""+houseID+"\""};		
		//chargeInfo为收费信息，包含两项收费内容，一共一个HashMap
		HashMap<String,Object> chargeInfo=select.selectSet(USER, PASS, "house", content, selectHouseInfo).get(0);
		for(int i=0;i<content.length;i++) {
			recieve[i]=chargeStandard[i]*(Double)chargeInfo.get(content[i]);
		}
		return recieve;
	}
	
	public double[] houseInfo(String houseID) {
		double[] recieve=new double[2];
		String[] content= {"area","room"};	//house表要查询的内容
		String[] selectHouseInfo= {"house_id","\""+houseID+"\""};
		HashMap<String,Object> houseInfo=select.selectSet(USER, PASS, "house", content, selectHouseInfo).get(0);
		for(int i=0;i<content.length;i++) {
			recieve[i]=(Double)houseInfo.get(content[i]);
		}
		return recieve;
	}
	
	public String getUser() {return USER;}
	public String getPass() {return PASS;}
	public String getID() {return staffID;}
	
}
