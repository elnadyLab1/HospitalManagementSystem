package front.doctor;

import java.awt.*;
import java.awt.event.*;
import Entity.OperationEntity;
import Entity.CurePlanEntity;
import Entity.DoctorEntity;
import Factory.DataAccessFactory;
import Service.Impl.OperationServiceImpl;
import Service.Impl.CurePlanServiceImpl;
import Service.Impl.DoctorServiceImpl;
import front.ComboItem;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;


public class cureForm extends JPanel {
    public cureForm() {
        initComponents();
    }
    private int cureId = 0;

    private void clearFields(){
        txDuration.setText("");
        operationList.setSelectedIndex(0);
        txName.setText("");
        this.cureId = 0;
    }
    private void btnSaveActionPerformed(ActionEvent e) {
        CurePlanServiceImpl curePlanService = new CurePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
        CurePlanEntity plan = null;
        if(this.cureId != 0){
            plan = curePlanService.getCurePlanById(this.cureId);
        }else{
            plan = new CurePlanEntity();
        }
        plan.setDuration(txDuration.getText());
        List<ComboItem> operations = operationList.getSelectedValuesList();
        String operationIds = "";
        for(ComboItem operation:operations){
            if(operationIds.equals("")){
                operationIds = operation.getValue();
            }else{
                operationIds = operation.getValue()+","+operationIds;
            }
        }
        plan.setOperationId(operationIds.toString());
        plan.setDoctorId(cmbDoctor.getSelectedIndex()+1);
        plan.setCureName(txName.getText());
        curePlanService.saveCurePlan(plan);
        fillTable();

    }

    private void scrollPane1MouseClicked(MouseEvent e) {

    }

    private void tblCureMouseClicked(MouseEvent e) {
        CurePlanServiceImpl curePlanService = new CurePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
        CurePlanEntity curePlanEntity = curePlanService.getCurePlanById(Integer.parseInt(tblCure.getValueAt(tblCure.getSelectedRow(),0).toString()));
        this.cureId = curePlanEntity.getId();
        txDuration.setText(curePlanEntity.getDuration());
        cmbDoctor.setSelectedIndex(curePlanEntity.getDoctorId()-1);
        int[] operationIndex = new int[10];
        int i=0;
        for(String id : curePlanEntity.getOperationId().split(",")){
            operationIndex[i] = Integer.parseInt(id);
        }
        operationList.setSelectedIndices(operationIndex);
        txName.setText(curePlanEntity.getCureName());
    }

    private void btnDeleteMouseClicked(MouseEvent e) {
        CurePlanServiceImpl curePlanService = new CurePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
        curePlanService.deleteCurePlan(curePlanService.getCurePlanById(this.cureId));
        clearFields();
        fillTable();
    }

    private void btnClearMouseClicked(MouseEvent e) {
        clearFields();
    }

