package process;

import datatypes.Student;
import datatypes.events.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sudarshan on 5/20/2016.
 */
public class DataManager {
    public final ArrayList<Student> allMembers = new ArrayList<>();
    public final ArrayList<Meeting> meetings = new ArrayList<>();
    public final ArrayList<Social> socials = new ArrayList<>();
    public final ArrayList<Tutorial> tutorials = new ArrayList<>();
    public final ArrayList<AMC> amcs = new ArrayList<>();
    public final ArrayList<MathContest> mathContests = new ArrayList<>();
    public final ArrayList<OtherEvent> otherEvents = new ArrayList<>();

    public DataManager(){
        loadData();
    }


    public void serializeData(){
        try {
            FileOutputStream fout = new FileOutputStream(new File("data/members.serialized"));
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(allMembers);
            oos.flush();
            oos.close();
            fout.close();
        }catch (IOException e){e.printStackTrace();}

        try {
            FileOutputStream fout = new FileOutputStream(new File("data/meetings.serialized"));
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(meetings);
            oos.flush();
            oos.close();
            fout.close();
        }catch (IOException e){e.printStackTrace();}

        try {
            FileOutputStream fout = new FileOutputStream(new File("data/socials.serialized"));
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(socials);
            oos.flush();
            oos.close();
            fout.close();
        }catch (IOException e){e.printStackTrace();}

        try {
            FileOutputStream fout = new FileOutputStream(new File("data/tutorials.serialized"));
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(tutorials);
            oos.flush();
            oos.close();
            fout.close();
        }catch (IOException e){e.printStackTrace();}

        try {
            FileOutputStream fout = new FileOutputStream(new File("data/amcs.serialized"));
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(amcs);
            oos.flush();
            oos.close();
            fout.close();
        }catch (IOException e){e.printStackTrace();}

        try {
            FileOutputStream fout = new FileOutputStream(new File("data/amcs.serialized"));
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(amcs);
            oos.flush();
            oos.close();
            fout.close();
        }catch (IOException e){e.printStackTrace();}

        try {
            FileOutputStream fout = new FileOutputStream(new File("data/mathContest.serialized"));
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(mathContests);
            oos.flush();
            oos.close();
            fout.close();
        }catch (IOException e){e.printStackTrace();}

        try {
            FileOutputStream fout = new FileOutputStream(new File("data/otherEvents.serialized"));
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(otherEvents);
            oos.flush();
            oos.close();
            fout.close();
        }catch (IOException e){e.printStackTrace();}

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("data/points.serialized")));
            String toWrite = "";
            toWrite += EventPoints.meeting+",";
            toWrite += EventPoints.social+",";
            toWrite += EventPoints.tutorial+",";
            toWrite += EventPoints.amc+",";
            toWrite += EventPoints.mathContest+",";
            toWrite += EventPoints.maoInduct+",";
            toWrite += EventPoints.thetaInduct;

            bw.write(toWrite);
            bw.flush();
            bw.close();

        }catch (IOException e){e.printStackTrace();}
    }

    public Student getMember(String studentID){
        for(Student s: allMembers){
            if(s.getStudentID().equalsIgnoreCase(studentID)){
                return s;
            }
        }
        return null;
    }


    public Meeting getMeeting(String eventName){
        for(Meeting event: meetings){
            if(event.getEventName().equalsIgnoreCase(eventName))
                return event;
        }
        return null;
    }

    public Social getSocial(String eventName){
        for(Social event: socials){
            if(event.getEventName().equalsIgnoreCase(eventName))
                return event;
        }
        return null;
    }

    public Tutorial getTutorial(String eventName){
        for(Tutorial event: tutorials){
            if(event.getEventName().equalsIgnoreCase(eventName))
                return event;
        }
        return null;
    }

    public AMC getAMC(String eventName){
        for(AMC event: amcs){
            if(event.getEventName().equalsIgnoreCase(eventName))
                return event;
        }
        return null;
    }

    public void loadData(){
        File dataFolder = new File("data");
        dataFolder.mkdir();

        loadMembers();
        loadAMC();
        loadMeetings();
        loadSocials();
        loadTutorials();
        loadEventPoints();

        System.out.println(allMembers);
    }

    private void loadMembers(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("data/members.serialized"));
            ArrayList<Student> temp = (ArrayList<Student>)(objectInputStream.readObject());
            for(Student m: temp){
                if(m == null)
                    continue;
                allMembers.add(m);
            }
        }catch (IOException | ClassNotFoundException e){
            try {
                File file = new File("data/members.serialized");
                file.createNewFile();
            }catch (IOException e2){
                e2.printStackTrace();
            }
        }
    }

    private void loadMeetings(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("data/meetings.serialized"));
            ArrayList<Meeting> temp = (ArrayList<Meeting>)(objectInputStream.readObject());
            for(Meeting m: temp){
                for(int i = 0; i < m.getAttendees().size(); i++){
                    if(m.getAttendees().get(i) == null) {
                        m.getAttendees().remove(i);
                        i--;
                    }
                }

                meetings.add(m);
            }
        }catch (IOException | ClassNotFoundException e){
            try {
                File file = new File("data/meetings.serialized");
                file.createNewFile();


            }catch (IOException e2){
                e2.printStackTrace();
            }

//            e.printStackTrace();
        }
    }

    private void loadSocials(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("data/socials.serialized"));
            ArrayList<Social> temp = (ArrayList<Social>)(objectInputStream.readObject());
            for(Social m: temp){
                for(int i = 0; i < m.getAttendees().size(); i++){
                    if(m.getAttendees().get(i) == null) {
                        m.getAttendees().remove(i);
                        i--;
                    }
                }

                socials.add(m);
            }
        }catch (IOException | ClassNotFoundException e){
            try {
                File file = new File("data/socials.serialized");
                file.createNewFile();
            }catch (IOException e2){
                e2.printStackTrace();
            }

//            e.printStackTrace();
        }
    }

    private void loadTutorials(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("data/tutorials.serialized"));
            ArrayList<Tutorial> temp = (ArrayList<Tutorial>)(objectInputStream.readObject());
            for(Tutorial m: temp){
                for(int i = 0; i < m.getAttendees().size(); i++){
                    if(m.getAttendees().get(i) == null) {
                        m.getAttendees().remove(i);
                        i--;
                    }
                }

                tutorials.add(m);
            }
        }catch (IOException | ClassNotFoundException e){
            try {
                File file = new File("data/tutorials.serialized");
                file.createNewFile();
            }catch (IOException e2){
                e2.printStackTrace();
            }

//            e.printStackTrace();
        }
    }

    private void loadAMC(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("data/amcs.serialized"));
            ArrayList<AMC> temp = (ArrayList<AMC>)(objectInputStream.readObject());
            for(AMC m: temp){
                for(int i = 0; i < m.getAttendees().size(); i++){
                    if(m.getAttendees().get(i) == null) {
                        m.getAttendees().remove(i);
                        i--;
                    }
                }

                amcs.add(m);
            }
        }catch (IOException | ClassNotFoundException e){
            try {
                File file = new File("data/amcs.serialized");
                file.createNewFile();
            }catch (IOException e2){
                e2.printStackTrace();
            }

//            e.printStackTrace();
        }
    }

    public void loadEventPoints(){
        try {
            Scanner scan = new Scanner(new File("data/points.serialized"));
            String[] data = scan.nextLine().split(",");

            EventPoints.meeting = Integer.parseInt(data[0]);
            EventPoints.social = Integer.parseInt(data[1]);
            EventPoints.tutorial = Integer.parseInt(data[2]);
            EventPoints.amc = Integer.parseInt(data[3]);
            EventPoints.mathContest = Integer.parseInt(data[4]);
            EventPoints.maoInduct = Integer.parseInt(data[5]);
            EventPoints.thetaInduct = Integer.parseInt(data[6]);



        }catch (IOException e){

        }
    }

    public Student removeMember(String studentID){
        for(int i =0 ; i < allMembers.size(); i++){
            Student student = allMembers.get(i);
            if(student.getStudentID().equalsIgnoreCase(studentID))
                return allMembers.remove(i);
        }

        return null;
    }

}
