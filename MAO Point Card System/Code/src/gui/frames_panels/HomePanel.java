/*
 * Created by JFormDesigner on Thu May 19 18:39:26 CDT 2016
 */

package gui.frames_panels;

import datatypes.events.*;
import gui.misc.HeaderPanel;
import process.MainFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author - -
 */
public class HomePanel extends JPanel {
    public HomePanel() {
        initComponents();
        setSize(800, 400);



        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*String meetingName = JOptionPane.showInputDialog("Enter the Event Name:");
                if(meetingName == null  ||  meetingName.equalsIgnoreCase("null"))
                    return;*/

                /*String meetingName = calculateMeetingName();

                Meeting currentMeeting = MainFile.DM.getMeeting(meetingName);
                if(currentMeeting == null){
                    currentMeeting = new Meeting(Calendar.getInstance().getTime() ,meetingName);
                    MainFile.DM.meetings.add(currentMeeting);

                }*/

                MainFile.MAIN_FRAME.initPanelToDisplay(new EventCheckInPanel(EventType.MEETING, null));
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*String meetingName = JOptionPane.showInputDialog("Enter the Event Name:");
                if(meetingName == null  ||  meetingName.equalsIgnoreCase("null"))
                    return;*/

                /*String meetingName = calculateSocialName();

                Social currentSocial = MainFile.DM.getSocial(meetingName);
                if(currentSocial == null){
                    currentSocial = new Social(Calendar.getInstance().getTime() ,meetingName);
                    MainFile.DM.socials.add(currentSocial);

                }*/

                MainFile.MAIN_FRAME.initPanelToDisplay(new EventCheckInPanel(EventType.SOCIAL, null));
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*String meetingName = JOptionPane.showInputDialog("Enter the Event Name:");
                if(meetingName == null  ||  meetingName.equalsIgnoreCase("null"))
                    return;*/

                /*String meetingName = calculateTutorialName();

                Tutorial currentTutorial = MainFile.DM.getTutorial(meetingName);
                if(currentTutorial == null){
                    currentTutorial = new Tutorial(Calendar.getInstance().getTime() ,meetingName);
                    MainFile.DM.tutorials.add(currentTutorial);

                }*/

                MainFile.MAIN_FRAME.initPanelToDisplay(new EventCheckInPanel(EventType.TUTORIAL, null));
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Assuming one AMC
                /*String meetingName = JOptionPane.showInputDialog("Enter the Event Name:");
                if(meetingName == null  ||  meetingName.equalsIgnoreCase("null"))
                    return;*/

                String meetingName = "AMC Testing";

                AMC currentAMC = MainFile.DM.getAMC(meetingName);
                if(currentAMC == null){
                    JTextField month = new JTextField();
                    JTextField day = new JPasswordField();
                    JTextField year = new JPasswordField();
                    Object[] message = {
                            "Month:", month,
                            "Day:", day,
                            "Year:", year
                    };

                    int option = JOptionPane.showConfirmDialog(null, message, "Please enter the date for AMC", JOptionPane.OK_CANCEL_OPTION);

                    if(option == JOptionPane.CANCEL_OPTION)
                        return;

                    Date amcDate = new Date(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()));

                    currentAMC = new AMC(amcDate,meetingName);
                    MainFile.DM.amcs.add(currentAMC);

                }

