public class Request {
    public int requestCount;
    public Student student;
    public int status;
    // 1 = Approved
    // 2 = Denied
    // 0 = Pending
    public final String request;
    public String newCourse;

    //date pushed HMMMM?!?!?!?!?!?!??!
    //approvedDate HMMMMMMMM?!?!?!?!?!??!

    //CONSTRUCTOR
    public Request(int count,Student student,String request) {
        this.requestCount = count;
        this.student = student;
        this.status = 0;
        this.request = request;
        newCourse = " ";
    }
    public Request(int count,Student student,String request,String newCourse) {
        this.requestCount = count;
        this.student = student;
        this.status = 0;
        this.request = request;
        this.newCourse = newCourse;
    }

    public int getStatus() {
        return status;
    }

    public int getCount() {
        return requestCount;
    }
    public void setStatus (int status) {
        this.status = status;
    }

    public String display () {
        String status = switch (getStatus()) {
            case 1 -> "Approved";
            case 2 -> "Denied";
            default -> "Pending";
        };
        return (student.getStudentID() + String.format("     %-50s ",request) + status);
    }
}

