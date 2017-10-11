/*
 * Created by JFormDesigner on Mon Apr 04 16:41:12 CDT 2016
 */

package gui.misc;

import datatypes.events.*;
import gui.frames_panels.EventCheckInPanel;
import org.jdesktop.swingx.VerticalLayout;
import process.MainFile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sudarshan Gopalakrishnan
 */
public class NewEventDialog extends JDialog {

    public NewEventDialog(final EventCheckInPanel panelToRefresh) {
        super(MainFile.MAIN_FRAME);
        initComponents();

        DateFormat df = new SimpleDateFormat("MM/DD/YYYY");
        Date date = new Date();
        textField3.setText(""+df.format(date));

        textField1.addItem("Meeting");
        textField1.addItem("Social");
        textField1.addItem("Tutorial");
        textField1.addItem("AMC");
        textField1.addItem("Other Math Contest");
        textField1.addItem("Ohter Events");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String[] dateData = textField3.getText().split("/");
                    int year = Integer.parseInt(dateData[2]);
                    int month = Integer.parseInt(dateData[0]);
                    int day = Integer.parseInt(dateData[1]);

                    switch (textField1.getSelectedIndex()){
                        case EventType.MEETING:
                            MAOEvent event = new Meeting(new Date(year - 1900, month-1, day), textField2.getText());
                            MainFile.DM.meetings.add((Meeting)event);
                            break;

                        case EventType.SOCIAL:
                            event = new Social(new Date(year - 1900, month-1, day), textField2.getText());
                            MainFile.DM.socials.add((Social)event);
                            break;

                        case EventType.TUTORIAL:
                            event = new Tutorial(new Date(year - 1900, month-1, day), textField2.getText());
                            MainFile.DM.tutorials.add((Tutorial)event);
                            break;

                        case EventType.AMC:
                            event = new AMC(new Date(year - 1900, month-1, day), textField2.getText());
                            MainFile.DM.amcs.add((AMC)event);
                            break;

                        case EventType.MATH_CONTESTS:
                            event = new MathContest(new Date(year - 1900, month-1, day), textField2.getText());
                            MainFile.DM.mathContests.add((MathContest) event);
                            break;

                        case EventType.OTHER_EVENTS:
                            String points = "0";
                            while(!(Integer.parseInt(points) > 0)){
                                try{
                                    points = (JOptionPane.showInputDialog(NewEventDialog.this,
                                            "How many points is this event worth?:"));

                                    try{
                                        Integer.parseInt(points);
                                    }catch (Exception e2){
                                        JOptionPane.showMessageDialog(NewEventDialog.this, "The operation was canceled.");
                                        return;
                                    }

                                }catch (Exception e1){}
                            }

                            event = new OtherEvent(new Date(year - 1900, month-1, day), textField2.getText(), Integer.parseInt(points));
                            MainFile.DM.otherEvents.add((OtherEvent) event);
                            break;

                    }
                    panelToRefresh.refresh(false);
                    NewEventDialog.this.dispose();
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(NewEventDialog.this, "Failed to Create New Event!");
                    e1.printStackTrace();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewEventDialog.this.dispose();
            }
        });

        setVisible(true);

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        textField1 = new JComboBox<String>();
        vSpacer1 = new JPanel(null);
        label2 = new JLabel();
        textField2 = new JTextField();
        vSpacer2 = new JPanel(null);
        label3 = new JLabel();
        textField3 = new JTextField();
        vSpacer3 = new JPanel(null);
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Add New Event");
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
                label1.setText("Event Type");
                label1.setFont(new Font("Cambria", Font.PLAIN, 16));
                contentPanel.add(label1);
                contentPanel.add(textField1);
                contentPanel.add(vSpacer1);

                //---- label2 ----
                label2.setText("Event Name");
                label2.setFont(new Font("Cambria", Font.PLAIN, 16));
                contentPanel.add(label2);
                contentPanel.add(textField2);
                contentPanel.add(vSpacer2);

                //---- label3 ----
                label3.setText("Date (MM/DD/YYYY)");
                label3.setFont(new Font("Cambria", Font.PLAIN, 16));
                contentPanel.add(label3);
                contentPanel.add(textField3);
                contentPanel.add(vSpacer3);


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
    // Generated using JFormDesigner Evaluation license - - -
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JComboBox textField1;
    private JPanel vSpacer1;
    private JLabel label2;
    private JTextField textField2;
    private JPanel vSpacer2;
    private JLabel label3;
    private JTextField textField3;
    private JPanel vSpacer3;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
