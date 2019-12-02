package managerDisplay;
import staff.Manager;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import db.select;

public class Main {
	public static void Display(Manager manager) {
		String user=manager.getUser();
		String pass=manager.getPass();
		String[] content= {"staff_id","staff_name","phone","gender"};
		String[] selectInfo= {"department_id","\""+manager.getDepartmentID()+"\""};
		
		JFrame jf=new JFrame("Ա������ϵͳ");
		jf.setSize(1000, 1000);
		jf.setResizable(false);
	    jf.setLocationRelativeTo(null);
	    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    
	    Box head=Box.createHorizontalBox();
	    {   
	    	head.add(myLabel.label("Ա����"));
	    	head.add(myLabel.label("����"));
	    	head.add(myLabel.label("�绰"));
	    	head.add(myLabel.label("�Ա�"));
	    	JLabel label=new JLabel("����");
	    	label.setPreferredSize(new Dimension(100, 20));
	    	head.add(label);
	    }	    
	    Box body=Box.createVerticalBox();	    
		List<HashMap<String,Object>> recieve=select.selectSet(user, pass, "staff", content, selectInfo);
		for(int i=0;i<recieve.size();i++) {
			Box container=Box.createHorizontalBox();
			HashMap<String,Object> m=recieve.get(i);
			for(int j=0;j<content.length;j++) {
				container.add(myLabel.label((String)m.get(content[j])));
			}
			//����޸İ�ť�Լ���Ӧ�İ����¼�
			JButton btn1=new JButton("�޸���Ϣ");
			btn1.setPreferredSize(new Dimension(50, 10));
			btn1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			container.add(btn1);
			//���ɾ����ť�Լ���Ӧ�İ����¼�
			JButton btn2=new JButton("ɾ��");
			btn2.setPreferredSize(new Dimension(50, 10));
			btn2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			container.add(btn2);			
			body.add(container);
		}
		
		Box main=Box.createVerticalBox();
		main.add(head);
		main.add(body);
		
		jf.setContentPane(main);
		jf.pack();
        jf.setVisible(true);
	}
	public static void main(String[] args) {
		Manager m=new Manager("011");
		Display(m);
	}
}
