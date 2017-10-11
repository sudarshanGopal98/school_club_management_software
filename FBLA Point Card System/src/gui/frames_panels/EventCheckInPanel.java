/*
 * Created by JFormDesigner on Fri May 20 14:08:17 CDT 2016
 */

package gui.frames_panels;

import datatypes.Student;
import datatypes.events.EventType;
import datatypes.events.MAOEvent;
import gui.misc.HeaderPanelForSubpanel;
import gui.misc.HintTextFieldUI;
import gui.misc.NewEventDialog;
import process.MainFile;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author sud -
 */

public class EventCheckInPanel extends JPanel {
    private MAOEvent currentEvent;
    private int eventType;


    public EventCheckInPanel(int eventType, final MAOEvent event) {
        initComponents();
        setSize(800, 515);
        this.currentEvent = event;
        this.eventType = eventType;

        if(currentEvent == null) {
            delete.setText("Delete Event");
            delete.setEnabled(false);
            export.setEnabled(false);
        }
        else
            delete.setText("Delete Event - "+currentEvent.getEventName());

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(EventCheckInPanel.this, "Are you sure?\n" +
                        "(This CANNOT be UNDONE!)");

                if(response == JOptionPane.YES_OPTION){
                    switch (EventCheckInPanel.this.eventType){
                        case EventType.MEETING:
                            MainFile.DM.meetings.remove(currentEvent);
                            listOfEvents.setListData(MainFile.DM.meetings.toArray());
                            break;
                        case EventType.SOCIAL:
                            MainFile.DM.socials.remove(currentEvent);
                            listOfEvents.setListData(MainFile.DM.socials.toArray());
                            break;
                        case EventType.TUTORIAL:
                            MainFile.DM.tutorials.remove(currentEvent);
                            listOfEvents.setListData(MainFile.DM.tutorials.toArray());
                            break;
                        case EventType.AMC:
                            MainFile.DM.amcs.remove(currentEvent);
                            listOfEvents.setListData(MainFile.DM.amcs.toArray());
                            break;
                        case EventType.MATH_CONTESTS:
                            MainFile.DM.mathContests.remove(currentEvent);
                            listOfEvents.setListData(MainFile.DM.mathContests.toArray());
                            break;
                        case EventType.OTHER_EVENTS:
                            MainFile.DM.otherEvents.remove(currentEvent);
                            listOfEvents.setListData(MainFile.DM.otherEvents.toArray());
                            break;
                    }

                    currentEvent = null;
                    delete.setEnabled(false);
                    export.setEnabled(false);

                    MainFile.DM.serializeData();
                    refresh(true);
                    JOptionPane.showMessageDialog(MainFile.MAIN_FRAME,"Event Successfully Deleted!");
                    delete.setText("Delete Event");
                }
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewEventDialog(EventCheckInPanel.this);

            }
        });

        HeaderPanelForSubpanel panel1 = new HeaderPanelForSubpanel(getWidth());
        panel1.setBounds(0, 0, getWidth(), 25);
        add(panel1);
        panel1.repaint();

        switch (eventType){
            case EventType.MEETING:
                eventName.setText("Meeting Check In");
                listOfEvents.setListData(MainFile.DM.meetings.toArray());
                break;
            case EventType.SOCIAL:
                eventName.setText("Social Check In");
                listOfEvents.setListData(MainFile.DM.socials.toArray());
                break;
            case EventType.TUTORIAL:
                eventName.setText("Spirit Nights Check In");
                listOfEvents.setListData(MainFile.DM.tutorials.toArray());
                break;
            case EventType.AMC:
                eventName.setText("Area FLC Check In");
                listOfEvents.setListData(MainFile.DM.amcs.toArray());
                break;
            case EventType.MATH_CONTESTS:
                eventName.setText("Fundraisers Add In");
                listOfEvents.setListData(MainFile.DM.mathContests.toArray());
                break;
            case EventType.OTHER_EVENTS:
                eventName.setText("Other Events Add In");
                listOfEvents.setListData(MainFile.DM.otherEvents.toArray());
                break;
        }

        checkinID.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if(currentEvent == null){
                    checkinID.setText("");
                    JOptionPane.showMessageDialog(null, "Please Select a Event before Checking In!");
                }

                if(checkinID.getText().length() == 8){

                    if(currentEvent.hasAttendee(checkinID.getText())) {
                        JOptionPane.showMessageDialog(EventCheckInPanel.this, "This student has already Checked In!");
                        checkinID.setText("");
                        return;
                    }

                    currentEvent.addAttendee(checkinID.getText());
                    System.out.println(checkinID.getText());
                    checkinID.setText("");
                    checkinID.repaint();

                    attendeeList.setListData(currentEvent.getAttendeesAsStudentObjects().toArray());
                    attendeeList.repaint();

                }
            }
        });

        saveCloseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFile.DM.serializeData();
                MainFile.MAIN_FRAME.initPanelToDisplay(new HomePanel());
            }
        });

        attendeeFilter.setUI(new HintTextFieldUI("Search by Last Name", true));
        attendeeFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentEvent == null)
                    return;

                if(attendeeFilter.getText().length() == 0){
                    attendeeList.setListData(currentEvent.getAttendeesAsStudentObjects().toArray());
                }


                ArrayList<Student> toSet = new ArrayList<>();

                for(String studentID: currentEvent.getAttendees()){
                    Student data = MainFile.DM.getMember(studentID);

                    if(data.getLastName().length() >= attendeeFilter.getText().length()){
                        String dataToCheck = data.getLastName().substring(0, attendeeFilter.getText().length());

                        if(dataToCheck.equalsIgnoreCase(attendeeFilter.getText()))
                            toSet.add(data);
                    }
                }
                attendeeList.setListData(toSet.toArray());
                repaint();
            }
        });

        listOfEvents.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(listOfEvents.getSelectedValue() != null){
                    currentEvent = (MAOEvent)(listOfEvents.getSelectedValue());
                    delete.setText("Delete Event - "+currentEvent.getEventName());
                    delete.setEnabled(true);
                    export.setEnabled(true);

                    attendeeList.setListData(currentEvent.getAttendeesAsStudentObjects().toArray());

                }
            }
        });


        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Student> exportList = currentEvent.getAttendeesAsStudentObjects();

                JOptionPane.showMessageDialog(null, "You will be asked to choose a folder for saving the list!");
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.showOpenDialog(EventCheckInPanel.this);
                String fileName= JOptionPane.showInputDialog(EventCheckInPanel.this, "What would you like to name this file?");

                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(chooser.getSelectedFile().getPath() + "/"+fileName+".csv")));

                    bw.write("First Name, Last Name, Student ID, Grade, Meetings, Tutorials, Socials, AMC Taken, Other Math Contests, Other Events, Points from Other Events, Total Points");
                    bw.newLine();
                    bw.flush();
                    for(Student student: exportList){
                        bw.write(student.getSaveString());
                        bw.newLine();
                        bw.flush();
                    }
                    bw.close();
                    JOptionPane.showMessageDialog(EventCheckInPanel.this, "The file is ready!");

                }catch (IOException e1){
                    JOptionPane.showMessageDialog(EventCheckInPanel.this, "Error in creating list!\n" +
                            "Please try again.");
                    e1.printStackTrace();
                }

            }
        });

        setVisible(true);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(new Color(224, 0, 64));
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponents(g);


    }

    public void refresh(boolean deleteRefresh){
        try {
            switch (EventCheckInPanel.this.eventType){

                    case EventType.MEETING:
                        listOfEvents.setListData(MainFile.DM.meetings.toArray());
                        currentEvent = MainFile.DM.meetings.get(MainFile.DM.meetings.size() - 1);
                        break;
                    case EventType.SOCIAL:
                        listOfEvents.setListData(MainFile.DM.socials.toArray());
                        currentEvent = MainFile.DM.socials.get(MainFile.DM.socials.size() - 1);
                        break;
                    case EventType.TUTORIAL:
                        listOfEvents.setListData(MainFile.DM.tutorials.toArray());
                        currentEvent = MainFile.DM.tutorials.get(MainFile.DM.tutorials.size() - 1);
                        break;
                    case EventType.AMC:
                        listOfEvents.setListData(MainFile.DM.amcs.toArray());
                        currentEvent = MainFile.DM.amcs.get(MainFile.DM.amcs.size() - 1);
                        break;
                    case EventType.MATH_CONTESTS:
                        listOfEvents.setListData(MainFile.DM.mathContests.toArray());
                        currentEvent = MainFile.DM.mathContests.get(MainFile.DM.mathContests.size() - 1);
                        break;
                    case EventType.OTHER_EVENTS:
                        listOfEvents.setListData(MainFile.DM.otherEvents.toArray());
                        currentEvent = MainFile.DM.otherEvents.get(MainFile.DM.otherEvents.size() - 1);
                        break;

            }
        }catch (ArrayIndexOutOfBoundsException e){}

        delete.setEnabled(true);
        export.setEnabled(true);

        if(!deleteRefresh)
            JOptionPane.showMessageDialog(EventCheckInPanel.this, "The Event has been added!");
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - - -
        eventName = new JLabel();
        splitPane2 = new JSplitPane();
        splitPane4 = new JSplitPane();
        attendeeFilter = new JTextField();
        scrollPane1 = new JScrollPane();
        attendeeList = new JList();
        splitPane3 = new JSplitPane();
        panel1 = new JPanel();
        label1 = new JLabel();
        checkinID = new JTextField();
        saveCloseButton = new JButton();
        panel2 = new JPanel();
        scrollPane2 = new JScrollPane();
        listOfEvents = new JList();
        label2 = new JLabel();
        label3 = new JLabel();
        delete = new JButton();
        export = new JButton();
        add = new JButton();

        //======== this ========
        setBackground(new Color(224, 0, 64));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- eventName ----
        eventName.setText("NameHolder for Event Type");
        eventName.setHorizontalAlignment(SwingConstants.CENTER);
        eventName.setFont(new Font("Cambria Math", Font.PLAIN, 26));
        eventName.setForeground(Color.white);
        add(eventName);
        eventName.setBounds(0, 35, 800, 40);

        //======== splitPane2 ========
        {
            splitPane2.setDividerLocation(200);
            splitPane2.setDividerSize(1);

            //======== splitPane4 ========
            {
                splitPane4.setOrientation(JSplitPane.VERTICAL_SPLIT);
                splitPane4.setDividerSize(1);

                //---- attendeeFilter ----
                attendeeFilter.setFont(new Font("Cambria", Font.PLAIN, 12));
                splitPane4.setTopComponent(attendeeFilter);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(attendeeList);
                }
                splitPane4.setBottomComponent(scrollPane1);
            }
            splitPane2.setLeftComponent(splitPane4);

            //======== splitPane3 ========
            {
                splitPane3.setDividerLocation(400);
                splitPane3.setDividerSize(1);

                //======== panel1 ========
                {
                    panel1.setBackground(new Color(65, 16, 63));
                    panel1.setLayout(null);

                    //---- label1 ----
                    label1.setText("Enter or Scan ID");
                    label1.setFont(new Font("Cambria", Font.PLAIN, 19));
                    label1.setHorizontalAlignment(SwingConstants.CENTER);
                    label1.setForeground(Color.white);
                    panel1.add(label1);
                    label1.setBounds(40, 25, 315, label1.getPreferredSize().height);
                    panel1.add(checkinID);
                    checkinID.setBounds(40, 55, 315, 30);

                    //---- saveCloseButton ----
                    saveCloseButton.setText("Save and Close");
                    saveCloseButton.setFont(new Font("Cambria Math", Font.PLAIN, 15));
                    saveCloseButton.setBackground(new Color(192, 0, 52));
                    saveCloseButton.setForeground(Color.white);
                    panel1.add(saveCloseButton);
                    saveCloseButton.setBounds(40, 115, 315, 30);

                    { // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel1.getComponentCount(); i++) {
                            Rectangle bounds = panel1.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel1.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel1.setMinimumSize(preferredSize);
                        panel1.setPreferredSize(preferredSize);
                    }
                }
                splitPane3.setLeftComponent(panel1);

                //======== panel2 ========
                {
                    panel2.setBackground(new Color(224, 0, 64));
                    panel2.setLayout(null);

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setViewportView(listOfEvents);
                    }
                    panel2.add(scrollPane2);
                    scrollPane2.setBounds(0, 50, 196, 260);

                    //---- label2 ----
                    label2.setText("List of Past Events");
                    label2.setFont(new Font("Cambria", Font.PLAIN, 16));
                    label2.setHorizontalAlignment(SwingConstants.CENTER);
                    label2.setForeground(Color.white);
                    panel2.add(label2);
                    label2.setBounds(0, 5, 195, label2.getPreferredSize().height);

                    //---- label3 ----
                    label3.setText("Click on a listing to load that event");
                    label3.setHorizontalAlignment(SwingConstants.CENTER);
                    label3.setFont(new Font("Cambria Math", Font.PLAIN, 12));
                    label3.setForeground(Color.white);
                    panel2.add(label3);
                    label3.setBounds(0, 30, 195, label3.getPreferredSize().height);

                    { // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel2.getComponentCount(); i++) {
                            Rectangle bounds = panel2.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel2.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel2.setMinimumSize(preferredSize);
                        panel2.setPreferredSize(preferredSize);
                    }
                }
                splitPane3.setRightComponent(panel2);
            }
            splitPane2.setRightComponent(splitPane3);
        }
        add(splitPane2);
        splitPane2.setBounds(0, 80, 800, 315);

        //---- delete ----
        delete.setText("Delete <CurrentEventName>");
        delete.setFont(new Font("Cambria", Font.PLAIN, 18));
        delete.setBackground(new Color(118, 16, 63));
        delete.setForeground(Color.white);
        add(delete);
        delete.setBounds(0, 435, 800, 40);

        //---- export ----
        export.setText("Export Check In List");
        export.setFont(new Font("Cambria", Font.PLAIN, 18));
        export.setBackground(new Color(118, 16, 63));
        export.setForeground(Color.white);
        add(export);
        export.setBounds(0, 475, 800, 40);

        //---- add ----
        add.setText("Add New Event");
        add.setFont(new Font("Cambria", Font.PLAIN, 18));
        add.setBackground(new Color(118, 16, 63));
        add.setForeground(Color.white);
        add(add);
        add.setBounds(0, 395, 800, 40);

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
    // Generated using JFormDesigner Evaluation license - - -
    private JLabel eventName;
    private JSplitPane splitPane2;
    private JSplitPane splitPane4;
    private JTextField attendeeFilter;
    private JScrollPane scrollPane1;
    private JList attendeeList;
    private JSplitPane splitPane3;
    private JPanel panel1;
    private JLabel label1;
    private JTextField checkinID;
    private JButton saveCloseButton;
    private JPanel panel2;
    private JScrollPane scrollPane2;
    private JList listOfEvents;
    private JLabel label2;
    private JLabel label3;
    private JButton delete;
    private JButton export;
    private JButton add;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
