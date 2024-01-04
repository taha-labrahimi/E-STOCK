package e_stock.view.clientView;

import e_stock.RepositoryImplementation.ClientRepositoryImpl;
import e_stock.view.clientView.ClientView;
import e_stock.view.clientView.ModifyClientView;
import java.awt.Component;
import javax.swing.*;
import java.awt.event.*;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private boolean isPushed;
    private JTable table;
    private ClientRepositoryImpl clientRepository;  
    private ModifyClientView modifyClientView;    
    private ClientView clientView ;

    public ButtonEditor(Icon icon, ClientRepositoryImpl clientRepository, ModifyClientView modifyClientView,ClientView clientView ) {
        super(new JCheckBox());
        this.clientRepository = clientRepository;
        this.modifyClientView = modifyClientView;
        this.clientView = clientView;
        this.button = new JButton(icon);
        this.button.setOpaque(true);
        this.button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            int modelRow = table.convertRowIndexToModel(table.getEditingRow());
            if (table.getColumnName(table.getEditingColumn()).equals("Edit")) {
                performEditAction(modelRow);
            } else if (table.getColumnName(table.getEditingColumn()).equals("Delete")) {
                performDeleteAction(modelRow);
            }
        }
        isPushed = false;
        return "";
    }

    private void performEditAction(int row) {
        if (modifyClientView == null ) {
            modifyClientView = new ModifyClientView();
        }
        
        // Fetch client data from the row and set it to the modifyClientView
        modifyClientView.setClientcode(table.getValueAt(row, 0).toString());
        modifyClientView.setFirstname(table.getValueAt(row, 1).toString());
        modifyClientView.setLastname(table.getValueAt(row, 2).toString());
        modifyClientView.setAdresse(table.getValueAt(row, 3).toString());
        modifyClientView.setCity(table.getValueAt(row, 4).toString());
        modifyClientView.setCountry(table.getValueAt(row, 5).toString());
        modifyClientView.setPhonenumber(table.getValueAt(row, 6).toString());

        // Assuming ClientView.this refers to the current instance of your frame
        clientView.setVisible(false);
        modifyClientView.setVisible(true);
    }

    private void performDeleteAction(int row) {
        int selectedRowIndex = table.getSelectedRow();
        if (selectedRowIndex != -1) {
            int deletedclientcode = Integer.parseInt(table.getValueAt(selectedRowIndex, 0).toString());
            clientRepository.delete(deletedclientcode);
            JOptionPane.showMessageDialog(clientView, "Client deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

            clientView.loadClientsAndPopulateTable();
        } else {
            JOptionPane.showMessageDialog(null, "You should select a row!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
