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
		
		
				
		//������ϢҪ��ѯ������
		String[] houseSelectContent= {"house_id","owner_name"};		
		//������Ϣ�Ĳ�ѯԼ��
		String[] houseSelectInfo= {"house.owner_id","house_owner.owner_id"};
				
		List<HashMap<String,Object>> houseInfo=select.selectSet(user, pass, "house,house_owner", houseSelectContent, houseSelectInfo);
		
		//��ʼ�����ź�ҵ������
		houseID=(String)houseInfo.get(0).get("house_id");
		ownerName=(String)houseInfo.get(0).get("owner_name");
		//�˵���Ҫ��ѯ������
		String[] billSelectContent= {"time_stamp","property","clean","water","electricity","state"};
		//�˵���Ϣ�Ĳ�ѯԼ��
		String[] billSelectInfo= {"house_id","\""+houseID+"\""};
		
		
		JFrame jf=new JFrame("�շ�ϵͳ");
		jf.setResizable(false);
	    jf.setLocationRelativeTo(null);
	    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    //�Ҳ��շ���Ϣ
	    //��ֱBox����һ��ˮƽBox��rightTopPanel����һ����壨rightMiddlePanel����һ����ʽ������壨rightBottomPanel��
	    //ˮƽBox��rightTopPanel��������һ��rightTopLabelPanel��һ��rightTopButtonPanel��rightTopLabelPanel��Label��ǩ������������룻rightTopButtonPanel��Button��ǩ���������Ҷ���
	    //��壨rightMiddlePanel�������һ��������壨scrollPane���������������ű��
	    //��ʽ������壨rightBottomPanel�����Ҷ��룬�������һ��Button
	    Box rightBox=Box.createVerticalBox();	    
	    //�Ϸ����ĵ�ǰס����ʾ
	    Box rightTopPanel=Box.createHorizontalBox();
	    JPanel rightTopLabelPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
	    {
	    	rightTopLabelPanel.add(myLabel.normalLabel("��ǰ���ţ�"));
	    	rightTopLabelPanel.add(myLabel.normalLabel(houseID));
	    	rightTopLabelPanel.add(myLabel.normalLabel("ҵ����"));
	    	rightTopLabelPanel.add(myLabel.normalLabel(ownerName));
	    }
	    //�Ϸ��Ҳ�İ�ť
	    JPanel rightTopButtonPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    {
	    	JButton btn=myButton.normalButton("�շ�");
	    	//����¼�����¼����������շѽ���
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
	    
	    //�Ҳ��м�ķ�����Ϣ�ı�	    
	    String[] columnNames= {"����","��ҵ��","������","ˮ��","���","״̬"};	    
	    List<HashMap<String,Object>> billInfo=select.selectSet(user, pass, "bill", billSelectContent, billSelectInfo);
	    JTable table=myTable.normalTable(billInfo, columnNames);
	    table.setPreferredScrollableViewportSize(new Dimension(350, 300));
	    JScrollPane scrollPane = new JScrollPane(table);	       
	    //�Ҳ��м���������
	    JPanel rightMiddlePanel=new JPanel();
	    rightMiddlePanel.setBorder(BorderFactory.createTitledBorder("������ϸ"));
	    rightMiddlePanel.add(scrollPane);

	    
	    //�Ҳ�ײ���ˢ�°�ť
	    JPanel rightBottomPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));	    
	    JButton refresh=new JButton("ˢ��");
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
	    JButton quit=new JButton("�˳���¼");
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
	    
	    	    
	    //��෿�š�ҵ��
	    //JScrollPane����һ��Box��Box���׶��FlowLayout��Panel��Panel����һ��Button��һ��Label
	    Box leftBox=Box.createVerticalBox();
	    JPanel leftTopPanel=new JPanel(new FlowLayout());
	    {
	    	leftTopPanel.add(myLabel.normalLabel("����"));
	 	    leftTopPanel.add(new JLabel("    "));
	 	    leftTopPanel.add(myLabel.normalLabel("ҵ��"));
	    }	   
	    leftBox.add(leftTopPanel);
	    for(HashMap<String,Object> m:houseInfo) {
	    	JPanel containerPanel=new JPanel(new FlowLayout());
	    	{
	    		//Button�Ƿ��ţ�Label��ҵ��
	    		JButton btn=myButton.normalButton((String)m.get(houseSelectContent[0]));
	    		JLabel label=myLabel.normalLabel((String)m.get(houseSelectContent[1]));
	    		//��Ӱ�ť�ĵ���¼�������,ʹ�����houseID��ɰ�ť��ʾ��ID,ownerName���label��ʾ�����֣������»�����Ļ
	    		btn.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						houseID=btn.getText();
	    				ownerName=label.getText();
						//�Ҳ��Ϸ���ҵ����Ϣ��ʾ�ػ�
						rightTopLabelPanel.removeAll();
						rightTopLabelPanel.add(myLabel.normalLabel("��ǰ���ţ�"));
						rightTopLabelPanel.add(myLabel.normalLabel(houseID));
						rightTopLabelPanel.add(myLabel.normalLabel("ҵ����"));
						rightTopLabelPanel.add(myLabel.normalLabel(ownerName));
				    	//�Ҳ��м���շ���Ϣ��ʾ�ػ�
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
	    	    
	    //�ܲ���
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
