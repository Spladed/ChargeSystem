package staff;

import db.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class chargeStaff extends Staff {
	private static final String USER="staff";
	private static final String PASS="staff";
	private static final double[] chargeStandard= {1,10,0.7,0.8};
	
	public chargeStaff(String staffID) {
		super(staffID);
	}
	
	//��ȡ�շѽ��
	private String[] charge(String houseID) {		
		//result[0]��ҵ�ѡ�result[1]�����ѡ�result[2]ˮ�ѡ�result[3]���

		double[] recieve=new double[4];
		String[] result=null;
		
		String[] content= {"area","room","watercost","electricitycost"};	//house��Ҫ��ѯ������
		String[] selectHouseInfo= {"house_id",houseID};
		
		//chargeInfoΪ�շ���Ϣ�����������շ����ݣ�һ��һ��HashMap
		HashMap<String,Object> chargeInfo=select.selectSet(USER, PASS, "house", content, selectHouseInfo).get(0);
		for(int i=0;i<content.length;i++) {
			recieve[i]=chargeStandard[i]*(Double)chargeInfo.get(content[i]);
		}
		//��Double[]תΪString[]
		for(int i=0;i<4;i++) {
			result[i]=String.format("%.2f",recieve[i]);
		}
		return result;
	}
	
	//���շѽ����Ϣ����bill��
	private void insertIntoBill(String houseID) {		
		String[] recieve=charge(houseID);
		long l=System.currentTimeMillis();
		Date date=new Date(l);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String time = dateFormat.format(date);
		String[] content= {recieve[0],recieve[1],recieve[2],recieve[3],time,houseID,"unordered"};
		String[] column= {"property","clean","water","electricity","time_stamp","house_id","state"};
		insert.insertSet(USER, PASS, "bill", column, content);
	}
	
	public String getUser() {return USER;}
	public String getPass() {return PASS;}
	
	
	
	public static void main(String[] args) {
//		chargeStaff staff=new chargeStaff("004");
//		for(double f:staff.charge("A1608"))
//			System.out.printf("%.2f\n",f);
//		long l=System.currentTimeMillis();
//		Date date=new Date(l);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String nyr = dateFormat.format(date);
//		System.out.println(nyr);
		double d=0.1111111111;
		System.out.println(String.format("%.2f",d));
	}
}
