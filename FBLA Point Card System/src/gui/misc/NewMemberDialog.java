/*
 * Created by JFormDesigner on Mon Apr 04 16:41:12 CDT 2016
 */

package gui.misc;

import datatypes.Student;
import org.jdesktop.swingx.VerticalLayout;
import process.MainFile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Sudarshan Gopalakrishnan
 */
public class NewMemberDialog extends JDialog {

    public NewMemberDialog() {
        super(MainFile.MAIN_FRAME);
        initComponents();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Integer.parseInt(textField4.getText());
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null, "There was an error in the data entered.\n" +
                        "Please Try Again");
                }

                if(textField3.getText().length() == 8   &&  MainFile.DM.getMember(textField3.getText()) == null) {
                    Student data = new Student(textField1.getText(), textField2.getText(),
                            textField3.getText(), Integer.parseInt(textField4.getText()), checkBox1.isSelected());
                    MainFile.DM.allMembers.add(data);
                    NewMemberDialog.this.dispose();

                }else if(textField3.getText().length() != 8){
                    JOptionPane.showMessageDialog(null, "There was an error in the data entered.\n" +
                            "Please Try Again");

                }else JOptionPane.showMessageDialog(null,"The Student ID Already Exists!");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewMemberDialog.this.dispose();
            }
        });

        setVisible(true);

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Sudarshan Gopalakrishnan
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        vSpacer1 = new JPanel(null);
        label2 = new JLabel();
        textField2 = new JTextField();
        vSpacer2 = new JPanel(null);
        label3 = new JLabel();
        textField3 = new JTextField();
        vSpacer3 = new JPanel(null);
        label4 = new JLabel();
        textField4 = new JTextField();
        vSpacer4 = new JPanel(null);
        checkBox1 = new JCheckBox();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Add New Student");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new VerticalLayout());

                //---- label1 ----
                label1.setText("First Name");
                label1.setFont(new Font("Calibri", Font.PLAIN, 16));
                contentPanel.add(label1);
                contentPanel.add(textField1);
                contentPanel.add(vSpacer1);

                //---- label2 ----
                label2.setText("Last Name");
                label2.setFont(new Font("Calibri", Font.PLAIN, 16));
                contentPanel.add(label2);
                contentPanel.add(textField2);
                contentPanel.add(vSpacer2);

                //---- label3 ----
                label3.setText("Student ID");
                label3.setFont(new Font("Calibri", Font.PLAIN, 16));
                contentPanel.add(label3);
                contentPanel.add(textField3);
                contentPanel.add(vSpacer3);

                //---- label4 ----
                label4.setText("Grade");
                label4.setFont(new Font("Calibri", Font.PLAIN, 16));
                contentPanel.add(label4);
                contentPanel.add(textField4);
                contentPanel.add(vSpacer4);

                //---- checkBox1 ----
                checkBox1.setText("Is Theta Club Member?");
                checkBox1.setFont(checkBox1.getFont().deriveFont(checkBox1.getFont().getSize() + 5f));
                contentPanel.add(checkBox1);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Sudarshan Gopalakrishnan
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JTextField textField1;
    private JPanel vSpacer1;
    private JLabel label2;
    private JTextField textField2;
    private JPanel vSpacer2;
    private JLabel label3;
    private JTextField textField3;
    private JPanel vSpacer3;
    private JLabel label4;
    private JTextField textField4;
    private JPanel vSpacer4;
    private JCheckBox checkBox1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
