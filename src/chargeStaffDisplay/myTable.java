package chargeStaffDisplay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

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
	    tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
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
}
