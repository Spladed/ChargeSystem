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
		//�趨������ΪWindows10
    	try {
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { 
    		e.printStackTrace();
    	}
			
		JFrame jf=new JFrame("Ա������ϵͳ");
		jf.setResizable(false);
	    jf.setLocationRelativeTo(null);
	    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    //�ϲ��ľ�����Ϣ
	    Box aboveBox=Box.createHorizontalBox();
	    {
	    	String[] content= {"����",m.getName(),"        ","���ţ�",m.getDepartmentID()};
	    	for(int i=0;i<content.length;i++) {
	    		aboveBox.add(myLabel.normalLabel(content[i]));
	    	}
	    }
	    
	    //�²���Ա����Ϣ
	    Box belowBox=Box.createVerticalBox();
	    {	 
	    	//����Ա����Ϣ��ͷ��
	    	{
	    		Box container=Box.createHorizontalBox();
	    		String[] headContent= {"Ա����","����","��ϵ�绰","�Ա�","����"};
	    		for(int i=0;i<headContent.length;i++) {
	    			container.add(myLabel.normalLabel(headContent[i]));
		    	}
	    		belowBox.add(container);
	    	}
	    	//����Ա����Ϣ
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
