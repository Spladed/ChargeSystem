package chargeStaffDisplay;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class test {
	    public static void main(String[] args) {
	        JFrame jf = new JFrame("测试窗口");
	        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	        // 创建内容面板，使用边界布局
	        JPanel panel = new JPanel(new BorderLayout());

	        // 表头（列名）
	        final Object[] columnNames = {"姓名", "语文", "数学", "英语", "总分"};

	        // 表格所有行数据
	        final Object[][] rowData = {
	                {"张三", 80, 80, 80, 240},
	                {"John", 70, 80, 90, 240},
	                {"Sue", 70, 70, 70, 210},
	                {"Jane", 80, 70, 60, 210},
	                {"Joe", 80, 70, 60, 210}
	        };

	        // 自定义表格模型，创建一个表格
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
	                // 总分列的索引为 4，总分列不允许编辑，其他列允许编辑，
	                // 总分列的数值由 语文、数学、英语 这三列的值相加得出，并同步更新
	                return columnIndex != 4;
	            }

	            @Override
	            public Object getValueAt(int rowIndex, int columnIndex) {
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

	        /*
	         * 上面的继承 AbstractTableModel 实现自定义表格模型，功能并不完整，还有很多需要自己
	         * 去实现（例如更新数据，通知UI更新，列名称获取等），建议使用 DefaultTableModel 类，
	         * 该类对 TableModel 做了较为完善的实现，支持自动更新数据处理，支持UI自动更新，列名称
	         * 处理，添加/移除行列等。无特殊要求不需要重写方法，直接使用即可，如下两行代码即可:
	         */
	        // DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
	        // JTable table = new JTable(tableModel);

	        // 获取 表格模型
	        final TableModel tableModel = table.getModel();
	        // 在 表格模型上 添加 数据改变监听器
	        tableModel.addTableModelListener(new TableModelListener() {
	            @Override
	            public void tableChanged(TableModelEvent e) {
	                // 获取 第一个 和 最后一个 被改变的行（只改变了一行，则两者相同）
	                int firstRow = e.getFirstRow();
	                int lastRow = e.getLastRow();

	                // 获取被改变的列
	                int column = e.getColumn();

	                // 事件的类型，可能的值有:
	                //     TableModelEvent.INSERT   新行或新列的添加
	                //     TableModelEvent.UPDATE   现有数据的更改
	                //     TableModelEvent.DELETE   有行或列被移除
	                int type = e.getType();

	                // 针对 现有数据的更改 更新其他单元格数据
	                if (type == TableModelEvent.UPDATE) {
	                    // 只处理 语文、数学、英语 这三列（索引分别为1、2、3）的分数的更改
	                    if (column < 1 || column > 3) {
	                        return;
	                    }
	                    // 遍历每一个修改的行，单个学科分数更改后同时更新总分数
	                    for (int row = firstRow; row <= lastRow; row++) {
	                        // 获取当前行的 语文、数学、英语 的值
	                        Object chineseObj = tableModel.getValueAt(row, 1);
	                        Object mathObj = tableModel.getValueAt(row, 2);
	                        Object englishObj = tableModel.getValueAt(row, 3);

	                        // 把对象值转换为数值
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

	                        // 重新计算新的总分数
	                        int totalScore = chinese + math + english;
	                        // 将新的分数值设置到总分单元格（总分数的列索引为4）
	                        tableModel.setValueAt(totalScore, row, 4);
	                    }
	                }
	            }
	        });

	        // 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
	        panel.add(table.getTableHeader(), BorderLayout.NORTH);
	        // 把 表格内容 添加到容器中心
	        panel.add(table, BorderLayout.CENTER);

	        jf.setContentPane(panel);
	        jf.pack();
	        jf.setLocationRelativeTo(null);
	        jf.setVisible(true);
	    }
}
