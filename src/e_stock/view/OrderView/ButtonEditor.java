package e_stock.view.OrderView;

import com.raven.main.Main;
import e_stock.view.clientView.*;
import e_stock.RepositoryImplementation.ClientRepositoryImpl;
import e_stock.RepositoryImplementation.OrderLineRepositoryImpl;
import e_stock.RepositoryImplementation.OrderRepositoryImpl;
import e_stock.view.OrderView.report.FieldReportPayment;
import e_stock.view.OrderView.report.ParameterReportPayment;
import e_stock.view.OrderView.report.ReportManager;
import e_stock.view.clientView.ClientView;
import e_stock.view.clientView.ModifyClientView;

import java.awt.Component;
import javax.swing.*;
import java.awt.event.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;

public class ButtonEditor extends DefaultCellEditor {

    private JButton button;
    private boolean isPushed;
    private JTable table;
    private OrderRepositoryImpl orderRepositoryImpl;
    private OrderLineRepositoryImpl orderLineRepositoryImpl;
    private ModifyOrderView modifyOrderView;
    private DetailsClientView detailsClientView;
    private OrderView orderView;
    Main main;

    public ButtonEditor(Icon icon, OrderRepositoryImpl orderRepositoryImpl, OrderLineRepositoryImpl orderLineRepositoryImpl, ModifyOrderView modifyOrderView, OrderView orderView, Main main) {
        super(new JCheckBox());
        this.orderRepositoryImpl = orderRepositoryImpl;
        this.orderLineRepositoryImpl = orderLineRepositoryImpl;
        this.main = main;
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
                try {
                    performViewAction(modelRow);
                } catch (JRException ex) {
                    Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        isPushed = false;
        return "";
    }

    private void performEditAction(int row) {
        int selectedRow = table.getSelectedRow();
        if (modifyOrderView == null) {
            modifyOrderView = new ModifyOrderView(main);
        }
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            modifyOrderView.setOrderid((int) model.getValueAt(selectedRow, 0));
            String clientName = model.getValueAt(selectedRow, 2).toString(); // Assuming client name is in the 3rd column
            String productName = model.getValueAt(selectedRow, 3).toString(); // Assuming product name is in the 4th column
            int quantity = Integer.parseInt(model.getValueAt(selectedRow, 4).toString()); // Assuming quantity is in the 5th column

            modifyOrderView.setSelectedOrderDetails(clientName, productName, quantity);
            this.main.showForm(modifyOrderView);
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

    private void performViewAction(int modelRow) throws JRException {
        try {
            List<FieldReportPayment> fields = new ArrayList<>();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            // Get the order ID of the clicked row
            int orderIdClicked = (Integer) model.getValueAt(modelRow, 0); // Assuming order ID is in the first column

            // Collect data for all rows with the same order ID
            for (int i = 0; i < model.getRowCount(); i++) {
                int orderId = (Integer) model.getValueAt(i, 0); // Adjust column index as needed
                if (orderId == orderIdClicked) {
                    String productName = (String) model.getValueAt(i, 3); // Adjust column index as needed
                    int quantity = (Integer) model.getValueAt(i, 5); // Adjust column index as needed
                    double price = (Double) model.getValueAt(i, 4); // Adjust column index as needed
                    double total = (Double) model.getValueAt(i, 6); // Adjust column index as needed

                    fields.add(new FieldReportPayment(productName, quantity, price, total));
                }
            }

            // Assuming client name and total amount are same for all rows with same order ID
            String clientName = (String) model.getValueAt(modelRow, 2); // Get client name from the clicked row
            String totalAmount = calculateTotalAmount(fields); // Method to calculate total amount

            InputStream qrCodeStream = null; // Implement QR code generation or retrieval method

            ParameterReportPayment dataPrint = new ParameterReportPayment(clientName, totalAmount, qrCodeStream, fields);
            ReportManager.getInstance().printReportPayment(dataPrint);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String calculateTotalAmount(List<FieldReportPayment> fields) {
        double total = 0;
        for (FieldReportPayment field : fields) {
            total += field.getTotal();
        }
        return String.format("%.2f", total);
    }
}
