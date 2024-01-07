import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;

public class ButtonRenderer extends JPanel implements TableCellRenderer {
    private JButton editButton;
    private JButton deleteButton;

    public ButtonRenderer() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        add(editButton);
        add(deleteButton);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
