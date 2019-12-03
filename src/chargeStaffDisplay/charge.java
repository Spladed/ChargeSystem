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
		JFrame jf = new JFrame("���Դ���");
        jf.setSize(520, 520);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // ����������壬ָ������Ϊ null����ʹ�þ��Բ���
        JPanel panel = new JPanel(null);
        
        JPanel insidePanel=new JPanel(null);
        insidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        insidePanel.setBackground(Color.WHITE);
        insidePanel.setSize(460, 380);
        insidePanel.setLocation(20, 20);
        
        panel.add(insidePanel);

        JLabel label1=myLabel.normalLabel("���ţ�"+houseID);
        label1.setLocation(0, 0);
        insidePanel.add(label1);
        
        JLabel label2=myLabel.normalLabel("ҵ����"+ownerName);
        label2.setLocation(360, 0);
        insidePanel.add(label2);
        
        long l=System.currentTimeMillis();
		Date date=new Date(l);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String time = dateFormat.format(date);
        JLabel label3=new JLabel("���ڣ�"+time,SwingConstants.CENTER);
        label3.setSize(150,50);
        label3.setLocation(0, 324);
        insidePanel.add(label3);
        
        JLabel label4=myLabel.normalLabel("Ա����"+cs.getID());
        label4.setLocation(360, 324);
        insidePanel.add(label4);
        
        Object[] columnNames = {"���", "�շ�����", "����", "���"};

        // �������������
        Object[][] rowData = {
                {"1", "��ҵ��", cs.houseInfo(houseID)[0], cs.Charge(houseID)[0]},
                {"2", "������", cs.houseInfo(houseID)[1], cs.Charge(houseID)[1]},
                {"3", "ˮ��", null, null},
                {"4", "���", null, null}
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
        
        JLabel label5=new JLabel("�ϼ�",SwingConstants.CENTER);
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
        
        JButton btn1=new JButton("ȷ��");
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
        JButton btn2=new JButton("��ӡ");
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
        
        

        // ��ʾ����
        jf.setContentPane(panel);
        jf.setVisible(true);

        /*
                *  Ҳ������ jf.setVisible(true) ֮����Ӱ�ť
         *
         * PS_01: jf.setVisible(true) ֮�����������п��;
         * PS_02: ʹ����������ʱ, jf.setVisible(true) ֮����ӵ����, Ҳ�ᱻ�����Ǿ��Բ��������ø����������Ҫ�ֶ�ָ������Ϳ�ߣ�;
         * PS_03: ʹ����������ʱ, jf.setVisible(true) ֮ǰ��ӵ����, ����� jf.setVisible(true) ֮���ֶ����ø����������Ϳ��,
         *	 �Ὣ������������Բ������Դ�������������Ϳ�߻���Ч����
         */
	}

	public static void main(String[] args) {
		display("A0101","sss",new chargeStaff("001"));
	}
}
