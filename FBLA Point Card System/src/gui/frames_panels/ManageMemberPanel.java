/*
 * Created by JFormDesigner on Sat May 21 11:34:25 CDT 2016
 */

package gui.frames_panels;

import datatypes.Colors;
import datatypes.Student;
import gui.misc.HeaderPanelForSubpanel;
import gui.misc.HintTextFieldUI;
import gui.misc.NewMemberDialog;
import org.jdesktop.swingx.VerticalLayout;
import process.MainFile;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author - -
 */
public class ManageMemberPanel extends JPanel{
    public ManageMemberPanel() {
        setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50));
        initComponents();
        splitPane1.setDividerLocation((int)(1.20*getWidth()/10));
        rightSplitPanel.setDividerLocation((int)(7.3*getWidth()/10));


        saveCloseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFile.DM.serializeData();
                MainFile.MAIN_FRAME.initPanelToDisplay(new HomePanel());
            }
        });

        memberList.setListData(MainFile.DM.allMembers.toArray());

        addMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewMemberDialog();
                memberList.setListData(MainFile.DM.allMembers.toArray());
            }
        });

        removeMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = JOptionPane.showInputDialog(ManageMemberPanel.this, "Enter The Student ID of the Member");
                Student dataRemoved = MainFile.DM.removeMember(studentID);
                if(dataRemoved == null)
                    JOptionPane.showMessageDialog(ManageMemberPanel.this, "Sorry, the member was not found!");
                else {
                    JOptionPane.showMessageDialog(ManageMemberPanel.this, dataRemoved.getFirstName()+" "+dataRemoved.getLastName()+
                            " was removed from the system!");
                    memberList.setListData(MainFile.DM.allMembers.toArray());
                    table1.setModel(new DataTableModel());


                }
            }
        });

        loadMembers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You will be asked to choose a file for loading data!\n" +
                        "Please ensure that the file you are choosing has the following columns in order:\n" +
                        "1. Last Name\n" +
                        "2. First Name\n" +
                        "3. Student ID\n" +
                        "4. Grade (as a number)\n" +
                        "5. Is member part of Theta Club? (must only have either True or False)");
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.showOpenDialog(ManageMemberPanel.this);

                try{
                    File fileChosen = chooser.getSelectedFile();

                    if(fileChosen == null)
                        return;

                    Scanner scan = new Scanner(fileChosen);
                    scan.nextLine();
                    while(scan.hasNextLine()){
                        try {
                            String[] data = scan.nextLine().split(",");

                            System.out.println(data.length+"  "+ Arrays.toString(data));

                            if(data.length == 4){
                                Student newMember = new Student(data[0], data[1], data[2], Integer.parseInt(data[3]), false);
                                if(MainFile.DM.getMember(newMember.getStudentID()) == null)
                                    MainFile.DM.allMembers.add(newMember);
                                repaint();
                            }else{
                                Student newMember = new Student(data[0], data[1], data[2], Integer.parseInt(data[3]), Boolean.parseBoolean(data[4]));
                                if(MainFile.DM.getMember(newMember.getStudentID()) == null)
                                    MainFile.DM.allMembers.add(newMember);
                                repaint();
                            }


                        }catch (Exception e2){
                            e2.printStackTrace();

                            if(MainFile.DM.allMembers.size() > 0)
                                JOptionPane.showMessageDialog(null, "Sorry, there was an error during transfer!\n" +
                                    "The last student transferred was :"+ MainFile.DM.allMembers.get(MainFile.DM.allMembers.size()-1));
                            else
                                JOptionPane.showMessageDialog(null, "Sorry, there was an error during transfer!\n" +
                                        "No data was transfered!");

                            return;
                        }
                    }

                    JOptionPane.showMessageDialog(ManageMemberPanel.this, "The data has been successfully loaded.",
                            "Data Successfully Loaded", JOptionPane.INFORMATION_MESSAGE);

                    MainFile.DM.serializeData();
                    MainFile.MAIN_FRAME.initPanelToDisplay(new HomePanel());


                }catch(Exception e1){e1.printStackTrace();
                    return;}


            }
        });

        memberFilter.setUI(new HintTextFieldUI("Enter the Member Last Name"));
        memberFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(memberFilter.getText().length() == 0){
                    memberList.setListData( MainFile.DM.allMembers.toArray());
                }

                ArrayList<Student> toSet = new ArrayList<>();

                for(Student data: MainFile.DM.allMembers){
                    if(data.getLastName().length() >= memberFilter.getText().length()){
                        String dataToCheck = data.getLastName().substring(0, memberFilter.getText().length());

                        if(dataToCheck.equalsIgnoreCase(memberFilter.getText()))
                            toSet.add(data);
                    }
                }
                memberList.setListData(toSet.toArray());
            }
        });

        generateList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Student> inducteeList = new ArrayList<Student>();
                for(Student student: MainFile.DM.allMembers){
                    if(student.isInducted())
                        inducteeList.add(student);
                }

                JOptionPane.showMessageDialog(null, "You will be asked to choose a folder for saving the list!");
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.showOpenDialog(ManageMemberPanel.this);

                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(chooser.getSelectedFile().getPath() + "/Inductee List.csv")));

                    bw.write("First Name, Last Name, Student ID, Grade, Meetings, Spirit Nights, Socials, Attended AFLC, Fundraisers, Other Events, Points from Other Events, Total Points");
                    bw.newLine();
                    bw.flush();
                    for(Student inductee: inducteeList){
                        bw.write(inductee.getSaveString());
                        bw.newLine();
                        bw.flush();
                    }
                    bw.close();
                    JOptionPane.showMessageDialog(ManageMemberPanel.this, "The file is ready!");

                }catch (IOException e1){
                    JOptionPane.showMessageDialog(ManageMemberPanel.this, "Error in creating list!\n" +
                            "Please try again.");
                    e1.printStackTrace();
                }

            }
        });

        markFallRemoval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
                    @Override
                    public Component getTableCellRendererComponent(JTable table,
                                                                   Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                        String count = (String)table.getModel().getValueAt(row, 2);
                        if (Integer.parseInt(count) < 1) {
                            setBackground(Colors.crimson3);
                            setForeground(Color.white);
                        } else {
                            setBackground(Colors.LIGHT_GREEN);
                            setForeground(Color.black);
                        }
                        return this;
                    }
                });
                table1.repaint();
            }
        });

        removeFallRemovals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(ManageMemberPanel.this, "This action cannot be undone!\n" +
                        "For your reference, the removed data will be saved to the Desktop");

                if(response == JOptionPane.YES_OPTION) {
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(System.getProperty("user.home") + "/Desktop/Removed Members in Fall.csv")));
                        bw.write("First Name, Last Name, Student ID, Grade, Meetings, Spirit Nights, Socials, Attended AFLC, Fundraisers, Other Events, Points Earned From Other Events, Total Points");
                        bw.newLine();
                        bw.flush();


                        for(int i=0; i< MainFile.DM.allMembers.size(); i++){
                            Student member = MainFile.DM.allMembers.get(i);
                            if(member.tutorialCount() < 2) {
                                MainFile.DM.allMembers.remove(i);
                                i--;
                                bw.write(member.getSaveString());
                                bw.newLine();
                                bw.flush();
                            }
                        }

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                table1.setModel(new DataTableModel());
                memberList.setListData(MainFile.DM.allMembers.toArray());
            }
        });

        resetMarkings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
                    @Override
                    public Component getTableCellRendererComponent(JTable table,
                                                                   Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                        String status = (String)table.getModel().getValueAt(row, table1.getColumnCount()-1);
                        if ("Yes".equalsIgnoreCase(status)) {
                            setBackground(Colors.DARK_GREEN);
                            setForeground(Color.WHITE);
                        } else {
                            setBackground(Colors.crimsonLight1);
                            setForeground(Colors.purple1);
                        }
                        return this;
                    }

                });
                table1.repaint();
            }
        });

        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.setModel(new DataTableModel());
        table1.setCellSelectionEnabled(true);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.getColumnModel().getColumn(0).setPreferredWidth(200);
        table1.getColumnModel().getColumn(1).setPreferredWidth(150);
        table1.getColumnModel().getColumn(2).setPreferredWidth(150);
        table1.getColumnModel().getColumn(3).setPreferredWidth(150);
        table1.getColumnModel().getColumn(4).setPreferredWidth(150);
        table1.getColumnModel().getColumn(5).setPreferredWidth(150);
        table1.getColumnModel().getColumn(6).setPreferredWidth(150);
        table1.getColumnModel().getColumn(7).setPreferredWidth(150);
        table1.getColumnModel().getColumn(8).setPreferredWidth(150);


        table1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                String status = (String)table.getModel().getValueAt(row, table1.getColumnCount()-1);
                if ("Yes".equalsIgnoreCase(status)) {
                    setBackground(Colors.DARK_GREEN);
                    setForeground(Color.WHITE);
                } else {
                    setBackground(Colors.crimsonLight1);
                    setForeground(Colors.purple1);
                }
                return this;
            }
        });

        table1.doLayout();

        splitPane1.setSize(getWidth(),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100));

        HeaderPanelForSubpanel panel1 = new HeaderPanelForSubpanel(getWidth());
        panel1.setBounds(0, 0, getWidth(), 25);
        add(panel1);
        panel1.repaint();
    }

    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Sudarshan Gopalakrishnan
        splitPane1 = new JSplitPane();
        leftSplitPanel = new JSplitPane();
        memberFilter = new JTextField();
        scrollPane1 = new JScrollPane();
        memberList = new JList();
        rightSplitPanel = new JSplitPane();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        panel1 = new JPanel();
        vSpacer1 = new JPanel(null);
        saveCloseButton = new JButton();
        label1 = new JLabel();
        addMember = new JButton();
        removeMember = new JButton();
        loadMembers = new JButton();
        generateList = new JButton();
        vSpacer2 = new JPanel(null);
        vSpacer3 = new JPanel(null);
        markFallRemoval = new JButton();
        resetMarkings = new JButton();
        removeFallRemovals = new JButton();

        //======== this ========
        setBackground(new Color(250, 0, 84));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //======== splitPane1 ========
        {
            splitPane1.setDividerSize(0);
            splitPane1.setDividerLocation(200);
            splitPane1.setBackground(new Color(250, 0, 84));

            //======== leftSplitPanel ========
            {
                leftSplitPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
                leftSplitPanel.setDividerSize(0);
                leftSplitPanel.setBackground(new Color(250, 0, 84));

                //---- memberFilter ----
                memberFilter.setFont(new Font("Cambria", Font.PLAIN, 12));
                memberFilter.setBackground(new Color(232, 232, 232));
                leftSplitPanel.setTopComponent(memberFilter);

                //======== scrollPane1 ========
                {
                    scrollPane1.setBackground(new Color(250, 0, 84));

                    //---- memberList ----
                    memberList.setFont(new Font("Calibri", Font.PLAIN, 12));
                    scrollPane1.setViewportView(memberList);
                }
                leftSplitPanel.setBottomComponent(scrollPane1);
            }
            splitPane1.setLeftComponent(leftSplitPanel);

            //======== rightSplitPanel ========
            {
                rightSplitPanel.setDividerSize(5);
                rightSplitPanel.setDividerLocation(800);
                rightSplitPanel.setBackground(new Color(250, 0, 84));

                //======== scrollPane2 ========
                {
                    scrollPane2.setBackground(new Color(250, 0, 84));

                    //---- table1 ----
                    table1.setFont(new Font("Calibri", Font.PLAIN, 12));
                    scrollPane2.setViewportView(table1);
                }
                rightSplitPanel.setLeftComponent(scrollPane2);

                //======== panel1 ========
                {
                    panel1.setBackground(new Color(250, 0, 84));
                    panel1.setLayout(new VerticalLayout(10));
                    panel1.add(vSpacer1);

                    //---- saveCloseButton ----
                    saveCloseButton.setText("Save and Close");
                    saveCloseButton.setFont(new Font("Cambria", Font.BOLD, 18));
                    saveCloseButton.setBackground(new Color(96, 26, 93));
                    saveCloseButton.setForeground(Color.white);
                    panel1.add(saveCloseButton);

                    //---- label1 ----
                    label1.setText("Tools");
                    label1.setHorizontalAlignment(SwingConstants.CENTER);
                    label1.setFont(new Font("Cambria Math", Font.PLAIN, 20));
                    label1.setForeground(Color.white);
                    panel1.add(label1);

                    //---- addMember ----
                    addMember.setText("Add Member");
                    addMember.setFont(new Font("Cambria", Font.PLAIN, 15));
                    addMember.setBackground(new Color(96, 26, 93));
                    addMember.setForeground(Color.white);
                    panel1.add(addMember);

                    //---- removeMember ----
                    removeMember.setText("Remove Member");
                    removeMember.setFont(new Font("Cambria", Font.PLAIN, 15));
                    removeMember.setBackground(new Color(96, 26, 93));
                    removeMember.setForeground(Color.white);
                    panel1.add(removeMember);

                    //---- loadMembers ----
                    loadMembers.setText("Load Members from List");
                    loadMembers.setFont(new Font("Cambria", Font.PLAIN, 15));
                    loadMembers.setBackground(new Color(96, 26, 93));
                    loadMembers.setForeground(Color.white);
                    panel1.add(loadMembers);

                    //---- generateList ----
                    generateList.setText("Generate List Of Eligible Area Competitors");
                    generateList.setFont(new Font("Cambria", Font.PLAIN, 15));
                    generateList.setBackground(new Color(96, 26, 93));
                    generateList.setForeground(Color.white);
                    panel1.add(generateList);

                    //---- vSpacer2 ----
                    vSpacer2.setBackground(new Color(96, 26, 93));
                    vSpacer2.setForeground(Color.white);
                    panel1.add(vSpacer2);

                    //---- vSpacer3 ----
                    vSpacer3.setBackground(new Color(96, 26, 93));
                    vSpacer3.setForeground(Color.white);
                    panel1.add(vSpacer3);

                    //---- markFallRemoval ----
                    markFallRemoval.setText("Mark Fall Semester Removals");
                    markFallRemoval.setFont(new Font("Cambria", Font.PLAIN, 15));
                    markFallRemoval.setBackground(new Color(96, 26, 93));
                    markFallRemoval.setForeground(Color.white);
//                    panel1.add(markFallRemoval);

                    //---- resetMarkings ----
                    resetMarkings.setText("Reset Markings to Default");
                    resetMarkings.setFont(new Font("Cambria", Font.PLAIN, 15));
                    resetMarkings.setBackground(new Color(96, 26, 93));
                    resetMarkings.setForeground(Color.white);
//                    panel1.add(resetMarkings);

                    //---- removeFallRemovals ----
                    removeFallRemovals.setText("Remove Fall Semester Removals");
                    removeFallRemovals.setFont(new Font("Cambria", Font.PLAIN, 15));
                    removeFallRemovals.setBackground(new Color(96, 26, 93));
                    removeFallRemovals.setForeground(Color.white);
//                    panel1.add(removeFallRemovals);
                }
                rightSplitPanel.setRightComponent(panel1);
            }
            splitPane1.setRightComponent(rightSplitPanel);
        }
        add(splitPane1);
        splitPane1.setBounds(0, 25, 1000, 475);

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
    private JSplitPane splitPane1;
    private JSplitPane leftSplitPanel;
    private JTextField memberFilter;
    private JScrollPane scrollPane1;
    private JList memberList;
    private JSplitPane rightSplitPanel;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JPanel panel1;
    private JPanel vSpacer1;
    private JButton saveCloseButton;
    private JLabel label1;
    private JButton addMember;
    private JButton removeMember;
    private JButton loadMembers;
    private JButton generateList;
    private JPanel vSpacer2;
    private JPanel vSpacer3;
    private JButton markFallRemoval;
    private JButton resetMarkings;
    private JButton removeFallRemovals;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}


