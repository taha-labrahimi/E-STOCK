package e_stock.view.clientView;

import com.raven.main.Main;
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
    private DetailsClientView detailsClientView;
    private ClientView clientForm;
    private Main main;
    public ButtonEditor(Icon icon, ClientRepositoryImpl clientRepository, ModifyClientView modifyClientView, ClientView clientForm,Main main) {
        super(new JCheckBox());
        this.clientRepository = clientRepository;
        this.modifyClientView = modifyClientView;
        this.clientForm = clientForm;
        this.main = main;
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
            else if (table.getColumnName(table.getEditingColumn()).equals("View")) {
                performViewAction(modelRow);
            }
        }
        isPushed = false;
        return "";
    }

    private void performEditAction(int row) {
        if (modifyClientView == null) {
            modifyClientView = new ModifyClientView(main);
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
        this.main.showForm(new ModifyClientView(main));
    }

    private void performDeleteAction(int row) {
        if (row >= 0) { // Ensure the row index is valid
            int modelRow = table.convertRowIndexToModel(row);
            int clientCode = Integer.parseInt(table.getModel().getValueAt(modelRow, 0).toString());
            if (!clientRepository.hasClientOrders(clientCode)) {
                clientRepository.delete(clientCode);
                JOptionPane.showMessageDialog(clientForm, "Client deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(clientForm, "Cannot delete client. Client is referenced in orders.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
            }
            SwingUtilities.invokeLater(() -> clientForm.loadClientsAndPopulateTable()); // Safely update the table model
        } else {
            JOptionPane.showMessageDialog(clientForm, "No row selected to delete.", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void performViewAction(int modelRow) {
        if(detailsClientView ==null){
            detailsClientView =new DetailsClientView();
        }
         // Fetch client data from the row and set it to the modifyClientView
       
        detailsClientView.setFirstnam(table.getValueAt(modelRow, 1).toString());
        detailsClientView.setLastname(table.getValueAt(modelRow, 2).toString());
        detailsClientView.setAdresse(table.getValueAt(modelRow, 3).toString());
        detailsClientView.setCity(table.getValueAt(modelRow, 4).toString());
        detailsClientView.setCountry(table.getValueAt(modelRow, 5).toString());
        detailsClientView.setPhonenumber(table.getValueAt(modelRow, 6).toString());

        // Assuming ClientView.this refers to the current instance of your frame
        clientForm.setVisible(false);
        detailsClientView.setVisible(true);
    }
}
