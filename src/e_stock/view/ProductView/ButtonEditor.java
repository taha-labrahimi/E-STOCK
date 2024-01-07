package e_stock.view.ProductView;

import com.raven.main.Main;
import e_stock.view.clientView.*;
import e_stock.RepositoryImplementation.ClientRepositoryImpl;
import e_stock.RepositoryImplementation.ProductRepositoryImpl;
import e_stock.view.clientView.ClientView;
import e_stock.view.clientView.ModifyClientView;
import java.awt.Component;
import javax.swing.*;
import java.awt.event.*;

public class ButtonEditor extends DefaultCellEditor {

    private JButton button;
    private boolean isPushed;
    private JTable table;
    private ProductRepositoryImpl productRepository;
    private ModifyProductView modifyProductView;
    private DetailsProductView detailProductView;
    private ProductView productView;
    private Main main;
    public ButtonEditor(Icon icon, ProductRepositoryImpl productRepository, ModifyProductView modifyProductView, ProductView productView,Main main) {
        super(new JCheckBox());
        this.productRepository = productRepository;
        this.modifyProductView = modifyProductView;
        this.productView = productView;
        this.button = new JButton(icon);
        this.button.setOpaque(true);
        this.main = main;
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
        if (modifyProductView == null) {
            modifyProductView = new ModifyProductView(main);
        }

        // Fetch client data from the row and set it to the modifyClientView
        modifyProductView.setProductcode(table.getValueAt(row, 0).toString());
        modifyProductView.setProductname(table.getValueAt(row, 1).toString());
        modifyProductView.setQteTextField(table.getValueAt(row, 2).toString());
        modifyProductView.setPrice(table.getValueAt(row, 3).toString());
        modifyProductView.setSupplierCombo(table.getValueAt(row, 4).toString());
        ImageIcon imageicon = (ImageIcon) table.getValueAt(row, 5);
        modifyProductView.setImage(imageicon);

        this.main.showForm(modifyProductView);
    }

    private void performDeleteAction(int row) {
        if (row >= 0) { // Ensure the row index is valid
            int modelRow = table.convertRowIndexToModel(row);
            int productCode = Integer.parseInt(table.getModel().getValueAt(modelRow, 0).toString());
            if (!productRepository.hassupplier(productCode)) {
                productRepository.delete(productCode);
                JOptionPane.showMessageDialog(productView, "product deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(productView, "Cannot delete product. product is referenced in orders.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
            }
            SwingUtilities.invokeLater(() -> productView.loadProductAndPopulateTable()); // Safely update the table model
        } else {
            JOptionPane.showMessageDialog(productView, "No row selected to delete.", "Error", JOptionPane.ERROR_MESSAGE);
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
        if(detailProductView ==null){
            detailProductView =new DetailsProductView(main);
        }
         // Fetch client data from the row and set it to the modifyClientView
       
        detailProductView.setProductcode(table.getValueAt(modelRow, 0).toString());
        detailProductView.setProductname(table.getValueAt(modelRow, 1).toString());
        detailProductView.setQteTextField(table.getValueAt(modelRow, 2).toString());
        detailProductView.setPrice(table.getValueAt(modelRow, 3).toString());
        detailProductView.setSupplierName(table.getValueAt(modelRow, 4).toString());
        ImageIcon imageicon = (ImageIcon) table.getValueAt(modelRow, 5);
        detailProductView.setImage(imageicon);

        this.main.showForm(detailProductView);
    }
}
