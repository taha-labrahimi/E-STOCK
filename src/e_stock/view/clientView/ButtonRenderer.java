package e_stock.view.clientView;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonRenderer extends JPanel implements TableCellRenderer {

    JButton editButton;
    JButton deleteButton;

    public ButtonRenderer(Icon editIcon, Icon deleteIcon) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        editButton = new JButton(editIcon);
        deleteButton = new JButton(deleteIcon);

        // Initialize buttons with properties and listeners
        initButton(editButton);
        initButton(deleteButton);

        add(editButton);
        add(deleteButton);
    }

    private void initButton(JButton button) {
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setForeground(new Color(33, 37, 41)); // Dark grey color
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setContentAreaFilled(true);
                button.setBackground(new Color(230, 230, 230)); // Light grey on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setContentAreaFilled(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(new Color(200, 200, 200)); // Slightly darker on click
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(new Color(230, 230, 230)); // Back to light grey
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            editButton.setForeground(table.getSelectionForeground());
            editButton.setBackground(table.getSelectionBackground());
            deleteButton.setForeground(table.getSelectionForeground());
            deleteButton.setBackground(table.getSelectionBackground());
        } else {
            editButton.setForeground(table.getForeground());
            editButton.setBackground(UIManager.getColor("Button.background"));
            deleteButton.setForeground(table.getForeground());
            deleteButton.setBackground(UIManager.getColor("Button.background"));
        }
        return this;
    }
}
