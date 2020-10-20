package front.doctor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

public class doctorForm extends JPanel {
    public doctorForm() {
        initComponents();
    }

    private void btnCureActionPerformed(ActionEvent e) {
        this.setVisible(false);
        JFrame frame = new JFrame("Cure Plan Form");
        frame.setContentPane(new cureForm().getPanel1());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void btnPListActionPerformed(ActionEvent e) {
        this.setVisible(false);
        JFrame frame = new JFrame("Add Treatment to Patient");
        frame.setContentPane(new patientListForm().getPanel1());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void btnAddCureActionPerformed(ActionEvent e) {
        this.setVisible(false);
        JFrame frame = new JFrame("Add New Cure");
        frame.setContentPane(new operationForm().getPanel1());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void initComponents() {

        panel1 = new JPanel();
        btnPList = new JButton();
        btnCure = new JButton();
        btnAddOperation = new JButton();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(176, 196, 222));
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
                    . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing
                    .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
                    Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
            ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
        public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName (
        ) ) )throw new RuntimeException( ) ;} } );

            btnAddOperation.setText("Add New Cure");
            btnAddOperation.addActionListener(e -> btnAddCureActionPerformed(e));

            btnPList.setText("Add Treatment to Patient");
            btnPList.addActionListener(e -> btnPListActionPerformed(e));


            btnCure.setText("Cure plan operations");
            btnCure.addActionListener(e -> btnCureActionPerformed(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addComponent(btnPList, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)

                                    .addComponent(btnCure, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)

                                    .addComponent(btnAddOperation, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnPList, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnCure, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnAddOperation, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(200, Short.MAX_VALUE))
            );
        }

    }

    private JPanel panel1;
    private JButton btnPList;
    private JButton btnCure;
    private JButton btnAddOperation;


    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
