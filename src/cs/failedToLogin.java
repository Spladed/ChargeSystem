package cs;

import java.awt.FlowLayout;

import javax.swing.*;

public class failedToLogin {
	public static void showFailedWindow(JFrame relativeWindow) {
		JFrame newJFrame=new JFrame("��½ʧ��");
		newJFrame.setSize(100,100);
		newJFrame.setLocationRelativeTo(relativeWindow);
		newJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		newJFrame.setResizable(false);
		JPanel panel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel label=new JLabel("Ա���Ż��������");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		panel.add(label);
		newJFrame.setContentPane(panel);
		newJFrame.setVisible(true);
	}
}
