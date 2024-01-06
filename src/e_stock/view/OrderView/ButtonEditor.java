package e_stock.view.OrderView;

import e_stock.view.clientView.*;
import e_stock.RepositoryImplementation.ClientRepositoryImpl;
import e_stock.RepositoryImplementation.OrderLineRepositoryImpl;
import e_stock.RepositoryImplementation.OrderRepositoryImpl;
import e_stock.view.clientView.ClientView;
import e_stock.view.clientView.ModifyClientView;
import java.awt.Component;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class ButtonEditor extends DefaultCellEditor {

    private JButton button;
    private boolean isPushed;
    private JTable table;
    private OrderRepositoryImpl orderRepositoryImpl;
    private OrderLineRepositoryImpl orderLineRepositoryImpl;
    private ModifyOrderView modifyOrderView;
    private DetailsClientView detailsClientView;
    private OrderView orderView;

    public ButtonEditor(Icon icon, OrderRepositoryImpl orderRepositoryImpl, OrderLineRepositoryImpl orderLineRepositoryImpl, ModifyOrderView modifyOrderView, OrderView orderView) {
        super(new JCheckBox());
        this.orderRepositoryImpl = orderRepositoryImpl;
        this.orderLineRepositoryImpl = orderLineRepositoryImpl;
        this.modifyOrderView = modifyOrderView;
        this.orderView = orderView;
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
            } else if (table.getColumnName(table.getEditingColumn()).equals("View")) {
                performViewAction(modelRow);
            }
        }
        isPushed = false;
        return "";
    }

    private void performEditAction(int row) {
        int selectedRow = table.getSelectedRow();
        if (modifyOrderView == null) {
            modifyOrderView = new ModifyOrderView();
        }
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            modifyOrderView.setOrderid((int) model.getValueAt(selectedRow, 0));
            String clientName = model.getValueAt(selectedRow, 2).toString(); // Assuming client name is in the 3rd column
            String productName = model.getValueAt(selectedRow, 3).toString(); // Assuming product name is in the 4th column
            int quantity = Integer.parseInt(model.getValueAt(selectedRow, 4).toString()); // Assuming quantity is in the 5th column

            modifyOrderView.setSelectedOrderDetails(clientName, productName, quantity);
            modifyOrderView.setVisible(true);
            orderView.setVisible(false);
        }
    }

    private void performDeleteAction(int row) {
        if (row >= 0) { // Ensure the row index is valid
            int modelRow = table.convertRowIndexToModel(row);
            int OrderId = Integer.parseInt(table.getModel().getValueAt(modelRow, 0).toString());
            orderLineRepositoryImpl.delete(OrderId);
            orderRepositoryImpl.delete(OrderId);
            JOptionPane.showMessageDialog(orderView, "Client deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            SwingUtilities.invokeLater(() -> orderView.loadOrdersAndPopulateTable());
        } else {
            JOptionPane.showMessageDialog(orderView, "No row selected to delete.", "Error", JOptionPane.ERROR_MESSAGE);
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
//        if(detailsClientView ==null){
//            detailsClientView =new DetailsClientView();
//        }
//         // Fetch client data from the row and set it to the modifyClientView
//       
//        detailsClientView.setFirstnam(table.getValueAt(modelRow, 1).toString());
//        detailsClientView.setLastname(table.getValueAt(modelRow, 2).toString());
//        detailsClientView.setAdresse(table.getValueAt(modelRow, 3).toString());
//        detailsClientView.setCity(table.getValueAt(modelRow, 4).toString());
//        detailsClientView.setCountry(table.getValueAt(modelRow, 5).toString());
//        detailsClientView.setPhonenumber(table.getValueAt(modelRow, 6).toString());
//
//        // Assuming ClientView.this refers to the current instance of your frame
//        clientView.setVisible(false);
//        detailsClientView.setVisible(true);
    }
}
