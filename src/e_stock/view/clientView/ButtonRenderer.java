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

        // Add a mouse listener to change the button's background on click
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(Color.GRAY); // Color when button is pressed
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(UIManager.getColor("Button.background")); // Default color when released
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setContentAreaFilled(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setContentAreaFilled(false);
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
