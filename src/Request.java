import java.time.LocalDate;

public class Request {
    private final int ID;
    private final Student student;
    private int status;
    // 1 = Approved
    // 2 = Denied
    // 0 = Pending
    private final String request;
    private final String newCourse;
    private final LocalDate datePushed;
    private LocalDate processedDate;
    private final int newStatus;

    //CONSTRUCTOR
    public Request(int ID,Student student,String request,LocalDate datePushed,int newStatus) {
        this.ID = ID;
        this.student = student;
        this.status = 0;
        this.request = request;
        newCourse = " ";
        this.datePushed = datePushed;
        this.processedDate = null;
        this.newStatus = newStatus;
    }
    public Request(int count, Student student, String request, String newCourse,LocalDate datePushed) {
        this.ID = count;
        this.student = student;
        this.status = 0;
        this.request = request;
        this.newCourse = newCourse;
        this.datePushed = datePushed;
        processedDate = null;
        this.newStatus = 0;
    }

    public int getStatus() {
        return status;
    }

    public int get_ID() {
        return ID;
    }

    public Student getStudent() {
        return student;
    }

    public String getNewCourse () {
        return newCourse;
    }

    public String getRequest() {
        return request;
    }

    public LocalDate getDatePushed() {
        return datePushed;
    }

    public int getNewStatus() {return newStatus;}

    public LocalDate getProcessedDate() {
        return processedDate;
    }

    public void setStatus (int status) {
        this.status = status;
    }

    public void setStudentStatus(int status) {
        this.student.setIsActive(status);
    }

    public void changeCourse (String course) {
        this.student.setCourse(course);
    }

    public void setProcessedDate(LocalDate processedDate) {
        this.processedDate = processedDate;
    }

    public String display () {
        String status = switch (getStatus()) {
            case 1 -> "Approved";
            case 2 -> "Denied";
            default -> "Pending";
        };
        return String.format("%-9d%-51s%-9s%-12s%s",student.getStudentID(),getRequest(),status,getDatePushed(),(getProcessedDate() == null) ? "Processing" : getProcessedDate());
    }

}

