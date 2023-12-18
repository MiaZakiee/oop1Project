import java.util.*;

public class Student extends Person{
    private final int studentID;
    private String course;
    private final int yearLevel;
    private boolean isActive;
    /*
    TODO: CHANGE isActive to Status
        1 = Active
        2 = Dismissed
        3 = Graduate
        4 = Removed
     */

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
    public void setCourse(String course) {
        this.course = course;
    }

    public void setIsActive (boolean isActive) {
        this.isActive = isActive;
    }
}