package managerDisplay;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class myLabel {
	public static JLabel borderLabel(String str) {
		JLabel label=new JLabel(str,SwingConstants.CENTER);
		label.setSize(100, 50);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return label;
	}
	
	public static JLabel normalLabel(String str) {
		JLabel label=new JLabel(str,SwingConstants.CENTER);
		label.setSize(200, 100);
		label.setFont(new Font(null,Font.PLAIN,15));
		return label;
	}
}
