package datatypes;

import datatypes.events.*;
import process.MainFile;

import java.io.Serializable;

/**
 * Created by Sudarshan on 5/20/2016.
 */
public class Student implements Serializable {
    private String firstName;
    private String lastName;
    private String studentID;
    private int grade;
    boolean isThetaMember;


    public Student(String lastName, String firstName, String studentID, int grade, boolean isThetaMember) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentID = studentID;
        this.grade = grade;
        this.isThetaMember = isThetaMember;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSaveString(){
        return firstName+","+lastName+","+studentID+","+grade+","+meetingCount()+","+tutorialCount()+","+socialCount()+","+amcTaken()
                +","+mathContestCount()+","+otherEventCount()+","+pointsFromOtherEvents()+","+getTotalPoints();
    }

    @Override
    public String toString(){
        return lastName+", "+firstName;
    }


    public int meetingCount(){
        int count = 0;
        for(Meeting m: MainFile.DM.meetings) {
            for (String s : m.getAttendees()) {
                if (s.equalsIgnoreCase(studentID))
                    count++;
            }
        }
        return count;
    }

    public int tutorialCount(){
        int count = 0;
        for(Tutorial tutorial: MainFile.DM.tutorials) {
            for (String s : tutorial.getAttendees()) {
                if (s.equalsIgnoreCase(studentID))
                    count++;
            }
        }
        return count;
    }

    public boolean amcTaken(){
        int count = 0;
        for(AMC amc: MainFile.DM.amcs) {
            for (String s : amc.getAttendees()) {
                if (s.equalsIgnoreCase(studentID))
                    count++;
            }
        }
        if(count>0)
            return true;
        return false;
    }

    public int socialCount(){
        int count = 0;
        for(Social social: MainFile.DM.socials) {
            for (String s : social.getAttendees()) {
                if (s.equalsIgnoreCase(studentID))
                    count++;
            }
        }
        return count;
    }

    public int mathContestCount(){
        int count = 0;
        for(MathContest mathContest: MainFile.DM.mathContests) {
            for (String s : mathContest.getAttendees()) {
                if (s.equalsIgnoreCase(studentID))
                    count++;
            }
        }
        return count;
    }


    public int otherEventCount(){
        int count = 0;
        for(OtherEvent otherEvent: MainFile.DM.otherEvents) {
            for (String s : otherEvent.getAttendees()) {
                if (s.equalsIgnoreCase(studentID))
                    count++;
            }
        }
        return count;
    }

    public int pointsFromOtherEvents(){
        int pointsFromOtherEvents = 0;
        for(OtherEvent otherEvent: MainFile.DM.otherEvents) {
            for (String s : otherEvent.getAttendees()) {
                if (s.equalsIgnoreCase(studentID)){
                    pointsFromOtherEvents+= otherEvent.getEventPoints();
                }
            }
        }
        return pointsFromOtherEvents;
    }

    public int getTotalPoints(){
        int totalPoints =
                        meetingCount() * EventPoints.meeting +
                        tutorialCount() * EventPoints.tutorial +
                        socialCount() * EventPoints.social +
                        mathContestCount() * EventPoints.mathContest +
                        pointsFromOtherEvents();

        if(amcTaken())
            totalPoints += EventPoints.amc;



        return totalPoints;
    }

    public boolean isInducted(){
        int totalPoints = getTotalPoints();
        if (totalPoints >= EventPoints.maoInduct) {
            System.out.println("TEST3");
            return true;
        } else return false;

    }

}
