import java.time.LocalDate;
import java.util.LinkedList;
import java.util.*;

public class StudentDatabase {
    int studentCount;
    int makeStudentID;
    boolean isApprover;
    int requestCount;

    LinkedList<Student> studentList = new LinkedList<>();
    Stack<Request> pendingRequest = new Stack<>();
    public StudentDatabase () {
        studentCount = 0;
        makeStudentID = 2300;
        requestCount = 0;
    }

    public boolean getApprover() {
        return isApprover;
    }

    public void setApprover(boolean approver) {
        this.isApprover = approver;
    }

    public void enrollStudent (Scanner sc) {
        int choice;
        Student newStudent = new Student (makeStudentID);
        sc.nextLine();
        newStudent.setLastName(sc);
        newStudent.setFirstName(sc);
        newStudent.setMiddleName(sc);
        newStudent.setDateOfBirth(sc);
        newStudent.setContactNumber(sc);
        newStudent.setEmailAddress(sc);
        newStudent.setAddress(sc);
        newStudent.setSex(sc);
        newStudent.setCourse(sc);

        //finalize decision
        System.out.println("\nPlease confirm information:");
        displayIndividual(newStudent);

        choice = Util.getUserChoice("""
                Confirm details?:
                [1] Yes
                [2] No
                """,2,sc);

        if (choice == 2) {
            System.out.println("\nEnter details again");
            enrollStudent(sc);
        }

        System.out.println("\nStudent enrolled successfully.");
        studentList.add(newStudent);
        makeStudentID++;

        /* TODO: IMPLEMENT IF NEED APPROVAL ANG ENROLLMENT
        if (getApprover()) {
            System.out.println("Student enrolled successfully.");
            studentList.add(newStudent);
            makeStudentID++;
        } else {
            //TODO: Add to pending request
            System.out.println("Used enroller account. Added to pending request");
        }
        */

    }

    public void batchEnroll (Scanner sc) {
        int num = Util.getUserChoiceWithPrompt("Enter number of students to enroll: ",10,sc);

        for (int i = 0; i < num; i++) {
            enrollStudent(sc);
        }
    }

    public void viewAllStudents () {
        if (studentList.isEmpty()) {
            System.out.println("\nList Empty.");
            return;
        }
        System.out.println("\nID NUMBER LAST NAME      FIRST NAME     MIDDLE NAME     AGE  SEX  COURSE    YEAR LEVEL CONTACT INFORMATION   EMAIL ADDRESS            ADDRESS");
        for (Student i : studentList) {
            if (i.getIsActive()) {
                displayIndividualTable(i);
            }
        }
    }

    public void viewAllInactiveStudents () {
        if (studentList.isEmpty()) {
            System.out.println("\nList Empty.");
            return;
        }
        System.out.println("\nID NUMBER LAST NAME      FIRST NAME     MIDDLE NAME     AGE  SEX  COURSE    YEAR LEVEL CONTACT INFORMATION   EMAIL ADDRESS            ADDRESS");
        for (Student i : studentList) {
            if (!i.getIsActive()) {
                displayIndividualTable(i);
            }
        }
    }

    public void displayIndividual(Student student) {
        System.out.println(
                "Name:             " + student.getLastName() + ", " + student.getFirstName() + " " + student.getMiddleName() + "\n" +
                "Age:              " + Student.getAge(student.getDateOfBirth())+ "\n" +
                "Gender:           " + student.getSex() + "\n" +
                "Course:           " + student.getCourse() + " " + student.getYearLevel() + "\n" +
                "Contact Number:   " + student.getContactNumber() + "\n" +
                "Address:          " + student.getAddress() + "\n" +
                "Email Address:    " + student.getEmailAddress()
        );
    }

    public void displayIndividualTable(Student student) {

        System.out.printf("%-10d" +
                        "%-15s" +
                        "%-15s" +
                        "%-16s" +
                        "%-5d" +
                        "%-5s" +
                        "%-10s" +
                        "%-11d" +
                        "%-22s" +
                        "%-25s" +
                        "%s\n",
                student.getStudentID(),
                student.getLastName(),
                student.getFirstName(),
                student.getMiddleName(),
                Student.getAge(student.getDateOfBirth()),
                student.getSex(),
                student.getCourse(),
                student.getYearLevel(),
                student.getContactNumber(),
                student.getEmailAddress(),
                student.getAddress());
    }

