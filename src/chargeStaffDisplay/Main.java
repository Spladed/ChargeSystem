package chargeStaffDisplay;

import db.select;
import staff.chargeStaff;
import cs.Display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class Main {	
	private static String houseID=null;
	private static String ownerName=null;
	
	public static void Display(chargeStaff cs) {
		String user=cs.getUser();
		String pass=cs.getPass();
		
		
				
		//房屋信息要查询的内容
		String[] houseSelectContent= {"house_id","owner_name"};		
		//房屋信息的查询约束
		String[] houseSelectInfo= {"house.owner_id","house_owner.owner_id"};
				
		List<HashMap<String,Object>> houseInfo=select.selectSet(user, pass, "house,house_owner", houseSelectContent, houseSelectInfo);
		
		//初始化房号和业主姓名
		houseID=(String)houseInfo.get(0).get("house_id");
		ownerName=(String)houseInfo.get(0).get("owner_name");
		//账单表要查询的内容
		String[] billSelectContent= {"time_stamp","property","clean","water","electricity","state"};
		//账单信息的查询约束
		String[] billSelectInfo= {"house_id","\""+houseID+"\""};
		
		
		JFrame jf=new JFrame("收费系统");
		jf.setResizable(false);
	    jf.setLocationRelativeTo(null);
	    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    //右侧收费信息
	    //垂直Box里套一个水平Box（rightTopPanel）、一个面板（rightMiddlePanel）、一个流式布局面板（rightBottomPanel）
	    //水平Box（rightTopPanel）里套了一个rightTopLabelPanel和一个rightTopButtonPanel；rightTopLabelPanel是Label标签的容器，左对齐；rightTopButtonPanel是Button标签的容器，右对齐
	    //面板（rightMiddlePanel）里放了一个滚动面板（scrollPane），滚动面板里放着表格
	    //流式布局面板（rightBottomPanel）是右对齐，里面放了一个Button
	    Box rightBox=Box.createVerticalBox();	    
	    //上方左侧的当前住户显示
	    Box rightTopPanel=Box.createHorizontalBox();
	    JPanel rightTopLabelPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
	    {
	    	rightTopLabelPanel.add(myLabel.normalLabel("当前房号："));
	    	rightTopLabelPanel.add(myLabel.normalLabel(houseID));
	    	rightTopLabelPanel.add(myLabel.normalLabel("业主："));
	    	rightTopLabelPanel.add(myLabel.normalLabel(ownerName));
	    }
	    //上方右侧的按钮
	    JPanel rightTopButtonPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    {
	    	JButton btn=myButton.normalButton("收费");
	    	//添加事件点击事件，点击后打开收费界面
	    	btn.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			});
	    	rightTopButtonPanel.add(btn);
	    }
	    rightTopPanel.add(rightTopLabelPanel);
	    rightTopPanel.add(rightTopButtonPanel);
	    rightTopPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    
	    //右侧中间的费用信息的表单	    
	    String[] columnNames= {"日期","物业费","卫生费","水费","电费","状态"};	    
	    List<HashMap<String,Object>> billInfo=select.selectSet(user, pass, "bill", billSelectContent, billSelectInfo);
	    JTable table=myTable.normalTable(billInfo, columnNames);
	    table.setPreferredScrollableViewportSize(new Dimension(350, 300));
	    JScrollPane scrollPane = new JScrollPane(table);	       
	    //右侧中间的容器面板
	    JPanel rightMiddlePanel=new JPanel();
	    rightMiddlePanel.setBorder(BorderFactory.createTitledBorder("费用明细"));
	    rightMiddlePanel.add(scrollPane);

	    
	    //右侧底部的刷新按钮
	    JPanel rightBottomPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));	    
	    JButton refresh=new JButton("刷新");
	    refresh.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				rightMiddlePanel.removeAll();
			    String[] selectInfo= {"house_id","\""+houseID+"\""};
			    List<HashMap<String,Object>> currentBill=select.selectSet(user, pass, "bill", billSelectContent, selectInfo);				    	
			    JTable newTable=myTable.normalTable(currentBill, columnNames);
			    newTable.setPreferredScrollableViewportSize(new Dimension(350,250));
			    JScrollPane newScrollPane=new JScrollPane(newTable);
			    rightMiddlePanel.add(newScrollPane);
			    rightMiddlePanel.updateUI();
			}
		});
	    JButton quit=new JButton("退出登录");
	    quit.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				Display.main(null);
				jf.dispose();				
			}
		});
	    rightBottomPanel.add(refresh);
	    rightBottomPanel.add(quit);
	    
	    rightBox.add(rightTopPanel);
	    rightBox.add(rightMiddlePanel);
	    rightBox.add(rightBottomPanel);
	    
	    	    
	    //左侧房号、业主
	    //JScrollPane里套一个Box，Box里套多个FlowLayout的Panel，Panel里套一个Button，一个Label
	    Box leftBox=Box.createVerticalBox();
	    JPanel leftTopPanel=new JPanel(new FlowLayout());
	    {
	    	leftTopPanel.add(myLabel.normalLabel("房号"));
	 	    leftTopPanel.add(new JLabel("    "));
	 	    leftTopPanel.add(myLabel.normalLabel("业主"));
	    }	   
	    leftBox.add(leftTopPanel);
	    for(HashMap<String,Object> m:houseInfo) {
	    	JPanel containerPanel=new JPanel(new FlowLayout());
	    	{
	    		//Button是房号，Label是业主
	    		JButton btn=myButton.normalButton((String)m.get(houseSelectContent[0]));
	    		JLabel label=myLabel.normalLabel((String)m.get(houseSelectContent[1]));
	    		//添加按钮的点击事件监听器,使点击后houseID变成按钮显示的ID,ownerName变成label显示的名字，并重新绘制屏幕
	    		btn.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						houseID=btn.getText();
	    				ownerName=label.getText();
						//右侧上方的业主信息显示重绘
						rightTopLabelPanel.removeAll();
						rightTopLabelPanel.add(myLabel.normalLabel("当前房号："));
						rightTopLabelPanel.add(myLabel.normalLabel(houseID));
						rightTopLabelPanel.add(myLabel.normalLabel("业主："));
						rightTopLabelPanel.add(myLabel.normalLabel(ownerName));
				    	//右侧中间的收费信息显示重绘
				    	rightMiddlePanel.removeAll();
				    	String[] selectInfo= {"house_id","\""+btn.getText()+"\""};
				    	List<HashMap<String,Object>> currentBill=select.selectSet(user, pass, "bill", billSelectContent, selectInfo);				    	
				    	JTable newTable=myTable.normalTable(currentBill, columnNames);
				    	newTable.setPreferredScrollableViewportSize(new Dimension(350,250));
				    	JScrollPane newScrollPane=new JScrollPane(newTable);
				    	rightMiddlePanel.add(newScrollPane);
					    
				    	rightTopLabelPanel.updateUI();
					    rightMiddlePanel.updateUI();
					    
					}
				});	    		
	    		containerPanel.add(btn);
		    	containerPanel.add(label);
	    	}
	    	leftBox.add(containerPanel);
	    }
	    JScrollPane scrollPane1 = new JScrollPane(leftBox,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);   
	    	    
	    //总布局
	    Box mainBox=Box.createHorizontalBox();
	    mainBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    mainBox.add(scrollPane1);
	    mainBox.add(rightBox);
	    
	    jf.setContentPane(mainBox);
        jf.pack();
        jf.setVisible(true);
	}
	public static void main(String[] args) {
		chargeStaff cs=new chargeStaff("011");
		Display(cs);
	}
}
