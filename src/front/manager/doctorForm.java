package front.manager;

import java.awt.*;
import java.awt.event.*;
import Entity.DoctorEntity;
import Factory.DataAccessFactory;
import Service.Impl.HospitalServiceImpl;
import Service.Impl.DoctorServiceImpl;
import front.ComboItem;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

public class doctorForm extends JPanel {
    private int doctorId = 0;
    public doctorForm() {
        initComponents();
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("doctorForm");
        frame.setContentPane(new doctorForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void tblDoctorMouseClicked(MouseEvent e) {
        this.doctorId = Integer.parseInt(tblDoctor.getValueAt(tblDoctor.getSelectedRow(),0).toString());
        txName.setText(tblDoctor.getValueAt(tblDoctor.getSelectedRow(),1).toString());
        txSurname.setText(tblDoctor.getValueAt(tblDoctor.getSelectedRow(),2).toString());
        txPhone.setText(tblDoctor.getValueAt(tblDoctor.getSelectedRow(),3).toString());
        txPassword.setText(tblDoctor.getValueAt(tblDoctor.getSelectedRow(),4).toString());
        txSalary.setText(tblDoctor.getValueAt(tblDoctor.getSelectedRow(),6).toString());
        if(tblDoctor.getValueAt(tblDoctor.getSelectedRow(),5).toString().equals("true")){
            cmbStatus.setSelectedIndex(0);
        }else{
            cmbStatus.setSelectedIndex(1);
        }
    }

    private void btnDeleteActionPerformed(ActionEvent e) {
        DoctorServiceImpl doctorService = new DoctorServiceImpl(DataAccessFactory.getRepository("txt", "doctors.txt"));
        DoctorEntity doctor = doctorService.getDoctorById(this.doctorId);
        doctorService.deleteDoctor(doctor);
        clearFields();
        fillTable();
    }

    private void btnUpdateActionPerformed(ActionEvent e) {
        DoctorServiceImpl doctorService = new DoctorServiceImpl(DataAccessFactory.getRepository("txt", "doctors.txt"));
        DoctorEntity doctorEntity = null;
        if(this.doctorId != 0){
            doctorEntity = doctorService.getDoctorById(this.doctorId);
        }else{
            doctorEntity = new DoctorEntity();
            doctorEntity.setStartDate(new Date());
            doctorEntity.setEndDate(new Date());
        }
        doctorEntity.setName(txName.getText());
        doctorEntity.setSurname(txSurname.getText());
        doctorEntity.setPhoneNumber(txPhone.getText());
        doctorEntity.setPassword(txPassword.getText());
        doctorEntity.setSalary(Double.parseDouble(txSalary.getText()));

        if(cmbStatus.getSelectedIndex() == 0){
            doctorEntity.setStatus(true);
        }else{
            doctorEntity.setStatus(false);
        }
        doctorService.saveDoctor(doctorEntity);
        clearFields();
        fillTable();

    }
    private void initComponents() {
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblDoctor = new JTable();
        txName = new JTextField();
        txSurname = new JTextField();
        txPhone = new JTextField();
        txPassword = new JTextField();
        txSalary = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        btnUpdate = new JButton();
        btnDelete = new JButton();
        label6 = new JLabel();
        cmbStatus = new JComboBox();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(176, 196, 222));
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
                    . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder
                    . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .
                    awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder( )) )
            ; panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
        ;

            //======== scrollPane1 ========
            {

                //---- tblDoctor ----
                tblDoctor.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblDoctorMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblDoctor);
            }

            //---- label1 ----
            label1.setText("Name");

            //---- label2 ----
            label2.setText("Surname");

            //---- label3 ----
            label3.setText("Phone");

            //---- label4 ----
            label4.setText("Password");

            //---- label5 ----
            label5.setText("Salary");

            //---- btnUpdate ----
            btnUpdate.setText("Update");
            btnUpdate.addActionListener(e -> btnUpdateActionPerformed(e));

            //---- btnDelete ----
            btnDelete.setText("Delete");
            btnDelete.addActionListener(e -> btnDeleteActionPerformed(e));


            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 928, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addGap(18, 18, 18)
                                                    .addGroup(panel1Layout.createParallelGroup()
                                                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                                    .addComponent(label2)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                                                    .addComponent(txSurname, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                                    .addComponent(label1)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                                                    .addComponent(txName, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                                    .addComponent(label3)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                                                    .addComponent(txPhone, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                                    .addComponent(label4)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                                                    .addComponent(txPassword, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                                    .addComponent(label5)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                            .addComponent(cmbStatus, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                                                            .addComponent(txSalary, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))))
                                                    .addGap(28, 28, 28))
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addGap(18, 18, 18)
                                                    .addGroup(panel1Layout.createParallelGroup()
                                                            .addGroup(panel1Layout.createSequentialGroup()
                                                                    .addComponent(btnUpdate)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnDelete))
                                                            .addComponent(label6))
                                                    .addContainerGap(35, Short.MAX_VALUE))))
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(44, 44, 44)
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(txName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label1))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(txSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label2))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(txPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label3))
                                    .addGap(18, 18, 18)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(txPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label4))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(txSalary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label5))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label6))
                                    .addGap(18, 18, 18)
                                    .addComponent(cmbStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnUpdate)
                                            .addComponent(btnDelete))
                                    .addGap(39, 39, 39))
            );
        }

        fillTable();
    }
    private void clearFields(){
        this.doctorId = 0;
        txName.setText("");
        txSurname.setText("");
        txPhone.setText("");
        txPassword.setText("");
        txSalary.setText("");
    }
    private void fillTable(){
        Object columnList[] = {"Id","Name","Surname","phoneNum","password","isActive","salary"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnList);
        DoctorServiceImpl doctorService = new DoctorServiceImpl(DataAccessFactory.getRepository("txt", "doctors.txt"));
        ArrayList<DoctorEntity> doctorEntityArrayList = doctorService.getAllDoctors();
        Object rowData[] = new Object[8];

        for(DoctorEntity doctorEntity : doctorEntityArrayList){
            rowData[0] = doctorEntity.getId();
            rowData[1] = doctorEntity.getName();
            rowData[2] = doctorEntity.getSurname();
            rowData[3] = doctorEntity.getPhoneNumber();
            rowData[4] = doctorEntity.getPassword();
            rowData[5] = doctorEntity.isStatus();
            rowData[6] = doctorEntity.getSalary();
            model.addRow(rowData);
        }
        tblDoctor.setModel(model);
        cmbStatus.addItem(new ComboItem("Active","1"));
        cmbStatus.addItem(new ComboItem("Passive","0"));

    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable tblDoctor;
    private JTextField txName;
    private JTextField txSurname;
    private JTextField txPhone;
    private JTextField txPassword;
    private JTextField txSalary;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JLabel label6;
    private JComboBox cmbStatus;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
