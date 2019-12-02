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
		
		//账单表要查询的内容
		String[] billSelectContent= {"time_stamp","property","clean","water","electricity","state"};
		//房屋信息要查询的内容
		String[] houseSelectContent= {"house_id","owner_name"};
		
		//房屋信息的查询约束
		String[] houseSelectInfo= {"house.owner_id","house_owner.owner_id"};
		//账单信息的查询约束
		String[] billSelectInfo= {"house_id","\""+houseID+"\""};
		
		List<HashMap<String,Object>> houseInfo=select.selectSet(user, pass, "house,house_owner", houseSelectContent, houseSelectInfo);
		
		houseID=(String)houseInfo.get(0).get("house_id");
		ownerName=(String)houseInfo.get(0).get("owner_name");
		
		
		JFrame jf=new JFrame("收费系统");
		jf.setSize(1000, 300);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(null);
	    
	    //右侧收费信息
	    //垂直Box里套一个水平Box（rightTopPanel）、一个面板（rightMiddlePanel）、一个流式布局面板（rightBottomPanel）
	    //水平Box（rightTopPanel）里套了一个rightTopLabelPanel和一个rightTopButtonPanel；rightTopLabelPanel是Label标签的容器，左对齐；rightTopButtonPanel是Button标签的容器，右对齐
	    //面板（rightMiddlePanel）里放了一个滚动面板（scrollPane），滚动面板里放着表格
	    //流式布局面板（rightBottomPanel）是右对齐，里面放了一个Button
	    
	    //上方的当前住户显示
	    {	    	
	    	int x=100,y=0;
	    	JLabel label1=myLabel.normalLabel("当前房号");
	    	label1.setLocation(x, y);
	    	x+=50;
	    	JLabel label2=myLabel.normalLabel(houseID);
	    	label2.setLocation(x, y);
	    	x+=50;
	    	JLabel label3=myLabel.normalLabel("业主");
	    	label3.setLocation(x, y);
	    	x+=50;
	    	JLabel label4=myLabel.normalLabel("ownerName");
	    	label4.setLocation(x, y);
	    	x+=100;
	    	JButton btn=myButton.normalButton("收费");
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
	    
	    
	    //中间的费用信息的表单
	    String[] columnNames= {"日期","物业费","卫生费","水费","电费","状态"};	    
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
	    
	    //右侧底部的刷新按钮
	    {
	    	JButton btn=myButton.borderButton("刷新");
	    	btn.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub					
				}
			});
	    	btn.setLocation(100, 220);
	    	mainPanel.add(btn);
	    }
	 
	    
	    //左侧房号、业主
	    
	    {	
	    	//头部信息
	    	int x=0,y=0;
	    	JLabel label1=myLabel.borderLabel("房号");
	    	label1.setLocation(x, y);
	    	x=x+50;
	    	JLabel label2=myLabel.borderLabel("业主");
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
