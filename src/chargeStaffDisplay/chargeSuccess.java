package chargeStaffDisplay;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class chargeSuccess {
	public static void showFailedWindow(JFrame relativeWindow) {
		JFrame newJFrame=new JFrame();
		newJFrame.setSize(100,100);
		newJFrame.setLocationRelativeTo(relativeWindow);
		newJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		newJFrame.setResizable(false);
		
		Box box=Box.createVerticalBox();
		
		JPanel panel1=new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel label=new JLabel("收费成功");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		
		JPanel panel2=new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton btn=myButton.normalButton("确定");
		btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newJFrame.dispose();
			}
		});
		
		panel1.add(label);
		panel2.add(btn);
		
		box.add(panel1);
		box.add(panel2);
		newJFrame.setContentPane(box);
		newJFrame.setVisible(true);
	}
}
