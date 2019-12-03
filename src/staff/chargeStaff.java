package staff;

import db.*;

import java.text.SimpleDateFormat;
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
	
//	private String[] toString(double[] recieve) {		
//		//result[0]物业费、result[1]卫生费
//		String[] result=new String[2];
//		//把Double[]转为String[]
//		for(int i=0;i<2;i++) {
//			result[i]=recieve[i]+"";
//		}
//		return result;
//	}
	
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
	
//	//将收费金额信息插入bill表
//	private void insertIntoBill(String houseID) {		
//		String[] recieve=charge(houseID);
//		long l=System.currentTimeMillis();
//		Date date=new Date(l);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String time = dateFormat.format(date);
//		String[] content= {recieve[0],recieve[1],recieve[2],recieve[3],time,houseID,"unordered"};
//		String[] column= {"property","clean","water","electricity","time_stamp","house_id","state"};
//		insert.insertSet(USER, PASS, "bill", column, content);
//	}
	
	public String getUser() {return USER;}
	public String getPass() {return PASS;}
	public String getID() {return staffID;}
	
	public static void main(String[] args) {
//		chargeStaff cs=new chargeStaff("001");
//		for(String s:cs.Charge("A0101"))
//			System.out.println(s);
//		for(String s:cs.houseInfo("A0101"))
//			System.out.println(s);
	}
}
