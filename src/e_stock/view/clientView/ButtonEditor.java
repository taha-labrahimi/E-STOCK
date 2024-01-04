package e_stock.view.clientView;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.event.CellEditorListener;

public class ButtonEditor extends DefaultCellEditor {

    private ButtonRenderer buttonPanel;
    private JTable table;
    private boolean isEditing;
    private int editRow;

    public ButtonEditor(Icon editIcon, Icon deleteIcon) {
        super(new JCheckBox()); // We won't use this, but the superclass constructor requires it
        buttonPanel = new ButtonRenderer(editIcon, deleteIcon);

        // Set up the editor component
        buttonPanel.editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped(); // Make the renderer reappear
                System.out.println("Edit action on row " + editRow);
                // Implement your edit action here
            }
        });

        buttonPanel.deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped(); // Make the renderer reappear
                System.out.println("Delete action on row " + editRow);
                // Implement your delete action here
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.editRow = row;
        buttonPanel.editButton.setBackground(UIManager.getColor("Button.background")); // Default color
        buttonPanel.deleteButton.setBackground(UIManager.getColor("Button.background")); // Default color

        buttonPanel.editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                buttonPanel.editButton.setBackground(Color.GRAY); // Color when button is pressed
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                buttonPanel.editButton.setBackground(UIManager.getColor("Button.background")); // Default color when released
            }
        });

        buttonPanel.deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                buttonPanel.deleteButton.setBackground(Color.GRAY); // Color when button is pressed
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                buttonPanel.deleteButton.setBackground(UIManager.getColor("Button.background")); // Default color when released
            }
        });
        return buttonPanel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        isEditing = false;
        return super.stopCellEditing();
    }

    @Override
    public void cancelCellEditing() {
        isEditing = false;
        super.cancelCellEditing();
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        super.addCellEditorListener(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        super.removeCellEditorListener(l);
    }
}
