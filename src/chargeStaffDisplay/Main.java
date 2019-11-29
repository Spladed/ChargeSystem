package chargeStaffDisplay;

import db.select;
import staff.chargeStaff;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;

public class Main {
	public static void Display(chargeStaff cs) {
		String user=cs.getUser();
		String pass=cs.getPass();
		String houseID=null;
		String ownerName=null;
		String[] chargeSelectContent= {"time_stamp","property","clean","water","electricity","state"};
		String[] houseSelectContent= {"house_id","owner_name"};
		String[] houseSelectInfo= {"house.owner_id","house_owner.owner_id"};
		String[] billSelectInfo= {"house_id","\""+houseID+"\""};
		List<HashMap<String,Object>> houseInfo=select.selectSet(user, pass, "bill", houseSelectContent, houseSelectInfo);
		
		JFrame jf=new JFrame("收费系统");
		jf.setResizable(false);
	    jf.setLocationRelativeTo(null);
	    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    
	    //左侧房号、业主
	    //Box里套一个JScrollPane，JScrollPane里套一个GridLayout的Panel
	    Box leftBox=Box.createVerticalBox();
	    JPanel panel1=new JPanel(new GridLayout(houseInfo.size(), 2));
	    panel1.add(new JLabel("房号"));
	    panel1.add(new JLabel("业主"));
	    for(HashMap<String,Object> m:houseInfo) {
	    	JButton btn=new JButton((String)m.get(houseSelectContent[0]));
	    	JLabel label=new JLabel((String)m.get(houseSelectContent[1]));
	    	panel1.add(btn);
	    	panel1.add(label);
	    }
	    JScrollPane scrollPane = new JScrollPane(panel1,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    leftBox.add(scrollPane);    
	    
	    //右侧收费信息
	    //Box里套一个FlowLayOut的Panel，一个Table，和最下面的一个Box；Panel里套四个Label和一个button，Box里套一个Button
	    Box rightBox=Box.createVerticalBox();
	    JPanel panel2=new JPanel(new FlowLayout());
	    panel2.add(new JLabel("当前房号："));
	    panel2.add(new JLabel(houseID));
	    panel2.add(new JLabel("业主："));
	    panel2.add(new JLabel(ownerName));
	    JButton btn=new JButton("收费");
	    panel2.add(btn);
	    panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    List<HashMap<String,Object>> billInfo=select.selectSet(user, pass, "bill", chargeSelectContent, billSelectInfo);
	    for(HashMap<String,Object> m:billInfo) {
	    	
	    }
	    
	    
	    
	    //总布局
	    Box mainBox=Box.createHorizontalBox();
	    mainBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    mainBox.add(leftBox);
	    mainBox.add(rightBox);
	    
	    jf.setContentPane(mainBox);
        jf.pack();
        jf.setVisible(true);
	}
}
