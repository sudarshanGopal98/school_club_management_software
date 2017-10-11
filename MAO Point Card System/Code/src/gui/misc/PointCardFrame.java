/*
 * Created by JFormDesigner on Tue Jul 19 11:57:18 CDT 2016
 */

package gui.misc;

import datatypes.Student;
import datatypes.events.*;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;
import org.jdesktop.swingx.VerticalLayout;
import process.MainFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Sudarshan Gopalakrishnan
 */
public class PointCardFrame extends JFrame {
    public PointCardFrame(final Student student) {
        initComponents();

        studentName.setText(student.getFirstName()+" "+student.getLastName());

        if(student.amcTaken())
            amcTaken.setText("Yes");
        else
            amcTaken.setText("No");

        int rowAdder = 0;
        int rowAdderHelper = 0;
        Font labelFont = new Font("Cambria", Font.PLAIN, 13);
        
        for(Meeting m: MainFile.DM.meetings){
            if(m.hasAttendee(student.getStudentID())) {
                JLabel template = new JLabel("        "+m.toString());
                template.setFont(labelFont);
                template.setForeground(Color.WHITE);
                meetings.add(template);
                rowAdderHelper++;
            }
        }

        if(rowAdder < rowAdderHelper){
            rowAdder = rowAdderHelper;
        }
        rowAdderHelper = 0;

        for(Tutorial t: MainFile.DM.tutorials){
            if(t.hasAttendee(student.getStudentID())) {
                JLabel template = new JLabel("        "+t.toString());
                template.setFont(labelFont);
                template.setForeground(Color.WHITE);
                tutorials.add(template);
                rowAdderHelper++;

            }
        }

        if(rowAdder < rowAdderHelper){
            rowAdder = rowAdderHelper;
        }
        rowAdderHelper = 0;

        for(Social s: MainFile.DM.socials){
            if(s.hasAttendee(student.getStudentID())) {
                JLabel template = new JLabel("        "+s.toString());
                template.setFont(labelFont);
                template.setForeground(Color.WHITE);
                socials.add(template);
                rowAdderHelper++;

            }
        }

        if(rowAdder < rowAdderHelper){
            rowAdder = rowAdderHelper;
        }
        rowAdderHelper = 0;

        for(MathContest m: MainFile.DM.mathContests){
            if(m.hasAttendee(student.getStudentID())) {
                JLabel template = new JLabel("        "+m.toString());
                template.setFont(labelFont);
                template.setForeground(Color.WHITE);
                contests.add(template);
                rowAdderHelper++;

            }
        }

        if(rowAdder < rowAdderHelper){
            rowAdder = rowAdderHelper;
        }
        rowAdderHelper = 0;

        for(OtherEvent o: MainFile.DM.otherEvents){
            if(o.hasAttendee(student.getStudentID())) {
                JLabel template = new JLabel(o.toString());
                template.setFont(new Font("Cambria", Font.PLAIN, 13));
                template.setForeground(Color.WHITE);
                others.add(template);
                rowAdderHelper++;

            }
        }

        if(rowAdder < rowAdderHelper){
            rowAdder = rowAdderHelper;
        }
        rowAdderHelper = 0;

        JLabel template = new JLabel("      Total Points Earned: "+student.getTotalPoints());
        template.setFont(new Font("Cambria", Font.PLAIN, 20));
        template.setForeground(Color.WHITE);
        totalPoints.add(template);

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D = image.createGraphics();
                PointCardFrame.this.paint(graphics2D);
                try {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setDialogTitle("Choose Location to Save File");
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.setMultiSelectionEnabled(false);
                    chooser.showOpenDialog(PointCardFrame.this);

                    ImageIO.write(image, "png", new File(chooser.getSelectedFile().getAbsoluteFile()+"/"+student.getFirstName()+" "+student.getLastName()+" Point Card.png"));

                    JOptionPane.showMessageDialog(PointCardFrame.this, "The point card is saved at the chosen location!");


                }catch (Exception ex){
                    JOptionPane.showMessageDialog(PointCardFrame.this, "Operation Failed.");
                    ex.printStackTrace();
                }

            }
        });


        setSize(900, 270 + rowAdder*20);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Sudarshan Gopalakrishnan
        vSpacer1 = new JPanel(null);
        panel10 = new JPanel();
        label16 = new JLabel();
        studentName = new JLabel();
        label12 = new JLabel();
        teacherName = new JLabel();
        label13 = new JLabel();
        amcTaken = new JLabel();
        vSpacer4 = new JPanel(null);
        panel1 = new JPanel();
        meetings = new JPanel();
        label2 = new JLabel();
        tutorials = new JPanel();
        label3 = new JLabel();
        socials = new JPanel();
        label4 = new JLabel();
        contests = new JPanel();
        label5 = new JLabel();
        others = new JPanel();
        events = new JLabel();
        totalPoints = new JPanel();
        vSpacer5 = new JPanel(null);
        print = new JButton();

        //======== this ========
        setVisible(true);
        setTitle("Student Point Card | MAO Point Card System by Sudarshan Gopalakrishnan");
        setBackground(new Color(250, 0, 84));
        Container contentPane = getContentPane();
        contentPane.setLayout(new VerticalLayout());

        //---- vSpacer1 ----
        vSpacer1.setBackground(new Color(250, 0, 84));
        contentPane.add(vSpacer1);

        //======== panel10 ========
        {
            panel10.setBackground(new Color(250, 0, 84));

            // JFormDesigner evaluation mark
            panel10.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), panel10.getBorder())); panel10.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            panel10.setLayout(new TableLayout(new double[][] {
                {208, 669},
                {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
            ((TableLayout)panel10.getLayout()).setHGap(5);
            ((TableLayout)panel10.getLayout()).setVGap(5);

            //---- label16 ----
            label16.setText("   Student Name");
            label16.setFont(new Font("Cambria", Font.BOLD, 24));
            label16.setBackground(new Color(250, 0, 84));
            label16.setForeground(Color.white);
            panel10.add(label16, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- studentName ----
            studentName.setText("Student Name");
            studentName.setFont(new Font("Cambria", Font.PLAIN, 20));
            studentName.setBackground(new Color(250, 0, 84));
            studentName.setForeground(Color.white);
            panel10.add(studentName, new TableLayoutConstraints(1, 0, 1, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label12 ----
            label12.setText("   Teacher Name");
            label12.setFont(new Font("Cambria", Font.BOLD, 18));
            label12.setBackground(new Color(250, 0, 84));
            label12.setForeground(Color.white);
            panel10.add(label12, new TableLayoutConstraints(0, 1, 0, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- teacherName ----
            teacherName.setText("Teacher Name");
            teacherName.setFont(new Font("Cambria", Font.PLAIN, 16));
            teacherName.setBackground(new Color(250, 0, 84));
            teacherName.setForeground(Color.white);
            panel10.add(teacherName, new TableLayoutConstraints(1, 1, 1, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label13 ----
            label13.setText("   AMC Taken?");
            label13.setFont(new Font("Cambria", Font.BOLD, 18));
            label13.setBackground(new Color(250, 0, 84));
            label13.setForeground(Color.white);
            panel10.add(label13, new TableLayoutConstraints(0, 2, 0, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- amcTaken ----
            amcTaken.setText("Yes or No");
            amcTaken.setFont(new Font("Cambria", Font.PLAIN, 16));
            amcTaken.setBackground(new Color(250, 0, 84));
            amcTaken.setForeground(Color.white);
            panel10.add(amcTaken, new TableLayoutConstraints(1, 2, 1, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        }
        contentPane.add(panel10);

        //---- vSpacer4 ----
        vSpacer4.setBackground(new Color(96, 26, 93));
        contentPane.add(vSpacer4);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(173, 0, 58));
            panel1.setLayout(new TableLayout(new double[][] {
                {175, 175, 149, 189, 198},
                {TableLayout.FILL}}));

            //======== meetings ========
            {
                meetings.setBackground(new Color(173, 0, 58));
                meetings.setLayout(new VerticalLayout(10));

                //---- label2 ----
                label2.setText("      Meetings");
                label2.setFont(new Font("Cambria", Font.BOLD, 16));
                label2.setBackground(new Color(219, 0, 74));
                label2.setForeground(Color.white);
                meetings.add(label2);
            }
            panel1.add(meetings, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.CENTER));

            //======== tutorials ========
            {
                tutorials.setBackground(new Color(173, 0, 58));
                tutorials.setLayout(new VerticalLayout(10));

                //---- label3 ----
                label3.setText("      Tutorials");
                label3.setFont(new Font("Cambria", Font.BOLD, 16));
                label3.setForeground(Color.white);
                label3.setBackground(new Color(186, 0, 63));
                tutorials.add(label3);
            }
            panel1.add(tutorials, new TableLayoutConstraints(1, 0, 1, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.CENTER));

            //======== socials ========
            {
                socials.setBackground(new Color(173, 0, 58));
                socials.setLayout(new VerticalLayout(10));

                //---- label4 ----
                label4.setText("      Socials");
                label4.setFont(new Font("Cambria", Font.BOLD, 16));
                label4.setForeground(Color.white);
                socials.add(label4);
            }
            panel1.add(socials, new TableLayoutConstraints(2, 0, 2, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.CENTER));

            //======== contests ========
            {
                contests.setBackground(new Color(173, 0, 58));
                contests.setLayout(new VerticalLayout(10));

                //---- label5 ----
                label5.setText("      Math Contests");
                label5.setFont(new Font("Cambria", Font.BOLD, 16));
                label5.setForeground(Color.white);
                contests.add(label5);
            }
            panel1.add(contests, new TableLayoutConstraints(3, 0, 3, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.CENTER));

            //======== others ========
            {
                others.setBackground(new Color(173, 0, 58));
                others.setLayout(new VerticalLayout(10));

                //---- events ----
                events.setText("      Other Events");
                events.setFont(new Font("Cambria", Font.BOLD, 16));
                events.setForeground(Color.white);
                others.add(events);
            }
            panel1.add(others, new TableLayoutConstraints(4, 0, 4, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.CENTER));
        }
        contentPane.add(panel1);

        //======== totalPoints ========
        {
            totalPoints.setBackground(new Color(96, 26, 93));
            totalPoints.setLayout(new VerticalLayout(10));

            //---- vSpacer5 ----
            vSpacer5.setBackground(new Color(96, 26, 93));
            totalPoints.add(vSpacer5);
        }
        contentPane.add(totalPoints);

        //---- print ----
        print.setText("Print Point Card");
        print.setFont(new Font("Cambria Math", Font.PLAIN, 19));
        print.setBackground(new Color(250, 0, 84));
        print.setForeground(Color.white);
        contentPane.add(print);
        setSize(900, 275);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Sudarshan Gopalakrishnan
    private JPanel vSpacer1;
    private JPanel panel10;
    private JLabel label16;
    private JLabel studentName;
    private JLabel label12;
    private JLabel teacherName;
    private JLabel label13;
    private JLabel amcTaken;
    private JPanel vSpacer4;
    private JPanel panel1;
    private JPanel meetings;
    private JLabel label2;
    private JPanel tutorials;
    private JLabel label3;
    private JPanel socials;
    private JLabel label4;
    private JPanel contests;
    private JLabel label5;
    private JPanel others;
    private JLabel events;
    private JPanel totalPoints;
    private JPanel vSpacer5;
    private JButton print;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
