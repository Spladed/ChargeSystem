package managerDisplay;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class test {
	public static void main(String[] args) {
		JFrame jf=new JFrame("���Դ���");
		jf.setSize(350,500);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//ÿ����ǩ��50����20
		JPanel panel=new JPanel(null);
		panel.setSize(350, 500);

		{
			JLabel label1=myLabel.label("Ա����");
			label1.setLocation(0, 0);		
			JLabel label2=myLabel.label("����");
			label2.setLocation(50, 0);		
			JLabel label3=new JLabel("��ϵ�绰",JLabel.CENTER);
			label3.setSize(100, 20);
			label3.setLocation(100, 0);
			label3.setBorder(BorderFactory.createLineBorder(Color.BLACK));		
			JLabel label4=myLabel.label("�Ա�");
			label4.setLocation(200, 0);		
			JLabel label5=new JLabel("����",JLabel.CENTER);
			label5.setSize(100, 20);
			label5.setLocation(250, 0); 
			label5.setBorder(BorderFactory.createLineBorder(Color.BLACK));		
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			panel.add(label4);
			panel.add(label5);
		}
		
		jf.add(panel);
		jf.setContentPane(panel);
        jf.setVisible(true);
	}
}
