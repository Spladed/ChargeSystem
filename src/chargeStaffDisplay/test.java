package chargeStaffDisplay;

import db.select;
import staff.chargeStaff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class test {	
	private static String houseID=null;
	private static String ownerName=null;
	
	public static void Display(chargeStaff cs) {
		String user=cs.getUser();
		String pass=cs.getPass();
		
		//�˵���Ҫ��ѯ������
		String[] billSelectContent= {"time_stamp","property","clean","water","electricity","state"};
		//������ϢҪ��ѯ������
		String[] houseSelectContent= {"house_id","owner_name"};
		
		//������Ϣ�Ĳ�ѯԼ��
		String[] houseSelectInfo= {"house.owner_id","house_owner.owner_id"};
		//�˵���Ϣ�Ĳ�ѯԼ��
		String[] billSelectInfo= {"house_id","\""+houseID+"\""};
		
		List<HashMap<String,Object>> houseInfo=select.selectSet(user, pass, "house,house_owner", houseSelectContent, houseSelectInfo);
		
		houseID=(String)houseInfo.get(0).get("house_id");
		ownerName=(String)houseInfo.get(0).get("owner_name");
		
		
		JFrame jf=new JFrame("�շ�ϵͳ");
		jf.setSize(1000, 300);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(null);
	    
	    //�Ҳ��շ���Ϣ
	    //��ֱBox����һ��ˮƽBox��rightTopPanel����һ����壨rightMiddlePanel����һ����ʽ������壨rightBottomPanel��
	    //ˮƽBox��rightTopPanel��������һ��rightTopLabelPanel��һ��rightTopButtonPanel��rightTopLabelPanel��Label��ǩ������������룻rightTopButtonPanel��Button��ǩ���������Ҷ���
	    //��壨rightMiddlePanel�������һ��������壨scrollPane���������������ű��
	    //��ʽ������壨rightBottomPanel�����Ҷ��룬�������һ��Button
	    
	    //�Ϸ��ĵ�ǰס����ʾ
	    {	    	
	    	int x=100,y=0;
	    	JLabel label1=myLabel.normalLabel("��ǰ����");
	    	label1.setLocation(x, y);
	    	x+=50;
	    	JLabel label2=myLabel.normalLabel(houseID);
	    	label2.setLocation(x, y);
	    	x+=50;
	    	JLabel label3=myLabel.normalLabel("ҵ��");
	    	label3.setLocation(x, y);
	    	x+=50;
	    	JLabel label4=myLabel.normalLabel("ownerName");
	    	label4.setLocation(x, y);
	    	x+=100;
	    	JButton btn=myButton.normalButton("�շ�");
	    	btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
	    	btn.setLocation(x, y);
	    	mainPanel.add(label1);
	    	mainPanel.add(label2);
	    	mainPanel.add(label3);
	    	mainPanel.add(label4);
	    	mainPanel.add(btn);
	    }
	    
	    
	    //�м�ķ�����Ϣ�ı�
	    String[] columnNames= {"����","��ҵ��","������","ˮ��","���","״̬"};	    
	    List<HashMap<String,Object>> billInfo=select.selectSet(user, pass, "bill", billSelectContent, billSelectInfo);
	    Object[][] rowData=new Object[billInfo.size()][6];
	    for(int i=0;i<billInfo.size();i++) {
	    	HashMap<String,Object> temp=billInfo.get(i);
	    	for(int j=0;j<6;j++) {
	    		rowData[i][j]=temp.get(billSelectContent[i]);
	    	}
	    }
	    JTable table=new JTable(rowData,columnNames);
	    table.setPreferredScrollableViewportSize(new Dimension(350, 200));
	    table.setLocation(100,20);
	    mainPanel.add(table);
	    
	    //�Ҳ�ײ���ˢ�°�ť
	    {
	    	JButton btn=myButton.borderButton("ˢ��");
	    	btn.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub					
				}
			});
	    	btn.setLocation(100, 220);
	    	mainPanel.add(btn);
	    }
	 
	    
	    //��෿�š�ҵ��
	    
	    {	
	    	//ͷ����Ϣ
	    	int x=0,y=0;
	    	JLabel label1=myLabel.borderLabel("����");
	    	label1.setLocation(x, y);
	    	x=x+50;
	    	JLabel label2=myLabel.borderLabel("ҵ��");
	    	label2.setLocation(x, y);
	    	mainPanel.add(label1);
	    	mainPanel.add(label2);
	    }
	    {
	    	int y=20;
	    	for(HashMap<String,Object> m:houseInfo) {
	    		int x=0;
	    		JButton btn=myButton.borderButton(((String)m.get(houseSelectContent[0])));
	    		btn.setLocation(x, y);
	    		btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
	    		x=x+50;
	    		JLabel label=myLabel.borderLabel((String)m.get(houseSelectContent[1]));
	    		label.setLocation(x,y);
	    		y=y+20;
	    		mainPanel.add(btn);
	    		mainPanel.add(label);
	    	}
	    }
	    
    
	    jf.setContentPane(mainPanel);
        //jf.pack();
        jf.setVisible(true);
	}
	public static void main(String[] args) {
		chargeStaff cs=new chargeStaff("011");
		Display(cs);
	}
}
