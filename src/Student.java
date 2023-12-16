import java.util.*;

public class Student extends Person{
    private final int studentID;
    private String course;
    private int yearLevel;
    private boolean isActive;

    //Constructor
    public Student(int makeStudentID) {
        this.studentID = makeStudentID;
        this.isActive = true;
        this.yearLevel = 1;
    }

    //Getters
    public int getStudentID() {
        return studentID;
    }
    public String getCourse() {
        return course;
    }
    public int getYearLevel() {
        return yearLevel;
    }
    public boolean getIsActive() {return isActive;}

    // setters
    public void setCourse(Scanner sc) {
        this.course = Util.inputCourse(sc);
    }

    //TODO: IMPLEMENT WHEN DATES ARE ADDED MAYBE??
    public void setYearLevel (int yearLevel) {
        this.yearLevel = yearLevel;
    }
    //TODO: IMPLEMENT WHEN APPROVER IS READY
    public void setIsActive (boolean isActive) {
        this.isActive = isActive;
    }

    // display

    @Override
    public String toString() {
        return getStudentID() + "\t" +
                getLastName() + "\t" +
                getFirstName() + "\t" +
                getMiddleName() + "\t" +
                getCourse() + "\t" +
                getYearLevel() + "\t" +
                getIsActive() + "\t" +
                getSex() + "\t" +
                //getAge() + "\t" +
                getContactNumber() + "\t" +
                getAddress() + "\t" +
                getEmailAddress();
    }
}