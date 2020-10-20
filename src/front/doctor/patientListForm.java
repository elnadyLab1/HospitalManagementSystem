package front.doctor;

import java.awt.*;
import java.awt.event.*;

import Entity.*;
import Factory.DataAccessFactory;
import Service.Impl.PatientServiceImpl;
import Service.Impl.CurePlanServiceImpl;
import Service.Impl.PatientListServiceImpl;
import Service.Impl.DoctorServiceImpl;
import front.ComboItem;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author unknown
 */
public class patientListForm extends JPanel {
    public patientListForm() { initComponents(); }
    private int patientListId;
    public void fillTable(){
        DoctorServiceImpl doctorService = new DoctorServiceImpl(DataAccessFactory.getRepository("txt","doctors.txt"));
        PatientServiceImpl patientService = new PatientServiceImpl(DataAccessFactory.getRepository("txt","patients.txt"));
        CurePlanServiceImpl curePlanService = new CurePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
        PatientListServiceImpl patientListService = new PatientListServiceImpl(DataAccessFactory.getRepository("txt","patientLists.txt"));
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Object columnList[] = {"Id","Patient","Operation","Doctor","End date"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnList);
        if(patientListService.getAllPatientLists() != null) {
            for (PatientListEntity patientList : patientListService.getAllPatientLists()) {
                Object rowData[] = new Object[5];
                rowData[0] = patientList.getId();
                rowData[1] = patientService.getPatientById(patientList.getPatientId()).getName();
                rowData[2] = curePlanService.getCurePlanById(patientList.getCureId()).getCureName();
                rowData[3] = doctorService.getDoctorById(patientList.getDoctorId()).getName();
                rowData[4] = format.format(patientList.getEndDate());
                model.addRow(rowData);
            }
        }
        tblSubs.setModel(model);

    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void btnSaveActionPerformed(ActionEvent e) throws ParseException {
        DoctorServiceImpl doctorService = new DoctorServiceImpl(DataAccessFactory.getRepository("txt","doctors.txt"));
        PatientServiceImpl patientService = new PatientServiceImpl(DataAccessFactory.getRepository("txt","patients.txt"));
        CurePlanServiceImpl curePlanService = new CurePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
        PatientListServiceImpl patientListService = new PatientListServiceImpl(DataAccessFactory.getRepository("txt","patientLists.txt"));

        PatientListEntity patientListEntity = null;
        if(this.patientListId != 0){
            patientListEntity = patientListService.getPatientListById(this.patientListId);
        }else{
            patientListEntity = new PatientListEntity();
        }
        if(patientListEntity == null){
            patientListEntity = new PatientListEntity();
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        patientListEntity.setEndDate(format.parse(txEndDate.getText()));
        patientListEntity.setPatientId(cmbPatient.getSelectedIndex()+1);
        patientListEntity.setDoctorId(cmbDoctor.getSelectedIndex()+1);
        patientListEntity.setCureId(cmbCure.getSelectedIndex()+1);
        int doctorCountAtSameDay = 0;
        int patientMultipleLessonCount = 0;
        for(PatientListEntity patientList : patientListService.getAllPatientLists()){
            if(patientList.getDoctorId() == patientListEntity.getDoctorId() && patientList.getEndDate().equals(patientListEntity.getEndDate())){
                doctorCountAtSameDay++;
            }
            if(patientList.getPatientId() == patientListEntity.getPatientId() && patientList.getEndDate().equals(patientListEntity.getEndDate())){
                patientMultipleLessonCount++;
            }
        }
        boolean errorStatus = false;
        if(doctorCountAtSameDay != 0) {
            JOptionPane.showMessageDialog(null, "The same doctor has another appointment that day !");
            errorStatus = true;
        }
        if(patientMultipleLessonCount == 2){
            JOptionPane.showMessageDialog(null, "The patient cannot make more than 2 appointments on the same day !");
            errorStatus = true;
        }

        if(!errorStatus) {
            patientListService.savePatientList(patientListEntity);
        }
        clearFields();
        fillTable();
    }
    private void clearFields(){
        cmbCure.setSelectedIndex(0);
        cmbDoctor.setSelectedIndex(0);
        cmbPatient.setSelectedIndex(0);
        txEndDate.setText("");
    }
    private void btnDeleteActionPerformed(ActionEvent e) {
        PatientListServiceImpl patientListService = new PatientListServiceImpl(DataAccessFactory.getRepository("txt","patientLists.txt"));
        patientListService.deletePatientList(patientListService.getPatientListById(this.patientListId));
        fillTable();
    }

    private void btnClearActionPerformed(ActionEvent e) {
        clearFields();
    }

    private void tblSubsMouseClicked(MouseEvent e) {
        PatientListServiceImpl patientListService = new PatientListServiceImpl(DataAccessFactory.getRepository("txt","patientLists.txt"));
        PatientListEntity patientListEntity = patientListService.getPatientListById(Integer.parseInt(tblSubs.getValueAt(tblSubs.getSelectedRow(),0).toString()));
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        cmbCure.setSelectedIndex(patientListEntity.getCureId()-1);
        cmbPatient.setSelectedIndex(patientListEntity.getPatientId()-1);
        cmbDoctor.setSelectedIndex(patientListEntity.getDoctorId()-1);
        txEndDate.setText(format.format(patientListEntity.getEndDate()));
        this.patientListId = patientListEntity.getId();
    }

    private void initComponents() {

        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblSubs = new JTable();
        cmbDoctor = new JComboBox();
        cmbCure = new JComboBox();
        cmbPatient = new JComboBox();
        txEndDate = new JTextField();
        btnSave = new JButton();
        btnDelete = new JButton();
        btnClear = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(176, 196, 222));
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
                    . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder
                    . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .
                    awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder( )) )
            ; panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
        ;

