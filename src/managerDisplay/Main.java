package managerDisplay;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableCellRenderer;

import staff.Manager;

public class Main {
	public static void Display(Manager m) {
		//设定界面风格为Windows10
    	try {
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { 
    		e.printStackTrace();
    	}
			
		JFrame jf=new JFrame("员工管理系统");
		jf.setResizable(false);
	    jf.setLocationRelativeTo(null);
	    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    //上部的经理信息
	    Box aboveBox=Box.createHorizontalBox();
	    {
	    	String[] content= {"经理：",m.getName(),"        ","部门：",m.getDepartmentID()};
	    	for(int i=0;i<content.length;i++) {
	    		aboveBox.add(myLabel.normalLabel(content[i]));
	    	}
	    }
	    
	    //下部的员工信息
	    Box belowBox=Box.createVerticalBox();
	    {	 
	    	//构造员工信息的头部
	    	{
	    		Box container=Box.createHorizontalBox();
	    		String[] headContent= {"员工号","姓名","联系电话","性别","操作"};
	    		for(int i=0;i<headContent.length;i++) {
	    			container.add(myLabel.normalLabel(headContent[i]));
		    	}
	    		belowBox.add(container);
	    	}
	    	//构造员工信息
	    	{
	    		Box container=Box.createHorizontalBox();
	    		List<HashMap<String,Object>> recieve=DAL.getStaffInfo(m.getUser(), m.getPass(), m.getDepartmentID());
	    		for(HashMap<String,Object> temp:recieve) {
	    			
	    		}
	    	}
	    }
	    
	    
	    
	    Box mainBox=Box.createVerticalBox();
	    mainBox.add(aboveBox);
	    mainBox.add(belowBox);
	    jf.setContentPane(mainBox);
        jf.pack();
        jf.setVisible(true);
	}
	
	private class ButtonRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            button.setText("before"); //$NON-NLS-1$
            return button;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private static final long serialVersionUID = -6546334664166791132L;

        public ButtonEditor() {
            super(new JTextField());
            this.setClickCountToStart(1);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JButton button = (JButton)value;
            button.setText("after"); //$NON-NLS-1$
            button.addActionListener(new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("edit!!!!"); //$NON-NLS-1$
                }
            });
            return button;
        }
    }
	
	public static void main(String[] args) {
		Display(new Manager("011"));
	}
}
