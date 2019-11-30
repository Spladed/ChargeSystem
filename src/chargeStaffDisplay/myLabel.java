package chargeStaffDisplay;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class myLabel {
	public static JLabel Label(String str) {
		JLabel label=new JLabel(str,SwingConstants.CENTER);
		Dimension preferredSize = new Dimension(50,20);
    	label.setPreferredSize(preferredSize );
		return label;
	}
}
