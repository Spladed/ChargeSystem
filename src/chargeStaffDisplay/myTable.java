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
	    
	    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
	    tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
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
}
