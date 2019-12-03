package chargeStaffDisplay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class myTable {
	public static JTable normalTable(List<HashMap<String,Object>> info,String[] columnNames) {
		String[] billSelectContent= {"time_stamp","property","clean","water","electricity","state"};
		Object[][] rowData=new Object[info.size()][6];
	    for(int i=0;i<info.size();i++) {
	    	HashMap<String,Object> temp=info.get(i);
	    	for(int j=0;j<6;j++) {
	    		rowData[i][j]=temp.get(billSelectContent[j]);
	    	}
	    }
	    JTable table=new JTable(rowData,columnNames);
	    
	    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
	    tcr.setHorizontalAlignment(SwingConstants.CENTER);
	    table.setDefaultRenderer(Object.class, tcr);

	    
	    table.setForeground(Color.BLACK);                   // ������ɫ
	    table.setFont(new Font(null, Font.PLAIN, 14));      // ������ʽ
        table.setSelectionForeground(Color.DARK_GRAY);      // ѡ�к�������ɫ
        table.setSelectionBackground(Color.LIGHT_GRAY);     // ѡ�к����屳��
        table.setGridColor(Color.GRAY);                     // ������ɫ

        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // ���ñ�ͷ����������ʽ
        table.getTableHeader().setForeground(Color.RED);                // ���ñ�ͷ����������ɫ
        table.getTableHeader().setResizingAllowed(false);               // ���ò������ֶ��ı��п�
        table.getTableHeader().setReorderingAllowed(false);             // ���ò������϶������������

        // �����и�
        table.setRowHeight(30);
        
        // ��һ���п�����
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        
        // ���ù�������ӿڴ�С�������ô�С�������ݣ���Ҫ�϶����������ܿ�����
        table.setPreferredScrollableViewportSize(new Dimension(400, 300));

		return table;
	}
	
	public static JTable chargeTable(Object[][] rowData,Object[] columnNames) {
		JTable table=new JTable(new AbstractTableModel() {
									
			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return rowData.length;
			}
			
			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return rowData[0].length;
			}
			
			@Override
            public String getColumnName(int column) {
                return columnNames[column].toString();
            }
			
			 @Override
			 public boolean isCellEditable(int rowIndex, int columnIndex) {
				 // ��ŵ�����Ϊ 0���շ����͵�����Ϊ1������С��շ������в�����༭������������༭��
	             // ����е���ֵ�� �����е�ֵ�˶�Ӧ�ļ۸�ó�����ͬ������
				 return (columnIndex != 0 && columnIndex != 1 && rowIndex != 0 && rowIndex != 1);
			 }
			 
			 @Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return rowData[rowIndex][columnIndex];
			}
			 
			 @Override
			 public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
				 // �����µĵ�Ԫ������ʱ���������ֵ���õ�ԭ������ֵ�У�
	             // ������UI���µ��� getValueAt(...) ��ȡ��Ԫ��ֵʱ���ܻ�ȡ������ֵ
				 rowData[rowIndex][columnIndex] = newValue;
	             // ���������ݺ󣬱���֪ͨ���ȥ����UI���ػ浥Ԫ�񣩣�������ʾ�����ݲ���ı�
	             fireTableCellUpdated(rowIndex, columnIndex);
			 }
		});
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
				
				//��ȡʱ�������
				int type=e.getType();
				
				//����������ݵĸ��ģ�����������Ԫ������
				if(type==TableModelEvent.UPDATE) {
					//ֻ������������������еĸ���
					if(column<2 || column>3) {
						return;
					}
					//����ÿһ���޸ĵ��У��������ĺ�ͬʱ���½��
					for(int row=firstRow;row<lastRow;row++) {
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
						tableModel.setValueAt(money, row, 3);
					}
				}
				
			}
		});		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	    tcr.setHorizontalAlignment(SwingConstants.CENTER);
	    table.setDefaultRenderer(Object.class, tcr);
	    table.setRowHeight(50);
		return table;
	}
}