                MainFile.MAIN_FRAME.initPanelToDisplay(new EventCheckInPanel(EventType.AMC, currentAMC));
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFile.MAIN_FRAME.initPanelToDisplay(new EventCheckInPanel(EventType.MATH_CONTESTS, null));
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFile.MAIN_FRAME.initPanelToDisplay(new EventCheckInPanel(EventType.OTHER_EVENTS, null));
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFile.MAIN_FRAME.initPanelToDisplay(new ManageMemberPanel());
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFile.MAIN_FRAME.initPanelToDisplay(new SettingsPanel());
            }
        });



        HeaderPanel panel1 = new HeaderPanel(getWidth());
        panel1.setBounds(0, 0, getWidth(), 25);
        add(panel1);

    }

    private String calculateMeetingName(){
        if(MainFile.DM.meetings.size() == 0)
            return "Meeting 1";

        Meeting prevEvent = MainFile.DM.meetings.get(MainFile.DM.meetings.size()-1);
        String today = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        String eventDate = new SimpleDateFormat("MM/dd/yyyy").format(prevEvent.getEventDate());

        if(today.equalsIgnoreCase(eventDate)){
            return prevEvent.getEventName();
        } return "Meeting "+(MainFile.DM.meetings.size()+1);
    }

    private String calculateTutorialName(){
        if(MainFile.DM.tutorials.size() == 0)
            return "Tutorial 1";

        Tutorial prevEvent = MainFile.DM.tutorials.get(MainFile.DM.tutorials.size()-1);
        String today = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        String eventDate = new SimpleDateFormat("MM/dd/yyyy").format(prevEvent.getEventDate());

        if(today.equalsIgnoreCase(eventDate)){
            return prevEvent.getEventName();
        } return "Tutorial "+(MainFile.DM.tutorials.size()+1);
    }

    private String calculateSocialName(){
        if(MainFile.DM.socials.size() == 0)
            return "Social 1";

        Social prevEvent = MainFile.DM.socials.get(MainFile.DM.socials.size()-1);
        String today = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        String eventDate = new SimpleDateFormat("MM/dd/yyyy").format(prevEvent.getEventDate());

        if(today.equalsIgnoreCase(eventDate)){
            return prevEvent.getEventName();
        } return "Social "+(MainFile.DM.socials.size()+1);
    }






    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Sudarshan Gopalakrishnan
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        label1 = new JLabel();
        button8 = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        button9 = new JButton();

        //======== this ========
        setFont(new Font("Cambria Math", Font.PLAIN, 15));
        setBackground(new Color(250, 0, 84));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- button1 ----
        button1.setText("Meeting");
        button1.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        button1.setBackground(new Color(96, 26, 93));
        button1.setForeground(Color.white);
        add(button1);
        button1.setBounds(20, 65, 180, 50);

        //---- button2 ----
        button2.setText("Social");
        button2.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        button2.setBackground(new Color(96, 26, 93));
        button2.setForeground(Color.white);
        add(button2);
        button2.setBounds(210, 65, 180, 50);

        //---- button3 ----
        button3.setText("Tutorial");
        button3.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        button3.setBackground(new Color(96, 26, 93));
        button3.setForeground(Color.white);
        add(button3);
        button3.setBounds(395, 65, 180, 50);

        //---- button4 ----
        button4.setText("AMC");
        button4.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        button4.setBackground(new Color(96, 26, 93));
        button4.setForeground(Color.white);
        add(button4);
        button4.setBounds(580, 65, 180, 50);

        //---- button5 ----
        button5.setText("Math Contests");
        button5.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        button5.setBackground(new Color(96, 26, 93));
        button5.setForeground(Color.white);
        add(button5);
        button5.setBounds(20, 125, 370, 50);

        //---- button6 ----
        button6.setText("Others");
        button6.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        button6.setBackground(new Color(96, 26, 93));
        button6.setForeground(Color.white);
        add(button6);
        button6.setBounds(395, 125, 365, 50);

        //---- label1 ----
        label1.setText("Check In");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Cambria", Font.PLAIN, 23));
        label1.setForeground(Color.white);
        add(label1);
        label1.setBounds(5, 30, 770, 35);

        //---- button8 ----
        button8.setText("Manage Members");
        button8.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        button8.setBackground(new Color(96, 26, 93));
        button8.setForeground(Color.white);
        add(button8);
        button8.setBounds(115, 225, 280, 50);

        //---- label2 ----
        label2.setText("Data Management");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Cambria", Font.PLAIN, 23));
        label2.setForeground(Color.white);
        add(label2);
        label2.setBounds(5, 190, 770, 35);

        //---- label3 ----
        label3.setText("Software create by Sudarshan Gopalakrishnan, Tompkins Computer Science");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Cambria Math", Font.PLAIN, 17));
        label3.setForeground(Color.white);
        add(label3);
        label3.setBounds(0, 360, 785, label3.getPreferredSize().height);

        //---- button9 ----
        button9.setText("Settings");
        button9.setFont(new Font("Cambria Math", Font.PLAIN, 15));
        button9.setBackground(new Color(96, 26, 93));
        button9.setForeground(Color.white);
        add(button9);
        button9.setBounds(405, 225, 280, 50);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Sudarshan Gopalakrishnan
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JLabel label1;
    private JButton button8;
    private JLabel label2;
    private JLabel label3;
    private JButton button9;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
