import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;

public class ButtonEditor extends DefaultCellEditor {
    private JButton editButton;
    private JButton deleteButton;
    private JPanel panel;
    private Object currentValue;
    private int editedRow;

    public ButtonEditor() {
        super(new JCheckBox());
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        panel.add(editButton);
        panel.add(deleteButton);

        // Edit button action
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle Edit action
                // You can access editedRow to know which row is being edited
            }
        });

        // Delete button action
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle Delete action
                // You can access editedRow to know which row is being deleted
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentValue = value;
        editedRow = row;
        return panel;
    }

    public Object getCellEditorValue() {
        return currentValue;
    }
}
