package chargeStaffDisplay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import staff.chargeStaff;

public class charge {
	public static void display(String houseID,String ownerName,chargeStaff cs) {
		JFrame jf = new JFrame("测试窗口");
        jf.setSize(520, 520);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建内容面板，指定布局为 null，则使用绝对布局
        JPanel panel = new JPanel(null);
        
        JPanel insidePanel=new JPanel(null);
        insidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        insidePanel.setBackground(Color.WHITE);
        insidePanel.setSize(460, 380);
        insidePanel.setLocation(20, 20);
        
        panel.add(insidePanel);

        JLabel label1=myLabel.normalLabel("房号："+houseID);
        label1.setLocation(0, 0);
        insidePanel.add(label1);
        
        JLabel label2=myLabel.normalLabel("业主："+ownerName);
        label2.setLocation(360, 0);
        insidePanel.add(label2);
        
        long l=System.currentTimeMillis();
		Date date=new Date(l);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String time = dateFormat.format(date);
        JLabel label3=new JLabel("日期："+time,SwingConstants.CENTER);
        label3.setSize(150,50);
        label3.setLocation(0, 324);
        insidePanel.add(label3);
        
        JLabel label4=myLabel.normalLabel("员工："+cs.getID());
        label4.setLocation(360, 324);
        insidePanel.add(label4);
        
        Object[] columnNames = {"序号", "收费类型", "数量", "金额"};

        // 表格所有行数据
        Object[][] rowData = {
                {"1", "物业费", cs.houseInfo(houseID)[0], cs.Charge(houseID)[0]},
                {"2", "卫生费", cs.houseInfo(houseID)[1], cs.Charge(houseID)[1]},
                {"3", "水费", null, null},
                {"4", "电费", null, null}
        };
        JTable table=myTable.chargeTable(rowData, columnNames);        
        JPanel tablePanel=new JPanel(new BorderLayout());
        tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
        tablePanel.add(table, BorderLayout.CENTER);
        tablePanel.setSize(450, 224);
        tablePanel.setLocation(5, 50);
        //tablePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        
        
        
        //JTable table = new JTable(rowData, columnNames);
//        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
//	    tcr.setHorizontalAlignment(SwingConstants.CENTER);
//	    table.setDefaultRenderer(Object.class, tcr);
//        table.setRowHeight(50);
//        JScrollPane scrollPane=new JScrollPane(table);
//        scrollPane.setSize(450, 225);
//        scrollPane.setLocation(5, 50);
//        scrollPane.setBorder(null);
//        scrollPane.setBackground(null);
        insidePanel.add(tablePanel);
        
        JLabel label5=new JLabel("合计",SwingConstants.CENTER);
        label5.setSize(112, 50);
        label5.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        label5.setLocation(5, 274);
        insidePanel.add(label5);
        
        JLabel label6=new JLabel("",SwingConstants.CENTER);
        label6.setSize(224, 50);
        label6.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        label6.setLocation(117, 274);
        insidePanel.add(label6);
        
        JLabel label7=new JLabel("",SwingConstants.CENTER);
        label7.setSize(112, 50);
        label7.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        label7.setLocation(341, 274);
        insidePanel.add(label7);
        
        JButton btn1=new JButton("确定");
        btn1.setSize(100, 50);
        btn1.setLocation(270,410);
        btn1.setBackground(Color.WHITE);
        btn1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        panel.add(btn1);
        JButton btn2=new JButton("打印");
        btn2.setSize(100, 50);
        btn2.setLocation(380,410);
        btn2.setBackground(Color.WHITE);
        btn2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        panel.add(btn2);
        
        

        // 显示窗口
        jf.setContentPane(panel);
        jf.setVisible(true);

        /*
                *  也可以在 jf.setVisible(true) 之后添加按钮
         *
         * PS_01: jf.setVisible(true) 之后，内容面板才有宽高;
         * PS_02: 使用其他布局时, jf.setVisible(true) 之后添加的组件, 也会被当做是绝对布局来布置该组件（即需要手动指定坐标和宽高）;
         * PS_03: 使用其他布局时, jf.setVisible(true) 之前添加的组件, 如果在 jf.setVisible(true) 之后手动设置该组件的坐标和宽高,
         *	 会将该组件当做绝对布局来对待（即设置坐标和宽高会生效）。
         */
	}

	public static void main(String[] args) {
		display("A0101","sss",new chargeStaff("001"));
	}
}