class DataTableModel extends DefaultTableModel{

    public DataTableModel(){
        super();
        String[][] data = new String[9][MainFile.DM.allMembers.size()];
        for(int i = 0; i < MainFile.DM.allMembers.size(); i++){
            Student s = MainFile.DM.allMembers.get(i);

            data[0][i] = s.toString();
            data[1][i] = s.meetingCount()+"";
            data[2][i] = s.tutorialCount()+"";
            data[3][i] = s.amcTaken()+"";
            data[4][i] = s.socialCount()+"";
            data[5][i] = s.mathContestCount()+"";
            data[6][i] = s.otherEventCount()+"";
            data[7][i] = s.getTotalPoints()+"";
            if(s.isInducted()) {
                data[8][i] = "Yes";
            }
            else{
//                System.out.println(s.getFirstName()+"\t"+s.getTotalPoints());
                data[8][i] = "No";
            }


        }

        addColumn("Name", data[0]);
        addColumn("Meetings Attended", data[1]);
        addColumn("Spirit Nights Attended", data[2]);
        addColumn("AFLC Attended", data[3]);
        addColumn("Socials Attended", data[4]);
        addColumn("Fundraisers Completed", data[5]);
        addColumn("Other Events Attended", data[6]);
        addColumn("Total Points", data[7]);
        addColumn("Eligible for Area Competition", data[8]);

    }

}