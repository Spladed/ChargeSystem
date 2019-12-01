package managerDisplay;

import java.awt.Dimension;

import javax.swing.JLabel;

public class myLabel {
	public static JLabel label(String str) {
		JLabel label=new JLabel(str);
		Dimension preferredSize = new Dimension(50,20);
    	label.setPreferredSize(preferredSize );
		return label;
	}
}
