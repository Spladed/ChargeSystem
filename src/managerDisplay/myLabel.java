package managerDisplay;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class myLabel {
	public static JLabel label(String str) {
		JLabel label=new JLabel(str,JLabel.CENTER);
		label.setSize(50, 20);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return label;
	}
}
