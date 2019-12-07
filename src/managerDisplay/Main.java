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
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import db.select;

public class Main {
	public static void Display(Manager manager) {
		String user=manager.getUser();
		String pass=manager.getPass();
		String[] content= {"staff_id","staff_name","phone","gender"};
		String[] selectInfo= {"department_id","\""+manager.getDepartmentID()+"\""};
		
		JFrame jf = new JFrame("���Դ���");
        jf.setSize(300, 300);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ����������壬ָ������Ϊ null����ʹ�þ��Բ���
        JPanel panel = new JPanel(null);



        // ��ʾ����
        jf.setContentPane(panel);
        jf.setVisible(true);


	}
	public static void main(String[] args) {
		Manager m=new Manager("011");
		Display(m);
	}
}
