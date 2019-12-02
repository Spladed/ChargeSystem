package chargeStaffDisplay;

import javax.swing.JFrame;
import javax.swing.JPanel;

import staff.chargeStaff;

public class charge {
	public static void display(String houseID,String ownerName,chargeStaff cs) {
		JFrame jf=new JFrame("收费系统");
		jf.setResizable(false);
	    jf.setLocationRelativeTo(null);
	    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    jf.setSize(500, 700);
	    
	    JPanel panel=new JPanel();
	}
}
