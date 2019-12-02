package chargeStaffDisplay;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class myLabel {
	public static JLabel borderLabel(String str) {
		JLabel label=new JLabel(str,SwingConstants.CENTER);
		label.setSize(100, 50);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return label;
	}
	
	public static JLabel normalLabel(String str) {
		JLabel label=new JLabel(str,SwingConstants.CENTER);
		label.setSize(100, 50);
		return label;
	}
}
