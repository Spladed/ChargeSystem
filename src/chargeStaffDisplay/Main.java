package chargeStaffDisplay;

import db.select;
import staff.chargeStaff;

import java.awt.Color;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {
	public static void Display(chargeStaff cs) {
		String user=cs.getUser();
		String pass=cs.getPass();
		String house_id=null;
		//String[] content= {"time_stamp","property","clean","water","electricity","state"};
		String[] content= {"house_id","owner_name"};
		String[] selectInfo= {"house.owner_id","house_owner.owner_id"};
		List<HashMap<String,Object>> houseInfo=select.selectSet(user, pass, "bill", content, selectInfo);
		
		JFrame jf=new JFrame("收费系统");
		jf.setResizable(false);
	    jf.setLocationRelativeTo(null);
	    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    	    
	    Box leftBox=Box.createVerticalBox();
	    
	    
	    Box rightBox=Box.createVerticalBox();
	    
	    
	    Box mainBox=Box.createHorizontalBox();
	    mainBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    mainBox.add(leftBox);
	    mainBox.add(rightBox);
	    
	    jf.setContentPane(mainBox);
        jf.pack();
        jf.setVisible(true);
	}
}
