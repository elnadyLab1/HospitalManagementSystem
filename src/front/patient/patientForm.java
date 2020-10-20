package front.patient;

import java.awt.*;
import java.awt.event.*;
import Entity.PatientEntity;
import Entity.CurePlanEntity;
import Entity.PatientListEntity;
import Entity.DoctorEntity;
import Factory.DataAccessFactory;
import Service.Impl.PatientServiceImpl;
import Service.Impl.CurePlanServiceImpl;
import Service.Impl.PatientListServiceImpl;
import Service.Impl.DoctorServiceImpl;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class patientForm extends JPanel {
    public patientForm(int _id) {
        this.setPatientId(_id);
        initComponents();
    }
    private int patientId;
    private int patientListId=0;
    private void fillTable(){
        PatientServiceImpl patientService = new PatientServiceImpl(DataAccessFactory.getRepository("txt","patients.txt"));
        PatientEntity patient = patientService.getPatientById(this.getPatientId());
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        if(patient != null){
            PatientListServiceImpl patientListService = new PatientListServiceImpl(DataAccessFactory.getRepository("txt","patientLists.txt"));
            DoctorServiceImpl doctorService = new DoctorServiceImpl(DataAccessFactory.getRepository("txt","doctors.txt"));
            CurePlanServiceImpl curePlanService = new CurePlanServiceImpl(DataAccessFactory.getRepository("txt","plans.txt"));
            Object columnList[] = {"Id","Cure","Doctor","End date","Duration"};
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnList);
            for(PatientListEntity patientList : patientListService.getPPatientList(patient)){
                Object rowData[] = new Object[5];
                CurePlanEntity cure = curePlanService.getCurePlanById(patientList.getCureId());
                DoctorEntity doctor = doctorService.getDoctorById(patientList.getDoctorId());
                rowData[0] = patientList.getId();
                rowData[1] = cure.getCureName();
                rowData[2] = doctor.getName()+" "+doctor.getSurname();
                rowData[3] = format.format(patientList.getEndDate());
                rowData[4] = cure.getDuration();
                model.addRow(rowData);
            }
            tblLessons.setModel(model);
        }
    }

    private void btnSaveActionPerformed(ActionEvent e) {
        PatientServiceImpl patientService = new PatientServiceImpl(DataAccessFactory.getRepository("txt","patients.txt"));
        PatientEntity patient = patientService.getPatientById(this.getPatientId());
        patient.setPassword(txPassword.getText());
        patient.setPhoneNumber(txPhone.getText());
        patientService.savePatient(patient);
        JOptionPane.showMessageDialog(null, "Your information is updated !");
    }

    private void tblLessonsMouseClicked(MouseEvent e) {
        this.setPatientListId(Integer.parseInt(tblLessons.getValueAt(tblLessons.getSelectedRow(),0).toString()));
    }

    private void btnDeleteActionPerformed(ActionEvent e) {
        PatientListServiceImpl patientListService = new PatientListServiceImpl(DataAccessFactory.getRepository("txt","patientLists.txt"));
        patientListService.deletePatientList(patientListService.getPatientListById(this.getPatientListId()));
        JOptionPane.showMessageDialog(null, "Your cure is deleted !");
        fillTable();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblLessons = new JTable();
        btnDelete = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        txPhone = new JTextField();
        txPassword = new JTextField();
        btnSave = new JButton();
        lbFullName = new JLabel();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(176, 196, 222));
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
                    .swing.border.EmptyBorder(0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing
                    .border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
                    Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red
            ),panel1. getBorder()));panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
        public void propertyChange(java.beans.PropertyChangeEvent e){if("bord\u0065r".equals(e.getPropertyName(
        )))throw new RuntimeException();}});

            //======== scrollPane1 ========
            {

                //---- tblLessons ----
                tblLessons.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblLessonsMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblLessons);
            }

            //---- btnDelete ----
            btnDelete.setText("Delete my cure");
            btnDelete.addActionListener(e -> btnDeleteActionPerformed(e));

            //---- label1 ----
            label1.setText("My information");

            //---- label2 ----
            label2.setText("Full name");

            //---- label3 ----
            label3.setText("Password");

            //---- label4 ----
            label4.setText("Phone Num");

            //---- btnSave ----
            btnSave.setText("Save");
            btnSave.addActionListener(e -> btnSaveActionPerformed(e));

            //---- lbFullName ----
            lbFullName.setText("text");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 543, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addGap(0, 50, Short.MAX_VALUE)
                                                    .addComponent(label1)
                                                    .addGap(59, 59, 59))
                                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                            .addComponent(btnDelete, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                                            .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                                                    .addGroup(panel1Layout.createParallelGroup()
                                                                            .addComponent(label4)
                                                                            .addComponent(label3)
                                                                            .addComponent(label2))
                                                                    .addGap(18, 18, 18)
                                                                    .addGroup(panel1Layout.createParallelGroup()
                                                                            .addComponent(txPhone, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                                                            .addComponent(txPassword, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                                                            .addComponent(lbFullName, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
                                                            .addComponent(btnSave, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                                                    .addGap(32, 32, 32))))
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addComponent(label1)
                                                    .addGap(10, 10, 10)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(label2)
                                                            .addComponent(lbFullName))
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(label3)
                                                            .addComponent(txPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(label4)
                                                            .addComponent(txPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                    .addGap(18, 18, 18)
                                                    .addComponent(btnSave)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnDelete)))
                                    .addContainerGap(43, Short.MAX_VALUE))
            );
        }

        fillTable();
        PatientServiceImpl patientService = new PatientServiceImpl(DataAccessFactory.getRepository("txt","patients.txt"));
        PatientEntity patient = patientService.getPatientById(this.getPatientId());
        lbFullName.setText(patient.getName()+" "+patient.getSurname());
        txPassword.setText(patient.getPassword());
        txPhone.setText(patient.getPhoneNumber());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable tblLessons;
    private JButton btnDelete;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField txPhone;
    private JTextField txPassword;
    private JButton btnSave;
    private JLabel lbFullName;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPatientListId() {
        return patientListId;
    }

    public void setPatientListId(int patientListId) {
        this.patientListId = patientListId;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}