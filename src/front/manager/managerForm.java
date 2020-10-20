package front.manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;


public class managerForm extends JPanel {
    public managerForm() {
        initComponents();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Manager Form");
        frame.setContentPane(new managerForm().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void btnDoctorOpActionPerformed(ActionEvent e) {
        this.setVisible(false);
        JFrame frame = new JFrame("Doctor Form");
        frame.setContentPane(new doctorForm().getPanel1());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void button1ActionPerformed(ActionEvent e) {
        this.setVisible(false);
        JFrame frame = new JFrame("Patient Form");
        frame.setContentPane(new patientForm().getPanel1());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void btnOperationActionPerformed(ActionEvent e) {
        this.setVisible(false);
        JFrame frame = new JFrame("Operation Form");
        frame.setContentPane(new operationForm().getPanel1());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



    private void initComponents() {
        panel1 = new JPanel();
        btnDoctorOp = new JButton();
        button1 = new JButton();
        btnOperation = new JButton();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
                . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder
                . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .
                awt . Font. BOLD ,12 ) ,java . awt. Color .red ) , getBorder () ) )
        ;  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
        ) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
        ;
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setBackground(new Color(176, 196, 222));

            //btnDoctorOp
            btnDoctorOp.setText("Doctor operations");
            btnDoctorOp.addActionListener(e -> btnDoctorOpActionPerformed(e));

            //button1
            button1.setText("Patient operations");
            button1.addActionListener(e -> button1ActionPerformed(e));


            btnOperation.setText("Medical Operations");
            btnOperation.addActionListener(e -> btnOperationActionPerformed(e));


            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnOperation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnDoctorOp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(button1, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                                   .addGap(18, 18, 18))
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnDoctorOp, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnOperation, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(154, Short.MAX_VALUE))
            );
        }
        add(panel1, BorderLayout.NORTH);

    }


    private JPanel panel1;
    private JButton btnDoctorOp;
    private JButton button1;
    private JButton btnOperation;

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
