package managerDisplay;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import cs.Display;
import staff.Manager;

public class Main {
Manager m=new Manager("011");
	
	private JTable table = null;	
	
	private JButton viewButton1 = myButton.normalButton("�޸�");
	
	private JButton viewButton2 = myButton.normalButton("ɾ��");
	
	private DefaultTableModel model = null;
	private Manager manager=null;
	
	public Main(Manager m){
		this.manager=m;
		Display(m);
	}
	
	//���Ʊ��
	public void makeTable() {
		String[] columnNames = { "Ա����", "����", "��ϵ�绰", "�Ա�", "����"}; 
		Object[][] data = DAO.getStaffInfo(manager.getUser(), manager.getPass(), manager.getDepartmentID());
		model = new DefaultTableModel(data, columnNames) {};
		table = new JTable(model);
		table.setRowHeight(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		ActionPanelEditorRenderer er = new ActionPanelEditorRenderer();
		TableColumn column = table.getColumnModel().getColumn(4);
		column.setCellRenderer(er);
		column.setCellEditor(er);
	}
	
	//������
	public void Display(Manager m) {
		manager=m;
			
		JFrame jf=new JFrame("Ա������ϵͳ");
		jf.setSize(800, 650);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //�����
        JPanel mainPanel=new JPanel(null);
        mainPanel.setBackground(Color.WHITE);
        //���������Ĵ�С
	    mainPanel.setSize(jf.getWidth(),jf.getHeight());
	    //������λ��
	    mainPanel.setLocation(0, 0);
	    
	    //�ϲ��ľ�����Ϣ
	    JPanel abovePanel=new JPanel(null);
	    //�����ϲ����Ĵ�С
	    abovePanel.setSize(jf.getWidth()-60, 50);
	    //�ϲ�����λ��
	    abovePanel.setLocation(20, 20);
	    {
	    	String[] content= {"����",m.getName(),"���ţ�",m.getDepartmentID()};
	    	int x=0;
	    	for(int i=0;i<content.length;i++) {
	    		//����ѭ������Label
	    		JLabel label=myLabel.normalLabel(content[i]);
	    		label.setLocation(x, 0);
	    		x+=label.getWidth();
	    		abovePanel.add(label);
	    	}
	    	//�˳���¼��ť
	    	JButton btn=myButton.normalButton("�˳���¼");
	    	btn.setLocation(650, 0);
	    	//�˳���¼��ť���¼�
	    	btn.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Display.main(null);
					jf.dispose();
				}
			});
	    	abovePanel.add(btn);
	    }
	    abovePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    
	    //�²���Ա����Ϣ
	    JPanel belowPanel=new JPanel(null);
	    //�²����Ĵ�С
	    belowPanel.setSize(abovePanel.getWidth(),500);
	    //�²�����λ��
	    belowPanel.setLocation(20, 80);
	    	 
	    makeTable();
	    // ����table���ݾ���
	    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
	    // �� ��� �ŵ� ������� �У���ͷ���Զ���ӵ�������嶥����
	    JScrollPane scrollPane = new JScrollPane(table);
	    //��������λ��
	    scrollPane.setLocation(10, 10);
	    //�������Ĵ�С
	    scrollPane.setSize(belowPanel.getWidth()-20, belowPanel.getHeight()-100);	    
		//���ù������ı߿�
		scrollPane.setBorder(BorderFactory.createTitledBorder("Ա����Ϣ"));
	    belowPanel.add(scrollPane);
	        
	    //��Ӱ�ť
	    JButton btn1=myButton.borderButton("���");
	    btn1.setLocation(520, 425);
	    btn1.addActionListener(new ActionListener() {				
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		// TODO Auto-generated method stub
	    		addStaff.add(m);
			}
		});
	        
	    //ˢ�°�ť
	    JButton btn2=myButton.borderButton("ˢ��");
	    btn2.setLocation(630, 425);
	    btn2.addActionListener(new ActionListener() {				
	    	@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	    		new Main(m);
	    		jf.dispose();
			}
	    });
	        
	    belowPanel.add(btn1);
	    belowPanel.add(btn2);
	    
	    belowPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    
	    mainPanel.add(abovePanel);
	    mainPanel.add(belowPanel);

	    
	    jf.setContentPane(mainPanel);
        jf.setVisible(true);
	}
	
	//���ñ���а�ť���¼�
	class ActionPanelEditorRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

		JPanel panel  = new JPanel(new FlowLayout());

		public ActionPanelEditorRenderer() {
			super();
			panel.add(viewButton1);
			panel.add(viewButton2);
			
			//�޸�
			viewButton1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					int i = table.getSelectedRow();
					String s = (String)model.getValueAt(i, 0);
					updateStaff.update(manager,s);
				}
			});
			
			//ɾ��
			viewButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int i = table.getSelectedRow();
					String s = (String)model.getValueAt(i, 0);
					deleteStaff.delete(manager,s);
					DefaultTableModel  model  =  (DefaultTableModel) table.getModel(); 
					model.removeRow(i);
					JOptionPane.showMessageDialog(null, "��ɾ��");
				}
			});
		}
 
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
			panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
			panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());	
			return panel;
		}
 
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			panel.setBackground(table.getSelectionBackground());
			return panel;
		}
 
		@Override
		public Object getCellEditorValue() {
			return null;
		}
	}
	
//	public static void main(String[] args) {
//		new Main(new Manager("011"));
//	}
}
