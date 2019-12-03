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
	    
	    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
	    tcr.setHorizontalAlignment(SwingConstants.CENTER);
	    table.setDefaultRenderer(Object.class, tcr);

	    
	    table.setForeground(Color.BLACK);                   // 字体颜色
	    table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色

        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table.setRowHeight(30);
        
        // 第一列列宽设置
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        
        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
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
				 // 序号的索引为 0，收费类型的索引为1，序号列、收费类型列不允许编辑，其他列允许编辑，
	             // 金额列的数值由 数量列的值乘对应的价格得出，并同步更新
				 return (columnIndex != 0 && columnIndex != 1 && rowIndex != 0 && rowIndex != 1);
			 }
			 
			 @Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return rowData[rowIndex][columnIndex];
			}
			 
			 @Override
			 public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
				 // 设置新的单元格数据时，必须把新值设置到原数据数值中，
	             // 待更新UI重新调用 getValueAt(...) 获取单元格值时才能获取到最新值
				 rowData[rowIndex][columnIndex] = newValue;
	             // 设置完数据后，必须通知表格去更新UI（重绘单元格），否则显示的数据不会改变
	             fireTableCellUpdated(rowIndex, columnIndex);
			 }
		});
		final TableModel tableModel = table.getModel();
		tableModel.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				//获取第一个和最后一个被改变的行（只改变了一行，则两者相同）
				int firstRow=e.getFirstRow();
				int lastRow=e.getLastRow();
				
				//获取被改变的列
				int column=e.getColumn();
				
				//获取时间的类型
				int type=e.getType();
				
				//针对现有数据的更改，更新其他单元格数据
				if(type==TableModelEvent.UPDATE) {
					//只处理数量、金额这两行的更改
					if(column<2 || column>3) {
						return;
					}
					//遍历每一个修改的行，数量更改后同时更新金额
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
