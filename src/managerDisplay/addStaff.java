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
		JFrame jf = new JFrame("���Դ���");
		jf.setSize(455, 500);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ����������壬ʹ�ñ߽粼��
        JPanel panel = new JPanel(null);
        
        // ��ͷ��������
        Object[] columnNames = {"",""};

        // �������������
        Object[][] rowData = {
        						{"Ա����",""},
        						{"����",""},
        						{"����",""},
        						{"�Ա�",""},
        						{"סַ",""},
        						{"�ֻ���",""},
        						{"����",""}
        						};

        // ����һ�����ָ�� ���������� �� ��ͷ
        JTable table = new JTable(rowData, columnNames);
        //�����и�
        table.setRowHeight(50);
        //�����п�
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(40);
        // ����table���ݾ���
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	    tcr.setHorizontalAlignment(SwingConstants.CENTER);
	    table.setDefaultRenderer(Object.class, tcr);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(400, 360);
        scrollPane.setLocation(20, 20);
        panel.add(scrollPane);
        
        JButton btn=myButton.borderButton("���");
        btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] value=new String[9];
				for(int i=0;i<7;i++) {					
					value[i]=(String)table.getValueAt(i, 1);
					if(value[i].equals("��"))
						value[i]="M";
					if(value[i].equals("Ů"))
						value[i]="F";
				}
				value[7]=m.getDepartmentID();
				value[8]="S";
				DAO.addStaff(m.getUser(), m.getPass(), value);
				JOptionPane.showMessageDialog(null, "���");
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
