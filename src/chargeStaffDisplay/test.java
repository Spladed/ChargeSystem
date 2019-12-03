package chargeStaffDisplay;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class test {
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
	        JTable table = new JTable(new AbstractTableModel() {
	            @Override
	            public int getRowCount() {
	                return rowData.length;
	            }

	            @Override
	            public int getColumnCount() {
	                return rowData[0].length;
	            }

	            @Override
	            public String getColumnName(int column) {
	                return columnNames[column].toString();
	            }

	            @Override
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                // �ܷ��е�����Ϊ 4���ܷ��в�����༭������������༭��
	                // �ܷ��е���ֵ�� ���ġ���ѧ��Ӣ�� �����е�ֵ��ӵó�����ͬ������
	                return columnIndex != 4;
	            }

	            @Override
	            public Object getValueAt(int rowIndex, int columnIndex) {
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

	        /*
	         * ����ļ̳� AbstractTableModel ʵ���Զ�����ģ�ͣ����ܲ������������кܶ���Ҫ�Լ�
	         * ȥʵ�֣�����������ݣ�֪ͨUI���£������ƻ�ȡ�ȣ�������ʹ�� DefaultTableModel �࣬
	         * ����� TableModel ���˽�Ϊ���Ƶ�ʵ�֣�֧���Զ��������ݴ���֧��UI�Զ����£�������
	         * �������/�Ƴ����еȡ�������Ҫ����Ҫ��д������ֱ��ʹ�ü��ɣ��������д��뼴��:
	         */
	        // DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
	        // JTable table = new JTable(tableModel);

	        // ��ȡ ���ģ��
	        final TableModel tableModel = table.getModel();
	        // �� ���ģ���� ��� ���ݸı������
	        tableModel.addTableModelListener(new TableModelListener() {
	            @Override
	            public void tableChanged(TableModelEvent e) {
	                // ��ȡ ��һ�� �� ���һ�� ���ı���У�ֻ�ı���һ�У���������ͬ��
	                int firstRow = e.getFirstRow();
	                int lastRow = e.getLastRow();

	                // ��ȡ���ı����
	                int column = e.getColumn();

	                // �¼������ͣ����ܵ�ֵ��:
	                //     TableModelEvent.INSERT   ���л����е����
	                //     TableModelEvent.UPDATE   �������ݵĸ���
	                //     TableModelEvent.DELETE   ���л��б��Ƴ�
	                int type = e.getType();

	                // ��� �������ݵĸ��� ����������Ԫ������
	                if (type == TableModelEvent.UPDATE) {
	                    // ֻ���� ���ġ���ѧ��Ӣ�� �����У������ֱ�Ϊ1��2��3���ķ����ĸ���
	                    if (column < 1 || column > 3) {
	                        return;
	                    }
	                    // ����ÿһ���޸ĵ��У�����ѧ�Ʒ������ĺ�ͬʱ�����ܷ���
	                    for (int row = firstRow; row <= lastRow; row++) {
	                        // ��ȡ��ǰ�е� ���ġ���ѧ��Ӣ�� ��ֵ
	                        Object chineseObj = tableModel.getValueAt(row, 1);
	                        Object mathObj = tableModel.getValueAt(row, 2);
	                        Object englishObj = tableModel.getValueAt(row, 3);

	                        // �Ѷ���ֵת��Ϊ��ֵ
	                        int chinese = 0;
	                        try {
	                            chinese = Integer.parseInt("" + chineseObj);
	                        } catch (Exception ex) {
	                            ex.printStackTrace();
	                        }
	                        int math = 0;
	                        try {
	                            math = Integer.parseInt("" + mathObj);
	                        } catch (Exception ex) {
	                            ex.printStackTrace();
	                        }
	                        int english = 0;
	                        try {
	                            english = Integer.parseInt("" + englishObj);
	                        } catch (Exception ex) {
	                            ex.printStackTrace();
	                        }

	                        // ���¼����µ��ܷ���
	                        int totalScore = chinese + math + english;
	                        // ���µķ���ֵ���õ��ֵܷ�Ԫ���ܷ�����������Ϊ4��
	                        tableModel.setValueAt(totalScore, row, 4);
	                    }
	                }
	            }
	        });

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
