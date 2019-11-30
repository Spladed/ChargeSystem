package chargeStaffDisplay;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class myButton {
	public static JButton Button(String str) {
		JButton btn=new JButton(str);
		btn.setBorder(null);
    	btn.setBorderPainted(false);
    	btn.setContentAreaFilled(false);
    	btn.setForeground(Color.BLUE);
    	Dimension preferredSize = new Dimension(50,20);//…Ë÷√≥ﬂ¥Á
    	btn.setPreferredSize(preferredSize );
		return btn;
	}
}
