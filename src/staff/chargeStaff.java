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
	
	//�û�����ˮ�����õ���
	public double[] charge(String houseID) {		
		/* result[0]��ҵ��
		 * result[1]������ 
		 * result[2]ˮ��
		 * result[3]���
		 * */
		double[] result=new double[4];
		
		String[] content= {"area","room","watercost","electricitycost"};	//house��Ҫ��ѯ������
		String[] selectHouseInfo= {"house_id",houseID};
		
		//chargeInfoΪ�շ���Ϣ�����������շ����ݣ�һ��һ��HashMap
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
