package e_stock.view.ProductView;

import e_stock.view.clientView.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer(Icon icon) {
        setIcon(icon);
        setOpaque(true);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFont(new Font("Segoe UI", Font.BOLD, 12));
        setForeground(new Color(33, 37, 41)); // Dark grey color
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        return this;
    }
}
