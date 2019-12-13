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

public class updateStaff {
	public static void update(Manager m,String staffID) {
		JFrame jf = new JFrame("测试窗口");
		jf.setSize(455, 500);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // 创建内容面板，使用边界布局
        JPanel panel = new JPanel(null);
        
        // 表头（列名）
        Object[] columnNames = {"",""};
        
        Object[] recieve=DAO.getStaffDetail(m.getUser(), m.getPass(), staffID);
        
        if(recieve[3].equals("M"))
        	recieve[3]="男";
        if(recieve[3].equals("F"))
        	recieve[3]="女";
        
        // 表格所有行数据
        Object[][] rowData = {
        						{"员工号",recieve[0]},
        						{"姓名",recieve[1]},
        						{"生日",recieve[2]},
        						{"性别",recieve[3]},
        						{"住址",recieve[4]},
        						{"手机号",recieve[5]},
        						{"密码",recieve[6]}
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
	    
	    JButton btn=myButton.borderButton("确定");
        btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] value=new String[7];
				for(int i=0;i<7;i++) {					
					value[i]=String.valueOf(table.getValueAt(i, 1));
					if(value[i].equals("男"))
						value[i]="M";
					if(value[i].equals("女"))
						value[i]="F";
				}
				for(int i=0;i<value.length;i++) {
					value[i]="\""+value[i]+"\"";
				}
				DAO.updateStaff(m.getUser(), m.getPass(), staffID,value);
				JOptionPane.showMessageDialog(null, "完成");
				jf.dispose();
			}
		});
        btn.setLocation(320, 395);
        panel.add(btn);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(400, 360);
        scrollPane.setLocation(20, 20);
        panel.add(scrollPane);
        
        jf.setContentPane(panel);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
	}
}
