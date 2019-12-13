package chargeStaffDisplay;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class myButton {
	public static JButton normalButton(String str) {
		JButton btn=new JButton(str);
		btn.setBorder(null);
    	btn.setBorderPainted(false);
    	btn.setContentAreaFilled(false);
    	btn.setForeground(Color.BLUE);
    	btn.setSize(100, 50);
		return btn;
	}
}
