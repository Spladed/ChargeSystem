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
	
	//��ȡ�շѽ��
	public double[] Charge(String houseID) {		
		//result[0]��ҵ�ѡ�result[1]������
		double[] recieve=new double[2];		
		String[] content= {"area","room"};	//house��Ҫ��ѯ������
		String[] selectHouseInfo= {"house_id","\""+houseID+"\""};		
		//chargeInfoΪ�շ���Ϣ�����������շ����ݣ�һ��һ��HashMap
		HashMap<String,Object> chargeInfo=select.selectSet(USER, PASS, "house", content, selectHouseInfo).get(0);
		for(int i=0;i<content.length;i++) {
			recieve[i]=chargeStandard[i]*(Double)chargeInfo.get(content[i]);
		}
		return recieve;
	}
	
	public double[] houseInfo(String houseID) {
		double[] recieve=new double[2];
		String[] content= {"area","room"};	//house��Ҫ��ѯ������
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
	
	public static void main(String[] args) {
//		chargeStaff cs=new chargeStaff("001");
//		for(String s:cs.Charge("A0101"))
//			System.out.println(s);
//		for(String s:cs.houseInfo("A0101"))
//			System.out.println(s);
	}
}
