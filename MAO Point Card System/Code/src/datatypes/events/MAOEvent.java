package datatypes.events;

import datatypes.Student;
import process.MainFile;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sudarshan on 5/20/2016.
 */
abstract public class MAOEvent implements Serializable{
    private Date eventDate;
    private String eventName;
    private ArrayList<String> attendees;

    public MAOEvent(Date eventDate, String eventName) {
        this.eventDate = eventDate;
        this.eventName = eventName;
        this.attendees = attendees = new ArrayList<>();
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public ArrayList<String> getAttendees() {
        return attendees;
    }

    public ArrayList<Student> getAttendeesAsStudentObjects(){
        ArrayList<Student> toReturn = new ArrayList<>();
        for(String attendee: attendees){
            Student data = MainFile.DM.getMember(attendee);
            if(data != null)
                toReturn.add(data);
        }

        return toReturn;
    }

    public boolean addAttendee(String studentID){
        return attendees.add(studentID);
    }

    public void removeAttendee(String student){
        attendees.remove(student);
    }

    public boolean hasAttendee(String studentID){
        return attendees.contains(studentID);
    }

    public int attendeeSize(){
        return attendees.size();
    }

    @Override
    public String toString(){
        return getEventName()+"     "+ new SimpleDateFormat("MM/dd/yyyy").format(getEventDate());
    }

}