    public void searchByCourse(Scanner sc) {
        String course = Util.inputCourse(sc);
        boolean flag = true;
        for (Student i : studentList) {
            if (Objects.equals(i.getCourse(), course)) {
                if (flag) {
                    System.out.println("\nID NUMBER LAST NAME      FIRST NAME     MIDDLE NAME     AGE  SEX  COURSE    YEAR LEVEL CONTACT INFORMATION   EMAIL ADDRESS            ADDRESS");
                    flag = false;
                }
                displayIndividualTable(i);
            }
        }
        if (flag) {
            System.out.println("\nThere are no students in this course.");
        }
    }

    public void searchViaID (Scanner sc) {
        int studentIdInput = Util.getUserChoiceWithPrompt("Enter ID number: ",9999,sc);
        sc.nextLine();
        Student student = null;
        boolean found = false;

        for (Student i : studentList) {
            if (i.getStudentID() == studentIdInput) {
                found = true;
                student = i;
                break;
            }
        }

        if (found) {
            System.out.println("\nStudent found!");
            displayIndividual(student);
            modifyOptions(sc,student);
        } else {
            System.out.println("\nStudent not found.");
        }

    }

    public void modifyOptions(Scanner sc, Student student) {
        int choiceB;
        boolean flag;
        String pending = "";
        int choice = Util.getUserChoice("""
                Enter modify options:
                [1] Modify details
                [2] Delete student
                [3] Graduate student
                [4] Dismiss student
                [5] Back
                """,5,sc);

        switch (choice) {
            case 1:
                choiceB = Util.getUserChoice("""
                        [1] Modify Course
                        [2] Back
                        """,2,sc);

                if (choiceB == 1) {
                    sc.nextLine();
                    String newCourse = Util.inputCourse(sc);
                    flag = checkApprover("\nCourse changed successfully");
                    if (flag) {
                        student.setCourse(newCourse);
                    } else {
                        pending = "Change course of student " + student.getStudentID() + " from " + student.getCourse() + " to " + newCourse;
                        Request req = new Request(requestCount + 1,student,pending,newCourse,LocalDate.now());
                        requestCount++;
                        pendingRequest.push(req);
                    }
                } else {
                    modifyOptions(sc,student);
                }
                break;
            case 2:
                /*
                    Same cases from 2-4 (I think?)
                 */
            case 3:
            case 4:
                choiceB = Util.getUserChoice("""
                    Confirm action?:
                    [1] Yes
                    [2] No
                    """,2,sc);

                if (choiceB == 1) {
                    if (getApprover()) {
                        System.out.println("\nStudent is now set to inactive");
                        student.setIsActive(false);
                    } else {
                        System.out.println("\nEnroller account detected! Pushed request to approver");
                        pending = switch (choice) {
                            case 2 -> ("Delete student " + student.getStudentID());
                            case 3 -> ("Graduate student " + student.getStudentID());
                            case 4 -> ("Dismiss student " + student.getStudentID());
                            default -> pending;
                        };
                        Request req = new Request(requestCount + 1,student,pending,LocalDate.now());
                        requestCount++;
                        pendingRequest.push(req);
                    }
                }
                break;
            case 5:
                break;
        }
    }

    public void displayRequests(Scanner sc) {
        if (requestCount > 0) {
            Request toProcess = null;
           System.out.println("\nNo. ID       Request                                            Status   Date Pushed Date Processed");
            for (Request i : pendingRequest) {
                System.out.printf("[%d] %s\n",i.get_ID(), i.display());
            }
            int choice = Util.getUserChoiceWithPrompt("Enter request number: ",requestCount,sc);
            for (Request i: pendingRequest) {
                if (choice == i.get_ID()) {
                    toProcess = i;
                    break;
                }
            }

            handleRequest(toProcess,sc);
        } else {
            System.out.println("\nNo requests");
        }
    }

    public boolean checkApprover(String success) {
        if (getApprover()) {
            System.out.println(success);
            return true;
        } else {
            System.out.println("\nEnroller account detected! Pushed request to approver");
            return false;
        }
    }

    public void handleRequest (Request toProcess,Scanner sc) {
        int choice = Util.getUserChoice("""
                            [1] Approve choice
                            [2] Deny choice
                            [3] Open student details
                            [4] Back
                            """,4,sc);

        switch (choice) {
            case 1:
                if (Objects.equals(toProcess.getNewCourse(), " ")) {
                    toProcess.setStatus(false);
                } else {
                    toProcess.changeCourse(toProcess.getNewCourse());
                }
                toProcess.setStatus(1);
                System.out.println("\nRequest approved.");
                toProcess.setProcessedDate(LocalDate.now());
                break;
            case 2:
                toProcess.setStatus(2);
                System.out.println("\nRequest denied.");
                toProcess.setProcessedDate(LocalDate.now());
                break;
            case 3:
                displayIndividual(toProcess.getStudent());
                handleRequest(toProcess,sc);
                break;
            default:
                break;
        }
    }
}
