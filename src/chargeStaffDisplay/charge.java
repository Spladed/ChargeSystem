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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import staff.chargeStaff;

public class charge {
	private static String toChinese(String str) {
		return null;
	}
	
	public static void display(String houseID,String ownerName,chargeStaff cs) {
		JFrame jf = new JFrame("���Դ���");
        jf.setSize(520, 520);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
        
        JLabel label5=new JLabel("�ϼ�",SwingConstants.CENTER);
        label5.setSize(112, 50);
        label5.setBorder(BorderFactory.createMatteBorder(0,1,1,0,Color.GRAY));
        label5.setLocation(5, 274);
        insidePanel.add(label5);
        
        JLabel label6=new JLabel("",SwingConstants.CENTER);
        label6.setSize(224, 50);
        label6.setBorder(BorderFactory.createMatteBorder(0,1,1,1,Color.GRAY));
        label6.setLocation(117, 274);
        insidePanel.add(label6);
        
        JLabel label7=new JLabel("",SwingConstants.CENTER);
        label7.setSize(112, 50);
        label7.setBorder(BorderFactory.createMatteBorder(0,0,1,1,Color.GRAY));
        label7.setLocation(341, 274);
        insidePanel.add(label7);
        
        Object[] columnNames = {"���", "�շ�����", "����", "���"};

        // �������������
        Object[][] rowData = {
                {"1", "��ҵ��", cs.houseInfo(houseID)[0], cs.Charge(houseID)[0]},
                {"2", "������", cs.houseInfo(houseID)[1], cs.Charge(houseID)[1]},
                {"3", "ˮ��", "", ""},
                {"4", "���", "", ""}
        };
        JTable table=myTable.chargeTable(rowData, columnNames);
        final TableModel tableModel = table.getModel();
		tableModel.addTableModelListener(new TableModelListener() {			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				//��ȡ��һ�������һ�����ı���У�ֻ�ı���һ�У���������ͬ��
				int firstRow=e.getFirstRow();
				int lastRow=e.getLastRow();
				
				//��ȡ���ı����
				int column=e.getColumn();
				
				//��ȡ�¼�������
				int type=e.getType();
				
				//����������ݵĸ��ģ�����������Ԫ������
				if(type==TableModelEvent.UPDATE) {
					//ֻ�����������еĸ���
					if(column<2 || column>2) {
						return;
					}
					//����ÿһ���޸ĵ��У��������ĺ�ͬʱ���½��
					for(int row=firstRow;row<=lastRow;row++) {
						Object numberObj=tableModel.getValueAt(row, 2);
						
						double number=0;
						try {
							number=Double.parseDouble(""+numberObj);
						}
						catch(Exception ex) {
							ex.printStackTrace();
						}
						double money=0;
						if(row==2)
							money=number*0.7;
						else if(row==3)
							money=number*0.8;
						//�޸Ľ�������
						tableModel.setValueAt(money, row, 3);						
					}
				}
				if(table.getValueAt(2, 3).getClass().getName().equals("java.lang.Double") && table.getValueAt(3, 3).getClass().getName().equals("java.lang.Double")) {
					label7.setText(""+((Double)table.getValueAt(0, 3)+(Double)table.getValueAt(1, 3)+(Double)table.getValueAt(2, 3)+(Double)table.getValueAt(3, 3)));
					label6.setText(toChinese(label7.getText()));
			        insidePanel.updateUI();
				}
			}
		});				
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setSize(450, 225);
        scrollPane.setLocation(5, 50);
        scrollPane.setBorder(null);
        scrollPane.setBackground(null);
        insidePanel.add(scrollPane);
        
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
