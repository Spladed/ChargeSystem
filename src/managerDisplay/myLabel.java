package managerDisplay;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class myLabel {
	
	public static JLabel normalLabel(String str) {
		JLabel label=new JLabel(str,SwingConstants.CENTER);
		label.setSize(50, 50);
		label.setFont(new Font(null,Font.PLAIN,15));
		return label;
	}
}
