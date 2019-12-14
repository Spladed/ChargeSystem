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
	
	private JButton viewButton1 = myButton.normalButton("修改");
	
	private JButton viewButton2 = myButton.normalButton("删除");
	
	private DefaultTableModel model = null;
	private Manager manager=null;
	
	public Main(Manager m){
		this.manager=m;
		Display(m);
	}
	
	//绘制表格
	public void makeTable() {
		String[] columnNames = { "员工号", "姓名", "联系电话", "性别", "操作"}; 
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
	
	//主界面
	public void Display(Manager m) {
		manager=m;
			
		JFrame jf=new JFrame("员工管理系统");
		jf.setSize(800, 650);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //主面板
        JPanel mainPanel=new JPanel(null);
        mainPanel.setBackground(Color.WHITE);
        //设置主面板的大小
	    mainPanel.setSize(jf.getWidth(),jf.getHeight());
	    //主面板的位置
	    mainPanel.setLocation(0, 0);
	    
	    //上部的经理信息
	    JPanel abovePanel=new JPanel(null);
	    //设置上部面板的大小
	    abovePanel.setSize(jf.getWidth()-60, 50);
	    //上部面板的位置
	    abovePanel.setLocation(20, 20);
	    {
	    	String[] content= {"经理：",m.getName(),"部门：",m.getDepartmentID()};
	    	int x=0;
	    	for(int i=0;i<content.length;i++) {
	    		//利用循环构造Label
	    		JLabel label=myLabel.normalLabel(content[i]);
	    		label.setLocation(x, 0);
	    		x+=label.getWidth();
	    		abovePanel.add(label);
	    	}
	    	//退出登录按钮
	    	JButton btn=myButton.normalButton("退出登录");
	    	btn.setLocation(650, 0);
	    	//退出登录按钮的事件
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
	    
	    //下部的员工信息
	    JPanel belowPanel=new JPanel(null);
	    //下部面板的大小
	    belowPanel.setSize(abovePanel.getWidth(),500);
	    //下部面板的位置
	    belowPanel.setLocation(20, 80);
	    	 
	    makeTable();
	    // 设置table内容居中
	    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
	    // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
	    JScrollPane scrollPane = new JScrollPane(table);
	    //滚动面板的位置
	    scrollPane.setLocation(10, 10);
	    //滚动面板的大小
	    scrollPane.setSize(belowPanel.getWidth()-20, belowPanel.getHeight()-100);	    
		//设置滚动面板的边框
		scrollPane.setBorder(BorderFactory.createTitledBorder("员工信息"));
	    belowPanel.add(scrollPane);
	        
	    //添加按钮
	    JButton btn1=myButton.borderButton("添加");
	    btn1.setLocation(520, 425);
	    btn1.addActionListener(new ActionListener() {				
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		// TODO Auto-generated method stub
	    		addStaff.add(m);
			}
		});
	        
	    //刷新按钮
	    JButton btn2=myButton.borderButton("刷新");
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
	
	//设置表格中按钮的事件
	class ActionPanelEditorRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

		JPanel panel  = new JPanel(new FlowLayout());

		public ActionPanelEditorRenderer() {
			super();
			panel.add(viewButton1);
			panel.add(viewButton2);
			
			//修改
			viewButton1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					int i = table.getSelectedRow();
					String s = (String)model.getValueAt(i, 0);
					updateStaff.update(manager,s);
				}
			});
			
			//删除
			viewButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int i = table.getSelectedRow();
					String s = (String)model.getValueAt(i, 0);
					deleteStaff.delete(manager,s);
					DefaultTableModel  model  =  (DefaultTableModel) table.getModel(); 
					model.removeRow(i);
					JOptionPane.showMessageDialog(null, "已删除");
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
