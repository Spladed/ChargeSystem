package chargeStaffDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class chargeSuccess {
	public static void showFailedWindow(JFrame relativeWindow) {
		JFrame newJFrame=new JFrame();
		newJFrame.setSize(100,100);
		newJFrame.setLocationRelativeTo(relativeWindow);
		newJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		newJFrame.setResizable(false);
		
		Box box=Box.createVerticalBox();
		JLabel label=new JLabel("收费成功");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		
		JButton btn=new JButton("确定");
		btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newJFrame.dispose();
			}
		});
		
		box.add(label);
		box.add(btn);
		newJFrame.setContentPane(box);
		newJFrame.setVisible(true);
	}
}
