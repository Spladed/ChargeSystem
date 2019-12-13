package managerDisplay;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class myButton {
	public static JButton borderButton(String str) {
		JButton btn=new JButton(str);
		btn.setSize(100, 50);
		btn.setBackground(Color.WHITE);
		return btn;
	}
	
	public static JButton normalButton(String str) {
		JButton btn=new JButton(str);
		btn.setSize(100, 50);
		btn.setContentAreaFilled(false);
		btn.setBorder(null);
		btn.setFont(new Font(null,Font.PLAIN,15));
		return btn;
	}
}
