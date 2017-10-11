/*
 * Created by JFormDesigner on Thu Jun 02 18:35:16 CDT 2016
 */

package gui.frames_panels;

import datatypes.Colors;
import datatypes.events.EventPoints;
import gui.misc.HeaderPanelForSubpanel;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;
import process.MainFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author - -
 */
public class SettingsPanel extends JPanel {
    public SettingsPanel() {
        initComponents();
        setSize(400, 455);

        HeaderPanelForSubpanel header = new HeaderPanelForSubpanel(getWidth());
        header.modifyTopBarText("Mu Alpha Theta Point Card System");
        add(header, 0);

        panel1.setBackground(Colors.crimson1);
        panel2.setBackground(Colors.crimson1);
        panel3.setBackground(Colors.crimson1);
        panel4.setBackground(Colors.crimson1);
        panel5.setBackground(Colors.crimson1);
        panel6.setBackground(Colors.crimson1);
        panel7.setBackground(Colors.crimson1);

        setBackground(Colors.crimson1);
        fillFields();

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
                MainFile.MAIN_FRAME.initPanelToDisplay(new HomePanel());

            }
        });
        save.setBackground(Colors.purple1);


        discard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFile.MAIN_FRAME.initPanelToDisplay(new HomePanel());

            }
        });
        discard.setBackground(Colors.purple1);


    }

    private void fillFields(){
        meeting.setText(EventPoints.meeting+"");
        tutorial.setText(EventPoints.tutorial+"");
        social.setText(EventPoints.social+"");
        amc.setText(EventPoints.amc+"");
        mathContest.setText(EventPoints.mathContest+"");
        maoInductions.setText(EventPoints.maoInduct+"");
    }

    private void saveData(){
        EventPoints.meeting = Integer.parseInt(meeting.getText());
        EventPoints.tutorial = Integer.parseInt(tutorial.getText());
        EventPoints.social = Integer.parseInt(social.getText());
        EventPoints.amc = Integer.parseInt(amc.getText());
        EventPoints.mathContest = Integer.parseInt(mathContest.getText());
        EventPoints.maoInduct = Integer.parseInt(maoInductions.getText());

        MainFile.DM.serializeData();

    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        label1 = new JLabel();
        vSpacer2 = new JPanel(null);
        label6 = new JLabel();
        panel1 = new JPanel();
        hSpacer1 = new JPanel(null);
        label2 = new JLabel();
        meeting = new JTextField();
        label7 = new JLabel();
        panel2 = new JPanel();
        hSpacer2 = new JPanel(null);
        label3 = new JLabel();
        tutorial = new JTextField();
        label8 = new JLabel();
        panel3 = new JPanel();
        hSpacer3 = new JPanel(null);
        label4 = new JLabel();
        social = new JTextField();
        label9 = new JLabel();
        panel4 = new JPanel();
        hSpacer4 = new JPanel(null);
        label5 = new JLabel();
        amc = new JTextField();
        label10 = new JLabel();
        vSpacer3 = new JPanel(null);
        label12 = new JLabel();
        panel5 = new JPanel();
        hSpacer5 = new JPanel(null);
        label13 = new JLabel();

        panel7 = new JPanel();
        hSpacer7 = new JPanel(null);
        label17 = new JLabel();
        label18 = new JLabel();
        mathContest = new JTextField();

        maoInductions = new JTextField();
        label14 = new JLabel();
        panel6 = new JPanel();
        hSpacer6 = new JPanel(null);
        label15 = new JLabel();
        thetaInduction = new JTextField();
        label16 = new JLabel();
        save = new JButton();
        discard = new JButton();

        //======== this ========

        setLayout(new VerticalLayout(10));

        //---- label1 ----
        label1.setText("Settings");
        label1.setFont(new Font("Cambria", Font.PLAIN, 30));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setForeground(Color.white);
        add(label1);

        //---- vSpacer2 ----
        vSpacer2.setBorder(null);
        add(vSpacer2);

        //---- label6 ----
        label6.setText("Manage Points");
        label6.setFont(new Font("Cambria", Font.BOLD, 18));
        label6.setForeground(Color.white);
        add(label6);

        //======== panel1 ========
        {
            panel1.setLayout(new HorizontalLayout(10));

            //---- hSpacer1 ----
            hSpacer1.setBorder(null);
            panel1.add(hSpacer1);

            //---- label2 ----
            label2.setText("Meetings");
            label2.setFont(new Font("Cambria", Font.PLAIN, 18));
            label2.setForeground(Color.white);
            panel1.add(label2);

            //---- meeting ----
            meeting.setColumns(18);
            panel1.add(meeting);

            //---- label7 ----
            label7.setText("Points");
            label7.setFont(new Font("Cambria", Font.PLAIN, 16));
            label7.setForeground(Color.white);
            panel1.add(label7);
        }
        add(panel1);

        //======== panel2 ========
        {
            panel2.setLayout(new HorizontalLayout(10));

            //---- hSpacer2 ----
            hSpacer2.setBorder(null);
            panel2.add(hSpacer2);

            //---- label3 ----
            label3.setText("Tutorials");
            label3.setFont(new Font("Cambria", Font.PLAIN, 18));
            label3.setForeground(Color.white);
            panel2.add(label3);

            //---- tutorial ----
            tutorial.setColumns(18);
            panel2.add(tutorial);

            //---- label8 ----
            label8.setText("Points");
            label8.setFont(new Font("Cambria", Font.PLAIN, 16));
            label8.setForeground(Color.white);
            panel2.add(label8);
        }
        add(panel2);

        //======== panel3 ========
        {
            panel3.setLayout(new HorizontalLayout(10));

            //---- hSpacer3 ----
            hSpacer3.setBorder(null);
            panel3.add(hSpacer3);

            //---- label4 ----
            label4.setText("Socials    ");
            label4.setFont(new Font("Cambria", Font.PLAIN, 18));
            label4.setForeground(Color.white);
            panel3.add(label4);

            //---- social ----
            social.setColumns(18);
            panel3.add(social);

            //---- label9 ----
            label9.setText("Points");
            label9.setFont(new Font("Cambria", Font.PLAIN, 16));
            label9.setForeground(Color.white);
            panel3.add(label9);
        }
        add(panel3);

        //======== panel4 ========
        {
            panel4.setLayout(new HorizontalLayout(10));

            //---- hSpacer4 ----
            hSpacer4.setBorder(null);
            panel4.add(hSpacer4);

            //---- label5 ----
            label5.setText("AMC        ");
            label5.setFont(new Font("Cambria", Font.PLAIN, 18));
            label5.setForeground(Color.white);
            panel4.add(label5);

            //---- amc ----
            amc.setColumns(18);
            panel4.add(amc);

            //---- label10 ----
            label10.setText("Points");
            label10.setFont(new Font("Cambria", Font.PLAIN, 16));
            label10.setForeground(Color.white);
            panel4.add(label10);
        }
        add(panel4);

        //======== panel7 ========
        {
            panel7.setLayout(new HorizontalLayout(10));

            //---- hSpacer4 ----
            hSpacer7.setBorder(null);
            panel7.add(hSpacer7);

            //---- label5 ----
            label17.setText("Math Contests");
            label17.setFont(new Font("Cambria", Font.PLAIN, 18));
            label17.setForeground(Color.white);
            panel7.add(label17);

            //---- mathContest ----
            mathContest.setColumns(14);
            panel7.add(mathContest);

            //---- label10 ----
            label18.setText("Points");
            label18.setFont(new Font("Cambria", Font.PLAIN, 16));
            label18.setForeground(Color.white);
            panel7.add(label18);
        }
        add(panel7);

        //---- vSpacer3 ----
        vSpacer3.setBorder(null);
        add(vSpacer3);

        //---- label12 ----
        label12.setText("Manage Requirements");
        label12.setFont(new Font("Cambria", Font.BOLD, 18));
        label12.setForeground(Color.white);
        add(label12);

        //======== panel5 ========
        {
            panel5.setLayout(new HorizontalLayout(10));
            panel5.add(hSpacer5);

            //---- label13 ----
            label13.setText("MAO Requirement  ");
            label13.setFont(new Font("Cambria", Font.PLAIN, 18));
            label13.setForeground(Color.white);
            panel5.add(label13);

            //---- maoInductions ----
            maoInductions.setColumns(10);
            panel5.add(maoInductions);

            //---- label14 ----
            label14.setText("Points");
            label14.setFont(new Font("Cambria", Font.PLAIN, 16));
            label14.setForeground(Color.white);
            panel5.add(label14);
        }
        add(panel5);

        //======== panel6 ========
        {
            panel6.setLayout(new HorizontalLayout(10));
            panel6.add(hSpacer6);

            //---- label15 ----
            label15.setText("Theta Requirement");
            label15.setFont(new Font("Cambria", Font.PLAIN, 18));
            label15.setForeground(Color.white);
//            panel6.add(label15);

            //---- thetaInduction ----
            thetaInduction.setColumns(10);
//            panel6.add(thetaInduction);

            //---- label16 ----
            label16.setText("Points");
            label16.setFont(new Font("Cambria", Font.PLAIN, 16));
            label16.setForeground(Color.white);
            panel6.add(label16);
        }
        add(panel6);


        //---- save ----
        save.setText("Save");
        save.setForeground(Color.white);
        add(save);

        //---- discard ----
        discard.setText("Discard Changes");
        discard.setForeground(Color.white);
        add(discard);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - - -
    private JLabel label1;
    private JPanel vSpacer2;
    private JLabel label6;
    private JPanel panel1;
    private JPanel hSpacer1;
    private JLabel label2;
    private JTextField meeting;
    private JLabel label7;
    private JPanel panel2;
    private JPanel hSpacer2;
    private JLabel label3;
    private JTextField tutorial;
    private JLabel label8;
    private JPanel panel3;
    private JPanel hSpacer3;
    private JLabel label4;
    private JTextField social;
    private JLabel label9;
    private JPanel panel4;
    private JPanel hSpacer4;
    private JLabel label5;
    private JTextField amc;
    private JLabel label10;
    private JPanel vSpacer3;
    private JLabel label12;
    private JPanel panel5;
    private JPanel hSpacer5;
    private JLabel label13;
    private JTextField maoInductions;
    private JLabel label14;
    private JPanel panel6;
    private JPanel hSpacer6;
    private JLabel label15;
    private JTextField thetaInduction;
    private JLabel label16;
    private JPanel panel7;
    private JTextField mathContest;
    private JPanel hSpacer7;
    private JLabel label17;
    private JLabel label18;
    private JButton save;
    private JButton discard;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
