package table;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import managerDisplay.DAL;
import staff.Manager;
 
public class table {
	
	Manager m=new Manager("011");
	
	JTable table = null;	
	
	JButton viewButton1 = new JButton("修改");	
	JButton editButton1 = new JButton("修改");
	
	JButton viewButton2 = new JButton("删除");	
	JButton editButton2 = new JButton("删除");
	
	DefaultTableModel model = null;
	
	
	public JComponent makeUI() {
		String[] columnNames = { "员工号", "姓名", "联系电话", "性别", "操作"}; // When I increase the
														// number of columns it
														// hides another button
		Object[][] data = DAL.getStaffInfo(m.getUser(), m.getPass(), m.getDepartmentID());
		model = new DefaultTableModel(data, columnNames) {
			
		};
		table = new JTable(model);
		table.setRowHeight(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(400);
		ActionPanelEditorRenderer er = new ActionPanelEditorRenderer();
		TableColumn column = table.getColumnModel().getColumn(4);
		column.setCellRenderer(er);
		column.setCellEditor(er);
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(table));
		p.setPreferredSize(new Dimension(320, 200));
		return p;
	}
 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}
 
	public static void createAndShowGUI() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.getContentPane().add(new table().makeUI());
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
 
	
	
	class ActionPanelEditorRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel  = new JPanel();
		
		

		public ActionPanelEditorRenderer() {
			super();			
			panel1.add(viewButton1);
			panel2.add(viewButton2);
			panel.add(panel1);
			panel.add(panel2);
			//viewButton2.setBackground(Color.red);
			
			viewButton1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					int i = table.getSelectedRow();
					String s = (String)model.getValueAt(i, 0);
					
					JOptionPane.showMessageDialog(null, s);
				}
			});
			viewButton2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					int i = table.getSelectedRow();
					String s = (String)model.getValueAt(i, 0);
					
					JOptionPane.showMessageDialog(null, s);
				}
			});
		}
 
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
			panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());			
		//	panel2.setBackground(table.getBackground());
			return panel;
		}
 
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			panel1.setBackground(table.getSelectionBackground());
			return panel;
		}
 
		@Override
		public Object getCellEditorValue() {
			return null;
		}
	}
}
