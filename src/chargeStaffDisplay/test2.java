package chargeStaffDisplay;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class test2 {
	public static void main(String[] args) {
        JFrame jf = new JFrame("���Դ���");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // ����������壬ʹ�ñ߽粼��
        JPanel panel = new JPanel(new BorderLayout());

        // ��ͷ��������
        final Object[] columnNames = {"����", "����", "��ѧ", "Ӣ��", "�ܷ�"};

        // �������������
        final Object[][] rowData = {
                {"����", 80, 80, 80, 240},
                {"John", 70, 80, 90, 240},
                {"Sue", 70, 70, 70, 210},
                {"Jane", 80, 70, 60, 210},
                {"Joe", 80, 70, 60, 210}
        };

        // �Զ�����ģ�ͣ�����һ�����
        JTable table = myTable.chargeTable(rowData, columnNames);

        // �� ��ͷ ��ӵ�����������ʹ����ͨ���м�������ӱ��ʱ����ͷ �� ���� ��Ҫ�ֿ���ӣ�
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        // �� ������� ��ӵ���������
        panel.add(table, BorderLayout.CENTER);

        jf.setContentPane(panel);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
