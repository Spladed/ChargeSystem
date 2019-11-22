package cs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display {
	public static void main(String[] args) {
		JFrame jf=new JFrame("��¼");
		
		jf.setSize(300, 300);
	    jf.setLocationRelativeTo(null);
	    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JPanel panel1=new JPanel();
		JTextField jtf=new JTextField(20);	
		panel1.add(new JLabel("Ա����"));
		panel1.add(jtf);
		
		JPanel panel2=new JPanel();
		JPasswordField jpf=new JPasswordField(20);
		panel2.add(new JLabel("��    ��"));
		panel2.add(jpf);
		
		JPanel panel3=new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton login=new JButton("��¼");
		login.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String user=jtf.getText();
				String pass=new String(jpf.getPassword());
				if(logIn.in(user, pass))
					System.out.println("success");
				else
					System.out.println("failed");
			}
		});
		panel3.add(login);
		
		
		Box vBox=Box.createVerticalBox();
		vBox.add(panel1);
		vBox.add(panel2);
		vBox.add(panel3);
		
		jf.setContentPane(vBox);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
}
