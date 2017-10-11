package process;

import datatypes.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sudarshan on 4/18/2017.
 */
public class BackupProcess implements Runnable {
    @Override
    public void run() {
        final File backupFolder = new File("backups"+File.separatorChar+
                new SimpleDateFormat("MMM-dd-yyyy").format(new Date()));
        backupFolder.mkdirs();

        backup(backupFolder);

        Timer timer = new Timer(600000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                backup(backupFolder);
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    private void backup(File backupFolder){
        try {
            DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
            String fileName = "MAO Data Backup at Time "+ dateFormat.format(new Date())+".csv";
            File destination = new File(backupFolder.getPath()+File.separatorChar+fileName);
            boolean backupCreated = destination.createNewFile();

            if(backupCreated) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(destination));
                bw.write("First Name, Last Name, Student ID, Grade, Meetings, Tutorials, Socials, AMC Taken, Other Math Contests, Other Events, Points from Other Events, Total Points");
                bw.newLine();
                bw.flush();
                for (Student student : MainFile.DM.allMembers) {
                    bw.write(student.getSaveString());
                    bw.newLine();
                    bw.flush();
                }
                bw.close();
            }
        }catch (IOException e){e.printStackTrace();}
    }
}
