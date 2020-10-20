package front.manager;

import java.awt.*;
import java.awt.event.*;
import Entity.OperationEntity;
import Factory.DataAccessFactory;
import Service.Impl.OperationServiceImpl;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class  operationForm extends JPanel {
    public operationForm() {
        initComponents();
    }
    private int operationId=0;
    private void fillTable(){
        Object columnList[] = {"Id","Name"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnList);
        OperationServiceImpl operationService = new OperationServiceImpl(DataAccessFactory.getRepository("txt", "operations.txt"));
        ArrayList<OperationEntity> allOperations = operationService.getAllOperations();
        if(allOperations != null && !allOperations.isEmpty()) {
            Object rowData[] = new Object[2];
            for (OperationEntity operation : allOperations) {
                rowData[0] = operation.getId();
                rowData[1] = operation.getOperationName();
                model.addRow(rowData);
            }
        }
        tblEquip.setModel(model);
    }
    private void clearFields(){
        txOperationName.setText("");
        this.operationId = 0;
    }

    private void scrollPane1MouseClicked(MouseEvent e) {

    }

    private void btnSaveActionPerformed(ActionEvent e) {
        OperationEntity operationEntity;
        OperationServiceImpl operationService = new OperationServiceImpl(DataAccessFactory.getRepository("txt", "operations.txt"));
        if(this.operationId != 0){
            operationEntity = operationService.getOperationById(this.operationId);
        }else{
            operationEntity = new OperationEntity();
        }
        operationEntity.setOperationName(txOperationName.getText());
        operationService.saveOperation(operationEntity);
        fillTable();
        clearFields();
    }

    private void btnDeleteActionPerformed(ActionEvent e) {
        OperationServiceImpl operationService = new OperationServiceImpl(DataAccessFactory.getRepository("txt", "operations.txt"));
        OperationEntity operationEntity = operationService.getOperationById(this.operationId);
        operationService.deleteOperation(operationEntity);
        clearFields();
        fillTable();
    }

    private void tblEquipMouseClicked(MouseEvent e) {
        this.operationId = Integer.parseInt(tblEquip.getValueAt(tblEquip.getSelectedRow(),0).toString());
        txOperationName.setText(tblEquip.getValueAt(tblEquip.getSelectedRow(),1).toString());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblEquip = new JTable();
        txOperationName = new JTextField();
        label1 = new JLabel();
        btnSave = new JButton();
        btnDelete = new JButton();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(176, 196, 222));
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
                    border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER
                    , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font
                    .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (
                new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r"
                        .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

            //======== scrollPane1 ========
            {
                scrollPane1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        scrollPane1MouseClicked(e);
                    }
                });

                //---- tblEquip ----
                tblEquip.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblEquipMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblEquip);
            }

            //---- label1 ----
            label1.setText("Operation Name");

            //---- btnSave ----
            btnSave.setText("Save");
            btnSave.addActionListener(e -> btnSaveActionPerformed(e));

            //---- btnDelete ----
            btnDelete.setText("Delete");
            btnDelete.addActionListener(e -> btnDeleteActionPerformed(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(label1)
                                            .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                            .addComponent(txOperationName, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                    .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addGap(30, 30, 30)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(txOperationName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label1))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(btnSave)
                                                            .addComponent(btnDelete)))
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(64, Short.MAX_VALUE))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        fillTable();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable tblEquip;
    private JTextField txOperationName;
    private JLabel label1;
    private JButton btnSave;
    private JButton btnDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
