package table;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class JTableExamples {
    // frame
    JFrame f;
    // Table
    JTable j;

    // Constructor
    JTableExamples() {
        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("JTable Example"); //$NON-NLS-1$

        // Initializing the JTable
        j = new JTable(new JTableModel());
        j.addColumn(new TableColumn(0));
        j.addColumn(new TableColumn(1));
        j.addColumn(new TableColumn(2, 200, new ButtonRenderer(), new ButtonEditor()));
        j.addColumn(new TableColumn(3, 200, new ButtonRenderer(), new ButtonEditor()));

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);
    }

    // Driver method
    public static void main(String[] args) {
        new JTableExamples();
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
}