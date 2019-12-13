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
		JFrame jf = new JFrame("���Դ���");
		jf.setSize(455, 500);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // ����������壬ʹ�ñ߽粼��
        JPanel panel = new JPanel(null);
        
        // ��ͷ��������
        Object[] columnNames = {"",""};
        
        Object[] recieve=DAO.getStaffDetail(m.getUser(), m.getPass(), staffID);
        
        if(recieve[3].equals("M"))
        	recieve[3]="��";
        if(recieve[3].equals("F"))
        	recieve[3]="Ů";
        
        // �������������
        Object[][] rowData = {
        						{"Ա����",recieve[0]},
        						{"����",recieve[1]},
        						{"����",recieve[2]},
        						{"�Ա�",recieve[3]},
        						{"סַ",recieve[4]},
        						{"�ֻ���",recieve[5]},
        						{"����",recieve[6]}
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
	    
	    JButton btn=myButton.borderButton("ȷ��");
        btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] value=new String[7];
				for(int i=0;i<7;i++) {					
					value[i]=String.valueOf(table.getValueAt(i, 1));
					if(value[i].equals("��"))
						value[i]="M";
					if(value[i].equals("Ů"))
						value[i]="F";
				}
				for(int i=0;i<value.length;i++) {
					value[i]="\""+value[i]+"\"";
				}
				DAO.updateStaff(m.getUser(), m.getPass(), staffID,value);
				JOptionPane.showMessageDialog(null, "���");
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