    private void initComponents() {

        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblCure = new JTable();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        cmbDoctor = new JComboBox();
        scrollPane2 = new JScrollPane();
        operationList = new JList();
        txDuration = new JTextField();
        btnSave = new JButton();
        btnDelete = new JButton();
        btnClear = new JButton();
        label4 = new JLabel();
        txName = new JTextField();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(176, 196, 222));
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
                    border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER
                    ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font
                    .BOLD,12),java.awt.Color.red),panel1. getBorder()));panel1. addPropertyChangeListener(
                new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r"
                        .equals(e.getPropertyName()))throw new RuntimeException();}});

            //======== scrollPane1 ========
            {
                scrollPane1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        scrollPane1MouseClicked(e);
                    }
                });

                //---- tblExercise ----
                tblCure.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblCureMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblCure);
            }

            //---- label1 ----
            label1.setText("Doctor");

            //---- label2 ----
            label2.setText("Operations");

            //---- label3 ----
            label3.setText("Duration");

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(operationList);
            }

            //---- btnSave ----
            btnSave.setText("Save");
            btnSave.addActionListener(e -> btnSaveActionPerformed(e));

            //---- btnDelete ----
            btnDelete.setText("Delete");
            btnDelete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnDeleteMouseClicked(e);
                }
            });

            //---- btnClear ----
            btnClear.setText("Clear");
            btnClear.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnClearMouseClicked(e);
                }
            });

            //---- label4 ----
            label4.setText("Op. Name");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addGap(18, 18, 18)
                                                    .addGroup(panel1Layout.createParallelGroup()
                                                            .addComponent(label2)
                                                            .addGroup(panel1Layout.createSequentialGroup()
                                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                            .addComponent(label3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(label1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    .addGap(18, 18, 18)
                                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                            .addComponent(cmbDoctor, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                                                            .addComponent(txDuration, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                                                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                                    .addComponent(label4)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txName, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addGap(36, 36, 36)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                            .addGroup(panel1Layout.createSequentialGroup()
                                                                    .addComponent(btnSave)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(btnDelete))
                                                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))))
                                    .addContainerGap(17, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                    .addContainerGap(546, Short.MAX_VALUE)
                                    .addComponent(btnClear)
                                    .addGap(110, 110, 110))
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(label4)
                                                            .addComponent(txName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(label1)
                                                            .addComponent(cmbDoctor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                    .addGap(19, 19, 19)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(label3)
                                                            .addComponent(txDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                    .addGap(18, 18, 18)
                                                    .addComponent(label2)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(btnDelete)
                                                            .addComponent(btnSave)))
                                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE))
                                    .addGap(20, 20, 20)
                                    .addComponent(btnClear)
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        fillTable();
        OperationServiceImpl operationService = new OperationServiceImpl(DataAccessFactory.getRepository("txt","operations.txt"));
        ArrayList<OperationEntity> operationEntityArrayList = operationService.getAllOperations();
        DefaultListModel operationListModel = new DefaultListModel();
        for(OperationEntity operationEntity:operationEntityArrayList){
            ComboItem comboItem = new ComboItem(operationEntity.getOperationName(),Integer.toString(operationEntity.getId()));
            operationListModel.addElement(comboItem);
        }
        operationList.setModel(operationListModel);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable tblCure;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JComboBox cmbDoctor;
    private JScrollPane scrollPane2;
    private JList operationList;
    private JTextField txDuration;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnClear;
    private JLabel label4;
    private JTextField txName;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public void fillTable(){
        DoctorServiceImpl doctorService = new DoctorServiceImpl(DataAccessFactory.getRepository("txt","doctors.txt"));
        ArrayList<DoctorEntity> doctorEntities = doctorService.getAllDoctors();
        for(DoctorEntity doctorEntity : doctorEntities){
            cmbDoctor.addItem(new ComboItem(doctorEntity.getName()+" "+doctorEntity.getSurname(),String.valueOf(doctorEntity.getId())));
        }
        OperationServiceImpl operationService = new OperationServiceImpl(DataAccessFactory.getRepository("txt","operations.txt"));
        CurePlanServiceImpl curePlanService = new CurePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
        ArrayList<CurePlanEntity> curePlanEntities = curePlanService.getAllCurePlans();
        Object columnList[] = {"Id","Doctor","Operations","Duration"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnList);
        if(curePlanEntities != null && !curePlanEntities.isEmpty()){
            Object rowData[] = new Object[4];
            for(CurePlanEntity curePlanEntity:curePlanEntities){
                rowData[0] = curePlanEntity.getId();
                rowData[1] = doctorService.getDoctorById(curePlanEntity.getDoctorId()).getName();
                String operationNames = "";
                for (String operationId:curePlanEntity.getOperationId().split(",")){
                    if(operationNames.equals("")){
                        operationNames = operationService.getOperationById(Integer.parseInt(operationId)).getOperationName();
                    }else{
                        operationNames = operationService.getOperationById(Integer.parseInt(operationId)).getOperationName() + operationNames;
                    }
                }
                rowData[2] = operationNames;
                rowData[3] = curePlanEntity.getDuration();
                model.addRow(rowData);
            }
        }
        tblCure.setModel(model);

    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
