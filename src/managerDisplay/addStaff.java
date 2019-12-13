package managerDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import staff.Manager;

public class addStaff {
	public static void add(Manager m) {
		JFrame jf = new JFrame("测试窗口");
		jf.setSize(455, 500);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 创建内容面板，使用边界布局
        JPanel panel = new JPanel(null);
        
        // 表头（列名）
        Object[] columnNames = {"",""};

        // 表格所有行数据
        Object[][] rowData = {
        						{"员工号",""},
        						{"姓名",""},
        						{"生日",""},
        						{"性别",""},
        						{"住址",""},
        						{"手机号",""},
        						{"密码",""}
        						};

        // 创建一个表格，指定 所有行数据 和 表头
        JTable table = new JTable(rowData, columnNames);
        //设置行高
        table.setRowHeight(50);
        //设置列宽
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(40);
        // 设置table内容居中
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	    tcr.setHorizontalAlignment(SwingConstants.CENTER);
	    table.setDefaultRenderer(Object.class, tcr);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(400, 360);
        scrollPane.setLocation(20, 20);
        panel.add(scrollPane);
        
        JButton btn=myButton.borderButton("添加");
        btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] value=new String[9];
				for(int i=0;i<7;i++) {					
					value[i]=(String)table.getValueAt(i, 1);
					if(value[i].equals("男"))
						value[i]="M";
					if(value[i].equals("女"))
						value[i]="F";
				}
				value[7]=m.getDepartmentID();
				value[8]="S";
				DAO.addStaff(m.getUser(), m.getPass(), value);
				JOptionPane.showMessageDialog(null, "完成");
				jf.dispose();
			}
		});
        btn.setLocation(320, 395);
        panel.add(btn);

        jf.setContentPane(panel);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
	}
}
