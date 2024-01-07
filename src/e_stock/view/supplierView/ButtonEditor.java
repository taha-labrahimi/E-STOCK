/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e_stock.view.supplierView;

import com.raven.main.Main;
import e_stock.RepositoryImplementation.SupplierRepositoryImpl;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author douae
 */
public class ButtonEditor extends DefaultCellEditor {

    private JButton button;
    private boolean isPushed;
    private JTable table;
    private SupplierRepositoryImpl supplierRepository;
    private ModifySupplierView modifySuppliertView;
    private DetailsSupplierView detailsSupplierView;
    private SupplierView supplierView;
    private Main main;
    public ButtonEditor(Icon icon, SupplierRepositoryImpl supplierRepository, ModifySupplierView modifySuppliertVieww, SupplierView supplierView,Main main) {
        super(new JCheckBox());
        this.supplierRepository = supplierRepository;
        this.modifySuppliertView = modifySuppliertView;
        this.main=main;
        this.supplierView = supplierView;
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
        if (modifySuppliertView == null) {
            modifySuppliertView = new ModifySupplierView(main);
        }

        // Fetch client data from the row and set it to the modifyClientView
        modifySuppliertView.setSuppliercode(table.getValueAt(row, 0).toString());
        modifySuppliertView.setFirstname(table.getValueAt(row, 1).toString());
        modifySuppliertView.setLastname(table.getValueAt(row, 2).toString());
        modifySuppliertView.setAdresse(table.getValueAt(row, 3).toString());
        modifySuppliertView.setCity(table.getValueAt(row, 4).toString());
        modifySuppliertView.setCountry(table.getValueAt(row, 5).toString());
        modifySuppliertView.setPhonenumber(table.getValueAt(row, 6).toString());

        this.main.showForm(modifySuppliertView);
    }

    private void performDeleteAction(int row) {
        if (row >= 0) { // Ensure the row index is valid
            int modelRow = table.convertRowIndexToModel(row);
            int supplierCode = Integer.parseInt(table.getModel().getValueAt(modelRow, 0).toString());
            supplierRepository.delete(supplierCode);
             
            SwingUtilities.invokeLater(() -> supplierView.loadSuupliersAndPopulateTable()); // Safely update the table model
        } else {
            JOptionPane.showMessageDialog(supplierView, "No row selected to delete.", "Error", JOptionPane.ERROR_MESSAGE);
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
        if(detailsSupplierView ==null){
            detailsSupplierView =new DetailsSupplierView(main);
        }
         // Fetch client data from the row and set it to the modifyClientView
        detailsSupplierView.setSuppliercode(table.getValueAt(modelRow, 0).toString());
        detailsSupplierView.setFirstname(table.getValueAt(modelRow, 1).toString());
        detailsSupplierView.setLastname(table.getValueAt(modelRow, 2).toString());
        detailsSupplierView.setAdresse(table.getValueAt(modelRow, 3).toString());
        detailsSupplierView.setCity(table.getValueAt(modelRow, 4).toString());
        detailsSupplierView.setCountry(table.getValueAt(modelRow, 5).toString());
        detailsSupplierView.setPhonenumber(table.getValueAt(modelRow, 6).toString());

        this.main.showForm(detailsSupplierView);
    }
}

