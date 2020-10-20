package front;

import Entity.WorkersEntity;
import Entity.PatientEntity;
import Factory.DataAccessFactory;
import Factory.WorkerServiceFactory;
import Payment.PayCash;
import Service.IWorkerService;
import Service.Impl.PatientServiceImpl;
import front.patient.patientForm;
import front.manager.managerForm;
import front.doctor.doctorForm;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.*;


public class loginForm extends JPanel {


    public loginForm() {
        initComponents();
    }

    private void btnLoginActionPerformed(ActionEvent e) {

        PatientEntity patientEntity = new PatientEntity(1,"Caner","kan","532 565 48 11","12345",true,"test adress","caner@gmail.com",0,"NOT_PAID");
        PatientServiceImpl patientService = new PatientServiceImpl(DataAccessFactory.getRepository("txt", "patients.txt"));

        patientService.makePayment(patientEntity,100,new PayCash());

        patientService.savePatient(patientEntity);


        IWorkerService loginService = WorkerServiceFactory.getService(cmbUserType.getSelectedIndex());
        Object obj = loginService.login(txUsername.getText(),txPassword.getText());
        WorkersEntity workersEntity = (WorkersEntity) obj;
        if(workersEntity != null){
            if(cmbUserType.getSelectedIndex() == 1){
                // Manager screen opened.
                this.setVisible(false);
                JFrame frame = new JFrame("managerForm");
                frame.setContentPane(new managerForm().getPanel1());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }else if(cmbUserType.getSelectedIndex() ==0){
                this.setVisible(false);
                JFrame frame = new JFrame("doctorForm");
                frame.setContentPane(new doctorForm().getPanel1());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }else if(cmbUserType.getSelectedIndex() == 2){
                this.setVisible(false);
                JFrame frame = new JFrame("patientForm");
                JPanel patForm = new patientForm(workersEntity.getId());
                frame.setContentPane(((patientForm) patForm).getPanel1());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        txUsername = new JTextField();
        txPassword = new JPasswordField();
        cmbUserType = new JComboBox();
        label3 = new JLabel();
        btnLogin = new JButton();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
                .swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing
                .border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
                Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt.Color.red
        ), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
        public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName(
        )))throw new RuntimeException();}});
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setBackground(new Color(176, 196, 222));


            //---- label1 ----
            label1.setText("Name");

            //---- label2 ----
            label2.setText("Password");

            //---- label3 ----
            label3.setText("User type");

            //---- btnLogin ----
            btnLogin.setText("Login");
            btnLogin.addActionListener(e -> btnLoginActionPerformed(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addGroup(panel1Layout.createParallelGroup()
                                                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(cmbUserType, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                                            .addComponent(txUsername, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                                            .addComponent(txPassword, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))))
                                    .addContainerGap(43, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(59, 59, 59)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label1)
                                            .addComponent(txUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label2)
                                            .addComponent(txPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(cmbUserType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label3))
                                    .addGap(18, 18, 18)
                                    .addComponent(btnLogin)
                                    .addContainerGap(72, Short.MAX_VALUE))
            );
        }
        add(panel1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        cmbUserType.addItem(new ComboItem("Doctor","1"));
        cmbUserType.addItem(new ComboItem("Manager","2"));
        cmbUserType.addItem(new ComboItem("Patient","3"));

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JTextField txUsername;
    private JPasswordField txPassword;
    private JComboBox cmbUserType;
    private JLabel label3;
    private JButton btnLogin;
    // JFormDesigner - End of variables declaration  //GEN-END:variables



    public static void main(String[] args) {

        JFrame frame = new JFrame("loginForm");
        frame.setContentPane(new loginForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