            //======== scrollPane1 ========
            {

                //---- tblSubs ----
                tblSubs.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblSubsMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblSubs);
            }

            //---- btnSave ----
            btnSave.setText("Save");
            btnSave.addActionListener(e -> {
                button1ActionPerformed(e);
                try {
                    btnSaveActionPerformed(e);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            });

            //---- btnDelete ----
            btnDelete.setText("Delete");
            btnDelete.addActionListener(e -> btnDeleteActionPerformed(e));

            //---- btnClear ----
            btnClear.setText("Clear");
            btnClear.addActionListener(e -> btnClearActionPerformed(e));

            //---- label1 ----
            label1.setText("Doctor");

            //---- label2 ----
            label2.setText("Cure");

            //---- label3 ----
            label3.setText("Patient");

            //---- label4 ----
            label4.setText("End date");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addGroup(panel1Layout.createParallelGroup()
                                                            .addComponent(label1)
                                                            .addComponent(label2)
                                                            .addComponent(label3)
                                                            .addComponent(label4))
                                                    .addGap(65, 65, 65)
                                                    .addGroup(panel1Layout.createParallelGroup()
                                                            .addComponent(cmbCure, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                                            .addComponent(cmbPatient, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                                            .addComponent(cmbDoctor, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                                            .addComponent(txEndDate, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                                                    .addGap(45, 45, 45))
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(btnDelete)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                                    .addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(28, 28, 28))))
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addGap(25, 25, 25)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(cmbDoctor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label1))
                                                    .addGap(29, 29, 29)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(cmbCure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label2))
                                                    .addGap(33, 33, 33)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(cmbPatient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label3))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(txEndDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label4))
                                                    .addGap(23, 23, 23)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(btnSave)
                                                            .addComponent(btnDelete)
                                                            .addComponent(btnClear))))
                                    .addGap(2, 2, 2))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        fillTable();
        DoctorServiceImpl doctorService = new DoctorServiceImpl(DataAccessFactory.getRepository("txt","doctors.txt"));
        PatientServiceImpl patientService = new PatientServiceImpl(DataAccessFactory.getRepository("txt","patients.txt"));
        CurePlanServiceImpl curePlanService = new CurePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
        for(DoctorEntity doctor:doctorService.getAllDoctors()){
            cmbDoctor.addItem(new ComboItem(doctor.getName()+" "+doctor.getSurname(),String.valueOf(doctor.getId())));
        }
        for(PatientEntity patient:patientService.getAllPatients()){
            cmbPatient.addItem(new ComboItem(patient.getName()+" "+patient.getSurname(),String.valueOf(patient.getId())));
        }
        for(CurePlanEntity plan:curePlanService.getAllCurePlans()){
            cmbCure.addItem(new ComboItem(plan.getCureName(),String.valueOf(plan.getId())));
        }
    }

    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable tblSubs;
    private JComboBox cmbDoctor;
    private JComboBox cmbCure;
    private JComboBox cmbPatient;
    private JTextField txEndDate;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnClear;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
